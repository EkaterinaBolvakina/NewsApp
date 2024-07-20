package group40.newsapp.service.util;

import group40.newsapp.DTO.user.UserNewDTO;
import group40.newsapp.DTO.user.UserWithIdDTO;
import group40.newsapp.models.user.Role;
import group40.newsapp.models.user.State;
import group40.newsapp.models.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserConvert {
    public User fromDTOtoUser(UserNewDTO user, Role role, State state) {
        return   new User(user.getName(),
                user.getPassword(),
                user.getEmail(),
                role,state,"");
    }
    public User fromIdDTOtoUser(UserWithIdDTO user, Role role, State state) {
        return   new User(user.getId(), user.getName(),
                user.getPassword(),
                user.getEmail(), role, state,"");
    }
}