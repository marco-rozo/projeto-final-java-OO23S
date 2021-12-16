/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Automovel;
import entidades.Cliente;
import entidades.Modelo;
import dao.ArquivoDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreia
 */
public class AutomovelDao extends AbstractDao<Automovel> {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public boolean insert(Automovel automovel) {
        boolean inseriu = false;
        super.openConnection();
        try {
            preparedStatement = super.connection.prepareStatement("INSERT INTO automoveis"
                    + " (modelo_id, cliente_id, cor_automovel, ano_automovel)"
                    + " VALUES(?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, automovel.getModelo().getId());
            preparedStatement.setInt(2, automovel.getCliente().getId());
            preparedStatement.setString(3, automovel.getCorAutomovel());
            preparedStatement.setInt(4, automovel.getAnoAutomovel());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                automovel.setId(resultSet.getInt("id"));
            }
            inseriu = true;

        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro AutomovelDao.insert. Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao inserir o novo automovel. " + ex.getMessage());
            inseriu = false;
        } finally {
            super.closePreparedStatement(preparedStatement);
            super.closeResultSet(resultSet);
            super.closeConnection();
        }
        return inseriu;
    }

    @Override
    public List<Automovel> findAll() {
        //Cria lista do tipo clientes
        List<Automovel> listaAutomoveis = new ArrayList<>();
        try {
            //abre a conexão com o banco
            super.openConnection();
            //prepara o script de select
            preparedStatement = super.connection.prepareStatement("SELECT automoveis.*, clientes.nome_cliente, modelos.nome_modelo FROM automoveis "
                    + " INNER JOIN clientes "
                    + " ON automoveis.cliente_id = clientes.id "
                    + " INNER JOIN modelos "
                    + " ON automoveis.modelo_id = modelos.id ");
            //executa o script de select
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //inicia-se o objeto estado
                Automovel automovel = new Automovel();
                //adiciona os resultados da busca no banco
                automovel.setId(resultSet.getInt("id"));
                //automovel.setClienteId(resultSet.getInt("cliente_id"));
                automovel.setAnoAutomovel(resultSet.getInt("ano_automovel"));
                automovel.setCorAutomovel(resultSet.getString("cor_automovel"));
                //automovel.setModeloId(resultSet.getInt("modelo_id"));

                Cliente cliente = new Cliente();
                cliente.setNomeCliente(resultSet.getString("nome_cliente"));
                Modelo modelo = new Modelo();
                modelo.setNomeModelo(resultSet.getString("nome_modelo"));

                automovel.setCliente(cliente);
                automovel.setModelo(modelo);

                //adiciona o objeto cliente na lista de clientes
                listaAutomoveis.add(automovel);
            }
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro AutomovelDao.findAll. Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao buscar automovel: " + ex.getMessage());
        } finally {
            //fecha oq foi utilizado
            super.closePreparedStatement(preparedStatement);
            super.closeResultSet(resultSet);
            super.closeConnection();
        }
        //retorna a lista de clientes
        return listaAutomoveis;
    }

    @Override
    public boolean delete(int id) {
        try {
            super.openConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM automoveis WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro AutomovelDao.delete. Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao excluir Automovel. " + ex.getMessage());
            return false;
        } finally {
            super.closePreparedStatement(preparedStatement);
            super.closeConnection();
        }
    }

    @Override
    public Automovel findById(int id) {
        Automovel automovel = new Automovel();
        try {
            super.openConnection();
            preparedStatement = super.connection.prepareStatement("SELECT automoveis.*, clientes.nome_cliente, modelos.nome_modelo FROM automoveis "
                    + " INNER JOIN clientes "
                    + " ON automoveis.cliente_id = clientes.id "
                    + " INNER JOIN modelos "
                    + " ON automoveis.modelo_id = modelos.id "
                    + " WHERE automoveis.id = ? ");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                automovel.setId(id);
                automovel.setAnoAutomovel(resultSet.getInt("ano_automovel"));
                automovel.setCorAutomovel(resultSet.getString("cor_automovel"));

                Cliente cliente = new Cliente();
                cliente.setNomeCliente(resultSet.getString("nome_cliente"));
                automovel.setCliente(cliente);

                Modelo modelo = new Modelo();
                modelo.setNomeModelo(resultSet.getString("nome_modelo"));
                automovel.setModelo(modelo);

            }
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro AutomovelDao.findById. Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao encontrar automovel. " + ex.getMessage());
        } finally {
            super.closePreparedStatement(preparedStatement);
            super.closeResultSet(resultSet);
            super.closeConnection();
        }

        return automovel;
    }

    @Override
    public boolean update(Automovel automovel) {
        try {
            super.openConnection();
            preparedStatement = connection.prepareStatement("UPDATE automoveis"
                    + " SET modelo_id= ?, "
                    + " cliente_id= ?, "
                    + " cor_automovel= ?, "
                    + " ano_automovel= ? "
                    + " WHERE id = ?");
            preparedStatement.setInt(1, automovel.getModelo().getId());
            preparedStatement.setInt(2, automovel.getCliente().getId());
            preparedStatement.setString(3, automovel.getCorAutomovel());
            preparedStatement.setInt(4, automovel.getAnoAutomovel());
            preparedStatement.setInt(5, automovel.getId());
            return preparedStatement.executeUpdate() != 0; //true
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro AutomovelDao.update. Message: " + ex.getMessage());
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            super.closePreparedStatement(preparedStatement);
            super.closeConnection();
        }
    }

    private final String select = "SELECT automoveis.*, clientes.nome_cliente, modelos.nome_modelo FROM automoveis "
            + " INNER JOIN clientes "
            + " ON automoveis.cliente_id = clientes.id "
            + " INNER JOIN modelos "
            + " ON automoveis.modelo_id = modelos.id ";

    @Override
    public List<Automovel> search(int index, String pesquisa) {
        List<Automovel> listaAutomoveis = new ArrayList<>();
        pesquisa = pesquisa
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![")
                .toLowerCase();
        super.openConnection();
        try {
            switch (index) {
                case 0:
                    preparedStatement = super.connection.prepareStatement(select + " WHERE automoveis.id = ? ");
                    preparedStatement.setInt(1, Integer.parseInt(pesquisa));
                    resultSet = preparedStatement.executeQuery();
                    break;
                case 1:
                    preparedStatement = super.connection.prepareStatement(select + " WHERE lower(clientes.nome_cliente) LIKE ?");
                    preparedStatement.setString(1, "%" + pesquisa + "%");
                    resultSet = preparedStatement.executeQuery();
                    break;
                case 2:
                    preparedStatement = super.connection.prepareStatement(select + " WHERE lower(modelos.nome_modelo) LIKE ?");
                    preparedStatement.setString(1, "%" + pesquisa + "%");
                    resultSet = preparedStatement.executeQuery();
                    break;
                case 3:
                    preparedStatement = super.connection.prepareStatement(select + " WHERE automoveis.ano_automovel = ? ");
                    preparedStatement.setInt(1, Integer.parseInt(pesquisa));
                    resultSet = preparedStatement.executeQuery();
                    break;
                case 4:
                    preparedStatement = super.connection.prepareStatement(select + " WHERE lower(automoveis.cor_automovel) LIKE ?");
                    preparedStatement.setString(1, "%" + pesquisa + "%");
                    resultSet = preparedStatement.executeQuery();
                    break;
            }

            while (resultSet.next()) {
                //adiciona os resultados da busca no banco
                Integer id = resultSet.getInt("id");
                //inicia-se o objeto estado
                Automovel automovel = new Automovel();
                //adiciona os resultados da busca no banco
                automovel.setId(resultSet.getInt("id"));
                //automovel.setClienteId(resultSet.getInt("cliente_id"));
                automovel.setAnoAutomovel(resultSet.getInt("ano_automovel"));
                automovel.setCorAutomovel(resultSet.getString("cor_automovel"));
                //automovel.setModeloId(resultSet.getInt("modelo_id"));

                Cliente cliente = new Cliente();
                cliente.setNomeCliente(resultSet.getString("nome_cliente"));
                Modelo modelo = new Modelo();
                modelo.setNomeModelo(resultSet.getString("nome_modelo"));

                automovel.setCliente(cliente);
                automovel.setModelo(modelo);

                //adiciona o objeto cliente na lista de clientes
                listaAutomoveis.add(automovel);
            }
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro AutomovelDao.search. Message: " + ex.getMessage());
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaAutomoveis;
    }
}
