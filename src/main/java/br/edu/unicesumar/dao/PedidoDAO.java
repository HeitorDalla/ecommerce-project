package br.edu.unicesumar.dao;

import java.util.List;

import br.edu.unicesumar.exception.DAOException;
import br.edu.unicesumar.model.Pedido;
import jakarta.persistence.TypedQuery;

public class PedidoDAO extends DAO<Pedido>{

    // Listando todos os pedidos
    public List<Pedido> listAll(){
        try {
            TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p", Pedido.class);

            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Erro ao listar pedidos");
        }
    }

    // Listando pedido pelo identificador
    public Pedido findById(int id){
        try {
            return em.find(Pedido.class, id);
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar pedido pelo ID");
        }
    }
}