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
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.RoleDto;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.UserDto;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.service.RoleService;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PreAuthorize(value = "hasAnyAuthority('ADD_ROLE')")
    @PostMapping
    public HttpEntity<?> registerUser(@Valid @RequestBody RoleDto roleDto){
        ApiResponse apiResponse = roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse.getMessage());
    }
}
