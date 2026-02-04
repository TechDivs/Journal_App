package net.divs.journalApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import net.divs.journalApp.api_response.Quote;

@Component
public class QuoteService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.ninjas.key}")
    private String apikey;
    private static final String api="https://api.api-ninjas.com/v2/randomquotes";
    
    public String getQuote() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key",apikey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Quote>> response=restTemplate.exchange(api,HttpMethod.GET,entity,new ParameterizedTypeReference<List<Quote>>() {});

        List<Quote> quotes = response.getBody();
        return quotes.get(0).getQuote(); 
    } 
}
