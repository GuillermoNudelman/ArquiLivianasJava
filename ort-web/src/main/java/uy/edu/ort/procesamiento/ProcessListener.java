/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.procesamiento;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import uy.edu.ort.model.Camioneta;


@Component
public class ProcessListener {
    
    public void processMessage(Message<Camioneta> message) {
        Camioneta camioneta = message.getPayload();
        //procesar usuario
        System.out.println("user processed " + camioneta);
    }
    
}
