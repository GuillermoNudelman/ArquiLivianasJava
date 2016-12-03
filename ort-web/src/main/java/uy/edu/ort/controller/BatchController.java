package uy.edu.ort.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uy.edu.ort.batch.model.*;
import uy.edu.ort.batch.model.Package;
import uy.edu.ort.exceptions.ReferenciaNoEncontradaException;
import uy.edu.ort.model.Camioneta;
import uy.edu.ort.model.Chofer;
import uy.edu.ort.model.Cliente;
import uy.edu.ort.model.Convenio;
import uy.edu.ort.model.Entrega;
import uy.edu.ort.model.Paquete;
import uy.edu.ort.service.CamionetaService;
import uy.edu.ort.service.ChoferService;
import uy.edu.ort.service.ClienteService;
import uy.edu.ort.service.ConvenioService;
import uy.edu.ort.service.EntregaService;
import uy.edu.ort.service.PaqueteService;

@Controller
@RequestMapping("/batch")
public class BatchController {

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

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String post(@RequestBody Batch batch) throws ParseException {
        String resultado = "Se realizó la inserccion con exito.";
        List<Van> vans = batch.getVans();
        if (vans != null) {
            for (Van v : vans) {
                Camioneta cam = transformVanToCamioneta(v);
                camionetaService.addCamioneta(cam);
            }
        }
        List<Driver> drivers = batch.getDrivers();
        if (drivers != null) {
            for (Driver drv : drivers) {
                Chofer cho = transformDriverToChofer(drv);
                choferService.addChofer(cho);
            }
        }
        List<Client> client = batch.getClients();
        if (client != null) {
            for (Client cli : client) {
                Cliente cliente = transformClientToCliente(cli);
                clienteService.addCliente(cliente);
            }
        }
        List<Package> packages = batch.getPackages();
        if (packages != null) {
            for (Package pck : packages) {
                Paquete pqt = transformPackageToPaquete(pck);
                paqueteService.addPaquete(pqt);
            }
        }
        List<Agreement> agreements = batch.getAgreements();
        if (agreements != null) {
            for (Agreement agree : agreements) {
                Convenio con = transformAgreementToConvenio(agree);
                convenioService.addConvenio(con);
            }
        }
        List<Delivery> deliveries = batch.getDeliveries();
        if (deliveries != null) {
            try {
                for (Delivery del : deliveries) {
                    Entrega entr = transformDeliverToEntrega(del);
                    entregaService.addEntrega(entr);
                }
            } catch (ReferenciaNoEncontradaException rne) {
                if (rne.getMessage() == "paquete") {
                    resultado = "El listado de id de paquetes ingresados es incorrecto (al menos uno de ellos).";
                } else if (rne.getMessage() == "camioneta") {
                    resultado = "La camioneta seleccionada no puede realizar la entrega.";
                } else if (rne.getMessage() == "chofer") {
                    resultado = "El chofer seleccionado no puede realizar la entrega.";
                }
            }
        }
        return resultado;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Batch get() throws ParseException {
        Batch batch = new Batch();

        List<Entrega> entregas = entregaService.listEntrega();
        if (!entregas.isEmpty()) {
            List<Delivery> deliveries = new ArrayList<Delivery>();
            for (Entrega e : entregas) {
                Delivery d = transformEntregaToDeliver(e);
                deliveries.add(d);
            }
            batch.setDeliveries(deliveries);
        }

        List<Camioneta> camionetas = camionetaService.listCamioneta();
        if (!camionetas.isEmpty()) {
            List<Van> vans = new ArrayList<Van>();
            for (Camioneta c : camionetas) {
                Van v = this.transformCamionetaToVan(c);
                vans.add(v);
            }
            batch.setVans(vans);
        }

        List<Paquete> paquetes = paqueteService.listPaquetes();
        if (!paquetes.isEmpty()) {
            List<Package> packages = new ArrayList<Package>();
            for (Paquete pqt : paquetes) {
                Package pkg = this.transformPaqueteToPackage(pqt);
                packages.add(pkg);
            }
            batch.setPackages(packages);
        }

        List<Convenio> convenios = convenioService.listConvenio();
        if (!convenios.isEmpty()) {
            List<Agreement> agreements = new ArrayList<Agreement>();
            for (Convenio conv : convenios) {
                Agreement agree = this.transformConvenioToAgreement(conv);
                agreements.add(agree);
            }
            batch.setAgreements(agreements);
        }

        List<Chofer> choferes = choferService.listChofer();
        if (!choferes.isEmpty()) {
            List<Driver> drivers = new ArrayList<Driver>();
            for (Chofer cho : choferes) {
                Driver drv = this.transformDriverToChofer(cho);
                drivers.add(drv);
            }
            batch.setDrivers(drivers);
        }

        List<Cliente> clientes = clienteService.listCliente();
        if (!clientes.isEmpty()) {
            List<Client> clients = new ArrayList<Client>();
            for (Cliente cliente : clientes) {
                Client cli = this.transformClienteToClient(cliente);
                clients.add(cli);
            }
            batch.setClients(clients);
        }

        return batch;
    }

    private Camioneta transformVanToCamioneta(Van van) {
        Camioneta camioneta = new Camioneta();
        camioneta.setCodigo(van.getCode());
        camioneta.setPlaca(van.getPlate());
        camioneta.setCapacidadKgs(Math.round(van.getTransportCapacity()));
        camioneta.setKmsProxService(Math.round(van.getNextServiceKm()));
        camioneta.setKmsRecorridos(Math.round(van.getTraveledKm()));
        return camioneta;
    }

    private Chofer transformDriverToChofer(Driver driver) {
        Chofer chofer = new Chofer();
        chofer.setCodigo(driver.getCode());
        chofer.setLibretaDeConducir(driver.getDrivingLicense());
        chofer.setNombre(driver.getName());
        return chofer;
    }

    private Cliente transformClientToCliente(Client client) {
        Cliente cli = new Cliente();
        cli.setNombreContacto(client.getContactName());
        cli.setNombreEmpresa(client.getCompanyName());
        cli.setDireccion(client.getAdrress());
        cli.setTelefono(client.getPhone().toString());
        return cli;
    }

    private Convenio transformAgreementToConvenio(Agreement agree) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Convenio conv = new Convenio();
        conv.setCodigo(agree.getCode());
        Date date = sdf.parse(agree.getCreationDate());
        conv.setFechaCreacion(date);
        Cliente cli = clienteService.buscarClientePorNombreEmpresa(agree.getCompanyName());
        conv.setCliente(cli);
        conv.setImporteActualConvenio((int) Math.round(agree.getCurrentValue()));
        conv.setImporteInicialConvenio((int) Math.round(agree.getInitialValue()));
        return conv;
    }

    private Paquete transformPackageToPaquete(Package pkg) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Paquete pqt = new Paquete();
        pqt.setCodigo(pkg.getCode());
        pqt.setDescripcion(pkg.getDescription());
        pqt.setCosto((int) Math.round(pkg.getCost()));
        Cliente cli = clienteService.buscarClientePorNombreEmpresa(pkg.getCompanyName());
        pqt.setCliente(cli);
        pqt.setPeso((int) Math.round(pkg.getWeight()));
        return pqt;
    }

    private Entrega transformDeliverToEntrega(Delivery dlv) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Entrega entrega = new Entrega();
        entrega.setCodigo(dlv.getCode());
        entrega.setDistanciaRecorrerKm((int) Math.round(dlv.getTravelDistance()));
        Date date = new Date();
        entrega.setFechaEntrega(date);
        Chofer cho = choferService.buscarChoferPorCodigo(dlv.getDriverCode());
        entrega.setChofer(cho);
        Camioneta camioneta = camionetaService.buscarCamioneta(dlv.getVanCode());
        entrega.setCamioneta(camioneta);
        List<String> listaPaquetes = dlv.getPackageCodes();
        List<Paquete> listaPaquetesBD = paqueteService.listPaquetes();
        String paquetes = "";
        for (String p : listaPaquetes) {
            int id = 0;
            for(Paquete pqt : listaPaquetesBD){
                if(p.equals(pqt.getCodigo())){
                    id = pqt.getId();
                }
            }
            if (paquetes == "") {
                paquetes += id;
            } else {
                paquetes += "-" + id;
            }

        }

        entrega.setListaPaquetesString(paquetes);
        return entrega;
    }

    private Van transformCamionetaToVan(Camioneta cam) {
        Van van = new Van();
        van.setCode(cam.getCodigo());
        van.setPlate(van.getPlate());
        van.setTransportCapacity((double) cam.getCapacidadKgs());
        van.setNextServiceKm((double) cam.getKmsProxService());
        van.setTraveledKm((double) cam.getKmsRecorridos());
        return van;
    }

    private Driver transformDriverToChofer(Chofer chofer) {
        Driver driver = new Driver();
        driver.setCode(chofer.getCodigo());
        driver.setDrivingLicense(chofer.getLibretaDeConducir());
        driver.setName(chofer.getNombre());
        return driver;
    }

    private Client transformClienteToClient(Cliente cliente) {
        Client cli = new Client();
        cli.setContactName(cliente.getNombreContacto());
        cli.setCompanyName(cliente.getNombreEmpresa());
        cli.setAdrress(cliente.getDireccion());
        cli.setPhone(Long.parseLong(cliente.getTelefono()));
        return cli;
    }

    private Agreement transformConvenioToAgreement(Convenio conv) throws ParseException {
        Agreement agree = new Agreement();
        agree.setCode(conv.getCodigo());
        agree.setCreationDate(conv.getFechaCreacion().toString());
        agree.setCompanyName(conv.getCliente().getNombreEmpresa());
        agree.setInitialValue((double) conv.getImporteInicialConvenio());
        agree.setCurrentValue((double) conv.getImporteActualConvenio());
        return agree;
    }

    private Package transformPaqueteToPackage(Paquete pqt) {
        Package pkg = new Package();
        pkg.setCode(pqt.getCodigo());
        pkg.setDescription(pqt.getDescripcion());
        pkg.setCost((double) pqt.getCosto());
        pkg.setCompanyName(pqt.getCliente().getNombreEmpresa());
        pkg.setWeight((double) pqt.getPeso());
        pkg.setCreationDate(pqt.getFechaCreacion().toString());
        return pkg;
    }

    private Delivery transformEntregaToDeliver(Entrega entrega) {
        Delivery dlv = new Delivery();
        dlv.setCode(entrega.getCodigo());
        dlv.setTravelDistance((double) entrega.getDistanciaRecorrerKm());
        dlv.setDate(entrega.getFechaEntrega().toString());
        dlv.setDriverCode(entrega.getChofer().getNombre());
        dlv.setVanCode(entrega.getCamioneta().getCodigo());
        List<Paquete> listaPaquetes = paqueteService.listPaquetes();
        List<String> paquetes = new ArrayList<String>();
        for (Paquete p : listaPaquetes) {
            if (p.getEntrega() != null) {
                if (p.getEntrega().getCodigo() == entrega.getCodigo()) {
                    paquetes.add(p.getCodigo());
                }
            }
        }
        dlv.setPackageCodes(paquetes);
        return dlv;
    }

    private Batch createBatch01() {
        Batch batch = new Batch();

        List<Van> vans = new ArrayList<>();
        Van van1 = new Van("van01", "plate01", 100d, 1000d, 10000d);
        vans.add(van1);
        Van van2 = new Van("van02", "plt02", 500d, 9000d, 10000d);
        vans.add(van2);
        batch.setVans(vans);

        List<Driver> drivers = new ArrayList<>();
        Driver driver1 = new Driver("drv01", "name01", "lic01");
        drivers.add(driver1);
        Driver driver2 = new Driver("drv02", "name02", "lic02");
        drivers.add(driver2);
        batch.setDrivers(drivers);

        List<Client> clients = new ArrayList<>();
        Client client1 = new Client("company01", "adrr01", 10001l, "contact01");
        clients.add(client1);
        Client client2 = new Client("company02", "adrr02", 20002l, "contact02");
        clients.add(client2);
        batch.setClients(clients);

        List<Package> packages = new ArrayList<>();
        Package pkg1 = new Package("pkg01", "12-12-2016 13:00:00", "company01", 100d, 10d, "desc01");
        packages.add(pkg1);
        Package pkg2 = new Package("pkg02", "15-12-2016 15:00:00", "company02", 150d, 15d, "desc02");
        packages.add(pkg2);
        batch.setPackages(packages);

        List<Agreement> agreements = new ArrayList<>();
        Agreement agreement1 = new Agreement("agrmt01", "10-12-2016 10:30:00", "company01", 1000d, 1000d);
        agreements.add(agreement1);
        Agreement agreement2 = new Agreement("agrmt02", "12-12-2016 11:30:00", "company01", 1500d, 1500d);
        agreements.add(agreement2);
        Agreement agreement3 = new Agreement("agrmt03", "15-12-2016 09:20:10", "company02", 1700d, 1700d);
        agreements.add(agreement3);
        batch.setAgreements(agreements);

        List<String> codes = new ArrayList<>();
        codes.add("pkg01");
        codes.add("pkg02");
        List<Delivery> deliveries = new ArrayList<>();
        Delivery delivery1 = new Delivery("delivery01", "25-12-2016 00:00:00", "van02", "drv01", codes, 159d);
        deliveries.add(delivery1);
        batch.setDeliveries(deliveries);

        return batch;
    }

    private Batch createBatch02() {
        Batch batch = new Batch();

        List<Van> vans = new ArrayList<>();
        Van van1 = new Van("van01", "plate01", 100d, 1000d, 10000d);
        vans.add(van1);
        batch.setVans(vans);

        List<Driver> drivers = new ArrayList<>();
        Driver driver1 = new Driver("drv01", "name01", "lic01");
        drivers.add(driver1);
        batch.setDrivers(drivers);

        List<Client> clients = new ArrayList<>();
        Client client1 = new Client("company01", "adrr01", 10001l, "contact01");
        clients.add(client1);
        batch.setClients(clients);

        List<Package> packages = new ArrayList<>();
        Package pkg1 = new Package("pkg01", "12-12-2016 13:00:00", "company01", 100d, 10d, "desc01");
        packages.add(pkg1);
        Package pkg2 = new Package("pkg02", "15-12-2016 15:00:00", "company01", 150d, 15d, "desc02");
        packages.add(pkg2);
        batch.setPackages(packages);

        List<String> codes = new ArrayList<>();
        codes.add("pkg01");
        List<Delivery> deliveries = new ArrayList<>();
        Delivery delivery1 = new Delivery("delivery01", "25-12-2016 00:00:00", "van01", "drv01", codes, 159d);
        deliveries.add(delivery1);
        batch.setDeliveries(deliveries);

        return batch;
    }

    private Batch createBatch03() {
        Batch batch = new Batch();

        List<Van> vans = new ArrayList<>();
        Van van1 = new Van("van01", "plate01", 120d, 1750d, 10000d);
        vans.add(van1);
        Van van2 = new Van("van02", "plate02", 200d, 800d, 10000d);
        vans.add(van2);
        Van van3 = new Van("van03", "plate03", 170d, 15000d, 20000d);
        vans.add(van3);
        batch.setVans(vans);

        List<Driver> drivers = new ArrayList<>();
        Driver driver1 = new Driver("drv01", "name01", "lic01");
        drivers.add(driver1);
        Driver driver2 = new Driver("drv02", "name02", "lic02");
        drivers.add(driver2);
        batch.setDrivers(drivers);

        List<Client> clients = new ArrayList<>();
        Client client1 = new Client("company01", "adrr01", 10001l, "contact01");
        clients.add(client1);
        batch.setClients(clients);

        List<Package> packages = new ArrayList<>();
        Package pkg1 = new Package("pkg01", "17-12-2016 13:00:00", "company01", 100d, 10d, "desc01");
        packages.add(pkg1);
        batch.setPackages(packages);

        return batch;
    }
}
