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
}
