package bad.app.controller;

import bad.app.domain.User;
import bad.app.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager em;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(Model model, @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("error", error);
        return "/login";
    }

    @RequestMapping(value = "/login1", method = RequestMethod.POST)
    public String login1(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        BigInteger count = (BigInteger) em.createNativeQuery("select count(*) from user where username = '" + username + "' and password = '" + password + "'").getSingleResult();
        if (count.compareTo(BigInteger.ONE) == 0) {
            return "redirect:success/1";
        }
        return "redirect:show?error=bad password";
    }

    @RequestMapping(value = "/success/{id}", method = RequestMethod.GET)
    public String success(Model model, @PathVariable("id") String id) {
        User user = (User) em.createNativeQuery("select * from user where id=" + id, User.class).getSingleResult();
        model.addAttribute("user", user);
        return "/success";
    }

}
