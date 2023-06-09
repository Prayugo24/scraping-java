package com.scraping.scrapingjava.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.scraping.scrapingjava.dto.ResponseDTO;
import com.scraping.scrapingjava.model.HomeEntity;
import com.scraping.scrapingjava.repository.HomeRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class ScraperServiceImpl implements ScraperService {
	
	@Value("${scraping.app.urlScrap}")
	private String urls;
	

	private static final Logger logger = Logger.getLogger(ScraperServiceImpl.class.getName());
	
	private final HomeRepository homeRepository;
	
	
	
	
//	@Value("#{'${website.urls}'.split(',')}")
//	List<String> urls;

	@Autowired
	public ScraperServiceImpl(HomeRepository homeRepository) {
		this.homeRepository = homeRepository;
	}

	@Override
	public Set<ResponseDTO> getListNanimeByModel(String title) {
		Set<ResponseDTO> responseDTOs = new HashSet<>();
		extractDataNanime(responseDTOs,urls);
		saveTodatabase(responseDTOs);
		return responseDTOs;
	}
	
	public void saveTodatabase(Set<ResponseDTO> responDTOs) {
		Set<HomeEntity> entities = responDTOs.stream()
				.map(ResponseDTO::convertToEntity)
				.filter(entity -> !homeRepository.existsByTitle(entity.getTitle()))
				.collect(Collectors.toSet());
		homeRepository.saveAll(entities);
	}
	
	private void extractDataNanime(Set<ResponseDTO> responseDTOs, String urls)  {
		try {
			
		
			final Document document = Jsoup.connect(urls).get();
			Element content = document.getElementById("content");
//		    final Elements boxPoster = content.select(".col-md-7 .box-default .box-poster .content-item");
			final Elements listupd = content.select(".bixbox.bbnofrm .listupd.normal .excstf .bs");
//			logger.warning("cek"+listupd);
		
				listupd.forEach(poster -> {
		          final ResponseDTO responseDTO = new ResponseDTO();
		          final Element link = poster.selectFirst("a");
		          if (link !=null) {
		        	  
		        	  responseDTO.setTitle(link.attr("title"));
		              responseDTO.setImage(link.selectFirst("img").attr("src"));
		          }
		          
		          final Element status = poster.selectFirst(".limit .typez");
		          if(status !=null ) {
		        	  responseDTO.setStatus(status.text());
		        	  
		          }
		          
		          final Element episode = poster.selectFirst(".limit .epx");
		          if(episode !=null) {
		        	  responseDTO.setEpisode(episode.text());
		          }
		          
		          if (responseDTO.getTitle() != null) {
		              responseDTOs.add(responseDTO);
		          }
		          
//		          nanime
//		          final Element link = poster.selectFirst("a");
//		          final Element episode = poster.selectFirst(".episode .label.btn-danger");
//		          final Element status = poster.selectFirst(".status .label.btn-primary");
//		          if (link != null) {
//		              responseDTO.setTitle(link.attr("title"));
//		              responseDTO.setImage(link.selectFirst("img").attr("src"));
//		          }
//		          if (episode != null) {
//		        	    String episodes = episode.text().replace("Episode ", "");
//		        	    responseDTO.setEpisode(episodes);
//		          }
//		          
//		          if (status != null) {
//		        	  String statuses = status.text();
//		        	  logger.info("status"+statuses);
//		        	  responseDTO.setStatus(statuses);
//		        	  
//		          }
//		          
//
//		           if (responseDTO.getTitle() != null) {
//		              responseDTOs.add(responseDTO);
//		           }
		     });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
