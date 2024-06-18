package com.cobuy.BE.board.model.service;


import com.cobuy.BE.board.model.BoardDto;
import com.cobuy.BE.board.model.BoardListDto;
import com.cobuy.BE.board.model.FileInfoDto;
import com.cobuy.BE.board.model.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardMapper boardMapper;
    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) {
        super();
        this.boardMapper = boardMapper;
    }

    @Override
    @Transactional
    public void writeArticle(BoardDto boardDto) throws Exception {
        boardMapper.writeArticle(boardDto);

        List<FileInfoDto> fileInfos = boardDto.getFileInfos();
        if (fileInfos != null && !fileInfos.isEmpty()) {
            boardMapper.registerFile(boardDto);
        }
        /* fileInfos가 null이면 isEmpty()를 호출할 때
        NullPointerException이 발생할 수 있다
        이를 방지하기 위해 null 체크를 먼저 하는 것 */
    }

    @Override
    public BoardListDto listArticle(Map<String, String> map) throws Exception {
        /* param 객체에는 pgno, spp, key, word가 포함되며,
        이 중 key는 사용자가 선택한 검색 조건 */

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("word", map.get("word") == null ? "" : map.get("word"));

        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
        int start = currentPage * sizePerPage - sizePerPage;
        param.put("start", start);
        param.put("listsize", sizePerPage);
        /* totalArticle 50, currentPage 3, sizePerPage 10,
        start = 3 * 10 - 10 = 20 */

        String key = map.get("key");
        param.put("key", key == null ? "" : key);
        if ("user_id".equals(key))param.put("key", key == null ? "" : "b.user_id");
        List<BoardDto> list = boardMapper.listArticle(param);
        if ("user_id".equals(key))param.put("key", key == null ? "" : "user_id");

        int totalArticleCount = boardMapper.getTotalArticleCount(param);
        int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;
        /* totalArticleCount: 전체 게시글 수.
        sizePerPage: 한 페이지당 표시할 게시글 수.
        (21 - 1) /(10 + 1) = 2 + 1 = 3. 이 경우 실제로 3페이지가 필요합니다.
        */

        BoardListDto boardListDto = new BoardListDto();
        boardListDto.setArticles(list);
        boardListDto.setCurrentPage(currentPage);
        boardListDto.setTotalPageCount(totalPageCount);

        return boardListDto;
    }

/*	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if ("userid".equals(key))
			key = "user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int totalCount = boardMapper.getTotalArticleCount(param);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	} */

    @Override
    public BoardDto getArticle(int articleNo) throws Exception {
        return boardMapper.getArticle(articleNo);
    }

    @Override
    public void updateHit(int articleNo) throws Exception {
        boardMapper.updateHit(articleNo);
    }

    @Override
    public void modifyArticle(BoardDto boardDto) throws Exception {
        boardMapper.modifyArticle(boardDto);
    }

    @Override
    public void deleteArticle(int articleNo) throws Exception {
        boardMapper.deleteFile(articleNo);
        boardMapper.deleteArticle(articleNo);
    }

    /* @Override
	@Transactional
	public void deleteArticle(int articleNo, String path) throws Exception {
		// TODO : BoardDaoImpl의 deleteArticle 호출
		List<FileInfoDto> fileList = boardMapper.fileInfoList(articleNo);
		boardMapper.deleteFile(articleNo);
		boardMapper.deleteArticle(articleNo);
		for(FileInfoDto fileInfoDto : fileList) {
			File file = new File(path + File.separator + fileInfoDto.getSaveFolder() + File.separator + fileInfoDto.getSaveFile());
			file.delete();
		}
	} */
}
