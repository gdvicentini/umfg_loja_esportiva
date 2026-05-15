package service;

import model.ItemVenda;
import model.Produto;
import model.Venda;

public class ItemVendaService {

    public ItemVenda criarItem(Venda venda,
                               Produto produto,
                               int quantidade) {

        return new ItemVenda(venda, produto, quantidade);
    }
}