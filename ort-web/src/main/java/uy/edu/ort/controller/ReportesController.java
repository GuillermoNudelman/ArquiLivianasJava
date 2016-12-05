package uy.edu.ort.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uy.edu.ort.model.Entrega;
import uy.edu.ort.model.Paquete;
import uy.edu.ort.model.ProcesamientoCamioneta;
import uy.edu.ort.service.EntregaService;
import uy.edu.ort.service.PaqueteService;
import uy.edu.ort.utilities.ProcesamientoCamionetaService;

/**
 *
 * @author ptrecca
 */
@Controller
@RequestMapping("/reportes")
public class ReportesController {

    @Autowired
    private EntregaService entregaService;

    @Autowired
    private PaqueteService paqueteService;

    @Autowired
    private ProcesamientoCamionetaService procesamientoCamionetaService;

    @RequestMapping(value = "/entregasPorMesPDF", method = RequestMethod.GET)
    public String entregasPorMesPDF(@RequestParam("mes") int mes, Model model) {
        List<Entrega> entregas = this.entregaService.listEntregaPorMes(mes);
        generarPDFReporteMes(entregas, paqueteService.listPaquetes(), true);
        model.addAttribute("entregas", entregas);
        return "reportes/EntregasPorMesPantYPDF";
    }

    @RequestMapping(value = "/entregasPorMesYCamioneta", method = RequestMethod.GET)
    public String entregasPorMesYCamioneta(@RequestParam("mes") int mes, @RequestParam("camioneta") String codigoCamiometa, Model model) {
        List<Entrega> entrega = this.entregaService.listEntregaPorMesYCamioneta(mes, codigoCamiometa);
        model.addAttribute(entrega);
        return "reportes/entregasPorMesYCamioneta";
    }

    @RequestMapping(value = "/entregasPorMesYCamionetaPDF", method = RequestMethod.GET)
    public String entregasPorMesYCamionetaPDF(@RequestParam("mes") int mes, @RequestParam("camioneta") String codigoCamiometa, Model model) {
        List<Entrega> entrega = this.entregaService.listEntregaPorMesYCamioneta(mes, codigoCamiometa);
        generarPDFReporteMes(entrega, paqueteService.listPaquetes(), false);
        model.addAttribute(entrega);
        return "reportes/entregasPorMesYCamionetaPDF";
    }

    @RequestMapping(value = "/entregasPorMesYCamionetaYChofer/{mes}/{camioneta}/{chofer}", method = RequestMethod.GET)
    @ResponseBody
    public List<Entrega> entregasPorMesYCamionetaYChofer(@PathVariable("mes") int mes, @PathVariable("chofer") String chofer) {
        List<Entrega> lista = entregaService.listEntregaPorMesCamionetaYChofer(mes, chofer);
        return lista;
    }

    @RequestMapping(value = "/entregasPorMesYCamionetaYChoferPDF/{mes}/{camioneta}/{chofer}", method = RequestMethod.GET)
    @ResponseBody
    public List<Entrega> entregasPorMesYCamionetaYChoferPDF(@PathVariable("mes") int mes, @PathVariable("chofer") String chofer) {
        List<Entrega> entrega = entregaService.listEntregaPorMesCamionetaYChofer(mes, chofer);
        generarPDFReporteMes(entrega, paqueteService.listPaquetes(), false);
        return entrega;
    }

    @RequestMapping(value = "/auditoriaCamionetas", method = RequestMethod.GET)
    @ResponseBody
    public List<ProcesamientoCamioneta> auditoriaCamionetas() {
        List<ProcesamientoCamioneta> auditorias = procesamientoCamionetaService.listaProcesamientoCamioneta();
        return auditorias;
    }

    @RequestMapping(value = "/auditoriaCamionetasPDF", method = RequestMethod.GET)
    @ResponseBody
    public List<ProcesamientoCamioneta> auditoriaCamionetasPDF() {
        List<ProcesamientoCamioneta> auditorias = procesamientoCamionetaService.listaProcesamientoCamioneta();
        if(!auditorias.isEmpty()){
            generarPDFAuditoria(auditorias);
        }
        return auditorias;
    }

    public void generarPDFReporteMes(List<Entrega> listEntregas, List<Paquete> listPaquetes, boolean esReporteMesCamioneta) {
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
                    if (p.getEntrega() != null) {
                        if (p.getEntrega().getCodigo().equals(entrega.getCodigo())) {
                            pesoTotal += p.getPeso();
                        }
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

    private void generarPDFAuditoria(List<ProcesamientoCamioneta> auditorias) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AuditoriaCamionetas.pdf"));
            document.open();
            Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
            Paragraph paragraphOne = new Paragraph();

            paragraphOne = new Paragraph("Auditorias Camionetas", redFont);

            document.add(paragraphOne);

            PdfPTable table = new PdfPTable(5); // 6 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            //Set Column widths
            float[] columnWidths = {1f, 1f, 1f, 1f, 1f};
            table.setWidths(columnWidths);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Código de Camioneta"));
            cell1.setBorderColor(BaseColor.GREEN);
            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Perido procesado"));
            cell2.setBorderColor(BaseColor.GREEN);
            cell2.setPaddingLeft(10);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell3 = new PdfPCell(new Paragraph("Valido"));
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

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
          

            for (ProcesamientoCamioneta pc : auditorias) {
                cell1 = new PdfPCell(new Paragraph(pc.getCamioneta().getCodigo()));
                cell2 = new PdfPCell(new Paragraph(pc.getPeriodo()));
                cell3 = new PdfPCell(new Paragraph(pc.isValido() ? "Si" : "No"));                
                cell4 = new PdfPCell(new Paragraph("" + pc.getPesoTransportado()));
                cell5 = new PdfPCell(new Paragraph("" + pc.getKilometrosRecorridos()));
               
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
             
            }

            document.add(table);
            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
