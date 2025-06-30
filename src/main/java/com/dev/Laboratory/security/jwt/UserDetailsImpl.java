package com.dev.Laboratory.security.jwt;


import com.dev.Laboratory.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    // Constructor used when creating a UserDetailsImpl from a User object
    public UserDetailsImpl(Collection<? extends GrantedAuthority> authorities, Long id, String password, String username) {
        this.authorities = authorities;
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public static UserDetailsImpl build(User user) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().name());
        return new UserDetailsImpl(
                Collections.singletonList(grantedAuthority),
                user.getId(),
                user.getPassword(),
                user.getUsername()
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }




    public UserDetailsImpl setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }
    public UserDetailsImpl setId(Long id) {
        this.id = id;
        return this;
    }

    public UserDetailsImpl setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDetailsImpl setUsername(String username) {
        this.username = username;
        return this;
    }




}
