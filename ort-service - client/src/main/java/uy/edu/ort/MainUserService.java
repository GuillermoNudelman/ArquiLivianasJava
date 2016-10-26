package uy.edu.ort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uy.edu.ort.model.Cliente;
import uy.edu.ort.model.Paquete;
import uy.edu.ort.service.PaqueteService;
import uy.edu.ort.model.Camioneta;
import uy.edu.ort.model.Entrega;
import uy.edu.ort.utilities.ServicioBean;

public class MainUserService {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");

        ServicioBean servicioBean = (ServicioBean) applicationContext.getBean("beanService");

        System.out.println("Bienvenido a sistema de gestion de Cadetify!");
        String[] menu = {"Manejo Camionetas", "Manejo Convenios", "Manejo Clientes", "Manejo Paquetes", "Manejo Entregas", "Profiling y Estadística", "Beans del sistems", "Terminar"};
        int opcion = 0;
        Scanner in = new Scanner(System.in);
        while (opcion != menu.length) {
            //muestra menu
            System.out.println("Menu Principal");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + " - " + menu[i]);
            }
            opcion = validarOpcion(menu.length);
            switch (opcion) {
                case 1: {
                    InterfazCamioneta.CamionetaInferfaz(applicationContext);
                    break;
                }
                case 2: {
                    InterfazConvenio.ConvenioInferfaz(applicationContext);
                    break;
                }
                case 3: {
                    InterfazCliente.ClienteInferfaz(applicationContext);
                    break;
                }
                case 4: {
                    InterfazPaquete.PaqueteInferfaz(applicationContext);
                    break;
                }
                case 5: {
                    InterfazEntrega.EntregaInferfaz(applicationContext);
                    break;
                }
                case 6: {
                    InterfazEstadisticas.EstadisticasInferfaz(applicationContext);
                    break;
                }
                case 7: {
                    List<String> listaBean = servicioBean.getBeans();
                    for (String bean : listaBean) {
                        System.out.println(bean);
                    }
                    break;
                }
                default: {
                }
            }
        }
    }

    public static int validarOpcion(int unLargo) {
        Scanner in = new Scanner(System.in);
        int verificacion = 0;
        while (verificacion == 0) {
            try {
                verificacion = in.nextInt();
                while (verificacion < 1 || verificacion > unLargo) {
                    System.out.println("El numero tiene que pertenecer al rango especificado, intente nuevamente");
                    verificacion = in.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println("Error");
                verificacion = 0;
                in.nextLine();
            }
        }
        return verificacion;
    }

    public static long esPositivo(Scanner in) {
        long verificacion = 0;
        while (verificacion == 0) {
            try {
                verificacion = in.nextInt();
                if (verificacion < 1) {
                    System.out.println("Dato no valido, el numero debe ser positivo, intente nuevamente");
                    verificacion = 0;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error");
                verificacion = 0;
                in.nextLine();
            }
        }
        in.nextLine();
        return verificacion;
    }

    public static Date esFecha(String fecha) {
        Scanner in = new Scanner(System.in);
        boolean fechaCorrecta = false;
        Date date = new Date();
        while (!fechaCorrecta) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String dateInString = fecha;
            try {
                date = formatter.parse(dateInString);
                fechaCorrecta = true;
            } catch (ParseException e) {
                System.out.println("error en fecha, reingrese: ");
                fecha = in.nextLine();
            }
        }
        return date;
    }

    public static boolean esUnCliente(String cliente, List<Cliente> listCliente) {
        boolean esCliente = false;
        for (Cliente c : listCliente) {
            System.out.println(c.getNombreContacto());
            if (c.getNombreEmpresa().equals(cliente)) {
                esCliente = true;
            }
        }
        return esCliente;
    }

    public static void ListarClientes(List<Cliente> listCliente) {
        for (Cliente c : listCliente) {
            System.out.println("");
            System.out.println("Nombre de la empresa: " + c.getNombreEmpresa());
        }
    }

    public static void ListarCamionetas(List<Camioneta> listCamionetas) {
        for (Camioneta c : listCamionetas) {
            System.out.println("");
            System.out.println("Código de la camioneta: " + c.getCodigo());
            System.out.println("Placa de la camioneta: " + c.getPlaca());
        }
    }

    public static boolean ExisteCamioneta_PesoYDistancia(List<Camioneta> listCamionetas, int peso, int distancia) {
        boolean existe = false;
        for (Camioneta c : listCamionetas) {
            int distanciaTotal = (int) (c.getKmsRecorridos() + distancia);
            if (c.getCapacidadKgs() >= peso && c.getKmsProxService() >= distanciaTotal) {
                existe = true;
            }
        }
        return existe;
    }

    public static void ListarCamionetas_PesoYDistancia(List<Camioneta> listCamionetas, int peso, int distancia) {
        for (Camioneta c : listCamionetas) {
            int distanciaTotal = (int) (c.getKmsRecorridos() + distancia);
            if (c.getCapacidadKgs() >= peso && c.getKmsProxService() >= distanciaTotal) {
                System.out.println("");
                System.out.println("Código de la camioneta: " + c.getCodigo());
                System.out.println("Placa de la camioneta: " + c.getPlaca());
            }
        }
    }

    public static void ListarPaquetes(List<Paquete> listPaquetes) {
        for (Paquete paquete : listPaquetes) {
            System.out.println("");
            System.out.println("Código: " + paquete.getCodigo());
            System.out.println("Fecha de creación: " + paquete.getFechaCreacion().toString());
            System.out.println("Nombre de la empresa: " + paquete.getCliente().getNombreEmpresa());
            System.out.println("Costo: " + paquete.getCosto());
            System.out.println("Peso: " + paquete.getPeso());
            System.out.println("Descripción: " + paquete.getDescripcion());
            System.out.println("Nombre de la empresa de destino: " + paquete.getCliente().getNombreEmpresa());
        }
    }

    public static void ListarPaquetesSinEntrega(List<Paquete> listPaquetes) {
        for (Paquete paquete : listPaquetes) {
            if (paquete.getEntrega() == null) {
                System.out.println("");
                System.out.println("Código: " + paquete.getCodigo());
                System.out.println("Fecha de creación: " + paquete.getFechaCreacion().toString());
                System.out.println("Nombre de la empresa: " + paquete.getCliente().getNombreEmpresa());
                System.out.println("Costo: " + paquete.getCosto());
                System.out.println("Peso: " + paquete.getPeso());
                System.out.println("Descripción: " + paquete.getDescripcion());
                System.out.println("Nombre de la empresa de destino: " + paquete.getCliente().getNombreEmpresa());
            }
        }
    }

    public static boolean ExistePaqueteSinEntrega(List<Paquete> listPaquetes) {
        boolean existe = false;
        for (Paquete paquete : listPaquetes) {
            if (paquete.getEntrega() == null) {
                existe = true;
            }
        }
        return existe;
    }

    public static void ListarEntregas(List<Entrega> listEntregas, List<Paquete> listPaquetes) {
        for (Entrega entrega : listEntregas) {
            System.out.println("");
            System.out.println("Código: " + entrega.getCodigo());
            System.out.println("Fecha de entrega: " + entrega.getFechaEntrega());
            System.out.println("Código de camioneta: " + entrega.getCamioneta().getCodigo());
            System.out.println("Importe entrega: " + entrega.getImporteEntrega());
            System.out.println("Distancia: " + entrega.getDistanciaRecorrerKm());
            System.out.println("Paquetes: ");
            for (Paquete p : listPaquetes) {
                if (p.getEntrega().getCodigo().equals(entrega.getCodigo())) {
                    System.out.println("    Paquete: " + p.getCodigo());
                }
            }
        }
    }

    public static boolean esUnaCamioneta(String codigoCamioneta, List<Camioneta> listCamioneta) {
        boolean esCamioneta = false;
        for (Camioneta c : listCamioneta) {
            if (c.getCodigo().equals(codigoCamioneta)) {
                esCamioneta = true;
            }
        }
        if (!esCamioneta) {
            System.out.println("El código de camioneta ingresado no está registrado. No se asignará camioneta.");
        }
        return esCamioneta;
    }

    public static boolean esCamionetaUtil(String codigoCamioneta, List<Camioneta> listCamioneta, int peso, int distancia) {
        boolean esCamioneta = false;
        for (Camioneta c : listCamioneta) {
            int distanciaTotal = (int) (c.getKmsRecorridos() + distancia);
            if (c.getCodigo().equals(codigoCamioneta) && c.getCapacidadKgs() >= peso && c.getKmsProxService() >= distanciaTotal) {
                esCamioneta = true;
            }
        }
        if (!esCamioneta) {
            System.out.println("El código de camioneta ingresado no es correcto. No se asignará camioneta.");
        }
        return esCamioneta;
    }

    public static boolean esUnPaquete(String codigoPaquete, List<Paquete> listPaquete) {
        boolean esPaquete = false;
        for (Paquete c : listPaquete) {
            if (c.getCodigo().equals(codigoPaquete)) {
                esPaquete = true;
            }
        }
        if (!esPaquete) {
            System.out.println("El código de paquete ingresado no está registrado. No se asignará Paquete.");
        }
        return esPaquete;
    }
}
