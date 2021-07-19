/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import dao.VocabulosDao;
import view.Mensagem;

/**
 *
 * @author Elvis Nogueira
 */
public class ModuloMorse {
    
    private Sprite ledStatus, ajuda;
    private VocabuloMorse[] vocabuloMorse;
    private String resposta;
    private boolean status;
       
    public ModuloMorse() {   
    	vocabuloMorse = new VocabuloMorse[3];
    	try {
			ledStatus = new Sprite("BooleanDefuse-files/Imagens/LED-STATUS.png", 0, 65, 22, 1, 2, 367, 420);
			ajuda = new Sprite("BooleanDefuse-files/Imagens/icone ajuda sprite.png", 0, 34, 30, 1, 3, 111, 420);
		} catch (IOException e) {
			Mensagem.mostrar("Erro ao carregar Sprite! Verifique se o arquivo  do caminho \"BooleanDefuse-files/Imagens/LED-STATUS.png\" está no seu computador!", Util.ERRRO);
		}
    	
    	initModMorse();
    }
    
    public void initModMorse() {
    	selecOperador();
    	ledStatus.aparencia = 0;
    	Util.statusMorse = false;
    	status = false;
    }
    
    private void selecOperador() {
    	Random random = new Random();
    	ArrayList<VocabuloMorse> vocabulos = new ArrayList<VocabuloMorse>();
    	vocabulos = VocabulosDao.getAll("vocabulosOpLog");
    	
    	int limite = vocabulos.size();
    	
    	vocabuloMorse[1] = VocabulosDao.getAll("vocabulosOpLog").get(random.nextInt(limite));
    	selecElementos();
    	
    }
    
    private void selecElementos() {
    	Random random = new Random();
    	ArrayList<VocabuloMorse> vocabulos = new ArrayList<VocabuloMorse>();
    	
    	if(vocabuloMorse[1].getTraducao().equals("&&") || vocabuloMorse[1].getTraducao().equals("||")) {
    		vocabuloMorse[0] = VocabulosDao.getAll("vocabulosVF").get(random.nextInt(2));
    		vocabuloMorse[2] = VocabulosDao.getAll("vocabulosVF").get(random.nextInt(2));
    	}else {
    		vocabuloMorse[0] = VocabulosDao.getAll("vocabulosNum").get(random.nextInt(10));
    		vocabuloMorse[2] = VocabulosDao.getAll("vocabulosNum").get(random.nextInt(10));
    	}
    }
    
    public boolean corrigirResposta(String elemento1,String elemento2,String elementoOp, String resultado) {
    	if(!status) {
    		if(vocabuloMorse[0].getTraducao().equalsIgnoreCase(elemento1) &&
    		    	   vocabuloMorse[1].getTraducao().equalsIgnoreCase(elementoOp) &&	
    		    	   vocabuloMorse[2].getTraducao().equalsIgnoreCase(elemento2)) {
    		    		if(corrigirResultado(elemento1, elemento2, elementoOp, resultado)) {
    		    			status = true;
    		    			Util.statusMorse = true;
    		    			Util.modulosDesativados++;
    		    			ledStatus.aparencia++;
    		    			Sons.tocar("BooleanDefuse-files/Sons/acerto.wav");
    		    			return true;
    		    		}
    		}
    	}
    	return false;
    	
    }
    
    public boolean corrigirResultado(String elemento1,String elemento2,String elementoOp, String resultado) {
    	if(elementoOp.equals("&&")){
    		if(letraValida(resultado)) {
    			if((letrasToBoolean(elemento1) && letrasToBoolean(elemento2))==letrasToBoolean(resultado)) {
    				return true;
    			}
    		}
    	}else if(elementoOp.equals("||")) {
    		if(letraValida(resultado)) {
    			if((letrasToBoolean(elemento1) || letrasToBoolean(elemento2))==letrasToBoolean(resultado)) {
    				return true;
    			}
    		}
    	}
    	
    	try {
    		if(elementoOp.equals(">")) {
        		if(letraValida(resultado)) {
        			if((Integer.parseInt(elemento1) > Integer.parseInt(elemento2))==letrasToBoolean(resultado)) {
        				return true;
        			}
        		}
        	}else if(elementoOp.equals("<")) {
        		if(letraValida(resultado)) {
        			if((Integer.parseInt(elemento1) < Integer.parseInt(elemento2))==letrasToBoolean(resultado)) {
        				return true;
        			}
        		}
        	}else if(elementoOp.equals("==")) {
        		if(letraValida(resultado)) {
        			if((Integer.parseInt(elemento1) == Integer.parseInt(elemento2))==letrasToBoolean(resultado)) {
        				return true;
        			}
        		}
        	}else if(elementoOp.equals("!=")) {
        		if(letraValida(resultado)) {
        			if((Integer.parseInt(elemento1) != Integer.parseInt(elemento2))==letrasToBoolean(resultado)) {
        				return true;
        			}
        		}
        	}
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
    	
    	
    	
    	return false;
    }
    
    private boolean letrasToBoolean(String letra) {
    	if(letra.equalsIgnoreCase("f"))
    		return false;
    	else
    		return true;
    }
    
    private boolean letraValida(String letra1) {
    	if((letra1.equalsIgnoreCase("f") || letra1.equalsIgnoreCase("V"))) {
    		return true;
    	}
    	
    	return false;
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

	public Sprite getLedStatus() {
		return ledStatus;
	}

	public VocabuloMorse[] getVocabuloMorse() {
		return vocabuloMorse;
	}

	public String getResposta() {
		return resposta;
	}

	public boolean isStatus() {
		return status;
	}

	public Sprite getAjuda() {
		return ajuda;
	}
    
	
    
    
}
