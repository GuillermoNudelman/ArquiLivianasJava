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
import uy.edu.ort.exceptions.ReferenciaNoEncontradaException;
import uy.edu.ort.model.Camioneta;
import uy.edu.ort.model.Chofer;
import uy.edu.ort.model.Cliente;
import uy.edu.ort.model.Convenio;
import uy.edu.ort.model.Entrega;
import uy.edu.ort.model.Paquete;
import uy.edu.ort.service.CamionetaService;
import uy.edu.ort.service.ChoferService;
import uy.edu.ort.service.ConvenioService;
import uy.edu.ort.service.EntregaService;
import uy.edu.ort.service.PaqueteService;

/**
 *
 * Esta clase maneja los servicios de los entrega
 */
@Controller
@RequestMapping("/entrega")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @Autowired
    private CamionetaService camionetaService;

    @Autowired
    private PaqueteService paqueteService;

    @Autowired
    private ConvenioService convenioService;

    @Autowired
    private ChoferService choferService;

    @RequestMapping(value = "/listadoEntregas", method = RequestMethod.GET)
    public String lista(Entrega entrega, BindingResult result, Model model) {
        List<Entrega> entregas = entregaService.listEntrega();
        model.addAttribute("entregas", entregas);
        return "entrega/listadoEntregas";
    }

    @RequestMapping(value = "/formularioNuevaEntrega", method = RequestMethod.GET)
    public String entregaForm(Model model) {
        Entrega entrega = new Entrega();
        Camioneta camioneta = new Camioneta();
        entrega.setCamioneta(camioneta);
        model.addAttribute(entrega);

        List<Camioneta> camionetas = camionetaService.listCamioneta();
        model.addAttribute("camionetas", camionetas);

        List<Chofer> choferes = choferService.listChofer();
        model.addAttribute("choferes", choferes);

        List<Paquete> paquetes = paqueteService.listPaquetes();
        model.addAttribute("paquetes", paquetes);

        return "entrega/formularioNuevaEntrega";
    }

    @RequestMapping(value = "/entregaAgregada", method = RequestMethod.POST)
    public String agregar(Entrega entrega, BindingResult result) {

        //List<Paquete> paquetes = obtenerListadoPaquetes(entrega.getListaPaquetesString());
        try {
            Long idC = Long.valueOf(entrega.getIdCamioneta());
            Camioneta camioneta = camionetaService.buscarCamionetaPorId(idC);
            if (camioneta != null) {
                Long idChofer = Long.valueOf(entrega.getIdChofer());
                Chofer chofer = choferService.buscarChoferPorId(idChofer);
                if (chofer != null) {
                    entrega.setChofer(chofer);
                    if (!entrega.getCodigo().equals("")) {
                        entrega.setCamioneta(camioneta);
                        Date fechaEntrega = obtenerFecha(entrega.getFechaEntregaString());
                        entrega.setFechaEntrega(fechaEntrega);
                        this.entregaService.addEntrega(entrega);
                        return "entrega/vistaPreviaEntregas";
                    } else {
                        result.reject("", "El codigo no puede ser vacio");
                        return "entrega/formularioNuevaEntrega";
                    }
                } else {
                    result.reject("", "El id del chofer es incorrecto");
                    return "entrega/formularioNuevaEntrega";
                }
            } else {
                result.reject("", "El id de la camioneta es incorrecto");
                return "entrega/formularioNuevaEntrega";
            }
        } catch (ReferenciaNoEncontradaException rne) {
            if (rne.getMessage() == "paquete") {
                result.reject("", "El listado de id de paquetes ingresados es incorrecto (al menos uno de ellos).");
                return "entrega/formularioNuevaEntrega";
            }
        }
        return null;
    }
/*
    @RequestMapping(value = "/entregaAgregada", method = RequestMethod.POST)
    public String agregar2(Entrega entrega, BindingResult result) {

        List<Paquete> paquetes = obtenerListadoPaquetes(entrega.getListaPaquetesString());
        if (paquetes != null) {
            Long idC = Long.valueOf(entrega.getIdCamioneta());
            Camioneta camioneta = camionetaService.buscarCamionetaPorId(idC);
            if (camioneta != null) {
                Long idChofer = Long.valueOf(entrega.getIdChofer());
                Chofer chofer = choferService.buscarChoferPorId(idChofer);
                if (chofer != null) {
                    entrega.setChofer(chofer);
                    if (!entrega.getCodigo().equals("")) {
                        entrega.setCamioneta(camioneta);
                        Date fechaEntrega = obtenerFecha(entrega.getFechaEntregaString());
                        entrega.setFechaEntrega(fechaEntrega);
                        this.entregaService.addEntrega(entrega);
                        return "entrega/vistaPreviaEntregas";
                    } else {
                        result.reject("", "El codigo no puede ser vacio");
                        return "entrega/formularioNuevaEntrega";
                    }
                } else {
                    result.reject("", "El id del chofer es incorrecto");
                    return "entrega/formularioNuevaEntrega";
                }
            } else {
                result.reject("", "El id de la camioneta es incorrecto");
                return "entrega/formularioNuevaEntrega";
            }
        } else {
            result.reject("", "El listado de id de paquetes ingresados es incorrecto (al menos uno de ellos).");
            return "entrega/formularioNuevaEntrega";
        }
    }
*/
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

    private List<Paquete> obtenerListadoPaquetes(String listaPaquetesString) {
        try {
            String[] idPaquetes = listaPaquetesString.split("-");
            List<Paquete> paquetes = new ArrayList<Paquete>();
            for (int i = 0; i < idPaquetes.length; i++) {
                Paquete p = paqueteService.buscarPaquetePorId(Long.parseLong(idPaquetes[i]));
                if (p != null) {
                    if (!paquetes.contains(p)) {
                        paquetes.add(p);
                    }
                } else {
                    return null;
                }
            }
            return paquetes;
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody List<Entrega> entregas) {
        String resultado = "";
        Boolean correcto = true;
        List<Entrega> entregasAux = new ArrayList<Entrega>();
        int count = 0;
        for (Entrega entr : entregas) {
            if (entr.getCodigo() != null) {
                int countAux = 0;
                Boolean existe = false;
                for (Entrega entr2 : entregas) {
                    if (entr.getCodigo() == entr2.getCodigo() && countAux != count) {
                        existe = true;
                    }
                    countAux++;
                }
                if (existe) {
                    correcto = false;
                    resultado += "2 Entregas tienen el mismo nombre.";
                } else {
                    List<Paquete> listaPaquetes = tienenCodigoYExistenPaquetes(entr.getListaPaquetes());
                    if (listaPaquetes != null) {
                        entr.setListaPaquetes(listaPaquetes);
                        int pesoTotal = 0;
                        int importeEntrega = 0;
                        for (Paquete p : listaPaquetes) {
                            pesoTotal += p.getPeso();
                            importeEntrega += p.getCosto() - p.getDescuento();
                        }
                        entr.setImporteEntrega(importeEntrega);
                        Camioneta camionetaAsociada = validarCamioneta(entr);
                        if (camionetaAsociada != null) {
                            if (ExisteCamioneta_PesoYDistancia(camionetaAsociada, pesoTotal, entr.getDistanciaRecorrerKm())) {
                                entr.setCamioneta(camionetaAsociada);
                                Chofer choferAsociado = validarChofer(entr);
                                if (choferAsociado != null) {
                                    entr.setChofer(choferAsociado);
                                    existe = entregaService.buscarEntrega(entr.getCodigo()) != null;
                                    if (existe) {
                                        correcto = false;
                                        resultado += " Ya existe un entrega con ese nombre " + entr.getCodigo();
                                    } else {
                                        entregasAux.add(entr);
                                    }

                                } else {
                                    correcto = false;
                                    resultado += "La entrega debe tene un chofer Valido. ";
                                }
                            } else {
                                correcto = false;
                                resultado += "La camioneta no es elegible para realizar la entrega. ";
                            }
                        } else {
                            correcto = false;
                            resultado += "El entrega debe tener una camioneta valida. ";
                        }
                    } else {
                        correcto = false;
                        resultado += "Uno de los paquetes no existe o no tiene codigo ";
                    }
                }
            } else {
                resultado += "El codigo puede ser vacio";
                correcto = false;
            }
            count++;
        }

        if (correcto) {
            resultado = "Los entregas se agregaron correctamente";
            for (Entrega entrega : entregasAux) {
                entregaService.addEntrega(entrega);
                Entrega newEntrega = entregaService.buscarEntrega(entrega.getCodigo());
                asociarPaquetesAEntrega(entrega.getListaPaquetes(), newEntrega);
                agregarKmsACamioneta(entrega.getCamioneta(), entrega.getDistanciaRecorrerKm());
            }
        }

        return resultado;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Entrega> list() {
        List<Entrega> listaEntregas = entregaService.listEntrega();
        List<Paquete> listaPaquetes = paqueteService.listPaquetes();
        List<Entrega> listaaux = new ArrayList<Entrega>();
        for (Entrega e : listaEntregas) {
            e.setListaPaquetes(obtenerPaquetesEntrega(listaPaquetes, e));
            listaaux.add(e);
        }
        return listaaux;
    }

    public List<Paquete> obtenerPaquetesEntrega(List<Paquete> listaPaquetes, Entrega entrega) {
        List<Paquete> listaaux = new ArrayList<Paquete>();
        for (Paquete p : listaPaquetes) {
            if (p.getEntrega() != null) {
                if (p.getEntrega().equals(entrega)) {
                    p.setEntrega(null);
                    listaaux.add(p);
                }
            }
        }
        return listaaux;
    }

    public boolean ExisteCamioneta_PesoYDistancia(Camioneta camioneta, int peso, int distancia) {
        boolean existe = false;
        int distanciaTotal = (int) (camioneta.getKmsRecorridos() + distancia);
        if (camioneta.getCapacidadKgs() >= peso && camioneta.getKmsProxService() >= distanciaTotal) {
            existe = true;
        }
        return existe;
    }

    public List<Paquete> tienenCodigoYExistenPaquetes(List<Paquete> lista) {
        List<Paquete> listaReturn = new ArrayList<Paquete>();
        boolean existe = false;
        if (lista != null) {
            for (Paquete pqt : lista) {
                if (pqt.getCodigo() != null) {
                    Paquete paquete = paqueteService.buscarPaquete(pqt.getCodigo());
                    if (paquete != null) {
                        existe = true;
                        listaReturn.add(paquete);
                    } else {
                        existe = false;
                    }
                } else {
                    existe = false;
                }
            }
        }

        if (!existe) {
            listaReturn = null;
        }
        return listaReturn;
    }

    public Camioneta validarCamioneta(Entrega entrega) {
        Camioneta cam = entrega.getCamioneta();
        boolean valido = false;
        if (cam != null) {
            String codigo = cam.getCodigo();
            if (codigo != null) {
                Camioneta camionetaAsociada = camionetaService.buscarCamioneta(codigo);
                if (camionetaAsociada != null) {
                    valido = true;
                    cam = camionetaAsociada;
                }
            }
        }
        if (!valido) {
            cam = null;
        }
        return cam;
    }

    public Chofer validarChofer(Entrega entrega) {
        Chofer cho = entrega.getChofer();
        boolean valido = false;
        if (cho != null) {
            String codigoCho = cho.getCodigo();
            if (codigoCho != null) {
                Chofer choferAsociado = choferService.buscarChoferPorCodigo(codigoCho);
                if (choferAsociado != null) {
                    cho = choferAsociado;
                    valido = true;
                }
            }
        }
        if (!valido) {
            cho = null;
        }
        return cho;
    }

    private void asociarPaquetesAEntrega(List<Paquete> listadoPaquetesAAgregar, Entrega entrega) {
        for (Paquete p : listadoPaquetesAAgregar) {
            p.setEntrega(entrega);
            paqueteService.editarPaquete(p);

            //actualizar convenio del paquete si importes son = los borro, sino los dejo 'sin usar'.
            Convenio c = p.getConvenio();
            if (c != null) {
                c.setEstaEnUso(false);
                if (c.getImporteActualConvenio() == c.getImporteInicialConvenio()) {
                    convenioService.removeConvenio(c);
                } else {
                    convenioService.editarConvenio(c);
                }
            }
        }
    }

    private void agregarKmsACamioneta(Camioneta camionetaAsociado, int distanciaARecorrer) {
        Long kmActuales = camionetaAsociado.getKmsRecorridos();
        camionetaAsociado.setKmsRecorridos(kmActuales + distanciaARecorrer);
        camionetaService.editarCamioneta(camionetaAsociado);
    }
}
