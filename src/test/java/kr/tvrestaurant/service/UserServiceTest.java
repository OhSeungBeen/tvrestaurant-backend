package kr.tvrestaurant.service;

import static org.junit.jupiter.api.Assertions.*;

import kr.tvrestaurant.user.domain.User;
import kr.tvrestaurant.user.domain.UserRepository;
import kr.tvrestaurant.user.application.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    void 회원가입() throws Exception{
        // given
        User user = new User("오승빈");

        // when
        Long saveId = userService.join(user);

        // then
        assertEquals(user, userRepository.findOne(saveId));
    }
}