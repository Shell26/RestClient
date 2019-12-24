package shell.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import shell.model.Role;
import shell.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RestTemplateUser {

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public RestTemplateUser(RestTemplate restTemplate, @Value("${application.server.url}") String serverUrl) {
        this.restTemplate = restTemplate;
        this.serverUrl = serverUrl;
    }


    public List<User> getUsersTable() {
        return restTemplate.getForObject(serverUrl + "/rest/users", List.class);
    }


    public void saveUsers(User user, String role) {
        Set<Role> roleSet = new HashSet<>();
        Role roleUser = new Role();

        roleUser.setRole(role);
        roleSet.add(roleUser);
        user.setRoles(roleSet);
        restTemplate.postForObject(serverUrl + "/rest/admin/save", user, Void.class);
    }

    public void deleteUser(Long userId) {
        restTemplate.delete(serverUrl + "/rest/admin/delete/" + userId);
    }


    public void updateUsers(User user, Long userId) {
        restTemplate.postForObject(serverUrl + "/rest/admin/edit/" + userId, user, Void.class);
    }
}
