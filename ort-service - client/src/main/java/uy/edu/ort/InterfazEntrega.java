/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort;

import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import static uy.edu.ort.MainClient.ExisteCamioneta_PesoYDistancia;
import static uy.edu.ort.MainClient.ExistePaqueteSinEntrega;
import static uy.edu.ort.MainClient.ListarCamionetas_PesoYDistancia;
import static uy.edu.ort.MainClient.ListarEntregas;
import static uy.edu.ort.MainClient.ListarPaquetes;
import static uy.edu.ort.MainClient.ListarPaquetesSinEntrega;
import static uy.edu.ort.MainClient.esFecha;
import static uy.edu.ort.MainClient.esPositivo;
import static uy.edu.ort.MainClient.esUnPaquete;
import static uy.edu.ort.MainClient.esCamionetaUtil;
import static uy.edu.ort.MainClient.validarOpcion;
import uy.edu.ort.model.Camioneta;
import uy.edu.ort.model.Convenio;
import uy.edu.ort.model.Paquete;
import uy.edu.ort.model.Entrega;
import uy.edu.ort.service.CamionetaService;
import uy.edu.ort.service.ConvenioService;
import uy.edu.ort.service.EntregaService;
import uy.edu.ort.service.PaqueteService;

import com.itextpdf.text.BaseColor;
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
import uy.edu.ort.utilities.UsuarioService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author Guillermo
 */
public final class InterfazEntrega {

    public static void EntregaInferfaz(ApplicationContext applicationContext) {

        EntregaService entregaService = (EntregaService) applicationContext.getBean("entregaService");
        CamionetaService camionetaService = (CamionetaService) applicationContext.getBean("camionetaService");
        PaqueteService paqueteService = (PaqueteService) applicationContext.getBean("paqueteService");
        ConvenioService convenioService = (ConvenioService) applicationContext.getBean("convenioService");

        String[] menu = {"Ingresar Entrega", "Listar Entregas", "Reporte Entrega Por Mes", "Reporte Entrega Por Mes PDF", "Reporte Entrega Por Mes Y Camioneta", "Reporte Entrega Por Mes Y Camioneta PDF", "Volver"};
        int opcion = 0;
        Scanner in = new Scanner(System.in);
        while (opcion != menu.length) {
            //muestra menu
            System.out.println("Menu Entrega");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + " - " + menu[i]);
            }
            opcion = validarOpcion(menu.length);
            switch (opcion) {
                case 1: {
                    List<Paquete> listadoPaquetes = paqueteService.listPaquetes();
                    List<Camioneta> listadoCamionetas = camionetaService.listCamioneta();
                    if (ExistePaqueteSinEntrega(listadoPaquetes)) {
                        if (!listadoCamionetas.isEmpty()) {
                            Entrega entrega = new Entrega();

                            String codigoIngresado = "";
                            boolean codigoLibre = false;
                            System.out.println("Codigo: ");
                            while (!codigoLibre) {
                                codigoIngresado = in.nextLine();
                                if (!codigoIngresado.trim().equals("")) {
                                    codigoLibre = (entregaService.buscarEntrega(codigoIngresado) == null);
                                    if (!codigoLibre) {
                                        System.out.println("Codigo en uso. Reingrese: ");
                                    }
                                } else {
                                    System.out.println("Codigo invalido. Reingrese: ");
                                }
                            }
                            entrega.setCodigo(codigoIngresado);

                            System.out.println("Fecha de entrega (ej. 31-12-2016): ");
                            entrega.setFechaEntrega(esFecha(in.nextLine()));
                            System.out.println("Distancia a recorrer para realizar la entrega: ");
                            int distanciaARecorrer = (int) esPositivo(in);
                            entrega.setDistanciaRecorrerKm(distanciaARecorrer);

                            //logica para solicitar paquetes
                            System.out.println("Paquete/s a incluir en la entrega (Si selecciona mas de uno, debe separarlos con un guión '-'): ");
                            listadoPaquetes = paqueteService.listPaquetes();
                            ListarPaquetesSinEntrega(listadoPaquetes);
                            System.out.println("Ingresar el codigo de paquetes separados por - (ej: pq1-pq2-pq3)");
                            String codigoPaquetes = "";
                            List<Paquete> listadoPaquetesAAgregar = new ArrayList<Paquete>();
                            while (codigoPaquetes == "") {
                                codigoPaquetes = in.nextLine();
                                if (codigoPaquetes == "") {
                                    System.out.println("Debe ingresar al menos un paquete.");
                                }
                                listadoPaquetesAAgregar = agregarPaquetes(codigoPaquetes, listadoPaquetes, paqueteService);
                                if(listadoPaquetesAAgregar.size() != codigoPaquetes.split("-").length){
                                    codigoPaquetes = "";
                                    System.out.println("Alguno de los paquetes ingresados no existen Intente nuevamente.");
                                }
                            }

                            //logica para calcular peso total y monto total
                            int pesoTotal = 0;
                            int importeEntrega = 0;
                            for (Paquete p : listadoPaquetesAAgregar) {
                                pesoTotal += p.getPeso();
                                importeEntrega += p.getCosto() - p.getDescuento();
                            }

                            System.out.println("El importe total de la entrega teniendo en cuenta los costos y descuentos de cada paquete es: " + importeEntrega);
                            entrega.setImporteEntrega(importeEntrega);

                            System.out.println("El peso total de los paquetes es: " + pesoTotal);

                            //logica para solicitar las camionetas que cumplan los requisitos de peso, distancia, etc.
                            if (ExisteCamioneta_PesoYDistancia(listadoCamionetas, pesoTotal, entrega.getDistanciaRecorrerKm())) {

                                System.out.println("Código de la camioneta que realiza la entrega (se muestra un listado con las camionetas que cumplen los requisitos de peso y distancia): ");
                                boolean esCamioneta = false;
                                Camioneta camionetaAsociado = new Camioneta();
                                while (!esCamioneta) {
                                    listadoCamionetas = camionetaService.listCamioneta();
                                    ListarCamionetas_PesoYDistancia(listadoCamionetas, pesoTotal, entrega.getDistanciaRecorrerKm());
                                    String codigoCamioneta = in.nextLine();
                                    esCamioneta = esCamionetaUtil(codigoCamioneta, listadoCamionetas, pesoTotal, entrega.getDistanciaRecorrerKm());
                                    if (esCamioneta) {
                                        camionetaAsociado = camionetaService.buscarCamioneta(codigoCamioneta);
                                        entrega.setCamioneta(camionetaAsociado);
                                    } else {
                                        System.out.println("Código de camioneta incorrecto, reingrese");
                                    }
                                }
                                entregaService.addEntrega(entrega);
                                
                                entrega = entregaService.buscarEntrega(entrega.getCodigo());

                                //actualizar paquetes y camioneta
                                asociarPaquetesAEntrega(listadoPaquetesAAgregar, entrega, paqueteService, convenioService);
                                agregarKmsACamioneta(camionetaAsociado, distanciaARecorrer, camionetaService);
                            } else {
                                System.out.println("No es posible ingresar la entrega ya que no hay camionetas que cumplan los requisitos necesarios.");
                            }
                        } else {
                            System.out.println("No es posible ingresar entregas ya que aun no existen camionetas.");
                        }
                    } else {
                        System.out.println("No es posible ingresar entregas ya que aun no existen paquetes, o todos fueron asignados previamente a una entrega.");
                    }
                    break;
                }
                case 2: {
                    ListarEntregas(entregaService.listEntrega(), paqueteService.listPaquetes());
                    break;
                }
                case 3: {
                    System.out.println("Ingrese un mes (MM)");
                    int mes = (int) esPositivo(in);
                    ListarEntregas(entregaService.listEntregaPorMes(mes), paqueteService.listPaquetes());
                    break;
                }
                case 4: {
                    System.out.println("Ingrese un mes (MM)");
                    int mes = (int) esPositivo(in);
                    generarPDFReporteMes(entregaService.listEntregaPorMes(mes), paqueteService.listPaquetes(), true);
                    break;
                }
                case 5: {
                    System.out.println("Ingrese un mes (MM)");
                    int mes = (int) esPositivo(in);
                    List<Camioneta> listadoCamionetas = camionetaService.listCamioneta();
                    for (Camioneta camioneta : listadoCamionetas) {
                        System.out.println(camioneta);
                    }
                    String codigoCamioneta = in.nextLine();

                    ListarEntregas(entregaService.listEntregaPorMesYCamioneta(mes, codigoCamioneta), paqueteService.listPaquetes());
                    break;
                }
                case 6: {
                    System.out.println("Ingrese un mes (MM)");
                    int mes = (int) esPositivo(in);
                    System.out.println("Seleccione una camioneta.");
                    List<Camioneta> listadoCamionetas = camionetaService.listCamioneta();
                    for (Camioneta camioneta : listadoCamionetas) {
                        System.out.println(camioneta);
                    }
                    String codigoCamioneta = in.nextLine();

                    generarPDFReporteMes(entregaService.listEntregaPorMesYCamioneta(mes, codigoCamioneta), paqueteService.listPaquetes(), false);
                    break;
                }
                default: {
                }
            }
        }
    }

    private static List<Paquete> agregarPaquetes(String codigoPaquetes, List<Paquete> listadoPaquetes, PaqueteService paqueteService) {
        List<Paquete> paquetesAgregar = new ArrayList<Paquete>();
        String[] listaCodigosPaquete = codigoPaquetes.split("-");
        for (int i = 0; i < listaCodigosPaquete.length; i++) {
            String codigoPaquete = listaCodigosPaquete[i];
            boolean esPaquete = esUnPaquete(codigoPaquete, listadoPaquetes);
            if (esPaquete) {
                Paquete paqueteAsociado = paqueteService.buscarPaquete(codigoPaquete);
                paquetesAgregar.add(paqueteAsociado);
            }
        }
        return paquetesAgregar;
    }

    private static void asociarPaquetesAEntrega(List<Paquete> listadoPaquetesAAgregar, Entrega entrega, PaqueteService paqueteService, ConvenioService convenioService) {
        for (Paquete p : listadoPaquetesAAgregar) {
            p.setEntrega(entrega);
            paqueteService.editarPaquete(p);

            //actualizar convenio del paquete si importes son = los borro, sino los dejo 'sin usar'.
            Convenio c = p.getConvenio();
            if(c != null){
                c.setEstaEnUso(false);
                if (c.getImporteActualConvenio() == c.getImporteInicialConvenio()) {
                    convenioService.removeConvenio(c);
                } else {
                    convenioService.editarConvenio(c);
                }
            }
        }
    }

    private static void agregarKmsACamioneta(Camioneta camionetaAsociado, int distanciaARecorrer, CamionetaService camionetaService) {
        Long kmActuales = camionetaAsociado.getKmsRecorridos();
        camionetaAsociado.setKmsRecorridos(kmActuales + distanciaARecorrer);
        camionetaService.editarCamioneta(camionetaAsociado);
    }
    
    public static void generarPDFReporteMes(List<Entrega> listEntregas, List<Paquete> listPaquetes, boolean esReporteMesCamioneta) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Reporte_Entregas_Mes.pdf"));
            document.open();
            Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
            Paragraph paragraphOne = new Paragraph();
            if (esReporteMesCamioneta) {
                paragraphOne = new Paragraph("Reporte de entregas para mes especifico", redFont);
            } else {
                paragraphOne = new Paragraph("Reporte de entregas para mes y camioneta especificos", redFont);
            }

            document.add(paragraphOne);

            PdfPTable table = new PdfPTable(6); // 6 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            //Set Column widths
            float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f};
            table.setWidths(columnWidths);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Código de Entrega"));
            cell1.setBorderColor(BaseColor.GREEN);
            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Fecha de Entrega"));
            cell2.setBorderColor(BaseColor.GREEN);
            cell2.setPaddingLeft(10);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell3 = new PdfPCell(new Paragraph("Código de Camioneta"));
            cell3.setBorderColor(BaseColor.GREEN);
            cell3.setPaddingLeft(10);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell4 = new PdfPCell(new Paragraph("Peso total"));
            cell4.setBorderColor(BaseColor.GREEN);
            cell4.setPaddingLeft(10);
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell5 = new PdfPCell(new Paragraph("Kilometros recorridos"));
            cell5.setBorderColor(BaseColor.GREEN);
            cell5.setPaddingLeft(10);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell6 = new PdfPCell(new Paragraph("Importe"));
            cell6.setBorderColor(BaseColor.GREEN);
            cell6.setPaddingLeft(10);
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);

            for (Entrega entrega : listEntregas) {
                cell1 = new PdfPCell(new Paragraph(entrega.getCodigo()));
                cell2 = new PdfPCell(new Paragraph(entrega.getFechaEntrega().toString()));
                cell3 = new PdfPCell(new Paragraph(entrega.getCamioneta().getCodigo()));
                int pesoTotal = 0;
                for (Paquete p : listPaquetes) {
                    if (p.getEntrega().getCodigo().equals(entrega.getCodigo())) {
                        pesoTotal += p.getPeso();
                    }
                }
                cell4 = new PdfPCell(new Paragraph("" + pesoTotal));
                cell5 = new PdfPCell(new Paragraph("" + entrega.getCamioneta().getKmsRecorridos()));
                cell6 = new PdfPCell(new Paragraph("" + entrega.getImporteEntrega()));
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
            }

            document.add(table);
            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
