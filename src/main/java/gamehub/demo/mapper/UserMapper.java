package gamehub.demo.mapper;
import gamehub.demo.model.entity.User;
import gamehub.demo.model.service.UserServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    @Mapping(source = "userName", target = "userName")
    User userServiceModelToEntity(UserServiceModel model);
    @Mapping(source = "userName", target = "userName")
    UserServiceModel userEntityToServiceModel(User user);
}
