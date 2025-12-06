package br.edu.unicesumar.service;

import java.util.List;

import br.edu.unicesumar.dao.CarrinhoDAO;
import br.edu.unicesumar.exception.BusinessException;
import br.edu.unicesumar.exception.DAOException;
import br.edu.unicesumar.model.Carrinho;
import br.edu.unicesumar.model.ItemCarrinho;

public class CarrinhoService {
    private CarrinhoDAO carrinhoDAO;

    public CarrinhoService () {
        this.carrinhoDAO = new CarrinhoDAO();
    }

    public void saveCarrinho (Carrinho carrinho) throws BusinessException {
        validarCarrinho(carrinho);

        try {
            carrinhoDAO.save(carrinho);
        } catch (DAOException e) {
            throw new BusinessException("Erro ao salvar carrinho");
        }
    }

    // Método que vai validar o objeto Carrinho e todos os campos de ItemCarrinho
    public void validarCarrinho (Carrinho carrinho) throws BusinessException {
        // Verificar se o carrinho esta vazio, se tiver, nao salva
        if (carrinho == null) {
            throw new BusinessException("Carrinho não pode ser nulo.");
        }

        // Verificar se a lista de itens esta vazia, se tiver, nao salva
        List<ItemCarrinho> itens = carrinho.getItens();
        if (itens == null || itens.isEmpty()) {
            throw new BusinessException("Carrinho deve conter ao menos um item");
        }
        
        // Verificar itens sem produto ou quantidade, se tiver, nao salva
        for (ItemCarrinho item : itens) {
            if (item.getProduto() == null || item.getQuantidade() <= 0) {
                throw new BusinessException("Não é permitido adicionar itens sem produto ou com quantidade inválida.");
            }
        }
    }

    // Método para listar todos os Carrinhos
    public List<Carrinho> listAll () throws BusinessException {
        try {
            List<Carrinho> list = carrinhoDAO.listAll();

            if (list.isEmpty()) {
                throw new BusinessException("Carrinhos nao encontrados");
            }

            return list;
        } catch (DAOException e) {
            throw new BusinessException("Erro ao listar carrinhos");
        }
    }

    // Recebe o id do Controller, busca o carrinho no DAO e retorna o resultado ao Controller
    public Carrinho findById (int id) throws BusinessException {
        try {
            return carrinhoDAO.findById(id);
        } catch (DAOException e) {
            throw new BusinessException("Erro ao buscar carrinho pelo ID");
        }
    }
}