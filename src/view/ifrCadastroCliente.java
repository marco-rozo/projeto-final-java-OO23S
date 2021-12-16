/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ClienteDao;
import dao.EstadoDao;
import entidades.Cliente;
import entidades.Estado;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ClienteListModel;

/**
 *
 * @author tmarc
 */
public class ifrCadastroCliente extends javax.swing.JInternalFrame {
    
    private List<Cliente> listaCliente = new ArrayList<>();
    private EstadoDao estadoDao;
    private Cliente clienteEdit;


    private ClienteListModel clienteListModel;
    private boolean edit = false;
    private int linhaSelecionada;
    private int idCliente;



    /**
     * Creates new form ifrCadastroCliente
     */
    public ifrCadastroCliente(ClienteListModel clienteListModel, int linhaSelecionada, boolean edit, Cliente cliente){
        this.edit = edit;
        this.clienteEdit = cliente;
        this.linhaSelecionada = linhaSelecionada;
        this.clienteListModel = clienteListModel;
        initComponents();
        super.setTitle("Editar de Clientes");
        
        clienteDao = new ClienteDao();
        estadoDao = new EstadoDao();
        Estado estadoEditar = new Estado();
        Cliente clienteEditar = new Cliente();
        
        clienteEditar = clienteDao.findById(cliente.getId());
        estadoEditar = estadoDao.findBySigla(cliente.getEstado().getSiglaEstado());

        tfCpf.setText(cliente.getCpf());
        tfNome.setText(cliente.getNomeCliente());
        idCliente = cliente.getId();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        tfDataNascimento.setText(dtf.format(cliente.getDataNascimento()));

        estadoDao.findAll().forEach((estado) -> {
            cbEstado.addItem(estado);
        });
    }
    
    public ifrCadastroCliente(){
        initComponents();
        super.setTitle("Cadastro de Clientes");

        estadoDao = new EstadoDao();
        estadoDao.findAll().forEach((estado) -> {
            System.err.println(estado);
            cbEstado.addItem(estado);
        });
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        tfDataNascimento.setText(dtf.format(LocalDate.now()));
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelNomeCompleto = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        tfCpf = new javax.swing.JTextField();
        jLabelNomeCompleto1 = new javax.swing.JLabel();
        tfDataNascimento = new javax.swing.JTextField();
        jLabelNomeCompleto2 = new javax.swing.JLabel();
        jLabelNomeCompleto3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btNovo = new javax.swing.JButton();
        btFechar = new javax.swing.JButton();
        cbEstado = new javax.swing.JComboBox<>();

        jLabelNomeCompleto.setText("Nome Completo");

        tfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomeActionPerformed(evt);
            }
        });

        tfCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCpfActionPerformed(evt);
            }
        });

        jLabelNomeCompleto1.setText("CPF");

        tfDataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDataNascimentoActionPerformed(evt);
            }
        });

        jLabelNomeCompleto2.setText("Data Nascimento");

        jLabelNomeCompleto3.setText("Estado");

        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/32x32/Hardware-Floppy-icon (Custom).png"))); // NOI18N
        btNovo.setText("Salvar");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });
        jPanel2.add(btNovo);

        btFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/32x32/cancel-icon (Custom).png"))); // NOI18N
        btFechar.setText("Fechar");
        btFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFecharActionPerformed(evt);
            }
        });
        jPanel2.add(btFechar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelNomeCompleto3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNomeCompleto2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNomeCompleto1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNomeCompleto, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                            .addComponent(tfCpf, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfDataNascimento, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNomeCompleto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNomeCompleto1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNomeCompleto2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabelNomeCompleto3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeActionPerformed

    private void tfCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCpfActionPerformed

    private void tfDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDataNascimentoActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        salvar();        // TODO add your handling code here:
    }//GEN-LAST:event_btNovoActionPerformed

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFecharActionPerformed
        int opcao = JOptionPane.showConfirmDialog(this, "Deseja fechar a tela de cadastro?", "Dialog de confirmação", JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (opcao == JOptionPane.YES_OPTION) {
                super.dispose();
        }        
    }//GEN-LAST:event_btFecharActionPerformed

    private Cliente getCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(tfDataNascimento.getText(), dtf);
        
        cliente.setDataNascimento(data);
        String nomeCliente = tfNome.getText();
        
        cliente.setNomeCliente(nomeCliente);
        String cpf = tfCpf.getText();
        
        cliente.setCpf(cpf);
        cliente.setEstado((Estado) cbEstado.getSelectedItem());

        return cliente;
    }
    
    private ClienteDao clienteDao;
    private Cliente cliente;

    private void salvar() {
        Cliente cliente = getCliente();
        clienteDao = new ClienteDao();
        boolean retorno = false;
        
        if (!edit) {
            retorno = clienteDao.insert(cliente);
            if(retorno){
                JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso. ");
            }
            this.dispose();
        } else {
            cliente.setId(cliente.getId());
            retorno = clienteDao.update(cliente);
            clienteListModel.atualizarModel(linhaSelecionada, cliente);

            if (retorno) {
                JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso. ");
            }
            this.dispose();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFechar;
    private javax.swing.JButton btNovo;
    private javax.swing.JComboBox<Estado> cbEstado;
    private javax.swing.JLabel jLabelNomeCompleto;
    private javax.swing.JLabel jLabelNomeCompleto1;
    private javax.swing.JLabel jLabelNomeCompleto2;
    private javax.swing.JLabel jLabelNomeCompleto3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField tfCpf;
    private javax.swing.JTextField tfDataNascimento;
    private javax.swing.JTextField tfNome;
    // End of variables declaration//GEN-END:variables
}
