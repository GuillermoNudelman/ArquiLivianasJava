package uy.edu.ort.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uy.edu.ort.model.Cliente;
import uy.edu.ort.model.Convenio;
import uy.edu.ort.model.Paquete;
import uy.edu.ort.service.ClienteService;
import uy.edu.ort.service.ConvenioService;
import uy.edu.ort.service.PaqueteService;
import uy.edu.ort.service.PaqueteService;

/**
 *
 * Esta clase maneja los servicios de los paquete
 */
@Controller
@RequestMapping("/paquete")
public class PaqueteController {

    @Autowired
    private PaqueteService paqueteService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ConvenioService convenioService;

    @RequestMapping(value = "/listadoPaquetes", method = RequestMethod.GET)
    public String lista(Paquete paquete, BindingResult result, Model model) {
        List<Paquete> paquetes = paqueteService.listPaquetes();
        model.addAttribute("paquetes", paquetes);
        return "paquete/listadoPaquetes";
    }

    @RequestMapping(value = "/formularioNuevoPaquete", method = RequestMethod.GET)
    public String paqueteForm(Model model) {
        Paquete paquete = new Paquete();
        model.addAttribute(paquete);

        List<Cliente> clientes = clienteService.listCliente();
        model.addAttribute("clientes", clientes);

        return "paquete/formularioNuevoPaquete";
    }

    @RequestMapping(value = "/paqueteAgregado", method = RequestMethod.POST)
    public String agregar(@RequestParam("idCliente") int idCliente, Paquete paquete, BindingResult result, Model model) {
        Long idCl = Long.valueOf(idCliente);
        Cliente c = clienteService.buscarClientePorId(idCl);
        if (c != null) {
            if (!paquete.getCodigo().equals("")) {
                paquete.setCliente(c);
                Date fechaCreacion = obtenerFecha(paquete.getFechaCreacionString());
                paquete.setFechaCreacion(fechaCreacion);
                this.paqueteService.addPaquete(paquete);
                return "paquete/vistaPreviaPaquetes";
            } else {

                List<Cliente> clientes = clienteService.listCliente();
                model.addAttribute("clientes", clientes);

                result.reject("", "El codigo no puede ser vacio");
                return "paquete/formularioNuevoPaquete";
            }
        } else {

            List<Cliente> clientes = clienteService.listCliente();
            model.addAttribute("clientes", clientes);

            result.reject("", "El id del cliente es incorrecto");
            return "paquete/formularioNuevoPaquete";
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
    public String editar(@RequestParam("idPaquete") Long idPaquete, Model model) {
        Paquete paquete = this.paqueteService.buscarPaquetePorId(idPaquete);
        model.addAttribute(paquete);

        List<Cliente> clientes = clienteService.listCliente();
        model.addAttribute("clientes", clientes);

        return "paquete/editarPaquete";
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.POST)
    public String modificar(@RequestParam("idCliente") int idCliente, Paquete paquete, BindingResult result, Model model) {
        Long idCl = Long.valueOf(idCliente);
        Cliente c = clienteService.buscarClientePorId(idCl);
        if (c != null) {
            paquete.setCliente(c);
            Date fechaCreacion = obtenerFecha(paquete.getFechaCreacionString());
            paquete.setFechaCreacion(fechaCreacion);
            this.paqueteService.editarPaquete(paquete);
            List<Paquete> paquetes = paqueteService.listPaquetes();
            model.addAttribute("paquetes", paquetes);
            return "paquete/listadoPaquetes";
        } else {
            result.reject("", "El id del cliente es incorrecto");
            return "paquete/editarPaquete";
        }
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.GET)
    public String eliminar(@RequestParam("idPaquete") Long idPaquete, Model model) {
        Paquete paquete = this.paqueteService.buscarPaquetePorId(idPaquete);
        this.paqueteService.removePaquete(paquete);

        List<Paquete> paquetes = paqueteService.listPaquetes();
        model.addAttribute("paquetes", paquetes);
        return "paquete/listadoPaquetes";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody List<Paquete> paquetes) {
        String resultado = "";
        Boolean correcto = true;
        List<Paquete> paquetesAux = new ArrayList<Paquete>();
        int count = 0;
        for (Paquete pqt : paquetes) {
            if (pqt.getCodigo() != null) {
                int countAux = 0;
                Boolean existe = false;
                for (Paquete pqt2 : paquetes) {
                    if (pqt.getCodigo() == pqt2.getCodigo() && countAux != count) {
                        existe = true;
                    }
                    countAux++;
                }
                if (existe) {
                    correcto = false;
                    resultado += "2 Paquetes tienen el mismo codigo.";
                } else {
                    Cliente cli = pqt.getCliente();
                    if (cli != null) {
                        String nombre = pqt.getCliente().getNombreEmpresa();
                        if (nombre != null) {
                            Cliente clienteAsociado = clienteService.buscarClientePorNombreEmpresa(pqt.getCliente().getNombreEmpresa());
                            if (clienteAsociado != null) {
                                pqt.setCliente(clienteAsociado);
                                List<Convenio> convenios = convenioService.listConvenio();
                                int descuento = 0;
                                int costoDePaquete = pqt.getCosto();
                                if (existeConvenioLibre(convenios, clienteAsociado.getNombreEmpresa())) {
                                    Convenio convenioLibre = obtenerPrimerConvenioLibre(convenios, clienteAsociado.getNombreEmpresa());
                                    convenioLibre.setEstaEnUso(true);
                                    descuento = convenioLibre.getImporteInicialConvenio() - convenioLibre.getImporteActualConvenio();

                                    if (costoDePaquete < descuento) {
                                        descuento = costoDePaquete;
                                    }
                                    pqt.setConvenio(convenioLibre);
                                    int impActual = convenioLibre.getImporteActualConvenio();
                                    convenioLibre.setImporteActualConvenio(descuento + impActual);
                                    convenioService.editarConvenio(convenioLibre);

                                }
                                existe = paqueteService.buscarPaquete(pqt.getCodigo()) != null;
                                if (existe) {
                                    correcto = false;
                                    resultado += " Ya existe un paquete con ese nombre " + pqt.getCodigo();
                                } else {
                                    paquetesAux.add(pqt);
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
                        resultado += "El paquete debe tener un cliente. ";
                    }
                }
            } else {
                resultado += "El codigo puede ser vacio";
                correcto = false;
            }
            count++;
        }

        if (correcto) {
            resultado = "Los paquetes se agregaron correctamente";
            for (Paquete cli : paquetesAux) {
                paqueteService.addPaquete(cli);
            }
        }

        return resultado;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Paquete> list() {
        List<Paquete> lista = paqueteService.listPaquetes();
        for (Paquete paquete : lista) {
            paquete.setEntrega(null);
        }
        return lista;
    }

    public static boolean existeConvenioLibre(List<Convenio> convenios, String nombreEmpresaASociada) {
        boolean convenioEncontrado = false;
        for (Convenio c : convenios) {
            if (!c.isEstaEnUso() && !convenioEncontrado && c.getCliente().getNombreEmpresa().equals(nombreEmpresaASociada)) {
                convenioEncontrado = true;
            }
        }
        return convenioEncontrado;
    }

    public static Convenio obtenerPrimerConvenioLibre(List<Convenio> convenios, String nombreEmpresaASociada) {
        Convenio convenioDisponible = new Convenio();
        boolean convenioEncontrado = false;
        for (Convenio c : convenios) {
            if (!c.isEstaEnUso() && !convenioEncontrado && c.getCliente().getNombreEmpresa().equals(nombreEmpresaASociada)) {
                convenioEncontrado = true;
                convenioDisponible = c;
            }
        }
        return convenioDisponible;
    }

}
