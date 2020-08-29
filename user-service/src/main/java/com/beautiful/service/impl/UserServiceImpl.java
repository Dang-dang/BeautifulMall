package com.beautiful.service.impl;

import com.beautiful.domain.User;
import com.beautiful.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private List<User> userList=new ArrayList<>();

    @PostConstruct
    public void initData(){
         userList.add(new User(1L,"dang","111"));
         userList.add(new User(2L,"yong","222"));
         userList.add(new User(3L,"jiu","333"));
    }

    @Override
    public int create(User user) {
        boolean success = userList.add(user);
        return success?1:0;
    }

    @Override
    public void update(User user) {
        userList.stream()
                .filter(u -> u.getId().equals(user.getId()))
                .forEach(u->{
                    u.setPassword(user.getPassword());
                    u.setUsername(user.getUsername());
                });
    }

    @Override
    public void delete(Long id) {
        User user=getUser(id);
        if(user!=null) userList.remove(user);
    }

    @Override
    public User getUser(Long id) {
        List<User> collect = userList.stream()
                .filter(u -> u.getId().equals(id))
                .collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(collect)){
            return collect.get(0);
        }
        return null;
    }
}
