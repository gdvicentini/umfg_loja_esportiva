package repository;

import model.ItemVenda;
import model.Venda;
import util.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class VendaRepository {

    public Venda salvar(Venda venda) throws SQLException {
        String sqlVenda = """
            INSERT INTO venda (id_cliente, id_usuario, data_venda, valor_total)
            VALUES (?, ?, ?, ?) RETURNING id
        """;
        String sqlItem = """
            INSERT INTO item_venda (id_venda, id_produto, quantidade, preco_unitario, subtotal)
            VALUES (?, ?, ?, ?, ?)
        """;
        String sqlEstoque = """
            UPDATE estoque SET quantidade_disponivel = quantidade_disponivel - ?
            WHERE id_produto = ?
        """;

        Connection conn = Conexao.getConnection();
        try {
            conn.setAutoCommit(false);

            PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda);
            stmtVenda.setLong(1, venda.getCliente().getId());
            stmtVenda.setLong(2, venda.getUsuario().getId());
            stmtVenda.setTimestamp(3, Timestamp.valueOf(venda.getData()));
            stmtVenda.setBigDecimal(4, venda.getValorTotal());
            ResultSet rs = stmtVenda.executeQuery();
            rs.next();
            Long idVenda = rs.getLong("id");

            PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
            PreparedStatement stmtEstoque = conn.prepareStatement(sqlEstoque);

            for (ItemVenda item : venda.getItens()) {
                stmtItem.setLong(1, idVenda);
                stmtItem.setLong(2, item.getProduto().getId());
                stmtItem.setInt(3, item.getQuantidade());
                stmtItem.setBigDecimal(4, item.getPrecoUnitario());
                stmtItem.setBigDecimal(5, item.getSubtotal());
                stmtItem.executeUpdate();

                stmtEstoque.setInt(1, item.getQuantidade());
                stmtEstoque.setLong(2, item.getProduto().getId());
                stmtEstoque.executeUpdate();
            }

            conn.commit();
            return venda;

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
    }

    public List<Venda> buscarPorPeriodo(LocalDate inicio, LocalDate fim) throws SQLException {
        // exercício para os alunos — estrutura básica:
        String sql = """
            SELECT * FROM venda
            WHERE data_venda BETWEEN ? AND ?
            ORDER BY data_venda DESC
        """;
        // montar e retornar lista de vendas...
        // deixamos como exercício guiado em sala
        throw new UnsupportedOperationException("Implementar com os alunos");
    }
}