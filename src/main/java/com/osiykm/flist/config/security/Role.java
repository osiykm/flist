package com.osiykm.flist.config.security;

import org.springframework.security.core.GrantedAuthority;

/***
 * @author osiykm
 * created 19.09.2017 22:13
 */
public enum Role implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
