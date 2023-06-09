package com.scraping.scrapingjava.dto;

import com.scraping.scrapingjava.model.HomeEntity;

public class ResponseDTO {
	String title;
	String image;
	String episode;
	String status;
	
	public HomeEntity convertToEntity() {
		HomeEntity entity = new HomeEntity();
		entity.setEpisode(this.episode);
		entity.setImage(this.image);
		entity.setStatus(this.status);
		entity.setTitle(this.title);
		return entity;
	}
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getEpisode() {
		return episode;
	}
	public void setEpisode(String episode) {
		this.episode = episode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
