package shell.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shell.model.User;

import java.util.List;

@RestController
public class UserServiceRest {

    @Autowired
    private RestTemplateUser restTemplate;

    @GetMapping(path = "/rest/users")
    public List<User> getUsersTable () {
        return restTemplate.getUsersTable();
    }

    @PostMapping(path = "/rest/admin/save")
    public void saveUsers(User user, String role) {
        restTemplate.saveUsers(user, role);
    }

    @DeleteMapping(path = "/rest/admin/delete/{*}")
    public void deleteUser(@PathVariable("*") Long userId) {
        restTemplate.deleteUser(userId);
    }

    @PostMapping(path = "/rest/admin/edit/{*}")
    public void updateUsers(User user, @PathVariable("*") Long userId) {
        restTemplate.updateUsers(user, userId);
    }
}
