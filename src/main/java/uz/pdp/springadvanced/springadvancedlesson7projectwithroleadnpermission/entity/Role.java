package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.enums.Permission;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.enums.RoleType;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)        //extends qilganda equals va hashcodlarni superclassdan inherit qiladi
public class Role extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String name;

    //uch xil admin user va custom, custom role bu admin tomonidan yaratilgan rollar
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)  //agar list enumlardan tashkil topgan bo'lsa ushby annotatsiya ishlatiladi
    private List<Permission> permissions;

    @Column(columnDefinition = "text")
    private String description;
}
