package com.vinodspringboot.socialmedia.restapi.jpa;

import com.vinodspringboot.socialmedia.restapi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User,Integer> {
}
