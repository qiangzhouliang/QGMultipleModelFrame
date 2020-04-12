package com.zdww.lzshzz.ucenter.auth.service;

import com.zdww.lzshzz.ucenter.auth.client.UserClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 强周亮
 * @desc 用户信息服务组件
 * @email 2538096489@qq.com
 * @time 2019/11/4 10:02
 * @class UserDetailsServiceImpl
 * @package com.zdww.lzshzz.ucenter.auth.service
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserClient userClient;
    @Autowired
    ClientDetailsService clientDetailsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if (authentication == null) {
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if (clientDetails != null) {
                //密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        if (!username.contains(",")) {
            return null;
        }
        String[] list = username.split(",");
        username = list[0];
        String password = list[1];

        //从数据库获取权限
        //指定用户的权限，这里暂时硬编码
        List<String> permissionList = new ArrayList<>();
        permissionList.add("app");
        //将权限串中间以逗号分隔
        String permissionString = StringUtils.join(permissionList.toArray(), ",");

        UserJwt userDetails = new UserJwt(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList(permissionString));

        return userDetails;
    }
}
