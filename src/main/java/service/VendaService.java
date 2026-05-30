package service;

import model.*;
import repository.VendaRepository;

import java.sql.SQLException;

public class VendaService {

    private VendaRepository vendaRepository;
    private EstoqueService estoqueService;

    public VendaService(VendaRepository vendaRepository, EstoqueService estoqueService) {
        this.vendaRepository = vendaRepository;
        this.estoqueService = estoqueService;
    }

    public void adicionarItemVenda(Venda venda, Estoque estoque, int quantidade) throws SQLException {
        // 1. valida estoque (regra de negócio — permanece no service)
        estoqueService.validarDisponibilidade(estoque, quantidade);

        // 2. reduz estoque no objeto E persiste no banco
        estoqueService.reduzirQuantidade(estoque, quantidade);

        // 3. cria item e adiciona na venda (em memória)
        ItemVenda item = new ItemVenda(venda, estoque.getProduto(), quantidade);
        venda.adicionarItem(item);
    }

    public Venda finalizar(Venda venda) throws SQLException {
        // persiste a venda e os itens no banco
        return vendaRepository.salvar(venda);
    }
}