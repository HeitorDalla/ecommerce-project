package br.edu.unicesumar.service;

import java.util.List;

import br.edu.unicesumar.dao.ProdutoDAO;

import br.edu.unicesumar.model.Produto;

import br.edu.unicesumar.exception.BusinessException;
import br.edu.unicesumar.exception.DAOException;

public class ProdutoService {
    private final ProdutoDAO produtoDAO;
    private final CategoriaService categoriaService;

    public ProdutoService () {
        this.produtoDAO = new ProdutoDAO();
        this.categoriaService = new CategoriaService();
    }

    // Salvar o produto no banco de dados
    public void saveProduto (Produto produto) throws BusinessException {
        validarProduto(produto);

        categoriaService.validarCategoria(produto.getCategoria());
        
        try {
            produtoDAO.save(produto);
        } catch (DAOException e) {
            throw new BusinessException("Erro ao salvar produto");
        }
    }

    // Validar cada campo do produto
    public void validarProduto (Produto produto) throws BusinessException {
        if (produto == null) {
            throw new BusinessException("Produto não pode ser nulo.");
        }

        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new BusinessException("Nome do produto não pode ser nulo.");
        }

        if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
            throw new BusinessException("Descrição do produto não pode ser nula.");
        }

        if (produto.getPreco() <= 0) {
            throw new BusinessException("Preco do produto não pode ser negativo.");
        }

        if (produto.getCategoria() == null) {
            throw new BusinessException("Categoria do produto não pode ser nula.");
        }
    }

    // Método para listar todos os Produtos
    public List<Produto> listAll () throws BusinessException {
        try {
            List<Produto> list = produtoDAO.listAll();

            if (list.isEmpty()) {
                throw new BusinessException("Lista de produto nao encontrada");
            }

            return list;
        } catch (DAOException e) {
            throw new BusinessException("Erro ao listar produtos");
        }
    }

    // Recebe o id do Controller, busca o Produto no DAO e retorna o resultado ao Controller
    public Produto findById (int id) throws BusinessException {
        try {
            return produtoDAO.findById(id);
        } catch (DAOException e) {
            throw new BusinessException("Erro ao buscar produto pelo ID");
        }
    }
}