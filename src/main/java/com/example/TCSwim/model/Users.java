package com.example.TCSwim.model;

import com.example.TCSwim.domain.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USERS")
public class Users implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ROLE")
    private UserRoles role;

//    public Users(String userName, String password, UserRoles role){
//        this.userName = userName;
//        this.password = password;
//        this.role = role;
//    }



    //validações
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRoles.COACH ) {
            return List.of(new SimpleGrantedAuthority("ROLE_COACH"),
                    new SimpleGrantedAuthority("ROLE_ATHLETE"));
        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_ATHLETE"));
        }
    }

    @Override
    public String getUsername() {
        return userName;
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
        return true;
    }
}
