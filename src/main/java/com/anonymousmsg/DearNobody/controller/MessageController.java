package com.anonymousmsg.DearNobody.controller;

import com.anonymousmsg.DearNobody.model.MessageLog;
import com.anonymousmsg.DearNobody.repository.MessageLogRepository;
import com.anonymousmsg.DearNobody.service.IpInfoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageLogRepository repo;

    @Autowired
    private IpInfoService ipInfoService;

    @PostMapping
    public ResponseEntity<String> logMessage(@RequestBody Map<String, String> payload,
                                             HttpServletRequest request) {

        String message = payload.get("message");
        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        Map<String, String> ipInfo = ipInfoService.getIpInfo(ipAddress);

        MessageLog log = MessageLog.builder()
                .message(message)
                .ipAddress(ipAddress)
                .userAgent(userAgent)
                .timestamp(LocalDateTime.now())
                .city(ipInfo.getOrDefault("city", "Unknown"))
                .region(ipInfo.getOrDefault("region", "Unknown"))
                .country(ipInfo.getOrDefault("country", "Unknown"))
                .loc(ipInfo.getOrDefault("loc", "Unknown"))
                .orgInfo(ipInfo.getOrDefault("org", "Unknown"))
                .build();

        repo.save(log);

        return ResponseEntity.ok("Message sent successfully!");
    }

    @GetMapping
    public ResponseEntity<?> getAllMessages() {
        return ResponseEntity.ok(repo.findAll());
    }


}