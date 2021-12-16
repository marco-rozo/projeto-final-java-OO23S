/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.time.LocalDate;

/**
 *
 * @author tmarc
 */
public class Cliente {

    private int id;
    private String nome_cliente;
    private String cpf;
    //private String sigla_estado;
    private Estado estado;
    private LocalDate data_nascimento;

    public Cliente() {

    }

    public Cliente(int id, String nome_cliente, String cpf, /*String sigla_estado,*/ LocalDate data_nascimento, Estado estado) {
        this.id = id;
        this.nome_cliente = nome_cliente;
        this.cpf = cpf;
        //this.sigla_estado = sigla_estado;
        this.data_nascimento = data_nascimento;
        this.estado = estado;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return this.nome_cliente;
    }

    public void setNomeCliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /*public String getSiglaEstado(){
            return this.sigla_estado;
	}
	public void setSiglaEstado(String sigla_estado){
            this.sigla_estado = sigla_estado;
	}*/
    public LocalDate getDataNascimento() {
        return this.data_nascimento;
    }

    public void setDataNascimento(LocalDate data) {
        this.data_nascimento = data;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nome_cliente;
    }
}
