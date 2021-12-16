/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author tmarc
 */
public class Automovel {

    private int id;
    //private int modelo_id;
    //private int cliente_id;
    private int ano_automovel;
    private String cor_automovel;
    private Cliente cliente;
    private Modelo modelo;

    public Automovel() {
    }

    public Automovel(int id, /*int modelo_id, int cliente_id,*/ int ano_automovel, String cor_automovel, Cliente cliente, Modelo modelo) {
        this.id = id;
        //this.modelo_id = modelo_id;
        //this.cliente_id = cliente_id;
        this.ano_automovel = ano_automovel;
        this.cor_automovel = cor_automovel;
        this.cliente = cliente;
        this.modelo = modelo;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public int getModeloId() {
        return this.modelo_id;
    }

    public void setModeloId(int modelo_id) {
        this.modelo_id = modelo_id;
    }

    public int getClienteId() {
        return this.cliente_id;
    }

    public void setClienteId(int cliente_id) {
        this.cliente_id = cliente_id;
    }*/

    public int getAnoAutomovel() {
        return this.ano_automovel;
    }

    public void setAnoAutomovel(int ano_automovel) {
        this.ano_automovel = ano_automovel;
    }

    public String getCorAutomovel() {
        return this.cor_automovel;
    }

    public void setCorAutomovel(String cor_automovel) {
        this.cor_automovel = cor_automovel;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Modelo getModelo() {
        return this.modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

}
