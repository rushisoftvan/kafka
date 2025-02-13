package com.kafka.notification_service.model.dto;

import java.util.Map;

public record MailDto(
        String subject,

        String toMail,

        Map<String, Object> props,

        String templateFileName
) {
}
