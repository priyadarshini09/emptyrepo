package com.stackroute.swisit.crawler.kafka;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
//import com.stackroute.swisit.crawler.domain.CrawlerBean;
import com.stackroute.swisit.crawler.domain.SwisitBean;

public interface MessageService {

	public void publishingMessage(String topicName,Object message) throws JsonProcessingException;
	
	public List<SwisitBean> receivingMessage(String string);
}
