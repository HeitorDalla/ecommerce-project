package br.edu.unicesumar.dao;

import java.util.List;

import br.edu.unicesumar.exception.DAOException;
import br.edu.unicesumar.model.Usuario;
import jakarta.persistence.TypedQuery;

public class UsuarioDAO extends DAO<Usuario>{

    // Listando todos os usuarios
    public List<Usuario> listAll(){
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);

            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Erro ao listar usuários");
        }
    }

    // Listando o usuario pelo identificador
    public Usuario findById(int id){
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u " + "WHERE u.id = :id", Usuario.class);

            query.setParameter("id", id);
            
            return query.getSingleResult();      
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar usuário pelo ID");
        }
    }
}