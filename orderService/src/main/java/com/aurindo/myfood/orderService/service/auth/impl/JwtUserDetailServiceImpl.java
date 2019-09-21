package com.aurindo.myfood.orderService.service.auth.impl;

import com.aurindo.myfood.orderService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Qualifier("jwtUserDetailService")
@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.aurindo.myfood.orderService.model.User user;
        try {
            user = this.userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("user name not found");
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("database error ");
        }
        return buildUserFromUserEntity(user);
    }

    private UserDetails buildUserFromUserEntity(com.aurindo.myfood.orderService.model.User user) {
        List<GrantedAuthority> authorities = this.buildUserAuthority(user.getRoles());

        return this.buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<String> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        for(String userRole  : userRoles){
            setAuths.add(new SimpleGrantedAuthority(userRole));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(setAuths);
        return grantedAuthorities;
    }

    private User buildUserForAuthentication(
            com.aurindo.myfood.orderService.model.User user,
            List<GrantedAuthority> authorities) {
        //accountNonExpired, credentialsNonExpired, accountNonLocked, authorities properties
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getEnable(),
                true,
                true,
                true, authorities);
    }
}
