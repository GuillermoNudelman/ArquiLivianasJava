/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.dao;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;
import uy.edu.ort.model.Camioneta;
import uy.edu.ort.service.CamionetaService;

/**
 *
 * @author ptrecca
 */
public class CamionetaDaoHibTemplateImplTest {

    public CamionetaDaoHibTemplateImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addCamioneta method, of class CamionetaDaoHibTemplateImpl.
     */
    @org.junit.Test
    public void testAddCamioneta() {
        System.out.println("addCamioneta");
        Camioneta camioneta = new Camioneta();
        camioneta.setCodigo("codigo");
        camioneta.setCapacidadKgs((long) 1);
        camioneta.setKmsProxService((long) 1);
        camioneta.setPlaca("placa");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
        CamionetaService camionetaService = (CamionetaService) applicationContext.getBean("camionetaService");

        camionetaService.addCamioneta(camioneta);

        Camioneta camioneta2 = camionetaService.buscarCamioneta("codigo");

        assertEquals(camioneta, camioneta2);
    }

    /**
     * Test of removeCamioneta method, of class CamionetaDaoHibTemplateImpl.
     */
    @org.junit.Test
    public void testRemoveCamioneta() {
        System.out.println("removeCamioneta");
        Camioneta camioneta = new Camioneta();
        camioneta.setCodigo("codigo");
        camioneta.setCapacidadKgs((long) 1);
        camioneta.setKmsProxService((long) 1);
        camioneta.setPlaca("placa");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
        CamionetaService camionetaService = (CamionetaService) applicationContext.getBean("camionetaService");
        camionetaService.removeCamioneta(camioneta);
        // TODO review the generated test code and remove the default call to fail.
        Camioneta camioneta2 = camionetaService.buscarCamioneta("codigo");

        assertEquals(null, camioneta2);
    }

    /**
     * Test of listCamionetas method, of class CamionetaDaoHibTemplateImpl.
     */
    @org.junit.Test
    public void testListCamionetas() {
        System.out.println("listCamionetas");
        Camioneta camioneta = new Camioneta();
        camioneta.setCodigo("codigo");
        camioneta.setCapacidadKgs((long) 1);
        camioneta.setKmsProxService((long) 1);
        camioneta.setPlaca("placa");

        List<Camioneta> expResult = new ArrayList<Camioneta>();
        expResult.add(camioneta);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
        CamionetaService camionetaService = (CamionetaService) applicationContext.getBean("camionetaService");
        camionetaService.addCamioneta(camioneta);
        List<Camioneta> result = camionetaService.listCamioneta();
        assertEquals(expResult, result);

    }

    /**
     * Test of buscarCamioneta method, of class CamionetaDaoHibTemplateImpl.
     */
    @org.junit.Test
    public void testBuscarCamioneta() {
        System.out.println("addCamioneta");
        Camioneta camioneta = new Camioneta();
        camioneta.setCodigo("codigo");
        camioneta.setCapacidadKgs((long) 1);
        camioneta.setKmsProxService((long) 1);
        camioneta.setPlaca("placa");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
        CamionetaService camionetaService = (CamionetaService) applicationContext.getBean("camionetaService");

        camionetaService.addCamioneta(camioneta);

        Camioneta camioneta2 = camionetaService.buscarCamioneta("codigo");

        assertEquals(camioneta, camioneta2);
    }

    /**
     * Test of editarCamioneta method, of class CamionetaDaoHibTemplateImpl.
     */
    @org.junit.Test
    public void testEditarCamioneta() {
        System.out.println("addCamioneta");
        Camioneta camioneta = new Camioneta();
        camioneta.setCodigo("codigo");
        camioneta.setCapacidadKgs((long) 1);
        camioneta.setKmsProxService((long) 1);
        camioneta.setPlaca("placa");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
        CamionetaService camionetaService = (CamionetaService) applicationContext.getBean("camionetaService");

        camionetaService.addCamioneta(camioneta);

        Camioneta camioneta2 = camionetaService.buscarCamioneta("codigo");
        camioneta.setCodigo("codigo2");
        camioneta.setCapacidadKgs((long) 2);
        camioneta.setKmsProxService((long) 2);
        camioneta.setPlaca("placa2");
        camionetaService.editarCamioneta(camioneta2);
                
        Camioneta camioneta3 = camionetaService.buscarCamioneta("codigo");

        assertEquals(camioneta3, camioneta2);
    }

}
