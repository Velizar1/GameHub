package gamehub.demo.service.Impl;

import gamehub.demo.model.service.UserServiceModel;
import gamehub.demo.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

//TODO not set ADMIN role
@Service
public class UserDetailsServiceIml implements UserDetailsService {
    private final UserService userService;

    private final HttpSession httpSession;
    public UserDetailsServiceIml(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserServiceModel user=userService.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("No such user "+username);
        }
        User mapedUser = map(user);
        httpSession.setAttribute("user",username);
        return  mapedUser;
    }
    private User map(UserServiceModel u){

        List<GrantedAuthority> grantedAuthority=new ArrayList<>();
        grantedAuthority.add(new SimpleGrantedAuthority("USER"));
        User user=new User(
                u.getUserName(),
                u.getPassword(),
                grantedAuthority
        );
        if(user.getPassword()==null){
            user.eraseCredentials();
        }
        return user;
    }
}

