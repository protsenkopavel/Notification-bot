package net.protsenko.notificationbot.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notifications")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID notificationId;

    String title;

    String description;

    @Enumerated(EnumType.STRING)
    Status status;

    Long timeUntilExpiration;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

}
