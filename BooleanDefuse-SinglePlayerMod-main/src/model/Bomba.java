/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.ImageIcon;

/**
 *
 * @author Elvis Nogueira
 */
public class Bomba {   
    private ImageIcon bomba;
    private ModuloFios moduloFios;
    private ModuloMorse moduloMorse;
    private ModuloQuiz moduloQuiz;
    private ModuloTesteMesa moduloTesteMesa;
    private Relogio relogio;
    private Celular celular;
    
    public Bomba() {
    	bomba = new ImageIcon("BooleanDefuse-files/Imagens/bomba 4.png");
    	moduloQuiz = new ModuloQuiz();
    	moduloFios = new ModuloFios();
    	moduloMorse = new ModuloMorse();
    	moduloTesteMesa = new ModuloTesteMesa();
    	
    	celular = new Celular();
    	relogio = new Relogio();
    	
    	
    }
    
    public void iniciarBomba() {
    	moduloQuiz.initModQuiz();
    	moduloFios.initModFios();
    	moduloMorse.initModMorse();
    	moduloTesteMesa.init();
    	
    	try {
    		relogio.setTempo(Util.TEMPO_JOGO_SEG);
        	relogio.start();
		} catch (Exception e) {
			reiniciarBomba();
		}
    	
    }
    
    public void reiniciarBomba() {
    	moduloQuiz.initModQuiz();
    	moduloFios.initModFios();
    	moduloMorse.initModMorse();
    	moduloTesteMesa.init();
    	celular.init();
    	Util.fiosOk = -1;
    	Util.FIOS_CORTADOS = 0;
    	
    	relogio.setTempo(Util.TEMPO_JOGO_SEG);
    	relogio.resume();
    	
    }
    
    public boolean desativarBomba() {
    	if(moduloFios.isStatus() && moduloQuiz.isStatus() && moduloMorse.isStatus() && moduloTesteMesa.isStatus()) {
    		parar();
    		Sons.tocar("BooleanDefuse-files/Sons/Bomb defused.wav");
    		Util.flagDesarmada = true;
    		
    		return Util.flagDesarmada;
    	}
    	
    	return false;
    }
    
    public void parar() {
    	try {
    		relogio.suspend();
		} catch (Exception e) {
			
		}
    }
    
    public boolean explodir() {
    	if(Util.explodir) {
    		relogio.suspend();
    		Sons.tocar("BooleanDefuse-files/Sons/bomba explosao.wav");
    		
    		return true;
    	}
    	
    	return false;
    }

	public ModuloFios getModuloFios() {
		return moduloFios;
	}

	public void setModuloFios(ModuloFios moduloFios) {
		this.moduloFios = moduloFios;
	}

	public ModuloMorse getModuloMorse() {
		return moduloMorse;
	}

	public void setModuloMorse(ModuloMorse moduloMorse) {
		this.moduloMorse = moduloMorse;
	}

	public ModuloQuiz getModuloQuiz() {
		return moduloQuiz;
	}

	public void setModuloQuiz(ModuloQuiz moduloQuiz) {
		this.moduloQuiz = moduloQuiz;
	}

	public ModuloTesteMesa getModuloTesteMesa() {
		return moduloTesteMesa;
	}

	public void setModuloTesteMesa(ModuloTesteMesa moduloTesteMesa) {
		this.moduloTesteMesa = moduloTesteMesa;
	}

	public Relogio getRelogio() {
		return relogio;
	}

	public void setRelogio(Relogio relogio) {
		this.relogio = relogio;
	}

	public ImageIcon getBomba() {
		return bomba;
	}

	public void setBomba(ImageIcon bomba) {
		this.bomba = bomba;
	}

	public Celular getCelular() {
		return celular;
	}

	public void setCelular(Celular celular) {
		this.celular = celular;
	}
    
	    
}
