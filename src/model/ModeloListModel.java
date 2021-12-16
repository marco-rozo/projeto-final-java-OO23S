/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.Modelo;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tmarc
 */
public class ModeloListModel extends AbstractTableModel{
    private List<Modelo> listaModelo;

    private String[] colunas = new String[]{"ID" ,"NOME MODELO", "VALOR FIPE"};
    
    public ModeloListModel(List<Modelo> listaModelo){
        this.listaModelo = listaModelo;
    }

    @Override
    public int getRowCount() {
        //Pega o numero de linhas
        return listaModelo.size();
    }

    @Override
    public int getColumnCount() {
        //Pega o numero de colunas
        return colunas.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Modelo modelo = listaModelo.get(row);
        //passa os valores para as colunas
        switch(column){
            case 0: return modelo.getId();
            case 1: return modelo.getNomeModelo();
            case 2: return modelo.getValorFipe();
            default: break;  
        }
        return modelo;    
    }
    
    @Override
    public String getColumnName(int column) {
        //seta os nomes das colunas
        return colunas[column];
    }
    
      
    
    public void insertModel(Modelo modelo) {
        listaModelo.add(modelo);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeModel(int indexRow) {
        listaModelo.remove(indexRow);
        fireTableRowsDeleted(indexRow, indexRow);
    }
    
    public void atualizarModel(int indiceLinha, Modelo modelo) {
        listaModelo.set(indiceLinha, modelo);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    }
}
