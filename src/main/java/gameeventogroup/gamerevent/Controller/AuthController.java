package gameeventogroup.gamerevent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthController {

    @GetMapping("/login")
    public String mostrarLogin(
        @RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "logout", required = false) String logout,
        Model model
    ) {
        model.addAttribute("error", error != null);  
        model.addAttribute("logout", logout != null);  
        return 
        
        "login";  
    }



  

}
