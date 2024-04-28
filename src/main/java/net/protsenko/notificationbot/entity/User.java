package net.protsenko.notificationbot.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bot_users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID userId;

    @Column(unique = true, nullable = false)
    Long chatId;

    @Column(nullable = false)
    String userName;

    @Column(nullable = false)
    LocalDateTime registeredAt;

    @OneToMany
    Set<Notification> notifications;

}
