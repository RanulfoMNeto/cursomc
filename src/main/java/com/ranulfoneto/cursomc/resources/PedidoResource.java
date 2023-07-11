package com.ranulfoneto.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ranulfoneto.cursomc.domain.Pedido;
import com.ranulfoneto.cursomc.services.PedidoService;
import com.ranulfoneto.cursomc.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
        Pedido obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
