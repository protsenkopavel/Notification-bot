package net.protsenko.notificationbot.repository;

import net.protsenko.notificationbot.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
