package model;

public class
Estoque {

    private Long id;
    private Produto produto;
    private Integer quantidadeDisponivel;

    public Estoque(Long id, Produto produto, Integer quantidadeDisponivel) {
        if ( quantidadeDisponivel < 0) {
            throw new IllegalArgumentException("Quantidade inicial válida");
        }
        this.id = id;
        this.produto = produto;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public boolean temDisponivel(int quantidade) {
        return quantidadeDisponivel >= quantidade;
    }

    public void reduzir(int quantidade) {
        if (!temDisponivel(quantidade)) {
            throw new IllegalArgumentException("model.Estoque insuficiente");
        }
        quantidadeDisponivel -= quantidade;
    }

    public void aumentar(int quantidade) {
        quantidadeDisponivel += quantidade;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public Produto getProduto() {
        return produto;
    }
}
