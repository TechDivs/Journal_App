package net.divs.journalApp.service;

import java.util.List;

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

    private static final String REDIS_QUOTE_KEY = "random_quote";
    private static final long QUOTE_TTL_SECONDS = 120L;

    private final RestTemplate restTemplate;
    private final RedisService redisService;
    private final AppCache appCache;

    @Value("${api.ninjas.key}")
    private String apikey;

    public QuoteService(RestTemplate restTemplate,RedisService redisService,AppCache appCache) {
        this.restTemplate = restTemplate;
        this.redisService = redisService;
        this.appCache = appCache;
    }

    public String getQuote() {
        String cachedQuote = redisService.get(REDIS_QUOTE_KEY, String.class);
        if(cachedQuote != null) {
            return cachedQuote;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apikey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Quote>> response =
                restTemplate.exchange(
                        appCache.APP_CACHE.get("quotes_api"),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<Quote>>() {}
                );

        List<Quote> quotes = response.getBody();
        if (quotes == null || quotes.isEmpty()) return null;

        String quote = quotes.get(0).getQuote();

        redisService.set(REDIS_QUOTE_KEY, quote, QUOTE_TTL_SECONDS);

        return quote;
    }
}
