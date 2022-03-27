package edu.escuelaing.arsw.Controllers;

import edu.escuelaing.arsw.model.Monstruo;
import edu.escuelaing.arsw.services.AdventureMapServices;
import edu.escuelaing.arsw.services.persistence.AdventureMapServicesPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Service
@RestController
public class appAPIController {
    @Autowired
    AdventureMapServices services;


    @RequestMapping(value = "/AdventureMap/monstruos", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorgetMonstruos() {
        ArrayList<Monstruo> monstruos = null;
        ResponseEntity<?> mensaje = null;
        try {
            monstruos = services.getMonstruos();
            mensaje = new ResponseEntity<>(monstruos, HttpStatus.ACCEPTED);
        } catch (AdventureMapServicesPersistenceException ex) {
            mensaje = new ResponseEntity<>("No se pudo cargar el arreglo de monstruos",HttpStatus.NOT_FOUND);
        }
        return mensaje;
    }
}
