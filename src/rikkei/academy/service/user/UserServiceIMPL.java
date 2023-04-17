package rikkei.academy.service.user;

import rikkei.academy.config.Config;
import rikkei.academy.model.Category;
import rikkei.academy.model.User;

import java.util.List;

public class UserServiceIMPL implements IUserService {
    List<User> userList = new Config<User>().readFromFile(Config.PATH_USER);

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public void save(User user) {
        if (findById(user.getId()) == null) {
            userList.add(user);
        } else {
            userList.set(userList.indexOf(findById(user.getId())), user);
        }
//        userList.add(user);
        new Config<User>().writeToFile(Config.PATH_USER, userList);
    }

    @Override
    public User findById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public boolean existeByUserName(String userName) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existeByEmail(String email) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        List<User> userLogin = new Config<User>().readFromFile(Config.PATH_USER_LOGIN);
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserName().equals(username) && userList.get(i).getPassWord().equals(password)) {
                userLogin.add(userList.get(i));
                new Config<User>().writeToFile(Config.PATH_USER_LOGIN, userLogin);
                return true;
            }
        }
        return false;
    }

    @Override
    public User getCurentUser() {
        if (new Config<User>().readFromFile(Config.PATH_USER_LOGIN).size() != 0) {
            User user = new Config<User>().readFromFile(Config.PATH_USER_LOGIN).get(0);
            return user;
        }
        return null;
    }

    @Override
    public void logout() {
        List<User> userLogin = new Config<User>().readFromFile(Config.PATH_USER_LOGIN);
        userLogin.remove(0);
        new Config<User>().writeToFile(Config.PATH_USER_LOGIN, userLogin);

    }


}
