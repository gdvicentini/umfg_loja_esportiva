import controller.VendaController;
import model.*;
import repository.ClienteRepository;
import repository.EstoqueRepository;
import repository.UsuarioRepository;
import repository.VendaRepository;
import service.EstoqueService;
import service.VendaService;

import java.math.BigDecimal;
import java.sql.SQLException;

void main() {
    VendaRepository vendaRepository = new VendaRepository();
    EstoqueRepository estoqueRepository = new EstoqueRepository();
    ClienteRepository clienteRepository = new ClienteRepository();
    UsuarioRepository usuarioRepository = new UsuarioRepository();
    EstoqueService estoqueService = new EstoqueService(estoqueRepository);
    VendaService vendaService = new VendaService(vendaRepository, estoqueService);
    VendaController vendaController = new VendaController(vendaService);

    try {
        Optional<Usuario> usuario = usuarioRepository.buscarPorId(1L);
        Optional<Cliente> cliente = clienteRepository.buscarPorId(1L);
        Optional<Estoque> estoque = estoqueRepository.buscarPorProduto(1L);

        if (usuario.isEmpty() || cliente.isEmpty() || estoque.isEmpty()) {
            System.out.println("Dados não encontrados no banco.");
            return;
        }

        vendaController.registrarVenda(cliente.get(), usuario.get(), estoque.get(), 2);

    } catch (SQLException e) {
        System.out.println("Erro ao buscar dados: " + e.getMessage());
    }

//    // monta as dependências manualmente (sem framework de injeção)
//    VendaRepository vendaRepository = new VendaRepository();
//    EstoqueRepository estoqueRepository = new EstoqueRepository();
//    EstoqueService estoqueService = new EstoqueService(estoqueRepository);
//    VendaService vendaService = new VendaService(vendaRepository, estoqueService);
//    VendaController vendaController = new VendaController(vendaService);
//
//    // objetos ainda vindos do banco futuramente — por ora simulados
//    Usuario usuario = new Usuario(1L, "Admin", "admin", "123", true);
//    Cliente cliente = new Cliente(1L, "João", "9999-9999");
//    Produto produto = new Produto(1L, "Bola", "Bola futebol", new BigDecimal("100.00"));
//    Estoque estoque = new Estoque(1L, produto, 10);
//
//    vendaController.registrarVenda(cliente, usuario, estoque, 2);
}
