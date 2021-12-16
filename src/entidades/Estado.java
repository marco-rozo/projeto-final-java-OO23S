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
public class Estado {
        private String nome_estado;
	public String sigla_estado;
        
        public Estado(){
            
        }
        public Estado(int id,String nome_estado,String sigla_estado){
            this.nome_estado = nome_estado;
            this.sigla_estado = sigla_estado;
	}

	public String getNomeEstado(){
            return this.nome_estado;
	}
	public void setNomeEstado(String value){
            this.nome_estado = value;
	}

	public String getSiglaEstado(){
            return this.sigla_estado;
	}
	public void setSiglaEstado(String value){
            this.sigla_estado = value;
	}
        
    @Override
    public String toString() {
        return  sigla_estado;
    }
}
