package school21.spring.service.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import school21.spring.service.config.TestApplicationConfig;

@Component
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersServiceImplTest {
    private UsersService usersService;

    @BeforeEach
    void init() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        usersService = applicationContext.getBean("usersServiceJdbcTemplate", UsersServiceImpl.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ok@gmail.ru","ok1@gmail.ru", "lol@gmail.ru", "lololo@gmail.ru", "hahaha@gmail.ru"})
    void usersServiceSuccessTest(String email) {
        Assertions.assertNotNull(usersService.signUp(email));
    }
}