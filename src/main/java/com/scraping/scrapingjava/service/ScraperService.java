package com.scraping.scrapingjava.service;

import java.util.Set;

import com.scraping.scrapingjava.dto.ResponseDTO;

public interface ScraperService {

	Set<ResponseDTO> getListNanimeByModel(String title);
}
