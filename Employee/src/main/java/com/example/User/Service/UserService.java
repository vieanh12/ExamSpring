package com.example.User.Service;

import com.example.User.Entity.User;
import com.example.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private List<User> users;
    public User createUser(User user){
        User user1 = new User();
        user1.setName(user.getName());
        user1.setAge(user.getAge());
        user1.setSalary(user.getSalary());
        return userRepository.save(user);
    }

    public User update(User user){
//        User user1 = userRepository.findById(id).orElseThrow(() -> new RuntimeException("k tim thay id"));
        User user1 = new User();
        user1.setName(user.getName());
        user1.setAge(user.getAge());
        user1.setSalary(user.getSalary());
        return userRepository.save(user1);
    }
    public void delete(long id){
        User user1 = userRepository.findById(id).orElseThrow(() -> new RuntimeException("k tim thay id"));
        userRepository.delete(user1);
    }

    public List<User> getAllUser() {
        List<User> list = userRepository.findAll(Sort.by("id").descending());
        return list;
    }


    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }


    public List<User> getTim(String name, int age, int salary) {
        List<User> list = userRepository.findByNameAndAgeAndSalary(name, age, salary);
        return list;
    }
}
