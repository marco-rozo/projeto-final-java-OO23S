/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Estado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Andreia
 */
public class EstadoDao extends AbstractDao<Estado> {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public boolean insert(Estado object) {
        //INSERT NÃO NECESSÁRIO 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Estado> findAll() {
        //Cria lista do tipo clientes
        List<Estado> listaEstados = new ArrayList<>();
        try {
            //abre a conexão com o banco
            super.openConnection();
            //prepara o script de select
            preparedStatement = super.connection.prepareStatement("SELECT * FROM estados");
            //executa o script de select
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Estado estado = new Estado();
                estado.setSiglaEstado(resultSet.getString("sigla_estado"));
                estado.setNomeEstado(resultSet.getString("nome_estado"));
                listaEstados.add(estado);
            }
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro EstadoDao.findAll. Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao adicionar estado: " + ex.getMessage());
        } finally {
            //fecha oq foi utilizado
            super.closePreparedStatement(preparedStatement);
            super.closeResultSet(resultSet);
            super.closeConnection();
        }
        //retorna a lista de clientes
        return listaEstados;
    }

    @Override
    public boolean delete(int id) {
        //FUNÇÃO NÃO NECESSÁRIA
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Estado findBySigla(String sigla_estado) {
        Estado estado = new Estado();
        try {
            super.openConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM estados "
                    + " WHERE sigla_estado = ? ");
            preparedStatement.setString(1, sigla_estado);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                estado.setNomeEstado(resultSet.getString("nome_estado"));
                estado.setSiglaEstado(resultSet.getString("sigla_estado"));
            }
        } catch (SQLException ex) {
            ArquivoDao arquivoDao = new ArquivoDao();
            arquivoDao.salvarArquivo("Erro EstadoDao.findBySigla. Message: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao encontrar estado. " + ex.getMessage());
        } finally {
            super.closePreparedStatement(preparedStatement);
            super.closeResultSet(resultSet);
            super.closeConnection();
        }
        return estado;
    }

    @Override
    public boolean update(Estado object) {
        //NÃO NECESSÁRIA
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Estado> search(int index, String pesquisa) {
        //NÃO NECESSÁRIA
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Estado findById(int id) {
        //NÃO NECESSÁRIA
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
