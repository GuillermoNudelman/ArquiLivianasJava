package uy.edu.ort;

import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import static uy.edu.ort.MainUserService.esPositivo;
import static uy.edu.ort.MainUserService.validarOpcion;
import uy.edu.ort.model.Camioneta;
import uy.edu.ort.service.CamionetaService;

/**
 *
 * @author ptrecca
 */
public final class InterfazCamioneta {
    
    
    public static void CamionetaInferfaz(ApplicationContext applicationContext){
        
        CamionetaService camionetaService = (CamionetaService) applicationContext.getBean("camionetaService");
         
        String[] menu = {"Ingresar Camioneta","Editar Camionieta","Eliminar Camioneta", "Listar Camionetas",  "Volver"};
        int opcion = 0;
        Scanner in = new Scanner(System.in);
        while (opcion != menu.length) {
            //muestra menu
            System.out.println("Menu Camioneta");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + " - " + menu[i]);
            }
            opcion = validarOpcion(menu.length);            
            switch (opcion) {
                case 1: {
                    Camioneta camioneta = new Camioneta();
                    System.out.println("Codigo :");
                    camioneta.setCodigo(in.nextLine());
                    System.out.println("Placa :");
                    camioneta.setPlaca(in.nextLine());
                    System.out.println("Capacidad :");
                    camioneta.setCapacidadKgs(esPositivo(in));
                    System.out.println("Kilometros Recorridos :");
                    camioneta.setKmsRecorridos(esPositivo(in));
                    System.out.println("kmsRecorridos :");
                    camioneta.setKmsProxService(esPositivo(in));                    
                    camionetaService.addCamioneta(camioneta);
                    
                    break;
                }
                case 2: {                   
                    System.out.println("Codigo :");
                    Camioneta camioneta = camionetaService.buscarCamioneta(in.nextLine());
                    if(camioneta == null){
                        System.out.println("No existe una camioneta con ese codigo.");
                    }else{
                        System.out.println("Placa :");
                        camioneta.setPlaca(in.nextLine());
                        System.out.println("Capacidad :");
                        camioneta.setCapacidadKgs(esPositivo(in));
                        System.out.println("Kilometros Recorridos :");
                        camioneta.setKmsRecorridos(esPositivo(in));
                        System.out.println("kmsRecorridos :");
                        camioneta.setKmsProxService(esPositivo(in));                    
                        camionetaService.addCamioneta(camioneta);
                    }
                    break;
                }
                case 3: {                   
                    System.out.println("Codigo :");
                    Camioneta camioneta = camionetaService.buscarCamioneta(in.nextLine());
                    if(camioneta == null){
                        System.out.println("No existe una camioneta con ese codigo.");
                    }else{
                        camionetaService.removeCamioneta(camioneta);
                        System.out.println("La camioneta ha sido eliminada con exito.");
                    }
                    break;
                }
                case 4: {
                    
                    List<Camioneta> camionetas = camionetaService.listCamioneta();
                    for (Camioneta c : camionetas) {
                        System.out.println(c);
                    }
                    break;
                }
                default :{}    

            }
        }
        
    
    }   
}
