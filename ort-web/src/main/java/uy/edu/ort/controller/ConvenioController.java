package uy.edu.ort.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import uy.edu.ort.model.Cliente;
import uy.edu.ort.model.Convenio;
import uy.edu.ort.service.ClienteService;
import uy.edu.ort.service.ConvenioService;

/**
 *
 * @author ptrecca
 */
@Controller
@RequestMapping("/convenio")
public class ConvenioController {

    @Autowired
    private ConvenioService convenioService;

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/agregarConvenios", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody List<Convenio> convenios) {
        String resultado = "";
        Boolean correcto = true;
        List<Convenio> conveniosAux = new ArrayList<Convenio>();
        int count = 0;
        for (Convenio conv : convenios) {
            if (conv.getCodigo() != null) {
                int countAux = 0;
                Boolean existe = false;
                for (Convenio conv2 : convenios) {
                    if (conv.getCodigo() == conv2.getCodigo() && countAux != count) {
                        existe = true;
                    }
                    countAux++;
                }
                if (existe) {
                    correcto = false;
                    resultado += "2 Convenios tienen el mismo nombre.";
                } else {
                    Cliente cli = conv.getCliente();
                    if (cli != null) {
                        String nombre = conv.getCliente().getNombreEmpresa();
                        if (nombre != null) {
                            Cliente clienteAsociado = clienteService.buscarClientePorNombreEmpresa(conv.getCliente().getNombreEmpresa());
                            if (clienteAsociado != null) {
                                conv.setCliente(clienteAsociado);
                                existe = convenioService.buscarConvenio(conv.getCodigo()) != null;
                                if (existe) {
                                    correcto = false;
                                    resultado += " Ya existe un convenio con ese nombre " + conv.getCodigo();
                                } else {
                                    conveniosAux.add(conv);
                                }
                            } else {
                                correcto = false;
                                resultado += "El cliente no existe. ";
                            }
                        } else {
                            correcto = false;
                            resultado += "El cliente debe tener nombre. ";
                        }
                    } else {
                        correcto = false;
                        resultado += "El convenio debe tener un cliente. ";
                    }
                }
            } else {
                resultado += "El codigo puede ser vacio";
                correcto = false;
            }
            count++;
        }

        if (correcto) {
            resultado = "Los convenios se agregaron correctamente";
            for (Convenio cli : conveniosAux) {
                convenioService.addConvenio(cli);
            }
        }

        return resultado;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Convenio> list() {
        return convenioService.listConvenio();
    }

}
