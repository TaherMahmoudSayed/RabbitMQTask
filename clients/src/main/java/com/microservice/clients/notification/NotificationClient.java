package com.microservice.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification",
             url = "http://localhost:8084"  )
public interface NotificationClient {
    @PostMapping("api/v1/notification/send")
    void sendNotification(@RequestBody NotificationRequest notificationRequest);
}
