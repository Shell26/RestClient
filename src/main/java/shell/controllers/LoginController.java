package shell.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication, ModelMap model, HttpServletRequest request) {
        if (authentication != null) {
            return "redirect: /user";
        }
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }
        return "login";
    }

    @GetMapping("/user")
    public String getIndexPage(Authentication authentication, ModelMap model, HttpServletRequest request) {
        if (authentication.getAuthorities().contains("ADMIN")) {
            authentication.toString();
            model.addAttribute("user", authentication.getName());
            return "admin";
        } else {
            return "user";
        }
    }

    @GetMapping("/")
    public String getLoginPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect: /user";
        }
        return "login";
    }

}
