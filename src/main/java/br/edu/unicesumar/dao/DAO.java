package br.edu.unicesumar.dao;

import br.edu.unicesumar.exception.DAOException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DAO<Object> {

    protected EntityManager em;

    // Padrao Singleton
    private static DAO instance;

    protected DAO(){
        em = getEntityManager();
    }

    private EntityManager getEntityManager(){
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("exemplo_unicesumar");
        if(em == null){
            // Padrao Factory Method
            em = emf.createEntityManager();
        }
        return em;
    }

    public static DAO getInstance(){
        if(instance == null){
            instance = new DAO();
        }
        return instance;
    }

    // Insercao
    public void save(Object object){
        try {
            // Iniciando uma transacao
            em.getTransaction().begin();
            // Salva o objeto
            em.persist(object);
            // Fecha a transacao
            em.getTransaction().commit();
        } catch (Exception e) {
            // Fazendo rollback para nao salvar informacoes no banco de dados
            em.getTransaction().rollback();

            throw new DAOException("Erro ao salvar no banco de dados");
        }
    }

    // Atualizacao
    public void update(Object object){
        try {
            // Iniciando uma transacao
            em.getTransaction().begin();
            // Salva o objeto
            em.merge(object);
            // Fecha a transacao
            em.getTransaction().commit();
        } catch (Exception e) {
            // Fazendo rollback para nao salvar informacoes no banco de dados
            em.getTransaction().rollback();

            throw new DAOException("Erro ao atualizar no banco");
        }
    }

    // Delecao
    public void delete(Object object){
        try {
            // Iniciando uma transacao
            em.getTransaction().begin();
            // Salva o objeto
            em.remove(object);
            // Fecha a transacao
            em.getTransaction().commit();
        } catch (Exception e) {
            // Fazendo rollback para nao salvar informacoes no banco de dados
            em.getTransaction().rollback();

            throw new DAOException("Erro ao deletar no banco");
        }
    }   
}