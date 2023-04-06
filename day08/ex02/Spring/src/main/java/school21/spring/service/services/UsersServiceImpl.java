package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import java.util.UUID;

@Component("usersServiceImpl")
public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryJdbcTemplateImpl") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(String email) {
        if (email == null || email.isEmpty())
            throw new RuntimeException("Email can't be empty!");

        if (usersRepository.findByEmail(email).isPresent())
            throw new RuntimeException("User with this email already exists!");

        String password = UUID.randomUUID().toString();

        usersRepository.save(new User(email, password));
        return password;
    }
}