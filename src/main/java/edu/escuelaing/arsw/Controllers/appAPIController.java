package edu.escuelaing.arsw.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
public class appAPIController {
    @Autowired


    @RequestMapping(value = "/monstruos", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorgetMonstruos() {
        ResponseEntity<?> mensaje = null;
        try {

        } catch () {

        }
        return mensaje;
    }
}
