package service;

import model.Estoque;

public class EstoqueService {

    public void validarDisponibilidade(Estoque estoque, int quantidade) {
        if (!estoque.temDisponivel(quantidade)) {
            throw new IllegalArgumentException("model.Estoque insuficiente");
        }
    }

    public void reduzirQuantidade(Estoque estoque, int quantidade) {
        estoque.reduzir(quantidade);
    }
}