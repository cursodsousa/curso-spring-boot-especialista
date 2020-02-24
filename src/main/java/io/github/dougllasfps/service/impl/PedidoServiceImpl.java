package io.github.dougllasfps.service.impl;

import io.github.dougllasfps.domain.repository.Pedidos;
import io.github.dougllasfps.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }
}
