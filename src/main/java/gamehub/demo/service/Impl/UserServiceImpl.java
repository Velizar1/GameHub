package gamehub.demo.service.Impl;

import gamehub.demo.model.entity.User;
import gamehub.demo.model.service.UserServiceModel;
import gamehub.demo.repository.PlayerRepository;
import gamehub.demo.repository.UserRepository;
import gamehub.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public UserServiceModel addUser(UserServiceModel user) {

        UserServiceModel userServiceModel=this.userRepository
                .findByUserName(user.getUserName())
                .map(u->{
                     return this.modelMapper
                            .map(u,UserServiceModel.class);
                })
                .orElse(null);
        if(userServiceModel==null){
            this.userRepository
                    .save(this.modelMapper
                            .map(user, User.class));
        }
        return userServiceModel;
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        UserServiceModel userServiceModel=this.userRepository
                .findByUserName(username)
                .map(u->{
                    return this.modelMapper
                            .map(u,UserServiceModel.class);
                })
                .orElse(null);
        return userServiceModel;
    }
}
