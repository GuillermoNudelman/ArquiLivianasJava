package uy.edu.ort.controller;

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
import uy.edu.ort.model.Chofer;
import uy.edu.ort.service.ChoferService;

@Controller
@RequestMapping("/chofer")
public class ChoferController {

    @Autowired
    private ChoferService choferService;

    @RequestMapping(value = "/listadoChoferes", method = RequestMethod.GET)
    public String lista(Chofer chofer, BindingResult result, Model model) {
        List<Chofer> choferes = choferService.listChofer();
        model.addAttribute("choferes", choferes);
        return "chofer/listadoChoferes";
    }

    @RequestMapping(value = "/formularioNuevoChofer", method = RequestMethod.GET)
    public String choferForm(Model model) {
        Chofer chofer = new Chofer();
        model.addAttribute(chofer);
        return "chofer/formularioNuevoChofer";
    }

    @RequestMapping(value = "/choferAgregado", method = RequestMethod.POST)
    public String agregar(Chofer chofer, BindingResult result) {
        if (!chofer.getCodigo().equals("")) {
            this.choferService.addChofer(chofer);
            return "chofer/vistaPreviaChoferes";
        } else {
            result.reject("", "El nombre de contacto no puede ser vacio");
            return "chofer/formularioNuevoChofer";
        }
    }

    @RequestMapping(value = "/editar", method = RequestMethod.GET)
    public String editar(@RequestParam("idChofer") Long idChofer, Model model) {
        Chofer chofer = this.choferService.buscarChoferPorId(idChofer);
        model.addAttribute(chofer);
        return "chofer/editarChofer";
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.POST)
    public String modificar(Chofer chofer, BindingResult result, Model model) {
        this.choferService.editarChofer(chofer);
        List<Chofer> choferes = choferService.listChofer();
        model.addAttribute("choferes", choferes);
        return "chofer/listadoChoferes";
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.GET)
    public String eliminar(@RequestParam("idChofer") Long idChofer, Model model) {
        Chofer chofer = this.choferService.buscarChoferPorId(idChofer);
        this.choferService.removeChofer(chofer);

        List<Chofer> choferes = choferService.listChofer();
        model.addAttribute("choferes", choferes);
        return "chofer/listadoChoferes";
    }

    @RequestMapping(value = "/agregarChoferes", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody List<Chofer> chofers) {
        String resultado = "";
        Boolean correcto = true;
        int count = 0;
        for (Chofer cho : chofers) {
            if(cho.getCodigo() != null){
                int countAux = 0;
                Boolean existe = false;
                for (Chofer cho2 : chofers) {
                    if(cho.getCodigo()== cho2.getCodigo() && countAux != count){
                        existe = true;
                    }
                    countAux++;
                }
                if(existe){
                    correcto = false;
                    resultado += "2 Choferes tienen el mismo nombre." ;
                }else{                
                    existe = choferService.buscarChoferPorCodigo(cho.getCodigo()) != null;
                    if(existe){
                        correcto = false;
                        resultado += " Ya existe un chofer con ese nombre "+cho.getCodigo() ;
                    }
                }
            }else{
                resultado += "El codigo de chofer puede ser vacio" ;
                correcto = false;
            }
            count++;
        }
        
        if(correcto){
            resultado = "Los chofers se agregaron correctamente";
            for (Chofer cho : chofers) {
                choferService.addChofer(cho);
            }
        }

        return resultado;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Chofer> list() {
        return choferService.listChofer();
    }
}
