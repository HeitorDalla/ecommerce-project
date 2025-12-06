package br.edu.unicesumar.dao;

import java.util.List;

import br.edu.unicesumar.exception.DAOException;
import br.edu.unicesumar.model.ItemPedido;
import jakarta.persistence.TypedQuery;

public class ItemPedidoDAO extends DAO<ItemPedido>{

    // Listando todos os itens do pedido
    public List<ItemPedido> listAll(){
        try {
            TypedQuery<ItemPedido> query = em.createQuery("SELECT ip FROM ItemPedido ip", ItemPedido.class);

            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Erro ao listar itens do pedido");
        }
    }

    // Listando o item do pedido pelo identificador
    public ItemPedido findById(int id){
        try {
            TypedQuery<ItemPedido> query = em.createQuery("SELECT ip FROM ItemPedido ip " + "WHERE ip.id = :id", ItemPedido.class);

            query.setParameter("id", id);

            return query.getSingleResult();      
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar item do pedido pelo ID");
        }
    }
}