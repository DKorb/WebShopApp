package WebShopApp.Application.Controller;

import WebShopApp.Application.Entity.Role;
import WebShopApp.Application.Entity.User;
import WebShopApp.Application.Exceptions.UserNotFoundException;
import WebShopApp.Application.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

/*    @GetMapping("/users")
    public String listAll(Model model) {

        List<User> usersList = userService.listAll();
        model.addAttribute("usersList", usersList);

        return "users";
    }*/

    @GetMapping("/users")
    public String listFirstPage(Model model) {
        return listByPage(1, model);
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        User user = new User();
        user.setStatus(true);
        List<Role> listRoles = userService.listAllRoles();

        model.addAttribute("listRoles", listRoles);
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Create New User");

        return "users/users_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "The user has been saved successfully!");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable(name = "id") Integer id,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        try {
            User user = userService.getUser(id);
            List<Role> listRoles = userService.listAllRoles();

            model.addAttribute("user", user);
            model.addAttribute("listRoles", listRoles);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

            return "users/users_form";
        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Integer id,
                             RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("message", "The user ID " + id
                    + " has been deleted successfully");
        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/status/{status}")
    public String changeStatus(@PathVariable("id") Integer id,
                               @PathVariable("status") boolean status,
                               RedirectAttributes redirectAttributes) {
        userService.updateUserStatus(id, status);

        String message = status ? "enabled" : "disabled";
        redirectAttributes.addFlashAttribute("message", "The user ID " + id +
                " has been " + message);
        return "redirect:/users";
    }

    @GetMapping("/users/page/{pageNumber}")
    public String listByPage(@PathVariable(name = "pageNumber") int pageNumber, Model model) {

        Page<User> page = userService.listByPage(pageNumber);
        List<User> usersList = page.getContent();

        long startCount = ((long) (pageNumber - 1) * UserService.USERS_PER_PAGE + 1);
        long endCount = startCount + UserService.USERS_PER_PAGE - 1;

        if (endCount > page.getTotalElements()) {
            page.getTotalElements();
        }

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("usersList", usersList);

        return "users/users";
    }
}