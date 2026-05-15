package controller;

import model.Cliente;
import model.Estoque;
import model.Usuario;
import model.Venda;
import service.VendaService;

import java.sql.SQLException;

public class VendaController {

    private VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    public void registrarVenda(Cliente cliente,
                               Usuario usuario,
                               Estoque estoque,
                               int quantidade) {
        try {
            Venda venda = vendaService.registrarVenda(cliente, usuario);
            vendaService.adicionarItemVenda(venda, estoque, quantidade);
            vendaService.finalizar(venda);

            System.out.println("Venda realizada com sucesso! Total: R$ " + venda.getValorTotal());

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao salvar venda: " + e.getMessage());
        }
    }
}