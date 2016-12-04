/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.job;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uy.edu.ort.model.Camioneta;
import uy.edu.ort.service.CamionetaService;

/**
 *
 * @author alumnoFI
 */
@Component
public class CamionetaJob {

    @Value("${lider}")
    private boolean esLider;

    @Autowired
    private MessageChannel processChannel;

    @Autowired
    private CamionetaService camionetaService;
    

    @Scheduled(cron = "0 0/50 * * * ?")
    public void jobMethod() {
        if (esLider) {
            List<Camioneta> listaCamionetas = camionetaService.listCamioneta();
            for (Camioneta camioneta : listaCamionetas) {
               Message<Camioneta> message = MessageBuilder.withPayload(camioneta).build();
               processChannel.send(message);
            }            
        }
    }
}
