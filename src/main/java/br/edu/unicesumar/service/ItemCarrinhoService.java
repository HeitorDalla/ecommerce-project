package br.edu.unicesumar.service;

import java.util.List;

import br.edu.unicesumar.dao.ItemCarrinhoDAO;

import br.edu.unicesumar.exception.BusinessException;

import br.edu.unicesumar.model.ItemCarrinho;
import br.edu.unicesumar.model.Produto;

public class ItemCarrinhoService {
    private ItemCarrinhoDAO itemCarrinhoDAO;
    private ProdutoService produtoService;

    public ItemCarrinhoService () {
        this.itemCarrinhoDAO = new ItemCarrinhoDAO();
        this.produtoService = new ProdutoService();
    }

    // Método que salvar/validar no banco de dados
    public void saveItemCarrinho (ItemCarrinho itemCarrinho) throws BusinessException {
        validarItemCarrinho(itemCarrinho);

        itemCarrinhoDAO.save(itemCarrinho);
    }

    // Validar o ItemCarrinho antes de salvar no banco de dados
    public void validarItemCarrinho (ItemCarrinho itemCarrinho) throws BusinessException {
        if (itemCarrinho == null) {
            throw new BusinessException("Item do carrinho não pode ser nulo.");
        }

        if (itemCarrinho.getProduto() == null) {
            throw new BusinessException("Produto do item do carrinho é obrigatório.");
        }

        // Verificar se o produto ja existe no banco de dados
        Produto produto = produtoService.findById(itemCarrinho.getProduto().getId());
        if (produto == null) {
            throw new BusinessException("Produto informado não existe no sistema.");
        }

        if (itemCarrinho.getQuantidade() <= 0) {
            throw new BusinessException("Quantidade do item deve maior que zero.");
        }
    }

    // Recebe o id do Controller, busca o ItemCarrinho no DAO e retorna o resultado ao Controller
    public ItemCarrinho findById(int id) {
        return itemCarrinhoDAO.findById(id);
    }

    // Método para listar todos os itens do carrinho
    public List<ItemCarrinho> listAll () {
        return itemCarrinhoDAO.listAll();
    }
}