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

import javax.swing.JOptionPane;

import dao.FiosDao;
import view.Mensagem;

/**
 *
 * @author Elvis Nogueira
 */
public class ModuloFios {
	private boolean status;
	private Sprite ledStatus, ajuda,dica;
	private Fios[] fios;
	private Sprite[] sprites;
	private Sprite alicate;

	public ModuloFios() {
		fios = new Fios[3];
		sprites = new Sprite[3];

		try {
			dica = new Sprite("BooleanDefuse-files/Imagens/sprite dicas5.png", 0, 241, 182, 5, 1, 0, 0);
		} catch (IOException e) {
			Mensagem.mostrar("Erro ao carregar Sprite! Verifique se o arquivo  do caminho \"BooleanDefuse-files/Imagens/sprite dicas5.png\" está no seu computador!", Util.ERRRO);
		}

		try {
			ledStatus = new Sprite("BooleanDefuse-files/Imagens/LED-STATUS.png", 0, 65, 22, 1, 2, 367, 135);
		} catch (IOException e) {
			Mensagem.mostrar("Erro ao carregar Sprite! Verifique se o arquivo  do caminho \"BooleanDefuse-files/Imagens/LED-STATUS.png\" está no seu computado!", Util.ERRRO);
		}

		try {
			ajuda = new Sprite("BooleanDefuse-files/Imagens/icone ajuda sprite.png", 0, 34, 30, 1, 3, 111, 130);
		} catch (IOException e) {
			Mensagem.mostrar("Erro ao carregar Sprite! Verifique se o arquivo  do caminho \"BooleanDefuse-files/Imagens/icone ajuda sprite.png\" está no seu computado!", Util.ERRRO);
		}

		try {
			alicate = new Sprite("BooleanDefuse-files/Imagens/Alicate.png", 1, 30, 30, 1, 2, 150, 115);
		} catch (IOException e) {
			Mensagem.mostrar("Erro ao carregar Sprite! Verifique se o arquivo  do caminho \"BooleanDefuse-files/Imagens/Imagens/Alicate.png\" está no seu computado!", Util.ERRRO);
		}


		initModFios();

	}

	public void initModFios() {
		sortearFios();
		setarSprites(fios);
		ledStatus.aparencia = 0;
		Util.statusFios = false;
		status = false;
	}

	private void sortearFios() {
		Random numAleatorio = new Random();
		ArrayList<Fios> fiosXML = FiosDao.getAll();
		ArrayList<Fios> fiosSelec = new ArrayList<Fios>();

		boolean igual = false;
		int sorteado;
		int i=0;

		fiosSelec.add(fiosXML.get(numAleatorio.nextInt(fiosXML.size())));

		while(i<2) {
			sorteado = numAleatorio.nextInt(fiosXML.size());
			for (Fios fios : fiosSelec) {
				if(fios.getCor().equals( fiosXML.get(sorteado).getCor()) && fios.getPosicao().equals(fiosXML.get(sorteado))) {
					igual = true;
				}
			}
			if(!igual) {
				fiosSelec.add(fiosXML.get(sorteado));
			}
			if(i==1 && !(fiosSelec.get(0).isResposta() || fiosSelec.get(1).isResposta() || fiosSelec.get(2).isResposta() )) {

				fiosSelec.remove(2);

			}else {
				i++;
			}

		}

		fios[0] = fiosSelec.get(0);
		fios[1] = fiosSelec.get(1);
		fios[2] = fiosSelec.get(2);  	
	}

	private Sprite[] setarSprites(Fios[] vetorFios) {
		int posX, posY;
		Sprite[] spritesFios;
		for(int i=0;i<3;i++) {
			try {
				sprites [i] = new Sprite("BooleanDefuse-files/Imagens/Fios v19.png", setarAparenciaSprite(vetorFios[i].getPosicao(), vetorFios[i].getCor()), 
						46, 133, 5, 9, posicaoXFios(i, vetorFios[i]), 200);
			} catch (IOException e) {
				Mensagem.mostrar("Erro ao carregar Sprite! Verifique se o arquivo  do caminho \"BooleanDefuse-files/Imagens/Fios v19.png\" está no seu computado!", Util.ERRRO);
			}
		}

		return null;
	}

	private int posicaoXFios(int numFio, Fios fio) {
		switch (numFio) {
		case 0:
			return nomeFio(fio.getPosicao());
		case 1:
			return nomeFio(fio.getPosicao())+110;
		case 2:
			return nomeFio(fio.getPosicao())+224;		
		}

		return 0;
	}

	private int nomeFio(String nome) {
		if(nome.equals("1A"))
			return 110;
		else if(nome.equals("2A"))
			return 122;
		else if(nome.equals("3A"))
			return 132;
		else if(nome.equals("1B"))
			return 122;
		else if(nome.equals("2B"))
			return 133;
		else if(nome.equals("3B"))
			return 144;
		else if(nome.equals("1C"))
			return 133;
		else if(nome.equals("2C"))
			return 144;
		else if(nome.equals("3C"))
			return 155;  	


		return 0;

	}

	private int setarAparenciaSprite(String nome, String cor) {
		if(nome.equals("1A") || nome.equals("2B") || nome.equals("3C")) {
			if(cor.equals("Vermelho")) {
				return 0;
			}else if(cor.equals("Amarelo")) {
				return 3;
			}else {
				return 6;
			}
		}else if(nome.equals("2A") || nome.equals("3B")) {
			if(cor.equals("Vermelho")) {
				return 9;
			}else if(cor.equals("Amarelo")) {
				return 12;
			}else {
				return 15;
			}
		}else if(nome.equals("3A")){
			if(cor.equals("Vermelho")) {
				return 18;
			}else if(cor.equals("Amarelo")) {
				return 21;
			}else {
				return 24;
			}
		}else if(nome.equals("1B") || nome.equals("2C")) {
			if(cor.equals("Vermelho")) {
				return 27;
			}else if(cor.equals("Amarelo")) {
				return 30;
			}else {
				return 33;
			}
		}else if(nome.equals("1C")){
			if(cor.equals("Vermelho")) {
				return 36;
			}else if(cor.equals("Amarelo")) {
				return 39;
			}else {
				return 42;
			}
		}
		return 0;

	}

	public boolean colisaoFios(int posXMouse,int posYMouse) {
		for (int i = 0; i < fios.length; i++) {
			if(colide(sprites[i], posXMouse, posYMouse)) {
				if(sprites[i].aparencia%3==0) {
					desmarcarFios();
					sprites[i].aparencia++;
					Sons.tocar("BooleanDefuse-files/Sons/select.wav");
					return true;    				
				}
			}
		}
		return false;   	

	}

	public boolean emCimaDoFio(int posXMouse,int posYMouse) {
		Rectangle r1 = new Rectangle(101, 185, 350, 220);
		Rectangle r2 = new Rectangle(posXMouse, posYMouse, 1, 1);

		if (r1.intersects(r2)) {
			return true;
		}

		return false; 	

	}

	public void cortarFios(int posXMouse,int posYMouse) {
		for (int i = 0; i < fios.length; i++) {
			if(colide(sprites[i], posXMouse, posYMouse)) {
				if(sprites[i].aparencia%3 - 1==0) {
					sprites[i].aparencia++;
					Sons.tocar("BooleanDefuse-files/Sons/alicate.wav");
					if(!corrigirFiosCortados(i))
						Util.explodir = true;
					Util.FIOS_CORTADOS++;
					verificarStatus();

				}
			}
		}
	}

	private void desmarcarFios() {
		for (int i = 0; i < fios.length; i++) {
			if(sprites[i].aparencia%3 - 1==0) {
				sprites[i].aparencia--;
			}
		}
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

	private boolean verificarStatus() {
		if((Util.fiosOk==2) || Util.FIOS_CORTADOS == 3) {
			for (int i = 0; i < fios.length; i++) {
				if(fios[i].isResposta()) {

					if(sprites[i].aparencia%3 - 2 != 0) {
						return false;
					}
				}else {
					if(sprites[i].aparencia%3 - 2 == 0) {
						return false;
					}
				}
			}

			if(ledStatus.aparencia==0) {
				ledStatus.aparencia++;
				Sons.tocar("BooleanDefuse-files/Sons/acerto.wav");
				Util.statusFios = true;
				Util.modulosDesativados++;
				status = true;
			}
			Util.statusFios = true;
			return true;
		}else {
			return false;
		}
	}

	private boolean corrigirFiosCortados(int indice) {
		if(!fios[indice].isResposta())
			return false;
		return true;
	}

	public void acaoCorreta() {
		//Aparencia fio inteiro
		if((sprites[Util.fiosOk].aparencia%3 - 2 != 0) && fios[Util.fiosOk].isResposta()) {
			Util.explodir = true;
		}

		if((Util.fiosOk==2) && !Util.statusFios) {
			Util.explodir = !verificarStatus();
		}

	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Sprite getLedStatus() {
		return ledStatus;
	}

	public Fios[] getFios() {
		return fios;
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	public Sprite getAjuda() {
		return ajuda;
	}

	public Sprite getDica() {
		return dica;
	}

	public Sprite getAlicate() {
		return alicate;
	}





}
