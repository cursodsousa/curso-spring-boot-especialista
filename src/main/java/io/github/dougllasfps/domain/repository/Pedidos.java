package io.github.dougllasfps.domain.repository;

import io.github.dougllasfps.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
