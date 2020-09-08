package com.beautiful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private List<User> userList;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData(){
        String password = passwordEncoder.encode("123456");
        userList=new ArrayList<>();
        userList.add(new User("dang",password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
        userList.add(new User("yong",password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
        userList.add(new User("jiu",password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> collect = userList.stream()
                .filter(user -> user.getUsername().equals(s)).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(collect)){
            return collect.get(0);
        }else {
            throw new UsernameNotFoundException("用户名密码错误");
        }
    }
}
