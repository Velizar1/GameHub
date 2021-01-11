package gamehub.demo.service;

import gamehub.demo.model.service.UserServiceModel;

import java.util.Optional;

public interface UserService {
    UserServiceModel addUser(UserServiceModel user);
    UserServiceModel findByUsername(String username);
}
