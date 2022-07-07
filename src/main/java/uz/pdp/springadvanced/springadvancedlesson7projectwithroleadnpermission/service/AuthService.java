package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.Role;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.User;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.exception.ResourceNotFoundException;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.ApiResponse;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.LoginDto;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.RegisterUserDto;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.repository.RoleRepository;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.repository.UserRepository;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.security.JwtProvider;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername.orElse(null);
    }

    public ApiResponse registerUser(RegisterUserDto registerUserDto) {

        if (!registerUserDto.getPassword().equals(registerUserDto.getPrePassword())){
            return new ApiResponse("password is not match", false);
        }
        if (userRepository.existsByUsername(registerUserDto.getUsername()))
            return new ApiResponse("user is already exist", false);
        User user = new User(
                registerUserDto.getFullName(),
                registerUserDto.getUsername(),
                passwordEncoder.encode(registerUserDto.getPassword()),
                roleRepository.findByName(Constants.USER).orElseThrow(()->new ResourceNotFoundException("Role", "name", Constants.USER)),
                true
        );
        userRepository.save(user);
        return new ApiResponse("user registration done successfully", true);
    }

    public ApiResponse loginUser(LoginDto loginDto) {
        try{
            Authentication authenticate =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateJwt(user.getUsername(), user.getRole());
            token="Bearer "+token;
            return new ApiResponse(token, true);
        }
        catch (BadCredentialsException e){
            return new ApiResponse("user or password is incorrect", false);
        }
    }
}
