package br.edu.unicesumar.service;

import br.edu.unicesumar.dao.ItemPedidoDAO;

import br.edu.unicesumar.exception.BusinessException;

import br.edu.unicesumar.model.ItemPedido;
import br.edu.unicesumar.model.Pedido;

import java.util.List;

public class ItemPedidoService {
    private ItemPedidoDAO itemPedidoDAO;

    public ItemPedidoService () {
        this.itemPedidoDAO = new ItemPedidoDAO();
    }

    // Método que vai salvar o itemPedido dentro do pedido no banco de dados
    public void saveItemPedido (Pedido pedido, ItemPedido itemPedido) throws BusinessException {
        validarItemPedido(itemPedido);

        if (pedido == null) {
            throw new BusinessException("Pedido não pode ser nulo.");
        }

        itemPedidoDAO.save(itemPedido);
    }

    // Método que vai validar o objeto ItemPedido e todos os campos de ItemPedido
    public void validarItemPedido (ItemPedido itemPedido) throws BusinessException {
        if (itemPedido == null) {
            throw new BusinessException("Item do pedido não pode ser nulo.");
        }

        if (itemPedido.getProduto() == null) {
            throw new BusinessException("Produto do item não pode ser nulo.");
        }

        if (itemPedido.getQuantidade() <= 0) {
            throw new BusinessException("Quantidade do item tem que ser maior que zero.");
        }
    }

    // Recebe o id do Controller, busca o ItemPedido no DAO e retorna o resultado ao Controller
    public ItemPedido findById(int id) {
        return itemPedidoDAO.findById(id);
    }

    // Listar todos os itens do pedido
    public List<ItemPedido> listAll () {
        return itemPedidoDAO.listAll();
    }
}