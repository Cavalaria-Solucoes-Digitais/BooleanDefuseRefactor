/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Elvis Nogueira
 */
public class Fios {
    private String posicao;
    private String cor;
    private boolean resposta;
    
	public Fios(String posicao, String cor, boolean resposta) {
		super();
		this.posicao = posicao;
		this.cor = cor;
		this.resposta = resposta;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public boolean isResposta() {
		return resposta;
	}

	public void setResposta(boolean resposta) {
		this.resposta = resposta;
	}
	
	    
    
}
