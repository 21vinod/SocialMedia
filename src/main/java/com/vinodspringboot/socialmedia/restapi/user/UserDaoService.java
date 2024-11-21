package com.vinodspringboot.socialmedia.restapi.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {
    public static int count = 1;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(count++, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(count++, "Brandi", LocalDate.now().minusYears(25)));
        users.add(new User(count++, "Charlie", LocalDate.now().minusYears(20)));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public User addUser(User user) {
        user.setId(count++);
        users.add(user);
        return user;
    }

//    public List<User> deleteUserById(int id) {
//        List<User> newUsers = users.stream().filter(user -> user.getId() != id).toList();
//        users = newUsers;
//        return users;
//    }

//    public List<User> updateUserById(int id) {
//        List<User> updatedUsers = users.stream().filter(user -> user.getId() == id).toList();
//        users = updatedUsers;
//        return users;
//    }
}
