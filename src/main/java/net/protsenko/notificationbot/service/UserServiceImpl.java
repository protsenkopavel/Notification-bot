package net.protsenko.notificationbot.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.protsenko.notificationbot.entity.User;
import net.protsenko.notificationbot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void checkUser(User user) {
        if (user != null) {
            if (!userRepository.existsById(user.getChatId())) {
                userRepository.save(user);
            }
        }
    }
}
