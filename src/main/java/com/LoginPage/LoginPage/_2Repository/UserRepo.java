package com.LoginPage.LoginPage._2Repository;

import com.LoginPage.LoginPage._1Entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 2.

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    // 6.

    User getUserByEmail(String email);
}
