package br.edu.unicesumar.service;

import java.util.List;

import br.edu.unicesumar.dao.PedidoDAO;

import br.edu.unicesumar.exception.BusinessException;

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

        // Se todos as validações forem corretas, ele chama o DAO para salvar no banco de dados
        pedidoDAO.save(pedido);
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
        itemPedidoService.validarItemPedido(itemPedido);
        
        pedido.addItemPedido(itemPedido);
    }

    // Recebe o id do Controller, busca o Pedido no DAO e retorna o resultado ao Controller
    public Pedido findById(int id) {
        return pedidoDAO.findById(id);
    }

    // Método para listar todos os Usuários
    public List<Pedido> listAll () {
        return pedidoDAO.listAll();
    }
}