/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Andreia
 */
public abstract class AbstractDao<T> {
   //T é tipo de objeto que será definido na classe herdada.
    protected Connection connection;
    public abstract boolean insert(T object); //INSERE UM REGISTRO NO BANCO DE DADOS
    public abstract List<T> findAll(); // RETORNA UMA LISTA COM TODOS OS REGISTROS DO BANCO DE DADOS
    public abstract boolean delete(int id); //EXCLUI UM REGISTRO DO BANCO DE DADOS
    public abstract T findById(int id); //RETORNA UM REGISTRO DO BANCO DE DADOS 
    public abstract boolean update(T object); //ATUALIZA UM REGISTRO NO BANCO DE DADOS
    public abstract List<T> search(int index, String pesquisa);
    
    /*As classes Connection, Statement, e ResultSet  possuem um método close(). 
    Essas classes são alocadas nos recursos do sistema quando criadas e usadas. 
    Portanto, para liberar recursos do sistema é necessário fechá-las, invocando o método close().
    */
    
    protected void openConnection(){
        try {
            if((connection == null)|| (connection.isClosed())){
                connection = new Conexao().getConexao();
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao abrir a conexão, " + ex.getMessage());
        }
    }
    
    protected void closeConnection(){
        try {
            if(connection != null || !connection.isClosed()){
                 connection.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão, " + ex.getMessage());
        }
    }
    
      protected void closePreparedStatement(PreparedStatement preparedStatement) {
        try {
            if ((preparedStatement != null) && (!preparedStatement.isClosed())) {
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível fechar o prepatedStatement." + ex);
        }
    }
     protected void closeResultSet(ResultSet rs){
         
         try {
            if ((rs != null) && (!rs.isClosed())) {
                rs.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível fechar o resultset.  " + ex.getMessage());
        }
     }
    
}
