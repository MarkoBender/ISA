package com.bender.Repositories;

import com.bender.Beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bender on 12/25/2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email,String password);
    User findByEmail(String email);

}
