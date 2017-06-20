package com.stackroute.swisit.crawler.domain;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonProperty;

public class IntensityBean {
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("intensity")
	private String intensity;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntensity() {
		return intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}

	public IntensityBean() {
		// TODO Auto-generated constructor stub
	}

	public IntensityBean(String title, String intensity) {
		super();
		this.title = title;
		this.intensity = intensity;
	}
	
	

}
