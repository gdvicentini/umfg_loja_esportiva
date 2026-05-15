package repository;

import model.Estoque;
import model.Produto;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class EstoqueRepository {

    public Estoque salvar(Estoque estoque) throws SQLException {
        String sql = "INSERT INTO estoque (id_produto, quantidade_disponivel) VALUES (?, ?) RETURNING id";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, estoque.getProduto().getId());
            stmt.setInt(2, estoque.getQuantidadeDisponivel());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Estoque(rs.getLong("id"), estoque.getProduto(), estoque.getQuantidadeDisponivel());
        }
    }

    public Optional<Estoque> buscarPorProduto(Long idProduto) throws SQLException {
        String sql = """
            SELECT e.id, e.quantidade_disponivel,
                   p.id AS pid, p.nome, p.descricao, p.preco
            FROM estoque e
            JOIN produto p ON p.id = e.id_produto
            WHERE e.id_produto = ?
        """;
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idProduto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Produto produto = new Produto(rs.getLong("pid"), rs.getString("nome"),
                        rs.getString("descricao"), rs.getBigDecimal("preco"));
                return Optional.of(new Estoque(rs.getLong("id"), produto, rs.getInt("quantidade_disponivel")));
            }
            return Optional.empty();
        }
    }

    public void atualizarQuantidade(Long idProduto, int novaQuantidade) throws SQLException {
        String sql = "UPDATE estoque SET quantidade_disponivel = ? WHERE id_produto = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novaQuantidade);
            stmt.setLong(2, idProduto);
            stmt.executeUpdate();
        }
    }
}