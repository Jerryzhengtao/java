package ntp.xxl.egather.service.impl;

import ntp.xxl.egather.dao.UserRepository;
import ntp.xxl.egather.po.User;
import ntp.xxl.egather.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public User checkUser(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public String registerCheck(String nickname,String username, String password, String rePassword,String type) {
        String message;
        if (!password.equals(rePassword)) {
            message = "两次输入密码不正确，请重新输入!";
        } else if (password.length() < 6) {
            message = "密码长度必须大于等于6!";
        } else if (userRepository.findUserByUsername(username) != null) {
            message = "用户名已存在！";
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setUserType(type);
            user.setNickName(nickname);
            userRepository.save(user);
            message = "success";
        }
        return message;
    }
}
