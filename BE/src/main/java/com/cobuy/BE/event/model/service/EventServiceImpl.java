package com.cobuy.BE.event.model.service;

import com.cobuy.BE.common.model.FileInfoDto;
import com.cobuy.BE.event.model.EventDto;
import com.cobuy.BE.event.model.EventListDto;
import com.cobuy.BE.event.model.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventServiceImpl implements EventService{

    private EventMapper eventMapper;
    @Autowired
    public EventServiceImpl(EventMapper eventMapper){
        super();
        this.eventMapper = eventMapper;
    }

    @Override
    @Transactional
    public void writeArticle(EventDto eventDto) throws Exception {
        eventMapper.writeArticle(eventDto);

        List<FileInfoDto> fileInfos = eventDto.getFileInfos();
        if (fileInfos != null && !fileInfos.isEmpty()) {
            eventMapper.registerFile(eventDto);
        }
    }

    /* TODO: */
    @Override
    public EventListDto listArticle(Map<String, String> map) throws Exception {
        Map<String, Object> param = new HashMap<>();

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
        List<EventDto> list = eventMapper.listArticle(param);
        if ("user_id".equals(key))param.put("key", key == null ? "" : "user_id");

        int totalArticleCount = eventMapper.getTotalArticleCount(param);
        int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;
        /* totalArticleCount: 전체 게시글 수.
        sizePerPage: 한 페이지당 표시할 게시글 수.
        (21 - 1) /(10 + 1) = 2 + 1 = 3. 이 경우 실제로 3페이지가 필요합니다.
        */

        EventListDto eventListDto = new EventListDto();
        eventListDto.setArticles(list);
        eventListDto.setCurrentPage(currentPage);
        eventListDto.setTotalPageCount(totalPageCount);

        return eventListDto;
    }

    @Override
    public EventDto getArticle(int articleNo) throws Exception {
        return eventMapper.getArticle(articleNo);
    }

    @Override
    public void updateHit(int articleNo) throws Exception {
        eventMapper.updateHit(articleNo);
    }

    @Override
    public void modifyArticle(EventDto eventDto) throws Exception {
        eventMapper.modifyArticle(eventDto);
    }

    @Override
    public void deleteArticle(int articleNo) throws Exception {
        eventMapper.deleteArticle(articleNo);
        eventMapper.deleteArticle(articleNo);
    }
}
