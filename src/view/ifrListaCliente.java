/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import dao.ClienteDao;
import entidades.Cliente;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import model.ClienteListModel;

/**
 *
 * @author tmarc
 */
public class ifrListaCliente extends javax.swing.JInternalFrame {
    
        private ClienteListModel clienteListModel;
        private JDesktopPane dpLista;
        private List<Cliente> clienteLista;



    /** Creates new form ifrListaCliente */
    public ifrListaCliente(JDesktopPane dpLista) {
        initComponents();
        super.setTitle("Listagem de Clientes");
        ClienteDao clienteDao = new ClienteDao();
        List<Cliente> listaCliente = clienteDao.findAll(); //busca os dados do pedido do banco
        System.out.println("pedidoDao.findAll");
        clienteListModel = new ClienteListModel(listaCliente);//cria a listModel
        jTable1.setModel(clienteListModel);
        this.dpLista = dpLista;
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        cbPesquisar = new javax.swing.JComboBox<>();
        tfPesquisa = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelNomeCompleto3 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/32x32/add-user-icon (Custom).png"))); // NOI18N
        jButton1.setText("Novo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/32x32/edit-user-icon (Custom).png"))); // NOI18N
        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/32x32/bin-empty-icon (Custom).png"))); // NOI18N
        jButton3.setText("Excluir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/32x32/1297714679_download-database (Custom).png"))); // NOI18N
        jButton5.setText("Atualizar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/32x32/cancel-icon (Custom).png"))); // NOI18N
        jButton4.setText("Fechar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4);

        cbPesquisar.setBackground(new java.awt.Color(204, 255, 255));
        cbPesquisar.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 10)); // NOI18N
        cbPesquisar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nome", "CPF" }));
        cbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPesquisarActionPerformed(evt);
            }
        });

        tfPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPesquisaActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/32x32/Search-icon (Custom).png"))); // NOI18N
        jButton6.setText("Buscar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Lista de Clientes");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabelNomeCompleto3.setText("Modelo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(316, 316, 316)
                    .addComponent(jLabelNomeCompleto3)
                    .addContainerGap(317, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(195, 195, 195)
                    .addComponent(jLabelNomeCompleto3)
                    .addContainerGap(195, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ifrCadastroCliente ifrCadastro = new ifrCadastroCliente();
        dpLista.add(ifrCadastro);
        ifrCadastro.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int opcao = JOptionPane.showConfirmDialog(this, "Deseja fechar a listagem?", "Dialog de confirmação", JOptionPane.YES_NO_CANCEL_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            super.dispose();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        excluir();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        atualizar();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        editar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPesquisarActionPerformed

    private void tfPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPesquisaActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int filter = cbPesquisar.getSelectedIndex();
        if(tfPesquisa.getText().isEmpty()){              
            atualizar();          
        } else{
            String pesquisa = tfPesquisa.getText();
            ClienteDao clienteDao = new ClienteDao();
            clienteLista = clienteDao.search(filter, pesquisa);
            clienteListModel = new ClienteListModel(clienteLista);
            jTable1.setModel(clienteListModel);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    
    private void editar(){
        int numLinha = jTable1.getSelectedRow();
        if (numLinha >= 0) {
            int index = (int) jTable1.getValueAt(numLinha, 0);
            ClienteDao clienteDao = new ClienteDao();
            Cliente cliente = new Cliente();
            cliente = clienteDao.findById(index);                  
                                  
            ifrCadastroCliente frameCadastro = new ifrCadastroCliente(clienteListModel, numLinha, true, cliente);
            dpLista.add(frameCadastro).setVisible(true);                  
        } 
    }
    
    private void atualizar(){
        ClienteDao clienteDao = new ClienteDao();
        List<Cliente> listaClienteAtualizada = clienteDao.findAll();
        clienteListModel = new ClienteListModel(listaClienteAtualizada);
        jTable1.setModel(clienteListModel);    
    }
    
    private void excluir() {
        int linhaSelecionada = jTable1.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int opcao = JOptionPane.showConfirmDialog(null,
                    "Deseja excluir o cliente selecionado? ",
                    "Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcao == JOptionPane.YES_OPTION) {
                int idCliente = (int) jTable1.getValueAt(linhaSelecionada, 0);
                ClienteDao clienteDao = new ClienteDao();
                
                if (clienteDao.verificaCliente(idCliente)) {
                    JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso. ");
                    clienteListModel.removeModel(linhaSelecionada);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um cliente para excluir. ");
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbPesquisar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabelNomeCompleto3;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField tfPesquisa;
    // End of variables declaration//GEN-END:variables

}