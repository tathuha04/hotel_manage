package rikkei.academy.service.user;

import rikkei.academy.model.User;
import rikkei.academy.service.IGenericService;

public interface IUserService extends IGenericService<User> {
    boolean existeByUserName(String userName);
    boolean existeByEmail(String email);

    boolean checkLogin(String username, String password);
    User getCurentUser();
    void logout();
}
