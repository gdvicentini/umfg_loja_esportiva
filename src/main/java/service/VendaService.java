package service;

import model.*;

public class VendaService {

    private ItemVendaService itemVendaService = new ItemVendaService();
    private EstoqueService estoqueService = new EstoqueService();

    public Venda registrarVenda(Cliente cliente, Usuario usuario) {
        return new Venda(cliente, usuario);
    }

    public void adicionarItemVenda(Venda venda,
                                   Estoque estoque,
                                   int quantidade) {

        // 1. valida estoque
        estoqueService.validarDisponibilidade(estoque, quantidade);

        // 2. reduz estoque
        estoqueService.reduzirQuantidade(estoque, quantidade);

        // 3. cria item
        ItemVenda item = itemVendaService.criarItem(
                venda,
                estoque.getProduto(),
                quantidade
        );

        // 4. adiciona na venda
        venda.adicionarItem(item);
    }
}
