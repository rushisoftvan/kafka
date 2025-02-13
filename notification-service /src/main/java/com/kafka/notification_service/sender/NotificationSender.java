package com.kafka.notification_service.sender;

public interface NotificationSender {

    void sendNotification(Object data);
}
