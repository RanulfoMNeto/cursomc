package com.ranulfoneto.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranulfoneto.cursomc.domain.ItemPedido;
import com.ranulfoneto.cursomc.domain.PagamentoComBoleto;
import com.ranulfoneto.cursomc.domain.Pedido;
import com.ranulfoneto.cursomc.domain.enums.EstadoPagamento;
import com.ranulfoneto.cursomc.repositories.ItemPedidoRepository;
import com.ranulfoneto.cursomc.repositories.PagamentoRepository;
import com.ranulfoneto.cursomc.repositories.PedidoRepository;
import com.ranulfoneto.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository repo;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
 	private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    public Pedido find(Integer id) throws ObjectNotFoundException {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    public Pedido insert(Pedido obj) throws ObjectNotFoundException {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        return obj;
    }

}
