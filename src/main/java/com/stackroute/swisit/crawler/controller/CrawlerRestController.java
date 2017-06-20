package com.stackroute.swisit.crawler.controller;


import java.io.File;
import java.io.IOException;
import java.util.*;

import org.slf4j.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.swisit.crawler.domain.CrawlerBean;
import com.stackroute.swisit.crawler.domain.SwisitBean;
import com.stackroute.swisit.crawler.kafka.KafkaConfig;
import com.stackroute.swisit.crawler.service.MasterScannerService;

@RestController
@RequestMapping(value="/crawl")
public class CrawlerRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private MasterScannerService masterScannerService;
	
	@Autowired
	public void setMasterScannerService(MasterScannerService masterScannerService){
		this.masterScannerService=masterScannerService;
	}	
	
	
	String data;
	
	private KafkaConfig kafkaconfig;
	
	@Autowired
	public void setKafkaConfig(KafkaConfig kafkaconfig){
		this.kafkaconfig=kafkaconfig;
	}
	
	
	@RequestMapping("/receiver")
	public String receiveMessage() throws JsonParseException, JsonMappingException, IOException{
		//testcontrol is my topic name
		List<SwisitBean> c=kafkaconfig.receivingMessage("testcontrol");
	SwisitBean crawlerbean[]= new SwisitBean[c.size()];
		c.toArray(crawlerbean);
		
		for(SwisitBean sb:c){
		System.out.println(sb.getLink());
		System.out.println(sb.getQuery());
		System.out.println(sb.getSnippet());
		System.out.println(sb.getTitle());
		}
		
		
		ObjectMapper mapper = new ObjectMapper();
		File file = new File("./src/main/resources/common/sample.json");
	            //List<LinkedHashMap<String,String>> list= (List<LinkedHashMap<String,String>>) mapper.readValue(file, ArrayList.class);
	            //List<CrawlerBean> cb=(List<CrawlerBean>) mapper.readValue(new File("./src/main/resources/common/sample.json"),ArrayList.class);
//	    SwisitBean[] crawlerbean=mapper.readValue(file, SwisitBean[].class);
	    System.out.println(crawlerbean[0].getLink());
	    System.out.println(crawlerbean.length);
	//    masterScannerService.scanDocument(crawlerbean);
		return null;
	}
	
	@RequestMapping(value="" , method=RequestMethod.POST)
	public ResponseEntity<Map<String,String>> getJsonInput(@RequestBody SwisitBean crawlerBean){
		//masterScannerService.scanDocument(crawlerBean);
		Map<String,String> map= new HashMap<String,String>();
        map.put("message","Data sent successfully");
        return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);

	}
	
	
}
