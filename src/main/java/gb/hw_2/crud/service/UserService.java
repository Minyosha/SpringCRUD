package gb.hw_2.crud.service;

import gb.hw_2.crud.model.User;
import gb.hw_2.crud.repositories.UserRepository;
import gb.hw_2.crud.sql.QueryTemplates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private QueryTemplates queryTemplates;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public List<User> findAll() {
//        return userRepository.findAll();
//    }

    public List<User> findAll() {
        return jdbcTemplate.query(queryTemplates.getFindAllUsers(), new BeanPropertyRowMapper<>(User.class));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.update(user);
    }

//    public void deleteById(int id) {
//        userRepository.delete(id);
//    }

    public void deleteById(int id) {
        jdbcTemplate.update(queryTemplates.getDeleteUserById(), id);
    }

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
