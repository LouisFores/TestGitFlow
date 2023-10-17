package com.example.boxanime.service.login_logout;

import com.example.boxanime.model.User;

import java.util.List;

public interface ILogin {
    public void creatUser(User user);
    public boolean checkAccountUser(User user);
    public List<User> getListUser();
    public boolean updateUser(int id);
    public boolean deleteUser(int id);
}
