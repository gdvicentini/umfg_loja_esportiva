package service;

import model.Estoque;
import repository.EstoqueRepository;

import java.sql.SQLException;

public class EstoqueService {

    private EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public void validarDisponibilidade(Estoque estoque, int quantidade) {
        if (!estoque.temDisponivel(quantidade)) {
            throw new IllegalArgumentException("Estoque insuficiente para o produto: "
                    + estoque.getProduto().getNome());
        }
    }

    public void reduzirQuantidade(Estoque estoque, int quantidade) throws SQLException {
        estoque.reduzir(quantidade);
        estoqueRepository.atualizarQuantidade(
                estoque.getProduto().getId(),
                estoque.getQuantidadeDisponivel()
        );
    }
}