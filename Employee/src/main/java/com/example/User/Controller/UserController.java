package com.example.User.Controller;


import com.example.User.Entity.MessageResponse;
import com.example.User.Entity.User;
import com.example.User.Repository.UserRepository;
import com.example.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/all")
    public ResponseEntity<List<User>> getListEnabled(){
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping
    public String getUsers(Model model) {
        // Gửi danh sách người dùng sang giao diện
        List<User> users = userService.getAllUser(); // Lấy danh sách người dùng từ service
        model.addAttribute("users", users);

        // Gửi một đối tượng User trống để thêm mới
        model.addAttribute("user", new User());

        // Trả về file giao diện employeeList.html
        return "employeeList";
    }
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "users";
    }
    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userService.update(user); 
        return "redirect:/users";
    }
    @PostMapping("")
    public String searchUsers(@RequestParam(required = false) String name,
                              @RequestParam(required = false) Integer age,
                              @RequestParam(required = false) Integer salary, Model model) {
        List<User> users;


        if (name != null && age != null && salary != null) {
            users = userRepository.findByNameAndAgeAndSalary(name, age, salary);
        } else {
            users = userRepository.findAll();
        }

        model.addAttribute("users", users);
        return "users";
    }
}
