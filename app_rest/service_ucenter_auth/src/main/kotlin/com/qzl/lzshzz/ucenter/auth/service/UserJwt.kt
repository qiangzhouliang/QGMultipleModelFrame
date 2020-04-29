package com.qzl.lzshzz.ucenter.auth.service

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class UserJwt(username: String, password: String, authorities: Collection<GrantedAuthority>) : User(username, password, authorities) {
}
