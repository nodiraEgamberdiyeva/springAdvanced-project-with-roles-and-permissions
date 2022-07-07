package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.Role;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.User;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.enums.Permission;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.enums.RoleType;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.repository.RoleRepository;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.repository.UserRepository;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.utils.Constants;

import javax.management.relation.RoleResult;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    //ushbu class runtime paytida ishlaydi. Ushbu holatda db ga admin va user rollarini qo'shib qo'yadi
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //application properties dagi popertini olish uchun ishlatiladi
    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
            Permission[] permissions = Permission.values();
            Role roleAdmin = roleRepository.save(new Role(Constants.ADMIN, RoleType.ROLE_ADMIN, Arrays.asList(permissions),"admin"));
            Role roleUser = roleRepository.save(new Role(Constants.USER, RoleType.ROLE_USER, Arrays.asList(Permission.ADD_COMMENT, Permission.EDIT_COMMENT, Permission.DELETE_MY_COMMENT), "user"));

            userRepository.save(new User(
                    "ADMIN",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    roleAdmin,
                    true
            ));
            userRepository.save(
                    new User(
                            "USER",
                            "user",
                            passwordEncoder.encode("user123"),
                            roleUser,
                            true
                    )
            );
        }
    }
}
