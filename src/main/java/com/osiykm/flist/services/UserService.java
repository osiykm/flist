package com.osiykm.flist.services;

import com.google.common.collect.ImmutableList;
import com.osiykm.flist.config.security.Role;
import com.osiykm.flist.config.security.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/***
 * @author osiykm
 * created 19.09.2017 22:06
 */
@Service
public class UserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return User.builder()
                .username(username)
                .password("password")
                .accountNonExpired(true)
                .accountNonLocked(true)
                .accountNonExpired(true)
                .authorities(ImmutableList.of(Role.USER))
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }
}
