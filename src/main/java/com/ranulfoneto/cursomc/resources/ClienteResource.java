package com.ranulfoneto.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ranulfoneto.cursomc.domain.Cliente;
import com.ranulfoneto.cursomc.services.ClienteService;
import com.ranulfoneto.cursomc.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "clientes")
public class ClienteResource {
    
    @Autowired
    private ClienteService service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) throws ObjectNotFoundException {
        Cliente obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
    
}
