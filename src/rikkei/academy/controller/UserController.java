package rikkei.academy.controller;

import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessage;
import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;
import rikkei.academy.service.role.IRoleService;
import rikkei.academy.service.role.RoleServiceIMPL;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {

    private IUserService userService = new UserServiceIMPL();
    private IRoleService roleService = new RoleServiceIMPL();

    public ResponseMessage register(SignUpDTO sign) {
        if (userService.existeByUserName(sign.getUserName())) {
            return new ResponseMessage("user_existed");
        }
        if (userService.existeByEmail(sign.getEmail())) {
            return new ResponseMessage("email_existed");
        }
        Set<Role> roleSet = new HashSet<>();
        Set<String> strRole = sign.getStrRole();

        strRole.forEach(role -> {
            switch (role) {
                case "admin":
                    roleSet.add(roleService.findByName(RoleName.ADMIN));
                    break;
                case "pm":
                    roleSet.add(roleService.findByName(RoleName.PM));
                    break;
                default:
                    roleSet.add(roleService.findByName(RoleName.USER));
            }
        });
        User user = new User(sign.getId(), sign.getName(), sign.getUserName(), sign.getEmail(), sign.getPassword(), roleSet);
        userService.save(user);
        return new ResponseMessage("create_success");
    }

    public List<User> getListUser() {
        return userService.findAll();
    }

    public ResponseMessage login(SignInDTO signInDTO) {
        if (userService.checkLogin(signInDTO.getUsername(), signInDTO.getPassword())) {
            return new ResponseMessage("Login_success");
        } else {
            return new ResponseMessage("Login_false");
        }
    }

    public User getUserLogin() {
        return userService.getCurentUser();
    }

    public void UpdateUser(User user) {
        userService.save(user);
    }
    public void userLogout(){
        userService.logout();
    }
    public User getUser(int id){
        return userService.findById(id);
    }
}
