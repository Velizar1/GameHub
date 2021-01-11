package gamehub.demo.service.Impl;

import gamehub.demo.mapper.UserMapper;
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

        UserServiceModel userByEmail=this.userRepository
                .findByEmail(user.getEmail())
                .map(u->{
                     return this.modelMapper
                            .map(u,UserServiceModel.class);
                })
                .orElse(null);
        UserServiceModel userByName=this.userRepository
                .findByUserName(user.getUserName())
                .map(u->{
                    return this.modelMapper
                            .map(u,UserServiceModel.class);
                })
                .orElse(null);
        if(userByEmail==null&&userByName==null){
              this.userRepository
                    .save(UserMapper.INSTANCE.userServiceModelToEntity(user));
              return null;
        }
        return user;
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
