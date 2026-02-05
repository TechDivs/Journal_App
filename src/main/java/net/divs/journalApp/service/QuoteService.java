package net.divs.journalApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.divs.journalApp.api_response.Quote;
import net.divs.journalApp.cache.AppCache;

@Service
public class QuoteService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Value("${api.ninjas.key}")
    private String apikey;
    
    public String getQuote() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key",apikey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Quote>> response=restTemplate.exchange(appCache.APP_CACHE.get("quotes_api"),HttpMethod.GET,entity,new ParameterizedTypeReference<List<Quote>>() {});

        List<Quote> quotes = response.getBody();
        return quotes.get(0).getQuote(); 
    } 
}
