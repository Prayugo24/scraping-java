package com.scraping.scrapingjava.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scraping.scrapingjava.dto.ResponseDTO;
import com.scraping.scrapingjava.service.ScraperService;

@RestController
@RequestMapping("/scrap")
public class HomeController {
	
	@Autowired
	ScraperService scraperService;
	
	@GetMapping("/home")
	public Set<ResponseDTO> homeService(){
		return scraperService.getListNanimeByModel("tets");
	}
	
}
