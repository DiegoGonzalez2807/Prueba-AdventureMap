package edu.escuelaing.arsw.Controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class StompMessageHandler {

    @MessageMapping("/newpoint")
    public void proof(){
        System.out.println("PRUENA DE INSERCION DE MESSAGEMAPPING");
    }
}
