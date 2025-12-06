package br.edu.unicesumar.dao;

import java.util.List;

import br.edu.unicesumar.exception.DAOException;
import br.edu.unicesumar.model.Carrinho;
import jakarta.persistence.TypedQuery;

public class CarrinhoDAO extends DAO<Carrinho>{

    // Listando todos os carrinhos
    public List<Carrinho> listAll(){
        try {
            TypedQuery<Carrinho> query = em.createQuery("SELECT c FROM Carrinho c", Carrinho.class);

            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Erro ao listar os carrinhos");
        }
    }

    // Listando o carrinho pelo identificador
    public Carrinho findById(int id){
        try {
            TypedQuery<Carrinho> query = em.createQuery("SELECT c FROM Carrinho c " + "WHERE c.id = :id", Carrinho.class);

            query.setParameter("id", id);
            
            return query.getSingleResult();      
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar o carrinho pelo identificador");
        }
    }
}