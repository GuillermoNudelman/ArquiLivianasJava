package uy.edu.ort.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import uy.edu.ort.model.Chofer;
import uy.edu.ort.service.ChoferService;

/**
 *
 * Esta clase maneja los servicios REST de los choferes
 */
@Controller
@RequestMapping("/chofer")
public class ChoferController {
    
    @Autowired
    private ChoferService choferService;

    @RequestMapping(value = "/agregarChofers", method = RequestMethod.POST)
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
