package br.edu.ifpb.sistemasdistribuidos.controller;

import br.edu.ifpb.sistemasdistribuidos.beans.ResponseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Nath on 11/7/2015.
 */

@Controller
@RequestMapping("/acronym")
public class AcronymController {

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody ResponseBean sayHello(@RequestParam(value="name",
            required=false, defaultValue="Deu pau") String name) {

        ResponseBean response = new ResponseBean();
        response.setStatus(1);
        response.setAcronimo("BA");
        response.setSignificado("Bucetofagos Anonimos");

        return response;
    }

}
