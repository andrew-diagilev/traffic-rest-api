/*
package com.traffic.api.security;


import com.traffic.api.models.users.Role;
import com.traffic.api.models.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create (User user){
        return new JwtUser(user.getId(), user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getEmail(), user.getPhone(), user.getTelegram(), mapToGrantedAuthorities(user.getRoles()));

        }
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles){
        return roles.stream().map(role ->new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }
}
*/
