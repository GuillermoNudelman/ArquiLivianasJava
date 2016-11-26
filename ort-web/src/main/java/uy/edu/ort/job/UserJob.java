/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author alumnoFI
 */
@Component
public class UserJob {

    @Scheduled(cron = "0/10 * * * * ?")
    public void jobMethod() {
        System.out.println("test method asdasd asdasd asd asd asd asd");
    }
}
