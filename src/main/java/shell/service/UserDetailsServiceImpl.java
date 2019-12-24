package shell.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import shell.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final RestTemplate restTemplate;
    private final String serverUrl;

    public UserDetailsServiceImpl(RestTemplate restTemplate, @Value("${application.server.url}") String serverUrl) {
        this.restTemplate = restTemplate;
        this.serverUrl = serverUrl;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return restTemplate.postForObject(serverUrl + "/rest/user", login, User.class);
    }
}