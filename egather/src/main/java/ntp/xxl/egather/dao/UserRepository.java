package ntp.xxl.egather.dao;

import ntp.xxl.egather.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsernameAndPassword(String username,String password);
    User findUserByUsername(String username);
    User save(User user);
}
