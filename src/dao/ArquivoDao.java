/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class ArquivoDao {
    private String url = System.getProperty("user.dir") + "/src/MarcoAntonioRozoTrabalhoFinalError.txt";

    public void salvarArquivo(String mensagemErro) {
        File file = new File("url");
        if (!file.exists()) {
            gravarArquivo(mensagemErro);
        } else {
            int opcao = JOptionPane.showConfirmDialog(null ,
                    "O arquivo especificado já existe. Deseja substituí-lo?",
                    "Arquivo", JOptionPane.YES_NO_CANCEL_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                gravarArquivo(mensagemErro);
            }
        }
    }

    public void gravarArquivo(String erro) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'as' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println();
        File file = new File(url);
        try ( OutputStream output = new FileOutputStream(file, true);  BufferedOutputStream buffer = new BufferedOutputStream(output)) {
            StringBuilder sb = new StringBuilder();
            sb.append(formatter.format(date).toString()).append(" - " + erro).append(";\n");
            buffer.write(sb.toString().getBytes());
            buffer.flush();

        } catch (IOException ex) {
            System.out.println("Erro ao salvar o arquivo." + ex.getMessage());
        }
    }

    public String abrirArquivo(String file) {
        String dados = "";
        try ( InputStream input = new FileInputStream(file);  BufferedInputStream buffer = new BufferedInputStream(input)) {
            int linha;
            while ((linha = buffer.read()) != -1) {
                dados += (char) linha;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado." + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro ao abrir o arquivo." + ex.getMessage());
        }
        return dados;
    }
}
