package br.edu.unicesumar.dao;

import java.util.List;

import br.edu.unicesumar.exception.DAOException;
import br.edu.unicesumar.model.ItemCarrinho;
import jakarta.persistence.TypedQuery;

public class ItemCarrinhoDAO extends DAO<ItemCarrinho>{

    // Listando todos os itens do carrinho
    public List<ItemCarrinho> listAll(){
        try {
            TypedQuery<ItemCarrinho> query = em.createQuery("SELECT ic FROM ItemCarrinho ic", ItemCarrinho.class);

            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Erro ao listar itens do carrinho");
        }
    }

    // Listando o item do carrinho pelo identificador
    public ItemCarrinho findById(int id){
        try {
            TypedQuery<ItemCarrinho> query = em.createQuery("SELECT ic FROM ItemCarrinho ic " + "WHERE ic.id = :id", ItemCarrinho.class);

            query.setParameter("id", id);

            return query.getSingleResult();      
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar item do carrinho pedo ID");
        }
    }
}