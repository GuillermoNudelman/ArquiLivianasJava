package uy.edu.ort.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uy.edu.ort.model.Camioneta;

@Controller
@RequestMapping("/inicio")
public class InicioController {

    @RequestMapping(value = "/iniciarsesion", method = RequestMethod.GET)
    public String iniciar(@RequestParam("nombreusuario") String nombreUsuario, Model model, HttpSession session) {
        String userName = (String) session.getAttribute("user");
        if (userName != null) {
            System.out.println(userName);
        } else {
            session.setAttribute("user", nombreUsuario);
        }
        return "redirect:/camioneta/listadoCamionetas";
    }
}
