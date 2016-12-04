/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.procesamiento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uy.edu.ort.model.Camioneta;
import uy.edu.ort.utilities.ProcesamientoCamionetaService;

@Component
public class ProcessListener {

    @Autowired
    private ProcesamientoCamionetaService procesamientoCamionetaService;

    @Value("${lider}")
    private boolean esLider;

    
    public void processMessage(Message<Camioneta> message) {
        if (esLider) {
            Camioneta camioneta = message.getPayload();
            procesamientoCamionetaService.addProcesamientoCamioneta(camioneta);
        }
    }

}
