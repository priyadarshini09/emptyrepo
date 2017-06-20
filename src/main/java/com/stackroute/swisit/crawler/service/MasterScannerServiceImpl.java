package com.stackroute.swisit.crawler.service;


import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.stackroute.swisit.crawler.domain.CrawlerBean;
import com.stackroute.swisit.crawler.domain.SwisitBean;

@Service
public class MasterScannerServiceImpl implements MasterScannerService{
	
	
	private DOMCreatorService domCreatorService;
	
	@Autowired
	public void setDomCreatorService(DOMCreatorService domCreatorService) {
		this.domCreatorService = domCreatorService;
	}
	
	 private KeywordScannerService keywordScannerService;
	 
	@Autowired
	public void setKeywordScannerService(KeywordScannerService keywordScannerService) {
		this.keywordScannerService = keywordScannerService;
	}
	
	private StructureScannerService structureScannerService;
		
	@Autowired
	public void setStructureScannerService(StructureScannerService structureScannerService) {
		this.structureScannerService = structureScannerService;
	}
		

	String term="Angular";
	
	@Override
	public String scanDocument(SwisitBean[] swisitBean) {
		System.out.println("inside master scandocs");
		for(SwisitBean sb:swisitBean)
		{
			String link=sb.getLink();
			System.out.println(link);
			Document document=domCreatorService.constructDOM(link);
			//System.out.println(document);
			keywordScannerService.scanDocument(document, term);
		}
		return "sucess";
	}



	

	

	

}
