package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.enums.Permission;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.enums.RoleType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    @NotBlank //belgilardan tashkil topgan bo'lsin probelni o'zi bo'lmasin
    private String name;

    @NotNull
    private List<Permission> permissions;

    private String description;
}
