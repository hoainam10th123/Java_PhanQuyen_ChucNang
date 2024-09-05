package com.hoainam10th.phanquyendatabase.DataSeeder;

import com.hoainam10th.phanquyendatabase.Helper.Constanst;
import com.hoainam10th.phanquyendatabase.entities.Function;
import com.hoainam10th.phanquyendatabase.entities.Permission;
import com.hoainam10th.phanquyendatabase.entities.Role;
import com.hoainam10th.phanquyendatabase.entities.User;
import com.hoainam10th.phanquyendatabase.repositories.FunctionRepository;
import com.hoainam10th.phanquyendatabase.repositories.PermissionRepository;
import com.hoainam10th.phanquyendatabase.repositories.RoleRepository;
import com.hoainam10th.phanquyendatabase.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DataSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final FunctionRepository functionRepository;
    private final PasswordEncoder passwordEncoder;
    private final PermissionRepository permissionRepository;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0){

            Role adminRole = new Role("ROLE_ADMIN");
            Role userRole = new Role("ROLE_USER");

            Role savedAdminRole = roleRepository.save(adminRole);
            Role savedUserRole = roleRepository.save(userRole);

            User hoainam10th = new User(
                    "Nguyen Hoai Nam",
                    "hoainam10th", passwordEncoder.encode("123456"));

            User ubuntu = new User(
                    "Ubuntu Nguyen",
                    "ubuntu", passwordEncoder.encode("123456"));

            User admin = new User(
                    "Administrator",
                    "admin", passwordEncoder.encode("123456"));

            User returnHoainam10th = userRepository.save(hoainam10th);
            User returnUbuntu = userRepository.save(ubuntu);
            User returnAdmin = userRepository.save(admin);

            returnHoainam10th.getRoles().add(savedUserRole);
            returnUbuntu.getRoles().add(savedUserRole);
            returnAdmin.getRoles().add(savedAdminRole);

            userRepository.save(returnHoainam10th);
            userRepository.save(returnUbuntu);
            userRepository.save(returnAdmin);
        }
        // seed function
        if(functionRepository.count() == 0){
            Function fun_TODO_GET = new Function(Constanst.TODO_GET);

            Function fun_TODO_UPDATE = new Function(Constanst.TODO_UPDATE);

            Function fun_TODO_ADD = new Function(Constanst.TODO_CREATED);

            Function fun_TODO_DELETE = new Function(Constanst.TODO_DELETE);

            functionRepository.save(fun_TODO_GET);
            functionRepository.save(fun_TODO_UPDATE);
            functionRepository.save(fun_TODO_ADD);
            functionRepository.save(fun_TODO_DELETE);
        }

        if(permissionRepository.count() == 0){
            Permission permission1 = new Permission("TODO_CREATED");
            Permission permission2 = new Permission("TODO_UPDATE");
            Permission permission3 = new Permission("TODO_DELETE");
            Permission permission4 = new Permission("TODO_GET");

            permissionRepository.save(permission1);
            permissionRepository.save(permission2);
            permissionRepository.save(permission3);
            permissionRepository.save(permission4);
        }

    }
}
