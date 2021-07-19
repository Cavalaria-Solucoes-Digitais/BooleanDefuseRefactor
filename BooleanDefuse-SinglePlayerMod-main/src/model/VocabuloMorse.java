package model;

public class VocabuloMorse {
	String palavra, traducao;

	public VocabuloMorse(String palavra, String traducao) {
		super();
		this.palavra = palavra;
		this.traducao = traducao;
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public String getTraducao() {
		return traducao;
	}

	public void setTraducao(String traducao) {
		this.traducao = traducao;
	}
	
	
}
