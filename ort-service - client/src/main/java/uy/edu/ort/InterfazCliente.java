/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort;

import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import static uy.edu.ort.MainUserService.esFecha;
import static uy.edu.ort.MainUserService.esPositivo;
import static uy.edu.ort.MainUserService.validarOpcion;
import uy.edu.ort.model.Cliente;
import uy.edu.ort.service.ClienteService;

/**
 *
 * @author Guillermo
 */
public final class InterfazCliente {
        public static void ClienteInferfaz(ApplicationContext applicationContext){
        ClienteService clienteService = (ClienteService) applicationContext.getBean("clienteService");
        String[] menu = {"Ingresar Cliente", "Editar Cliente", "Eliminar Cliente", "Listar Clientes", "Volver"};
        int opcion = 0;
        Scanner in = new Scanner(System.in);
        while (opcion != menu.length) {
            //muestra menu
            System.out.println("Menu Cliente");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + " - " + menu[i]);
            }
            opcion = validarOpcion(menu.length);            
            switch (opcion) {
                case 1: {
                    Cliente cliente = new Cliente();
                    System.out.println("Nombre de la Empresa: ");
                    cliente.setNombreEmpresa(in.nextLine());
                    System.out.println("Dirección: ");
                    cliente.setDireccion(in.nextLine());
                    System.out.println("Teléfono: ");
                    cliente.setTelefono((int) esPositivo(in));
                    System.out.println("Nombre de contacto: ");
                    cliente.setNombreContacto(in.nextLine());
                    clienteService.addCliente(cliente);
                    break;
                }
                case 2: {
                    System.out.println("Nombre de la Empresa: ");
                    Cliente cliente = clienteService.buscarClientePorNombreEmpresa(in.nextLine());
                    if(cliente == null){
                        System.out.println("No existe el cliente ingresado.");
                    }else{
                        System.out.println("Ingrese datos a modificar");
                        System.out.println("Dirección: ");
                        cliente.setDireccion(in.nextLine());
                        System.out.println("Teléfono: ");
                        cliente.setTelefono((int) esPositivo(in));
                        System.out.println("Nombre de contacto: ");
                        cliente.setNombreContacto(in.nextLine());       
                        clienteService.removeCliente(cliente);
                        clienteService.addCliente(cliente);
                        //camionetaService.editarCamioneta(camioneta);
                    }
                    break;
                }
                case 3: {                   
                    System.out.println("Ingrese nombre de la empresa: ");
                    Cliente cliente = clienteService.buscarClientePorNombreEmpresa(in.nextLine());
                    if(cliente == null){
                        System.out.println("No existe un cliente con ese nombre de empresa.");
                    }else{
                        clienteService.removeCliente(cliente);
                        System.out.println("El cliente ha sido eliminado con exito.");
                    }
                    break;
                }
                case 4: {
                    List<Cliente> clientes = clienteService.listCliente();
                    for (Cliente c : clientes) {
                        System.out.println("Nombre de la empresa: " + c.getNombreEmpresa());
                        System.out.println("Dirección: " + c.getDireccion());
                        System.out.println("Teléfono: " + c.getTelefono());
                        System.out.println("Nombre de contacto: " + c.getNombreContacto());
                        System.out.println("");
                    }
                    break;
                }
                default :{}
            }
        }
    }
}
