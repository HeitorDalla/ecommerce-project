package br.edu.unicesumar.service;

import java.util.List;

import br.edu.unicesumar.dao.UsuarioDAO;

import br.edu.unicesumar.model.Usuario;

import br.edu.unicesumar.utils.Utils;

import br.edu.unicesumar.exception.BusinessException;
import br.edu.unicesumar.exception.DAOException;;

public class UsuarioService {
    /* Aqui vai ter regras de negócio */
    /* Se algo der errado, ele vai retornar SEM SALVAR */
    /* Chama o DAO para acessar o banco de dados */

    // Atributo que vai armazenar o usuarioDAO
    private UsuarioDAO usuarioDAO;

    // Construtor da classe servico do usuario
    public UsuarioService () {
        /* Instancia o usuarioDAO, que vai ser responsabilizado por chamar o banco de dados */
        this.usuarioDAO = new UsuarioDAO();
    }

    // Método que vai salvar usuario e validar/enviar para o banco de dados
    public void saveUsuario (Usuario usuario) throws BusinessException { // recebe o objeto passado do controller do ususario
        validarUsuario(usuario);

        // Caso as validações estejam corretas, ele chama o DAO para savar/validar no banco de dados
        try {
            usuarioDAO.save(usuario);
        } catch (DAOException e) {
            throw new BusinessException("Erro ao salvar usuario");
        }
    }

    // Método que validar cada campo do Usuario
    // se dentro desse método eu chamo outro que lança 'BusinessException' mas nao coloco try/catch, preciso declarar throws na assinatura até chegar no controller
    // declara que o metodo pode lancar excessoes (throw)
    public void validarUsuario (Usuario usuario) throws BusinessException {
        // Apenas verifica se o objeto Usuario não é nulo, não verifica campo algum do objeto
        if (usuario == null) {
            throw new BusinessException("Usuário não pode ser nulo.");
        }

        // Validação para o nome do usuário
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new BusinessException("Nome do usuário não pode ser nulo.");
        }

        // Validação complexa para o cpf
        if (!Utils.validarCPF(usuario.getCpf())) {
            throw new BusinessException("CPF precisá ser válido.");
        }

        // Validação para o Endereço
        if (usuario.getEndereco() == null) {
            throw new BusinessException("Endereço do usuário não pode ser nulo.");
        }
    }

    // Método para listar todos os Usuários
    public List<Usuario> listAll () throws BusinessException {
        try {
            List<Usuario> list = usuarioDAO.listAll();

            if (list.isEmpty()) {
                throw new BusinessException("Nenhum usuario encontrado");
            }

            return list;
        } catch (DAOException e) {
            throw new BusinessException("Erro ao listar usuarios");
        }
    }

    // Recebe o id do Controller, busca o Usuario no DAO e retorna o resultado ao Controller
    public Usuario findById(int id) throws BusinessException {
        try {
            return usuarioDAO.findById(id);
        } catch (DAOException e) {
            throw new BusinessException("Erro ao buscar usuario pelo ID");
        }
    }
}