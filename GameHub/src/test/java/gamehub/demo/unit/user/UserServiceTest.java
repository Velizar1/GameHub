package gamehub.demo.unit.user;

import gamehub.demo.mapper.UserMapper;
import gamehub.demo.model.entity.User;
import gamehub.demo.model.service.UserServiceModel;
import gamehub.demo.repository.UserRepository;
import gamehub.demo.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserServiceImpl userService;

    @Mock
    UserRepository mockUserRepository;

    @BeforeEach
    public void setUp(){
        userService=new UserServiceImpl(mockUserRepository,new ModelMapper());
    }

    @Test
    public void testFindByUsername(){

        //arrange

        User user=new User();
        user.setEmail("asd@abv.bg");
        user.setUserName("Pesho");
        user.setPassword("123");

        when(mockUserRepository.findByUserName("Pesho"))
                .thenReturn(java.util.Optional.of(user));

        //act

        UserServiceModel userServiceModel= userService.findByUsername("Pesho");

        //assert
        Assertions.assertEquals(user.getEmail(),userServiceModel.getEmail());
        Assertions.assertEquals(user.getUserName(),userServiceModel.getUserName());
        Assertions.assertEquals(user.getPassword(),userServiceModel.getPassword());

    }
}
