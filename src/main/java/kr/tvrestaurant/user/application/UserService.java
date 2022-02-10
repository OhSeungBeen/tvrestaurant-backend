package kr.tvrestaurant.user.application;

import java.util.List;
import kr.tvrestaurant.user.domain.User;
import kr.tvrestaurant.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user) {
        userRepository.save(user);
        return user.getId();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }
}
