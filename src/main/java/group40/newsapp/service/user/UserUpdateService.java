package group40.newsapp.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserUpdateService {
//    UserRepository repository;
//    RoleRepository roleRepository;
//    UserConvert userConvert;
//    UserFindService userFindService;
//
//    public void EditUser(UserWithIdDTO user) {
//        User oldUser = userFindService.findUserById(user.getId());
//        User newUser = userConvert.fromIdDTOtoUser(user, oldUser.getRole());
//        repository.save(newUser);
//    }
//
//    public void changeRole(UserRoleDTO roleDTO) {
//        User newUser = userFindService.findUserById(roleDTO.getUserId());
//        Role role = roleRepository.findByName(roleDTO.getRole())
//                .orElseThrow(()->new NotFoundException("Role with name: "+roleDTO.getRole()+" not found"));
//        newUser.setRole(role);
//        repository.save(newUser);
//    }



}
