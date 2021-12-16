/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.Cliente;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tmarc
 */
public class ClienteListModel extends AbstractTableModel{
    private List<Cliente> listaCliente;

    private String[] colunas = new String[]{"ID" ,"NOME", "CPF", "DATA NASCMENTO", "ESTADO"};
    
    public ClienteListModel(List<Cliente> listaCliente){
        this.listaCliente = listaCliente;
    }

    @Override
    public int getRowCount() {
        //Pega o numero de linhas
        return listaCliente.size();
    }

    @Override
    public int getColumnCount() {
        //Pega o numero de colunas
        return colunas.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Cliente cliente = listaCliente.get(row);
        //passa os valores para as colunas
        switch(column){
            case 0: return cliente.getId();
            case 1: return cliente.getNomeCliente();
            case 2: return cliente.getCpf();
            case 3: return cliente.getDataNascimento();
            case 4: return cliente.getEstado().getSiglaEstado();
            default: break;  
        }
        return cliente;    
    }
    
    @Override
    public String getColumnName(int column) {
        //seta os nomes das colunas
        return colunas[column];
    }
    
      
    
    public void insertModel(Cliente cliente) {
        listaCliente.add(cliente);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeModel(int indexRow) {
        listaCliente.remove(indexRow);
        fireTableRowsDeleted(indexRow, indexRow);
    }
    
    public void atualizarModel(int indiceLinha, Cliente cliente) {
        listaCliente.set(indiceLinha, cliente);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    }
}
