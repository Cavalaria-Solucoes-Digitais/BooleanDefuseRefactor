package model;

import java.awt.Rectangle;
import java.io.IOException;

public class Celular {
	private Sprite cel, ajuda;
	private Chatbot chat;
	private String texto;
	
	
	public Celular() {
		super();
		
		chat = new Chatbot();
		//filename = this.getClass().getClassLoader().getResource("BooleanDefuse-files/Imagens/MAY.png").getPath();
		
		
		try {
			cel = new Sprite("BooleanDefuse-files/Imagens/celular.png", 0, 326, 641, 1, 1, 1000, 65);
			ajuda = new Sprite("BooleanDefuse-files/Imagens/icone ajuda sprite.png", 0, 34, 30, 1, 3, 1270, 130);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		init();
		
	}
	
	public void init() {
		texto = "";
		chat.init();
	}
	
	public String novaMensagem(String msg, boolean enviada) {
		texto += montarMensagem(msg, enviada); 
		
		return "<html>"
		+ "<style>"
		+ ".mensagem{"
		+ "		background-color:white;"
		+ "		font-family: Roboto;"
		+ "		font-size: 10px;"
		+ "		margin: 5px;"
		+ "		padding: 5px;"
		+ " 	border-radius: 25px;"
		+ "		max-width: 200px;"
		+ "}"
		+ ".mensagemEnviada{"
		+ "		background-color:#E5FFCC;"
		+ "		font-family: Roboto;"
		+ "		font-size: 10px;"
		+ "		margin: 5px;"
		+ "		padding: 5px;"
		+ " 	border-radius: 25px;"
		+ "		max-width: 10px;"
		+ "		float:left;"
		+ "}"
		+ "pre{"
		+ "		font-size:9px;"
		+ "}"
		
		+ "</style>"
		+texto+"</html>";
	}
	
	public String montarMensagem(String msg, boolean enviada) {
		if(enviada) {
			return "<div class=\"mensagemEnviada\">"+msg+"</div>";
		}
		return "<div class=\"mensagem\">"+msg+"</div>";
	}
	
	public boolean colisaoAjuda(int posXMouse,int posYMouse) {
    	if(colide(ajuda, posXMouse, posYMouse)) {
			ajuda.aparencia=1;
			return true;
		}else {
			ajuda.aparencia=0;
		}
    	return false;
    }
    
    private boolean colide(Sprite sprite1, int mouseX, int mouseY) {
    	  Rectangle r1 = new Rectangle(sprite1.posX, sprite1.posY, sprite1.width, sprite1.height);
    	  Rectangle r2 = new Rectangle(mouseX, mouseY, 1, 1);
    	  
    	  if (r1.intersects(r2)) {
    	    return true;
    	  }
    	  
    	  return false;
    }
	
	

	public Sprite getCel() {
		return cel;
	}

	public void setCel(Sprite cel) {
		this.cel = cel;
	}

	public Chatbot getChat() {
		return chat;
	}

	public void setChat(Chatbot chat) {
		this.chat = chat;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Sprite getAjuda() {
		return ajuda;
	}
	
	

}
