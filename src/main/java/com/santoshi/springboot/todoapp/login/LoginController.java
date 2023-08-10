package com.santoshi.springboot.todoapp.login;

import com.santoshi.springboot.todoapp.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

    private AuthenticationService authenticationService;
    private Logger logger = LoggerFactory.getLogger(getClass());


    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String gotoLoginPage() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String goToWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap modelMap) {

        if (authenticationService.authenticate(name, password)) {
            modelMap.put("name", name);
            return "welcome";
        }
        modelMap.put("errorMessage", "Invalid Credentials, Please try again!");
        return "login";
    }

}
