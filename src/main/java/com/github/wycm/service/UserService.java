package com.github.wycm.service;

import com.github.wycm.Autowired;
import com.github.wycm.Component;
import com.github.wycm.dao.UserDao;

@Component
public class UserService {
    @Autowired
    private UserDao userDao;
    public void addUser(String userName){
        userDao.addUser(userName);
    }
}
