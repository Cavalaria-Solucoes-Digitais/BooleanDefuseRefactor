package model;

public class Perguntas {
	private String pergunta;
    private boolean resposta;
    
    
	public Perguntas(String pergunta, boolean resposta) {
		super();
		this.pergunta = pergunta;
		this.resposta = resposta;
	}


	public String getPergunta() {
		return pergunta;
	}


	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}


	public boolean getResposta() {
		return resposta;
	}


	public void setResposta(boolean resposta) {
		this.resposta = resposta;
	}
    
    
}
