package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.Role;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.User;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.ApiResponse;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.UserDto;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.repository.RoleRepository;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public ApiResponse addUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())){
            return new ApiResponse("user is exist", false);
        }

        Optional<Role> byId = roleRepository.findById(userDto.getRoleId());
        if (!byId.isPresent()){
            return new ApiResponse("role nit found", false);
        }


        User user = new User(userDto.getFullName(), userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), byId.get(), true);
        userRepository.save(user);
        return new ApiResponse("user is added", true);
    }
}
