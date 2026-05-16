package repository;

import model.Usuario;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepository {

    public void salvar (Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, login, senha, ativo) VALUES (?, ?, ?, ?)";

        Connection conn = Conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario.getNome());
        stmt.setString(1, usuario.getLogin());
        stmt.setString(1, usuario.getSenha());
        stmt.setBoolean(1, usuario.isAtivo());
        ResultSet rs = stmt.executeQuery();

        rs.next();
    }

    public Usuario buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();

        return mapear(rs);
    }

    private Usuario mapear(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getString("login"),
                rs.getString("senha"),
                rs.getBoolean("ativo")
        );
    }

//    public atualizar() {
//
//    }
//
//    public apagar() {
//
//    }
}
