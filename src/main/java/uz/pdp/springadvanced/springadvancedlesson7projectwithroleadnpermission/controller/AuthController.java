package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.ApiResponse;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.LoginDto;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.RegisterUserDto;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public HttpEntity<?> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto){
        ApiResponse apiResponse = authService.registerUser(registerUserDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse.getMessage());
    }

    @PostMapping("/login")
    public HttpEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto){
        ApiResponse apiResponse = authService.loginUser(loginDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse);
    }


}
