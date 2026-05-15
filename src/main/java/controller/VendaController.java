package controller;

import model.Cliente;
import model.Estoque;
import model.Usuario;
import model.Venda;
import service.VendaService;

public class VendaController {

    private VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    public void registrarVenda(Cliente cliente,
                               Usuario usuario,
                               Estoque estoque,
                               int quantidade) {

        Venda venda = vendaService.registrarVenda(cliente, usuario);

        vendaService.adicionarItemVenda(venda, estoque, quantidade);

        System.out.println("model.Venda realizada. Total: " + venda.getValorTotal());
    }
}
