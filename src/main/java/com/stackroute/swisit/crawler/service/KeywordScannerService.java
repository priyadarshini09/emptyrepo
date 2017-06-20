package com.stackroute.swisit.crawler.service;

import org.jsoup.nodes.Document;

public interface KeywordScannerService {
	
	public float scanDocument(Document link, String term);
	
	public	String[] getFromWordNet(String term);

}
