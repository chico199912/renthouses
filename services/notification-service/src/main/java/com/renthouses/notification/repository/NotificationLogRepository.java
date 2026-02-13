package com.renthouses.notification.repository;

import com.renthouses.notification.domain.NotificationLog;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationLogRepository extends MongoRepository<NotificationLog, String> {
  List<NotificationLog> findByRecipient(String recipient);
}
