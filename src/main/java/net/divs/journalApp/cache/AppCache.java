package net.divs.journalApp.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import net.divs.journalApp.entity.ConfigJournalAppEntity;
import net.divs.journalApp.repository.ConfigJournalAppRepository;

@Component
public class AppCache {
    @Autowired
    private ConfigJournalAppRepository configrepo;

    public Map<String,String> APP_CACHE = new HashMap<>();

    @PostConstruct
    public void init() {
        List<ConfigJournalAppEntity> all = configrepo.findAll();
        for(ConfigJournalAppEntity curr : all) {
            APP_CACHE.put(curr.getKey(), curr.getValue());
        }
    }
}
