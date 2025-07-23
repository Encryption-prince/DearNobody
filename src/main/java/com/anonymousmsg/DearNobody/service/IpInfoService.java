package com.anonymousmsg.DearNobody.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Service
public class IpInfoService {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${ipinfo.api.key}")
    private String token;

//    = "0ad995b43cb6d0";

    public Map<String, String> getIpInfo(String ip) {
        try {
            String url = "https://ipinfo.io/" + ip + "?token=" + token;
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            return response.getBody();
        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }
}
