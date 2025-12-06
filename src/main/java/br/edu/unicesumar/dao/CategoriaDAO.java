package br.edu.unicesumar.dao;

import java.util.List;

import br.edu.unicesumar.exception.DAOException;
import br.edu.unicesumar.model.Categoria;
import jakarta.persistence.TypedQuery;

public class CategoriaDAO extends DAO<Categoria>{

    // Listando todas as categorias
    public List<Categoria> listAll(){
        try {
            TypedQuery<Categoria> query = em.createQuery("SELECT c FROM Categoria c", Categoria.class);

            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Erro ao listar categorias");
        }
    }

    // Listando a categoria pelo identificador
    public Categoria findById(int id){
        try {
            TypedQuery<Categoria> query = em.createQuery("SELECT c FROM Categoria c " + "WHERE c.id = :id", Categoria.class);

            query.setParameter("id", id);

            return query.getSingleResult();      
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar categoria pelo identificador");
        }
    }
}