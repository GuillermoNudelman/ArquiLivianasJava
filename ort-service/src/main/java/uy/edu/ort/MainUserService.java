package uy.edu.ort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uy.edu.ort.InterfazCamioneta;
import uy.edu.ort.model.Cliente;
import uy.edu.ort.service.ClienteService;

import uy.edu.ort.model.Paquete;
import uy.edu.ort.service.PaqueteService;

import uy.edu.ort.model.Camioneta;
import uy.edu.ort.model.Convenio;
import uy.edu.ort.service.CamionetaService;
import uy.edu.ort.model.Entrega;
import uy.edu.ort.service.ConvenioService;
import uy.edu.ort.service.EntregaService;

public class MainUserService {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
       
        //ClienteService clienteService = (ClienteService) applicationContext.getBean("clienteService");
        /*Cliente cliente = new Cliente();
        cliente.setNombreContacto("Nombre1");
        cliente.setNombreEmpresa("NombreEmp1");
        clienteService.addCliente(cliente);

        Cliente cliente2 = new Cliente();
        cliente2.setNombreContacto("Nombre2");
        cliente2.setNombreEmpresa("NombreEmp2");
        clienteService.addCliente(cliente2);

        List<Cliente> clientes = clienteService.listCliente();
        for (Cliente c : clientes) {
            System.out.println(c);
        }
        
        PaqueteService paqueteService = (PaqueteService) applicationContext.getBean("paqueteService");
        Paquete paquete = new Paquete();
        paquete.setCliente(cliente);
        paquete.setCodigo("Cod1g0");
        paquete.setCosto(123);
        paquete.setDescripcion("descripcion");
        paquete.setPeso(321);
        paqueteService.addPaquete(paquete);
        
        List<Paquete> paquetes = paqueteService.listPaquetes();
        for (Paquete p : paquetes) {
            System.out.println(p);
        }*/
        
        PaqueteService paqueteService = (PaqueteService) applicationContext.getBean("paqueteService");
       
                
        System.out.println("Bienvenido a sistema de gestion de Cadetify!");
        String[] menu = {"Manejo Camionetas", "Manejo Convenios", "Terminar"};
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
                    
                     break;
                }
                default :{}    

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
        return verificacion;
    }
     
     public static Date esFecha(String fecha) { 
        Scanner in = new Scanner(System.in);
        boolean fechaCorrecta = false;
        Date date = new Date();
        while(!fechaCorrecta){
            SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
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
}
