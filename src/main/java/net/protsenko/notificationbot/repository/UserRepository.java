package net.protsenko.notificationbot.repository;

import net.protsenko.notificationbot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
