package com.renthouses.notification.controller;

import com.renthouses.notification.domain.NotificationLog;
import com.renthouses.notification.repository.NotificationLogRepository;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

  private final NotificationLogRepository repository;

  public NotificationController(NotificationLogRepository repository) {
    this.repository = repository;
  }

  @PostMapping("/email")
  public NotificationLog sendEmail(@RequestBody NotificationLog log) {
    log.setChannel("EMAIL");
    return repository.save(log);
  }

  @PostMapping("/sms")
  public NotificationLog sendSms(@RequestBody NotificationLog log) {
    log.setChannel("SMS");
    return repository.save(log);
  }

  @GetMapping
  public List<NotificationLog> logs(@RequestParam(required = false) String recipient) {
    return recipient == null ? repository.findAll() : repository.findByRecipient(recipient);
  }
}
