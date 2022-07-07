package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.Role;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.enums.RoleType;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.ApiResponse;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.RoleDto;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    public ApiResponse addRole(RoleDto roleDto) {
        if (roleRepository.existsByName(roleDto.getName())){
            return new ApiResponse("role is exist", false);
        }
        Role role = new Role(roleDto.getName(), RoleType.ROLE_CUSTOM, roleDto.getPermissions(), roleDto.getDescription());
        roleRepository.save(role);
        return new ApiResponse("role is saved", true);
    }
}
