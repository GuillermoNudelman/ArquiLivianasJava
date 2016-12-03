/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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

    @Scheduled(cron = "0/10 * * * * ?")
    public void jobMethod() {
        System.out.println("test method asdasd asdasd asd asd asd asd");
    }
}
