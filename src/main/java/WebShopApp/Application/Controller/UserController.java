package WebShopApp.Application.Controller;

import WebShopApp.Application.Entity.User;
import WebShopApp.Application.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listAll(Model model) {

        List<User> usersList = userService.listAll();
        model.addAttribute("usersList", usersList);

        return "users";
    }
}