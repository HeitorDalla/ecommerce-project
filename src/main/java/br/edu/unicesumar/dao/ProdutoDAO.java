package br.edu.unicesumar.dao;

import java.util.List;

import br.edu.unicesumar.exception.DAOException;
import br.edu.unicesumar.model.Produto;
import jakarta.persistence.TypedQuery;

public class ProdutoDAO extends DAO<Produto>{

    // Listando todos os produtos
    public List<Produto> listAll(){
        try {
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p", Produto.class);

            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Erro ao listar produtos");
        }
    }

    // Listando o produto pelo identificador
    public Produto findById(int id){
        try {
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p " + "WHERE p.id = :id", Produto.class);

            query.setParameter("id", id);

            return query.getSingleResult();      
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar produto pelo ID");
        }
    }
}