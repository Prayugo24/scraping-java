package com.scraping.scrapingjava.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="home")
public class HomeEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
	public UUID id;
	
	@Column(name="title")
	public String title;
	
	@Column(name="image")
	public String image;
	
	@Column(name = "status")
	public String status;
	
	@Column(name="episode")
	public String episode;
	
	

	public HomeEntity() {
	}

	public HomeEntity(UUID id, String title, String image, String status, String episode) {
		this.id = id;
		this.title = title;
		this.image = image;
		this.status = status;
		this.episode = episode;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEpisode() {
		return episode;
	}

	public void setEpisode(String episode) {
		this.episode = episode;
	}
	
	
	
	

}
