package com.ranulfoneto.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ranulfoneto.cursomc.domain.enums.EstadoPagamento;

import jakarta.persistence.Entity;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
    public static final long serialVersionUID = 1L;
    
    private Integer numeroDeParcelas;

    public PagamentoComCartao() {
        
    }

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

}
