/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.Automovel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tmarc
 */
public class AutomovelListModel extends AbstractTableModel{
    private List<Automovel> listaAutomovel;

    private String[] colunas = new String[]{"ID" ,"CLIENTE", "MODELO AUTOMOVEL", "ANO AUTOMOVEL", "COR AUTOMOVEL"};
    
    public AutomovelListModel(List<Automovel> listaAutomovel){
        this.listaAutomovel = listaAutomovel;
    }

    @Override
    public int getRowCount() {
        //Pega o numero de linhas
        return listaAutomovel.size();
    }

    @Override
    public int getColumnCount() {
        //Pega o numero de colunas
        return colunas.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Automovel automovel = listaAutomovel.get(row);
        //passa os valores para as colunas
        switch(column){
            case 0: return automovel.getId();
            case 1: return automovel.getCliente().getNomeCliente();
            //case 1: return automovel.getClienteId();
            case 2: return automovel.getModelo().getNomeModelo();
            //case 2: return automovel.getModeloId();
            case 3: return automovel.getAnoAutomovel();
            case 4: return automovel.getCorAutomovel();

            default: break;  
        }
        return automovel;    
    }
    
    @Override
    public String getColumnName(int column) {
        //seta os nomes das colunas
        return colunas[column];
    }
    
      
    
    public void insertModel(Automovel automovel) {
        listaAutomovel.add(automovel);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeModel(int indexRow) {
        listaAutomovel.remove(indexRow);
        fireTableRowsDeleted(indexRow, indexRow);
    }
    
    public void atualizarModel(int indiceLinha, Automovel automovel) {
        listaAutomovel.set(indiceLinha, automovel);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    }
}
