package br.edu.unicesumar.service;

import java.util.List;

import br.edu.unicesumar.dao.CategoriaDAO;

import br.edu.unicesumar.model.Categoria;
import br.edu.unicesumar.exception.BusinessException;
import br.edu.unicesumar.exception.DAOException;

public class CategoriaService {
    private final CategoriaDAO categoriaDAO;

    public CategoriaService () {
        this.categoriaDAO = new CategoriaDAO();
    }

    // Método que vai enviar o objeto para ser salvo no banco de dados
    public void saveCategoria (Categoria categoria) throws BusinessException {
        validarCategoria(categoria);

        try {
            categoriaDAO.save(categoria);
        } catch (DAOException e) {
            throw new BusinessException("Erro ao salvar categoria");
        }
    }

    // Método que vai validar cada campo da categoria
    public void validarCategoria (Categoria categoria) throws BusinessException {
        if (categoria == null) {
            throw new BusinessException("Categoria não pode ser nula.");
        }

        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            throw new BusinessException("Nome da categoria não pode ser nula.");
        }
    }

    // Método para listar todos as Categorias
    public List<Categoria> listAll () throws BusinessException {
        try {
            List<Categoria> list = categoriaDAO.listAll();

            if (list.isEmpty()) {
                throw new BusinessException("Categorias nao encontradas");
            }

            return list;
        } catch (DAOException e) {
            throw new BusinessException("Erro ao listar categorias");
        }
    }

    // Recebe o id do Controller, busca a Categoria no DAO e retorna o resultado ao Controller
    public Categoria findById (int id) throws BusinessException {
        try {
            return categoriaDAO.findById(id);
        } catch (DAOException e) {
            throw new BusinessException("Erro ao buscar categoria pelo ID");
        }
    }
}