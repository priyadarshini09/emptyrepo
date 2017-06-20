package com.stackroute.swisit.crawler.service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonParseException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.JsonMappingException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class KeywordScannerServiceImpl implements KeywordScannerService{
	
	float count=0;
	String term="angular.io";
	
	private DOMCreatorService domCreatorService;
	
	@Autowired
	public void setDOMCreatorService(DOMCreatorService domCreatorService) {
		this.domCreatorService=domCreatorService;

	}

	@Override
	public float scanDocument(Document document, String term) {
		
		float  intensity;
		intensity= calculateIntensity(document, term);
		System.out.println("inside scan document my intensity is "+intensity);
		if(intensity == 0){
			String[] resultArrayFromWorkNet = getFromWordNet(term);
			for(String res : resultArrayFromWorkNet){
				intensity = calculateIntensity(document, res);
			}
		}
		return intensity;
	}

	@Override
	public String[] getFromWordNet(String term) {
		//Call WorkNet Service
		return null;
	}


	public float calculateIntensity(Document document, String term) {
		
		try{
			//System.out.println("inside calculate intensity "+document);
			//	myfunction();
			System.out.println(term);
			Elements tag = document.select("a");
			//System.out.println("This is my tag...."+tag);
			for(Element ele : tag){
				if(ele.text().matches("GitHub")){
					System.out.println("inside match");
					count=20;
				}
			}
			
		}catch(Exception e){
			System.out.println("ghost "+e);
		}
		return count;
	}
	
	/*public Iterable<String> myfunction()
	{
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Inside myfunction###################################");
		File file = new File("./src/main/resources/common/intensity.json");
		try {
			System.out.println("Inside try......");
			List<LinkedHashMap<String, String>> list = mapper.readValue(file,ArrayList.class);
			System.out.println(list.get(0));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
*/	
}
