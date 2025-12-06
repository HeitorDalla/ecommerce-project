package br.edu.unicesumar.service;

import br.edu.unicesumar.dao.ItemPedidoDAO;

import br.edu.unicesumar.exception.BusinessException;
import br.edu.unicesumar.exception.DAOException;
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

        try {
            itemPedidoDAO.save(itemPedido);
        } catch (DAOException e) {
            throw new BusinessException("Erro ao salvar item do pedido");
        }
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

    // Listar todos os itens do pedido
    public List<ItemPedido> listAll () throws BusinessException {
        try {
            List<ItemPedido> list = itemPedidoDAO.listAll();

            if (list.isEmpty()) {
                throw new BusinessException("Itens do pedido nao encontrado");
            }

            return list;
        } catch (DAOException e) {
            throw new BusinessException("Erro ao listar itens do pedido");
        }
    }

    // Recebe o id do Controller, busca o ItemPedido no DAO e retorna o resultado ao Controller
    public ItemPedido findById (int id) throws BusinessException {
        try {
            return itemPedidoDAO.findById(id);
        } catch (DAOException e) {
            throw new BusinessException("Erro ao buscar itens do pedido pelo ID");
        }
    }
}