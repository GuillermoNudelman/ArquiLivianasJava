package uy.edu.ort.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import uy.edu.ort.model.Beans;
import uy.edu.ort.utilities.ServicioBean;

/**
 *
 * @author ptrecca
 */

@Controller
@RequestMapping("/beans")
public class BeanController {
    
    @Autowired
    private ServicioBean servicioBean;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Beans> list() {
        return servicioBean.getBeans();
    }
}
