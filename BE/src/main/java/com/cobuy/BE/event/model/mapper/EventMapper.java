package com.cobuy.BE.event.model.mapper;

import com.cobuy.BE.event.model.EventDto;
import org.apache.ibatis.annotations.Mapper;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface EventMapper {

    void writeArticle(EventDto eventDto) throws SQLException;

    void registerFile(EventDto eventDto) throws Exception;

    List<EventDto> listArticle(Map<String, Object> param) throws SQLException;

    int getTotalArticleCount(Map<String, Object> param) throws SQLException;

    EventDto getArticle(int articleNo) throws SQLException;

    void updateHit(int articleNo) throws SQLException;

    void modifyArticle(EventDto eventDto) throws SQLException;

    void deleteFile(int articleNo) throws Exception;

    void deleteArticle(int articleNo) throws SQLException;
}
