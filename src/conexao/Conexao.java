/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Andreia
 */
public class Conexao {

    private Connection connection;
    //configurações de acesso ao banco de dados
    private final String URL = "jdbc:postgresql://localhost/javatrabalhofinalautomoveis";
    private final String USUARIO = "postgres";
    private final String SENHA = "admin";

    public Conexao() {
        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conectado ao banco");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar, " + ex.getMessage());
        }
    }

    public Connection getConexao() {
        return connection;
    }
    
}
