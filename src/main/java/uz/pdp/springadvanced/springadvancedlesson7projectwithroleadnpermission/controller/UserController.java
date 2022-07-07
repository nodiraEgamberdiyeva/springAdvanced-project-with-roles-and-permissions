package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.ApiResponse;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.RegisterUserDto;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.UserDto;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.service.AuthService;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize(value = "hasAnyAuthority('ADD_USER')")
    @PostMapping
    public HttpEntity<?> registerUser(@Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse.getMessage());
    }
}
