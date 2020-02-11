package io.github.dougllasfps.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClienteController {

    @RequestMapping(value = "/api/clientes/hello/{nome}", method = RequestMethod.GET)
    @ResponseBody
    public String helloCliente( @PathVariable("nome") String nomeCliente ){
        return String.format("Hello %s ", nomeCliente);
    }

}
