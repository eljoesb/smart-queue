package com.smartqueue.application.ports.out;

import com.smartqueue.domain.model.Notification;
import com.smartqueue.domain.model.Patient.NotificationChannel;

public interface NotificationService {
    boolean sendNotification(Notification notification);

    boolean supportsChannel(NotificationChannel channel);

    String getServiceName();
}