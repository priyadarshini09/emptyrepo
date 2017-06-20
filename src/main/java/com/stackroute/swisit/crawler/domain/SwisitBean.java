package com.stackroute.swisit.crawler.domain;


import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@JsonIgnoreProperties(ignoreUnknown=true)
public class SwisitBean extends ResourceSupport implements Deserializer<SwisitBean> {
	
    @JsonProperty("query")
	private String query;
    
    @JsonProperty("link")
	private String link;
    
    
	@JsonProperty("title")
	private String title;
    
    @JsonProperty("snippet")
	private String snippet;
	
	
    
	
	public SwisitBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SwisitBean(String query, String link, String title, String snippet) {
		super();
		this.query = query;
		this.link = link;
		this.title = title;
		this.snippet = snippet;
	}

	
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SwisitBean deserialize(String arg0, byte[] arg1) {
		//return (CrawlerBean) SerializationUtils.deserialize(arg1);
		ObjectMapper o=new ObjectMapper();
		SwisitBean c=null;
		try{
			System.out.println(arg1.toString());
			c=o.readValue(arg1,SwisitBean.class);
			}
		catch(Exception e){
			System.out.println("hi this "+e);
		}
		return c;
	}

	
	
	
}
