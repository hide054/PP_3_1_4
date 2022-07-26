package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.dao.RoleRepository;
import web.dao.UserRepository;
import web.model.Role;
import web.model.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

        User user = new User("User", "Userov", 18,  "user@mail.ru", "$2a$12$I2Jaja1tiG0OLLA8G9SrOO225e93KICb9qknGyO8Lgs5/CIMbxWV6", userRoles); //1234
        User admin = new User("Admin", "Adminov", 25,  "admin@mail.ru", "$2a$12$T69GNWh63fXfM7RuAcBZm.ZrZo9HjEJkf0cwpg8LWAc8XEM16Lg2e", adminRoles); //4321


        userRepository.save(user);
        userRepository.save(admin);

    }

}
