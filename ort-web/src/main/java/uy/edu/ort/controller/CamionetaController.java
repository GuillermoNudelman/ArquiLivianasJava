package uy.edu.ort.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uy.edu.ort.model.Camioneta;
import uy.edu.ort.service.CamionetaService;

@Controller
@RequestMapping("/camioneta")
public class CamionetaController {

    @Autowired
    private CamionetaService camionetaService;

    @RequestMapping(value = "/listadoCamionetas", method = RequestMethod.GET)
    public String lista(Camioneta camioneta, BindingResult result, Model model) {
        List<Camioneta> camionetas = camionetaService.listCamioneta();
        model.addAttribute("camionetas", camionetas);
        return "camioneta/listadoCamionetas";
    }
    
    @RequestMapping(value = "/listadoCamionetasProxService", method = RequestMethod.GET)
    public String lista(@RequestParam("kmsProxService") int kmsProxService, Camioneta camioneta, BindingResult result, Model model) {
        List<Camioneta> camionetas = camionetaService.listCamioneta();
        List<Camioneta> camionetasNuevo = new ArrayList<Camioneta>();
        for (int i = 0; i < camionetas.size(); i++) {
            if(camionetas.get(i).getKmsProxService()<=kmsProxService) {
                camionetasNuevo.add(camionetas.get(i));
            }
        }
        model.addAttribute("camionetas", camionetasNuevo);
        return "camioneta/listadoCamionetas";
    }

    @RequestMapping(value = "/formularioNuevaCamioneta", method = RequestMethod.GET)
    public String camionetaForm(Model model) {
        Camioneta camioneta = new Camioneta();
        model.addAttribute(camioneta);
        return "camioneta/formularioNuevaCamioneta";
    }

    @RequestMapping(value = "/camionetaAgregada", method = RequestMethod.POST)
    public String agregar(Camioneta camioneta, BindingResult result) {
        if (!camioneta.getCodigo().equals("")) {
            this.camionetaService.addCamioneta(camioneta);
            return "camioneta/vistaPreviaCamionetas";
        } else {
            result.reject("", "El codigo no puede ser vacio");
            return "camioneta/formularioNuevaCamioneta";
        }
    }

    @RequestMapping(value = "/editar", method = RequestMethod.GET)
    public String editar(@RequestParam("idCamioneta") Long idCamioneta, Model model) {
        Camioneta camioneta = this.camionetaService.buscarCamionetaPorId(idCamioneta);
        model.addAttribute(camioneta);
        return "camioneta/editarCamioneta";
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.POST)
    public String modificar(Camioneta camioneta, BindingResult result, Model model) {
        this.camionetaService.editarCamioneta(camioneta);
        List<Camioneta> camionetas = camionetaService.listCamioneta();
        model.addAttribute("camionetas", camionetas);
        return "camioneta/listadoCamionetas";
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.GET)
    public String eliminar(@RequestParam("idCamioneta") Long idCamioneta, Model model) {
        Camioneta camioneta = this.camionetaService.buscarCamionetaPorId(idCamioneta);
        this.camionetaService.removeCamioneta(camioneta);

        List<Camioneta> camionetas = camionetaService.listCamioneta();
        model.addAttribute("camionetas", camionetas);
        return "camioneta/listadoCamionetas";
    }

    @RequestMapping(value = "/agregarCamionetas", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody List<Camioneta> camionetas) {
        String resultado = "";
        Boolean correcto = true;

        for (Camioneta cam : camionetas) {
            if (cam.getCodigo() != null) {
                Boolean existe = camionetaService.buscarCamioneta(cam.getCodigo()) != null;
                if (existe) {
                    correcto = false;
                    resultado += " Ya existe una camioneta con el codigo " + cam.getCodigo();
                }
            } else {
                resultado += "El codigo no puede ser vacio";
                correcto = false;
            }
        }

        if (correcto) {
            resultado = "Las camionetas se agregaron correctamente";
            for (Camioneta cam : camionetas) {
                camionetaService.addCamioneta(cam);
            }
        }

        return resultado;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Camioneta> list() {
        return camionetaService.listCamioneta();
    }
}
