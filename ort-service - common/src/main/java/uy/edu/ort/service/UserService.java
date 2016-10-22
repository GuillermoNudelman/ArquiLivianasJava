package uy.edu.ort.service;

import java.util.List;
import uy.edu.ort.model.User;

public interface UserService {

    public void addUser(User user);

    public void removeUser(User user);

    public List<User> listUsers();
}
