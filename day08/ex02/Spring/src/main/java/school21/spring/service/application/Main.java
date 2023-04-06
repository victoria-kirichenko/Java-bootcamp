package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.services.UsersServiceImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UsersServiceImpl usersService = applicationContext.getBean("usersServiceImpl", UsersServiceImpl.class);
        try {
            System.out.println(usersService.signUp("vika@gmail.ru"));
            System.out.println(usersService.signUp("lololo@gmail.ru"));
            System.out.println(usersService.signUp("hehehe3@gmail.ru"));
            System.out.println(usersService.signUp("vika@gmail.ru"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
