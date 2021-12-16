/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Modelo;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreia
 */
public class ModeloDao extends AbstractDao<Modelo> {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public boolean insert(Modelo modelo) {
        boolean inseriu = false;
        super.openConnection();
        try {
            preparedStatement = super.connection.prepareStatement("INSERT INTO modelos "
                    + " (nome_modelo, valor_fipe) "
                    + " VALUES( ?, ?);", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, modelo.getNomeModelo());
            preparedStatement.setDouble(2, modelo.getValorFipe());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                modelo.setId(resultSet.getInt("id"));
            }
            inseriu = true;

        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ModeloDao.insert. Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao inserir o novo modelo. " + ex.getMessage());
            inseriu = false;
        } finally {
            super.closePreparedStatement(preparedStatement);
            super.closeResultSet(resultSet);
            super.closeConnection();
        }
        return inseriu;
    }

    @Override
    public List<Modelo> findAll() {
        //Cria lista do tipo clientes
        List<Modelo> listaModelos = new ArrayList<>();
        try {
            //abre a conexão com o banco
            super.openConnection();
            //prepara o script de select
            preparedStatement = super.connection.prepareStatement("SELECT * FROM modelos");
            //executa o script de select
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //inicia-se o objeto estado
                Modelo modelo = new Modelo();
                //adiciona os resultados da busca no banco
                modelo.setId(resultSet.getInt("id"));
                modelo.setNomeModelo(resultSet.getString("nome_modelo"));
                modelo.setValorFipe(resultSet.getDouble("valor_fipe"));

                //adiciona o objeto cliente na lista de clientes
                listaModelos.add(modelo);
            }
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ModeloDao.findAll. Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao adicionar modelo: " + ex.getMessage());
        } finally {
            //fecha oq foi utilizado
            super.closePreparedStatement(preparedStatement);
            super.closeResultSet(resultSet);
            super.closeConnection();
        }
        //retorna a lista de clientes
        return listaModelos;
    }

    @Override
    public boolean delete(int id) {
        try {
            super.openConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM modelos WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ModeloDao.findAll. delete: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao excluir Modelo. " + ex.getMessage());
            return false;
        } finally {
            super.closePreparedStatement(preparedStatement);
            super.closeConnection();
        }
    }

    public int selectAutomoveisByModelo(int id) {
        try {
            //abre a conexão com o banco
            super.openConnection();
            //prepara o script de select
            preparedStatement = super.connection.prepareStatement("SELECT COUNT(*) AS rowcount "
                    + " FROM automoveis"
                    + " WHERE modelo_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            Integer rowcount = 0;
            while (resultSet.next()) {
                rowcount += resultSet.getInt("rowcount");
            }

            return rowcount;
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ModeloDao.selectAutomoveisByModelo. delete: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao buscar Automovel com Modelo. " + ex.getMessage());
            return 1;
        }
    }

    public boolean verificaModelo(int id) {
        boolean result = false;
        Integer possuiAutomovel = selectAutomoveisByModelo(id);

        if (possuiAutomovel > 0) {
            int resp = JOptionPane.showConfirmDialog(null, "Modelo possui automoveis relacionados, deseja deletar os automóveis e o Modelo?",
                    "Modelo relacionado a automovel(s)", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                deleteAutomoveis(id);
                result = delete(id);
                System.out.println(possuiAutomovel + " Excluir ambos");

            } else {
                System.out.println(possuiAutomovel + " Excluir nada");
                JOptionPane.showMessageDialog(null, "Verifique os veículos relacionados a este modelo na lista de veículos...");
            }
        } else {
            System.out.println(possuiAutomovel + " Excluir modelo");
            result = delete(id);
        }
        return result;
    }

    public boolean deleteAutomoveis(int id) {
        try {
            super.openConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM automoveis WHERE modelo_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ModeloDao.deleteAutomoveis. delete: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao excluir Automoveis relacionados ao Modelo: " + id + ". " + ex.getMessage());
            return false;
        } finally {
            super.closePreparedStatement(preparedStatement);
            super.closeConnection();
        }
    }

    @Override
    public Modelo findById(int id) {
        Modelo modelo = new Modelo();
        try {
            super.openConnection();
            preparedStatement = super.connection.prepareStatement("SELECT * FROM modelos "
                    + " WHERE modelos.id = ? ");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                modelo.setId(id);
                //adiciona os resultados da busca no banco
                modelo.setNomeModelo(resultSet.getString("nome_modelo"));
                modelo.setValorFipe(resultSet.getDouble("valor_fipe"));

            }
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ModeloDao.findById. delete: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao encontrar modelo. " + ex.getMessage());
        } finally {
            super.closePreparedStatement(preparedStatement);
            super.closeResultSet(resultSet);
            super.closeConnection();
        }
        return modelo;
    }

    @Override
    public boolean update(Modelo modelo) {
        try {
            super.openConnection();
            preparedStatement = connection.prepareStatement("UPDATE modelos "
                    + " SET nome_modelo = ? ,"
                    + " valor_fipe = ?"
                    + " WHERE id = ? ");
            preparedStatement.setString(1, modelo.getNomeModelo());
            preparedStatement.setDouble(2, modelo.getValorFipe());
            preparedStatement.setInt(3, modelo.getId());
            return preparedStatement.executeUpdate() != 0; //true
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ModeloDao.update. delete: " + ex.getMessage());
            Logger.getLogger(ModeloDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            super.closePreparedStatement(preparedStatement);
            super.closeConnection();
        }
    }

    private final String select = "SELECT * FROM modelos ";

    @Override
    public List<Modelo> search(int index, String pesquisa) {
        List<Modelo> listaModelos = new ArrayList<>();
        super.openConnection();
        try {
            switch (index) {
                case 0:
                    preparedStatement = super.connection.prepareStatement(select + " WHERE modelos.id = ? ");
                    preparedStatement.setInt(1, Integer.parseInt(pesquisa));
                    resultSet = preparedStatement.executeQuery();
                    break;
                case 1:
                    preparedStatement = super.connection.prepareStatement(select + " WHERE lower(modelos.nome_modelo) LIKE ?");
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
                    preparedStatement = super.connection.prepareStatement(select + " WHERE modelos.valor_fipe = ? ");
                    preparedStatement.setDouble(1, Double.parseDouble(pesquisa));
                    resultSet = preparedStatement.executeQuery();
                    break;
            }

            while (resultSet.next()) {
                //adiciona os resultados da busca no banco
                Integer id = resultSet.getInt("id");
                String nomemodelo = resultSet.getString("nome_modelo");
                Double valofipe = resultSet.getDouble("valor_fipe");
                //inicia-se o objeto cliente
                Modelo modelo = new Modelo(id, nomemodelo, valofipe);

                listaModelos.add(modelo);
            }
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro ModeloDao.search. delete: " + ex.getMessage());
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaModelos;
    }
}
