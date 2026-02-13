package com.renthouses.notification.domain;

import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("notification_logs")
public class NotificationLog {
  @Id
  private String id;
  private String channel;
  private String recipient;
  private String subject;
  private String message;
  private Instant sentAt = Instant.now();

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getChannel() { return channel; }
  public void setChannel(String channel) { this.channel = channel; }
  public String getRecipient() { return recipient; }
  public void setRecipient(String recipient) { this.recipient = recipient; }
  public String getSubject() { return subject; }
  public void setSubject(String subject) { this.subject = subject; }
  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }
  public Instant getSentAt() { return sentAt; }
  public void setSentAt(Instant sentAt) { this.sentAt = sentAt; }
}
