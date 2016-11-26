package uy.edu.ort.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import uy.edu.ort.model.Convenio;
import uy.edu.ort.service.ClienteService;
import uy.edu.ort.service.ConvenioService;

@Controller
@RequestMapping("/convenio")
public class ConvenioController {

    @Autowired
    private ConvenioService convenioService;

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/listadoConvenios", method = RequestMethod.GET)
    public String lista(Convenio convenio, BindingResult result, Model model) {
        List<Convenio> convenios = convenioService.listConvenio();
        model.addAttribute("convenios", convenios);
        return "convenio/listadoConvenios";
    }

    @RequestMapping(value = "/formularioNuevoConvenio", method = RequestMethod.GET)
    public String convenioForm(Model model) {
        Convenio convenio = new Convenio();
        model.addAttribute(convenio);
        return "convenio/formularioNuevoConvenio";
    }

    @RequestMapping(value = "/convenioAgregado", method = RequestMethod.POST)
    public String agregar(@RequestParam("idCliente") int idCliente, Convenio convenio, BindingResult result) {
        Long idCl = Long.valueOf(idCliente);
        Cliente c = clienteService.buscarClientePorId(idCl);
        if (c != null) {
            if (!convenio.getCodigo().equals("")) {
                convenio.setCliente(c);
                Date fechaCreacion = obtenerFecha(convenio.getFechaCreacionString());
                convenio.setFechaCreacion(fechaCreacion);
                this.convenioService.addConvenio(convenio);
                return "convenio/vistaPreviaConvenios";
            } else {
                result.reject("", "El codigo no puede ser vacio");
                return "convenio/formularioNuevoConvenio";
            }
        } else {
            result.reject("", "El id del cliente es incorrecto");
            return "convenio/formularioNuevoConvenio";
        }
    }

    private Date obtenerFecha(String fechaString) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = df.parse(fechaString);
            String newDateString = df.format(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    @RequestMapping(value = "/editar", method = RequestMethod.GET)
    public String editar(@RequestParam("idConvenio") Long idConvenio, Model model) {
        Convenio convenio = this.convenioService.buscarConvenioPorId(idConvenio);
        model.addAttribute(convenio);
        return "convenio/editarConvenio";
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.POST)
    public String modificar(@RequestParam("idCliente") int idCliente, Convenio convenio, BindingResult result, Model model) {
        Long idCl = Long.valueOf(idCliente);
        Cliente c = clienteService.buscarClientePorId(idCl);
        if (c != null) {
            convenio.setCliente(c);
            Date fechaCreacion = obtenerFecha(convenio.getFechaCreacionString());
            convenio.setFechaCreacion(fechaCreacion);
            this.convenioService.editarConvenio(convenio);
            List<Convenio> convenios = convenioService.listConvenio();
            model.addAttribute("convenios", convenios);
            return "convenio/listadoConvenios";
        } else {
            result.reject("", "El id del cliente es incorrecto");
            return "convenio/editarConvenio";
        }
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.GET)
    public String eliminar(@RequestParam("idConvenio") Long idConvenio, Model model) {
        Convenio convenio = this.convenioService.buscarConvenioPorId(idConvenio);
        this.convenioService.removeConvenio(convenio);

        List<Convenio> convenios = convenioService.listConvenio();
        model.addAttribute("convenios", convenios);
        return "convenio/listadoConvenios";
    }

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
