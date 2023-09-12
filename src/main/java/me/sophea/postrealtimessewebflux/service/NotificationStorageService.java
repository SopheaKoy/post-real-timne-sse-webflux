package me.sophea.postrealtimessewebflux.service;

import me.sophea.postrealtimessewebflux.model.Notification;
import me.sophea.postrealtimessewebflux.repository.NotificationStorageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class NotificationStorageService {

    private final NotificationStorageRepository notifRepository;

    public NotificationStorageService(NotificationStorageRepository notifRepository) {
        this.notifRepository = notifRepository;
    }

    public Notification createNotificationStorage(Notification notificationStorage) {
        return notifRepository.save(notificationStorage);
    }


    public Notification getNotificationsByID(String id) {
        return notifRepository.findById(id).orElseThrow(() -> new RuntimeException("notification not found!"));
    }

    public List<Notification> getNotificationsByUserIDNotRead(String userID) {
        return notifRepository.findByUserToIdAndDeliveredFalse(userID);
    }


    public List<Notification> getNotificationsByUserID(String userID) {
        return notifRepository.findByUserToId(userID);
    }

    public Notification changeNotifStatusToRead(String notifID) {
        var notif = notifRepository.findById(notifID)
                .orElseThrow(() -> new RuntimeException("not found!"));
        notif.setRead(true);
        return notifRepository.save(notif);
    }

    public void clear() {
        notifRepository.deleteAll();
    }
}
