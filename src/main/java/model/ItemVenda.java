package model;

import java.math.BigDecimal;

public class ItemVenda {

    private Long id;
    private Venda venda;
    private Produto produto;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;

    public ItemVenda(Venda venda, Produto produto, Integer quantidade) {
        if(quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }

        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
        this.subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
