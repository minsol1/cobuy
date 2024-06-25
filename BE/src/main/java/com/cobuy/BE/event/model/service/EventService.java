package com.cobuy.BE.event.model.service;


import com.cobuy.BE.event.model.EventDto;
import com.cobuy.BE.event.model.EventListDto;

import java.util.Map;

public interface EventService {
    void writeArticle(EventDto eventDto) throws Exception;

    EventListDto listArticle(Map<String, String> map) throws Exception;

//	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;

    EventDto getArticle(int articleNo) throws Exception;

    void updateHit(int articleNo) throws Exception;

    void modifyArticle(EventDto eventDto) throws Exception;

    void deleteArticle(int articleNo) throws Exception;
}
