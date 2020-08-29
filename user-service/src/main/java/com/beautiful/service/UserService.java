package com.beautiful.service;

import com.beautiful.domain.User;

public interface UserService {

    int create(User user);

    void update(User user);

    void delete(Long id);

    User getUser(Long id);


}
