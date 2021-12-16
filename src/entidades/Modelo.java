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
public class Modelo {
	private int id;
	private String nome_modelo;
	private double valor_fipe;

        public Modelo(){};
        public Modelo(int id, String nome_modelo, double valor_fipe){
            this.id = id;
            this.nome_modelo = nome_modelo;
            this.valor_fipe = valor_fipe;
	}
        
	public int getId(){
            return this.id;
	}
	public void setId(int id ){
            this.id = id ;
	}

	public String getNomeModelo(){
            return this.nome_modelo;
	}
	public void setNomeModelo(String nome_modelo){
            this.nome_modelo = nome_modelo;
	}

	public double getValorFipe(){
            return this.valor_fipe;
	}
	public void setValorFipe(double valor_fipe){
            this.valor_fipe = valor_fipe;
	}
    
        @Override
    public String toString() {
        return  nome_modelo;
    }
}
