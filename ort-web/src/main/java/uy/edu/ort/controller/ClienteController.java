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
import uy.edu.ort.model.Cliente;
import uy.edu.ort.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/listadoClientes", method = RequestMethod.GET)
    public String lista(Cliente cliente, BindingResult result, Model model) {
        List<Cliente> clientes = clienteService.listCliente();
        model.addAttribute("clientes", clientes);
        return "cliente/listadoClientes";
    }

    @RequestMapping(value = "/formularioNuevoCliente", method = RequestMethod.GET)
    public String clienteForm(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute(cliente);
        return "cliente/formularioNuevoCliente";
    }

    @RequestMapping(value = "/clienteAgregado", method = RequestMethod.POST)
    public String agregar(Cliente cliente, BindingResult result) {
        if (!cliente.getNombreContacto().equals("")) {
            this.clienteService.addCliente(cliente);
            return "cliente/vistaPreviaClientes";
        } else {
            result.reject("", "El nombre de contacto no puede ser vacio");
            return "cliente/formularioNuevoCliente";
        }
    }

    @RequestMapping(value = "/editar", method = RequestMethod.GET)
    public String editar(@RequestParam("idCliente") Long idCliente, Model model) {
        Cliente cliente = this.clienteService.buscarClientePorId(idCliente);
        model.addAttribute(cliente);
        return "cliente/editarCliente";
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.POST)
    public String modificar(Cliente cliente, BindingResult result, Model model) {
        this.clienteService.editarCliente(cliente);
        List<Cliente> clientes = clienteService.listCliente();
        model.addAttribute("clientes", clientes);
        return "cliente/listadoClientes";
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.GET)
    public String eliminar(@RequestParam("idCliente") Long idCliente, Model model) {
        Cliente cliente = this.clienteService.buscarClientePorId(idCliente);
        this.clienteService.removeCliente(cliente);

        List<Cliente> clientes = clienteService.listCliente();
        model.addAttribute("clientes", clientes);
        return "cliente/listadoClientes";
    }

    @RequestMapping(value = "/agregarClientes", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody List<Cliente> clientes) {
        String resultado = "";
        Boolean correcto = true;
        int count = 0;
        for (Cliente cli : clientes) {
            if (cli.getNombreEmpresa() != null) {
                int countAux = 0;
                Boolean existe = false;
                for (Cliente cli2 : clientes) {
                    if (cli.getNombreEmpresa() == cli2.getNombreEmpresa() && countAux != count) {
                        existe = true;
                    }
                    countAux++;
                }
                if (existe) {
                    correcto = false;
                    resultado += "2 Clientes tienen el mismo nombre.";
                } else {
                    existe = clienteService.buscarClientePorNombreEmpresa(cli.getNombreEmpresa()) != null;
                    if (existe) {
                        correcto = false;
                        resultado += " Ya existe un cliente con ese nombre " + cli.getNombreEmpresa();
                    }
                }
            } else {
                resultado += "El nombre de la empresa puede ser vacio";
                correcto = false;
            }
            count++;
        }

        if (correcto) {
            resultado = "Los clientes se agregaron correctamente";
            for (Cliente cli : clientes) {
                clienteService.addCliente(cli);
            }
        }

        return resultado;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Cliente> list() {
        return clienteService.listCliente();
    }
}
