package ntp.xxl.egather.service;

import ntp.xxl.egather.po.User;

public interface UserService {
    User checkUser(String username,String password);
    String registerCheck(String nickname,String username,String password,String rePassword,String type);
}
