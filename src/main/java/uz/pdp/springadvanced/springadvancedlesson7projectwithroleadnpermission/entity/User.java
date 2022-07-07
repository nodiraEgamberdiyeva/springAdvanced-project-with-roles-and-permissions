package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.enums.Permission;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.*;

@Data@NoArgsConstructor@AllArgsConstructor
@Entity(name = "users")
@EqualsAndHashCode(callSuper = true)        //extends qilganda equals va hashcodlarni superclassdan inherit qiladi
public class User extends AbstractEntity implements UserDetails {
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    private boolean enabled;

    //role permissionlari listini berib yuborish
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Permission permission : this.role.getPermissions()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.name()));
        }

        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
