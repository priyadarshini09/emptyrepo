package com.stackroute.swisit.crawler.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

//import com.stackroute.swisit.crawler.domain.CrawlerBean;
import com.stackroute.swisit.crawler.domain.SwisitBean;

public interface CrawlerRepository extends MongoRepository< SwisitBean,String> {

}
