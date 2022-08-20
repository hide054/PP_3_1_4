package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.repository.RoleRepository;
import web.repository.UserRepository;
import web.model.Role;
import web.model.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public Init(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        Role roleUser = new Role("USER");
        Role roleAdmin = new Role("ADMIN");
        Set<Role> userRoles = new HashSet<>();
        Set<Role> adminRoles = new HashSet<>();

        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);

        userRoles.add(roleUser);
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);

        User user = new User("User",
                "User", 21,
                "user@mail.ru",
                "$2a$12$6M74xs41D4LxfEILkM2KBe8l4L3R.2xco5aO2E08lZUDPwz8X1nVS",
                userRoles);
        User admin = new User("Admin",
                "Admin", 35,
                "admin@mail.ru",
                "$2a$12$F528qWb9/2oWvUN50tXTQOlTHYVQkhByfFzWmfk4EFKOTqHQdwWIy",
                adminRoles);

        userRepository.save(user);
        userRepository.save(admin);

    }

}
