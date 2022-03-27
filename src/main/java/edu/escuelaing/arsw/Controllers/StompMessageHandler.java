package edu.escuelaing.arsw.Controllers;

import edu.escuelaing.arsw.services.AdventureMapServices;
import edu.escuelaing.arsw.services.persistence.AdventureMapServicesPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class StompMessageHandler {

    @Autowired
    AdventureMapServices services;

    @Autowired
    SimpMessagingTemplate msgt;

    @MessageMapping("/map")
    public void initMap(String prueba) throws AdventureMapServicesPersistenceException {
        System.out.println("SE COMIENZA EL JUEGO");
        try {
            services.iniciarMapa();
        } catch (AdventureMapServicesPersistenceException e) {
            e.printStackTrace();
        }
        msgt.convertAndSend("/map/monstruos", services.getMonstruos());
    }
}
