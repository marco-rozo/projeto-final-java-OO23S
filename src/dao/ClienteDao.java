/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Cliente;
import entidades.Estado;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Andreia
 */
public class ClienteDao extends AbstractDao<Cliente> {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public boolean insert(Cliente cliente) {
        boolean inseriu = false;
        super.openConnection();
        try {
            preparedStatement = super.connection.prepareStatement("INSERT INTO clientes "
                    + " (nome_cliente, cpf, estado_cod, data_nascimento) "
                    + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, cliente.getNomeCliente());
            preparedStatement.setString(2, cliente.getCpf());
            System.err.println(cliente.getEstado().getSiglaEstado());
            preparedStatement.setString(3, cliente.getEstado().getSiglaEstado());
            preparedStatement.setDate(4, Date.valueOf(cliente.getDataNascimento()));

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                cliente.setId(resultSet.getInt("id"));
            }
            inseriu = true;

        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ClienteDao.insert. Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao inserir o novo cliente. " + ex.getMessage());
            inseriu = false;
        } finally {
            super.closePreparedStatement(preparedStatement);
            super.closeResultSet(resultSet);
            super.closeConnection();
        }
        return inseriu;
    }

    @Override
    public List<Cliente> findAll() {
        //Cria lista do tipo clientes
        List<Cliente> listaClientes = new ArrayList<>();
        try {
            //abre a conexão com o banco
            super.openConnection();
            //prepara o script de select
            //preparedStatement = super.connection.prepareStatement("SELECT * FROM clientes");
            preparedStatement = super.connection.prepareStatement("SELECT clientes.*,"
                    + "estados.sigla_estado "
                    + "FROM clientes "
                    + "INNER JOIN estados "
                    + "ON estados.sigla_estado = clientes.estado_cod");
            //executa o script de select
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //adiciona os resultados da busca no banco
                Integer id = resultSet.getInt("id");
                String nomecliente = resultSet.getString("nome_cliente");
                String cpf = resultSet.getString("cpf");
                LocalDate datanascimento = resultSet.getDate("data_nascimento").toLocalDate();

                Estado estado = new Estado();
                estado.setSiglaEstado(resultSet.getString("sigla_estado"));

                //inicia-se o objeto cliente
                Cliente cliente = new Cliente(id, nomecliente, cpf, datanascimento, estado);

                //adiciona o objeto cliente na lista de clientes
                listaClientes.add(cliente);
            }
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ClienteDao.findAll. Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao adicionar cliente: " + ex.getMessage());
        } finally {
            //fecha oq foi utilizado
            super.closePreparedStatement(preparedStatement);
            super.closeResultSet(resultSet);
            super.closeConnection();
        }
        //retorna a lista de clientes
        return listaClientes;
    }

    @Override
    public boolean delete(int id) {
        try {
            super.openConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM clientes WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ClienteDao.delete. Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao excluir Cliente. " + ex.getMessage());
            return false;
        } finally {
            super.closePreparedStatement(preparedStatement);
            super.closeConnection();
        }
    }

    public boolean verificaCliente(int id) {
        boolean result = false;
        Integer possuiAutomovel = selectAutomoveisByCliente(id);

        if (possuiAutomovel > 0) {
            int resp = JOptionPane.showConfirmDialog(null, "Cliente possui automoveis cadastrados, deseja deletar os automóveis e o cliente?",
                    "Cliente com automovel(s)", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                deleteAutomoveis(id);
                result = delete(id);
                System.out.println(possuiAutomovel + " Excluir ambos");

            } else {
                System.out.println(possuiAutomovel + " Excluir nada");
                JOptionPane.showMessageDialog(null, "Verifique os veículos do cliente na lista de veículos...");
            }
        } else {
            System.out.println(possuiAutomovel + " Excluir cliente");
            result = delete(id);
        }
        return result;
    }

    public int selectAutomoveisByCliente(int id) {
        try {
            //abre a conexão com o banco
            super.openConnection();
            //prepara o script de select
            preparedStatement = super.connection.prepareStatement("SELECT COUNT(*) AS rowcount "
                    + " FROM automoveis"
                    + " WHERE cliente_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            Integer rowcount = 0;
            while (resultSet.next()) {
                System.err.println("resultSet");
                System.err.println(resultSet.getInt("rowcount"));
                rowcount += resultSet.getInt("rowcount");
            }

            return rowcount;
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ClienteDao.selectAutomoveisByCliente. Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao buscar Automovel de Cliente. " + ex.getMessage());
            return 1;
        }
    }

    public boolean deleteAutomoveis(int id) {
        try {
            super.openConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM automoveis WHERE cliente_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ClienteDao.deleteAutomoveis. Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao excluir Automoveis do Cliente " + id + ". " + ex.getMessage());
            return false;
        } finally {
            super.closePreparedStatement(preparedStatement);
            super.closeConnection();
        }
    }

    @Override
    public Cliente findById(int id) {
        Cliente cliente = new Cliente();
        try {
            super.openConnection();
            preparedStatement = super.connection.prepareStatement("SELECT clientes.*, "
                    + " estados.sigla_estado "
                    + " FROM clientes "
                    + " INNER JOIN estados "
                    + " ON estados.sigla_estado = clientes.estado_cod "
                    //+ " WHERE clientes.id = ? ");
                    /*SIMULAR EXCEPTION >>*/ + "WHERE clientes.id = 0 ");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cliente.setId(id);
                cliente.setNomeCliente(resultSet.getString("nome_cliente"));
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setDataNascimento(resultSet.getDate("data_nascimento").toLocalDate());

                Estado estado = new Estado();
                estado.setSiglaEstado(resultSet.getString("sigla_estado"));
                cliente.setEstado(estado);
            }
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ClienteDao.findById. Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao encontrar cliente 123. " + ex.getMessage());
        } finally {
            super.closePreparedStatement(preparedStatement);
            super.closeResultSet(resultSet);
            super.closeConnection();
        }
        return cliente;
    }

    @Override
    public boolean update(Cliente cliente) {

        try {
            super.openConnection();
            preparedStatement = connection.prepareStatement("UPDATE clientes "
                    + " SET nome_cliente = ?, "
                    + " cpf= ?, "
                    + " estado_cod= ?, "
                    + " data_nascimento= ? "
                    + " WHERE id = ?");
            preparedStatement.setString(1, cliente.getNomeCliente());
            preparedStatement.setString(2, cliente.getCpf());
            preparedStatement.setString(3, cliente.getEstado().getSiglaEstado());
            preparedStatement.setDate(4, Date.valueOf(cliente.getDataNascimento()));
            preparedStatement.setInt(5, cliente.getId());
            return preparedStatement.executeUpdate() != 0; //true
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ClienteDao.update. Message: " + ex.getMessage());
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            super.closePreparedStatement(preparedStatement);
            super.closeConnection();
        }
    }

    private final String select = "SELECT clientes.*,"
            + " estados.sigla_estado "
            + " FROM clientes "
            + " INNER JOIN estados "
            + " ON estados.sigla_estado = clientes.estado_cod";

    @Override
    public List<Cliente> search(int index, String pesquisa) {
        List<Cliente> listaClientes = new ArrayList<>();
        super.openConnection();
        try {
            switch (index) {
                case 0:
                    preparedStatement = super.connection.prepareStatement(select + " WHERE clientes.id = ? ");
                    preparedStatement.setInt(1, Integer.parseInt(pesquisa));
                    resultSet = preparedStatement.executeQuery();
                    break;
                case 1:
                    preparedStatement = super.connection.prepareStatement(select + " WHERE lower(clientes.nome_cliente) LIKE ?");
                    pesquisa = pesquisa
                            .replace("!", "!!")
                            .replace("%", "!%")
                            .replace("_", "!_")
                            .replace("[", "![")
                            .toLowerCase();
                    preparedStatement.setString(1, "%" + pesquisa + "%");

                    resultSet = preparedStatement.executeQuery();
                    break;
                case 2:
                    preparedStatement = super.connection.prepareStatement(select + " WHERE lower(clientes.cpf) LIKE ?");
                    pesquisa = pesquisa
                            .replace("!", "!!")
                            .replace("%", "!%")
                            .replace("_", "!_")
                            .replace("[", "![")
                            .toLowerCase();
                    preparedStatement.setString(1, "%" + pesquisa + "%");
                    resultSet = preparedStatement.executeQuery();
                    break;
            }

            while (resultSet.next()) {
                //adiciona os resultados da busca no banco
                Integer id = resultSet.getInt("id");
                String nomecliente = resultSet.getString("nome_cliente");
                String cpf = resultSet.getString("cpf");
                LocalDate datanascimento = resultSet.getDate("data_nascimento").toLocalDate();

                Estado estado = new Estado();
                estado.setSiglaEstado(resultSet.getString("sigla_estado"));

                //inicia-se o objeto cliente
                Cliente cliente = new Cliente(id, nomecliente, cpf, datanascimento, estado);

                listaClientes.add(cliente);
            }
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ClienteDao.search. Message: " + ex.getMessage());
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaClientes;
    }
}
