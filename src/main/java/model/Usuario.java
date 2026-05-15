package model;

public class Usuario {

    private Long id;
    private String nome;
    private String login;
    private String senha;
    private boolean ativo;

    public Usuario(Long id, String nome, String login, String senha, boolean ativo) {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Login não pode ser vazio");
        }
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
