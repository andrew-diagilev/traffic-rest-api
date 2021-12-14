/*package com.traffic.api.services;

import com.traffic.api.DAO.UserDAO;
import com.traffic.api.models.users.Role;
import com.traffic.api.models.users.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User getById(Integer id) {
        return userDAO.findById(id).get();
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public String createUser(String username, String name, String surname, String password, List<Role> roles, String email, String phone, boolean telegram) {
        boolean userExists = userDAO.existsByUsername(username);
        if (!userExists) {
            User newUser = userDAO.save(new User(username, name, surname, passwordEncoder.encode(password), roles, email, phone, telegram));
            return new String("Користувача " + newUser.getUsername() + " " + " успішно створено");
        } else return new String("Помилка. Користувач вже існує");
    }

    public String deleteUser(Integer id) {
        User user;
        Optional<User> userOptional = userDAO.findById(id);
        if (userOptional.isPresent()) {
            user = userOptional.get();
            userDAO.delete(user);
            return new String("Користувача " + user.getUsername() + " " + " успішно видалено");
        } else {
            return new String("Помилка видалення");
        }
    }

    public String editUser(Integer id, String password, List<Role> roles, String email, String phone, boolean telegram) {
        User user;
        Optional<User> userOptional = userDAO.findById(id);
        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(roles);
            user.setPhone(phone);
            user.setEmail(email);
            user.setTelegram(telegram);
            userDAO.save(user);
            return new String("Користувача " + user.getUsername() + " " + " збережено");
        } else return new String("Помилка редагування.");
    }

    public User findByUsername(String username){
        User user = userDAO.findByUsername(username);
        if(user == null){
            log.warn("Користувача {} не знайдено", username);
            return null;
        }
        return user;
    }


}
*/