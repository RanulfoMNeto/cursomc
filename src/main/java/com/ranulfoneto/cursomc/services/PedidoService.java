package com.ranulfoneto.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranulfoneto.cursomc.domain.Pedido;
import com.ranulfoneto.cursomc.repositories.PedidoRepository;
import com.ranulfoneto.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository repo;

    public Pedido find(Integer id) throws ObjectNotFoundException {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

}
