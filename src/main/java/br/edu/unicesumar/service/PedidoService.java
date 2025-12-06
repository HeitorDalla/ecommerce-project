package br.edu.unicesumar.service;

import java.util.List;

import br.edu.unicesumar.dao.PedidoDAO;

import br.edu.unicesumar.exception.BusinessException;
import br.edu.unicesumar.exception.DAOException;
import br.edu.unicesumar.model.ItemPedido;
import br.edu.unicesumar.model.Pedido;

public class PedidoService {
    private PedidoDAO pedidoDAO;
    private UsuarioService usuarioService;
    private ItemPedidoService itemPedidoService;

    public PedidoService () {
        this.pedidoDAO = new PedidoDAO();
        this.usuarioService = new UsuarioService();
        this.itemPedidoService = new ItemPedidoService();
    }

    // Salva o pedido e envia para o DAO armazenar/validar no banco de dados
    public void savePedido (Pedido pedido) throws BusinessException {
        validarPedido(pedido);

        // Se todos as validações forem corretas, ele chama o DAO para salvar no banco de dados\
        try {
            pedidoDAO.save(pedido);
        } catch (DAOException e) {
            throw new BusinessException("Erro ao salvar pedido");
        }
    }

    // Valida o pedido antes de salvar no banco de dados (DAO)
    public void validarPedido (Pedido pedido) throws BusinessException {
        if (pedido == null) {
            throw new BusinessException("Pedido não pode ser nulo.");
        }
        // Apenas verifica se o objeto Usuario não é nulo, não verifica campo algum do objeto
        if (pedido.getUsuario() == null) {
            throw new BusinessException("Usuário do pedido não pode ser nulo.");
        }

        // Chame o UsuarioService para validar o Usuario completo, nao precisa de 'if', pois ja lança excessão
        usuarioService.validarUsuario(pedido.getUsuario());
    }

    // Adiciona o itemPedido ao pedido
    public void addItemPedido (Pedido pedido, ItemPedido itemPedido) throws BusinessException {
        // Validar item antes de adicionar
        if (pedido == null) {
            throw new BusinessException("Pedido não pode ser nulo.");
        }
        
        itemPedidoService.validarItemPedido(itemPedido);
        
        pedido.addItemPedido(itemPedido);
    }

    // Método para listar todos os Pedidos
    public List<Pedido> listAll () throws BusinessException {
        try {
            List<Pedido> list = pedidoDAO.listAll();

            if (list.isEmpty()) {
                throw new BusinessException("Lista de pedidos nao encontrada");
            }

            return list;
        } catch (DAOException e) {
            throw new BusinessException("Erro ao listar pedidos");
        }
    }

    // Recebe o id do Controller, busca o Pedido no DAO e retorna o resultado ao Controller
    public Pedido findById (int id) throws BusinessException {
        try {
            return pedidoDAO.findById(id);
        } catch (DAOException e) {
            throw new BusinessException("Erro ao buscar pedido pelo ID");
        }
    }
}