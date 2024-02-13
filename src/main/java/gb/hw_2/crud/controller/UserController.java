package gb.hw_2.crud.controller;

import gb.hw_2.crud.model.User;
import gb.hw_2.crud.service.UserService;
import gb.hw_2.crud.sql.QueryTemplates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private QueryTemplates queryTemplates;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        log.info("Получение списка пользователей");
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        log.info("Создание пользователя");
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser (User user) {
        log.info("Создание пользователя");
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        log.info("Удаление пользователя по id");
//        jdbcTemplate.update(queryTemplates.getDeleteUserById(), id);
        userService.deleteById(id);
        return "redirect:/users";
    }

    @PostMapping("/user-delete")
    public String deleteUserPost(int id) {
        log.info("Удаление пользователя");
//        jdbcTemplate.update(queryTemplates.getDeleteUserById(), id);
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        log.info("Обновление данных пользователя по id");
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        log.info("Обновление данных пользователя");
        userService.updateUser(user);
        return "redirect:/users";
    }

    @PostMapping("/deleteAll")
    public String deleteAll() {
        log.info("Удаление всех пользователей");
        userService.deleteAll();
        return "redirect:/users";
    }

}
