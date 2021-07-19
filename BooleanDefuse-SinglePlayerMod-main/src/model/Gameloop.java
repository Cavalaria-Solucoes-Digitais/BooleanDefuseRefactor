package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import view.JogoPanel;
import view.Tela;

public class Gameloop {
	public static Bomba bomba;
	public static int[] relogio;
	
	public static void desenhar(Tela tela){
		
		Graphics2D g = (Graphics2D) tela.getBuffer1().getDrawGraphics(); 	
//		Graphics g = tela.getBuffer().getGraphics();		
		g.drawImage(tela.getBuffer(), 0, 0, null);
		bomba = tela.getJogoPanel().getBomba();
		
		
		
		
		if(tela.getJogoPanel().isVisible()) {
			//Background
			g.drawImage(tela.getJogoPanel().getBG().getImage(), -35, -35, null);
			
			
			//Bomba
			g.drawImage(tela.getJogoPanel().getBomba().getBomba().getImage(), 50, 50, null);
			
			//Relógio da bomba
			g.setFont(Util.getFont("Seven Segment",40));
			g.setColor(Color.RED);
			g.drawString(tela.getJogoPanel().getBomba().getRelogio().getTexto(), 542, 365);
			
			
			// Módulo fios
			g.drawImage(bomba.getModuloFios().getSprites()[0].sprites[bomba.getModuloFios().getSprites()[0].aparencia], 
					bomba.getModuloFios().getSprites()[0].posX, bomba.getModuloFios().getSprites()[0].posY, null);
			g.drawImage(bomba.getModuloFios().getSprites()[1].sprites[bomba.getModuloFios().getSprites()[1].aparencia], 
					bomba.getModuloFios().getSprites()[1].posX, bomba.getModuloFios().getSprites()[1].posY, null);
			g.drawImage(bomba.getModuloFios().getSprites()[2].sprites[bomba.getModuloFios().getSprites()[2].aparencia], 
					bomba.getModuloFios().getSprites()[2].posX, bomba.getModuloFios().getSprites()[2].posY, null);
			g.drawImage(bomba.getModuloFios().getLedStatus().sprites[bomba.getModuloFios().getLedStatus().aparencia], 
					bomba.getModuloFios().getLedStatus().posX, bomba.getModuloFios().getLedStatus().posY, null);
			
			g.drawImage(bomba.getModuloFios().getAjuda().sprites[bomba.getModuloFios().getAjuda().aparencia], 
					bomba.getModuloFios().getAjuda().posX, bomba.getModuloFios().getAjuda().posY, null);
			
			g.drawImage(bomba.getModuloFios().getAlicate().sprites[bomba.getModuloFios().getAlicate().aparencia],
					bomba.getModuloFios().getAlicate().posX, bomba.getModuloFios().getAlicate().posY, null);
			
			//Modulo quiz
			
			g.drawImage(bomba.getModuloQuiz().getLedStatus().sprites[bomba.getModuloQuiz().getLedStatus().aparencia], 
					bomba.getModuloQuiz().getLedStatus().posX, bomba.getModuloQuiz().getLedStatus().posY, null);
			
			g.drawImage(tela.getJogoPanel().getBotaoVerdadeiro().sprites[tela.getJogoPanel().getBotaoVerdadeiro().aparencia], 
					tela.getJogoPanel().getBotaoVerdadeiro().posX, tela.getJogoPanel().getBotaoVerdadeiro().posY, null);
			g.drawImage(tela.getJogoPanel().getBotaoFalso().sprites[tela.getJogoPanel().getBotaoFalso().aparencia], 
					tela.getJogoPanel().getBotaoFalso().posX, tela.getJogoPanel().getBotaoFalso().posY, null);
			
			
			g.setFont(Util.arial);
			g.setColor(Util.azulBic);
			g.drawString(bomba.getModuloMorse().getVocabuloMorse()[0].getPalavra(), 152, 470);
			g.drawString(bomba.getModuloMorse().getVocabuloMorse()[1].getPalavra(), 152, 490);
			g.drawString(bomba.getModuloMorse().getVocabuloMorse()[2].getPalavra(), 152, 515);
			g.drawImage(bomba.getModuloMorse().getLedStatus().sprites[bomba.getModuloMorse().getLedStatus().aparencia], 
					bomba.getModuloMorse().getLedStatus().posX, bomba.getModuloMorse().getLedStatus().posY, null);
			
			g.drawImage(bomba.getModuloQuiz().getLedStatus().sprites[bomba.getModuloQuiz().getLedStatus().aparencia], 
					bomba.getModuloQuiz().getLedStatus().posX, bomba.getModuloQuiz().getLedStatus().posY, null);
			
			g.drawImage(bomba.getModuloQuiz().getAjuda().sprites[bomba.getModuloQuiz().getAjuda().aparencia], 
					bomba.getModuloQuiz().getAjuda().posX, bomba.getModuloQuiz().getAjuda().posY, null);
			
			//Modulo teste mesa
			
			g.drawImage(bomba.getModuloTesteMesa().getSimbolo().sprites[bomba.getModuloTesteMesa().getSimbolo().aparencia], 
					bomba.getModuloTesteMesa().getSimbolo().posX, bomba.getModuloTesteMesa().getSimbolo().posY, null);
			g.drawImage(bomba.getModuloTesteMesa().getLedStatus().sprites[bomba.getModuloTesteMesa().getLedStatus().aparencia], 
					bomba.getModuloTesteMesa().getLedStatus().posX, bomba.getModuloTesteMesa().getLedStatus().posY, null);
			
			g.drawImage(bomba.getModuloTesteMesa().getAjuda().sprites[bomba.getModuloTesteMesa().getAjuda().aparencia], 
					bomba.getModuloTesteMesa().getAjuda().posX, bomba.getModuloTesteMesa().getAjuda().posY, null);
			
			g.drawImage(tela.getJogoPanel().getBotaoVerificar().sprites[tela.getJogoPanel().getBotaoVerificar().aparencia], 
					tela.getJogoPanel().getBotaoVerificar().posX, tela.getJogoPanel().getBotaoVerificar().posY, null);
			
			
			
			
			
			g.setFont(Util.getFont("Anton-Regular", 30));
			g.setColor(Color.white);
			
			g.drawString(bomba.getModuloTesteMesa().getEntradas()[0]+"", 750, 335);
			g.drawString(bomba.getModuloTesteMesa().getEntradas()[1]+"", 827, 335);
			g.drawString(bomba.getModuloTesteMesa().getEntradas()[2]+"", 905, 335);
						
			
			
			//Modulo Morse
			
			g.drawImage(bomba.getModuloMorse().getAjuda().sprites[bomba.getModuloMorse().getAjuda().aparencia], 
					bomba.getModuloMorse().getAjuda().posX, bomba.getModuloMorse().getAjuda().posY, null);
			
			g.drawImage(tela.getJogoPanel().getUpMorseValor1().sprites[tela.getJogoPanel().getUpMorseValor1().aparencia], 
					tela.getJogoPanel().getUpMorseValor1().posX, tela.getJogoPanel().getUpMorseValor1().posY, null);
			g.drawImage(tela.getJogoPanel().getValor1().sprites[tela.getJogoPanel().getValor1().aparencia], 
					tela.getJogoPanel().getValor1().posX, tela.getJogoPanel().getValor1().posY, null);
			g.drawImage(tela.getJogoPanel().getDownMorseValor1().sprites[tela.getJogoPanel().getDownMorseValor1().aparencia], 
					tela.getJogoPanel().getDownMorseValor1().posX, tela.getJogoPanel().getDownMorseValor1().posY, null);
			
			g.drawImage(tela.getJogoPanel().getUpMorseOperador().sprites[tela.getJogoPanel().getUpMorseOperador().aparencia], 
					tela.getJogoPanel().getUpMorseOperador().posX, tela.getJogoPanel().getUpMorseOperador().posY, null);
			g.drawImage(tela.getJogoPanel().getOperador().sprites[tela.getJogoPanel().getOperador().aparencia], 
					tela.getJogoPanel().getOperador().posX, tela.getJogoPanel().getOperador().posY, null);
			g.drawImage(tela.getJogoPanel().getDownMorseIOperador().sprites[tela.getJogoPanel().getDownMorseIOperador().aparencia], 
					tela.getJogoPanel().getDownMorseIOperador().posX, tela.getJogoPanel().getDownMorseIOperador().posY, null);
			
			g.drawImage(tela.getJogoPanel().getUpMorseValor2().sprites[tela.getJogoPanel().getUpMorseValor2().aparencia], 
					tela.getJogoPanel().getUpMorseValor2().posX, tela.getJogoPanel().getUpMorseValor2().posY, null);
			g.drawImage(tela.getJogoPanel().getValor2().sprites[tela.getJogoPanel().getValor2().aparencia], 
					tela.getJogoPanel().getValor2().posX, tela.getJogoPanel().getValor2().posY, null);
			g.drawImage(tela.getJogoPanel().getDownMorseValor2().sprites[tela.getJogoPanel().getDownMorseValor2().aparencia], 
					tela.getJogoPanel().getDownMorseValor2().posX, tela.getJogoPanel().getDownMorseValor2().posY, null);
			
			g.drawImage(tela.getJogoPanel().getUpMorseResultado().sprites[tela.getJogoPanel().getUpMorseResultado().aparencia], 
					tela.getJogoPanel().getUpMorseResultado().posX, tela.getJogoPanel().getUpMorseResultado().posY, null);
			g.drawImage(tela.getJogoPanel().getResultado().sprites[tela.getJogoPanel().getResultado().aparencia], 
					tela.getJogoPanel().getResultado().posX, tela.getJogoPanel().getResultado().posY, null);
			g.drawImage(tela.getJogoPanel().getDownMorseResultado().sprites[tela.getJogoPanel().getDownMorseResultado().aparencia], 
					tela.getJogoPanel().getDownMorseResultado().posX, tela.getJogoPanel().getDownMorseResultado().posY, null);
			
			//Módulo Mesa
			g.drawImage(tela.getJogoPanel().getUpSaida1Mesa().sprites[tela.getJogoPanel().getUpSaida1Mesa().aparencia], 
					tela.getJogoPanel().getUpSaida1Mesa().posX, tela.getJogoPanel().getUpSaida1Mesa().posY, null);

			g.drawImage(tela.getJogoPanel().getDownSaida1Mesa().sprites[tela.getJogoPanel().getDownSaida1Mesa().aparencia], 
					tela.getJogoPanel().getDownSaida1Mesa().posX, tela.getJogoPanel().getDownSaida1Mesa().posY, null);
			
		
			//Celular
			
			g.drawImage(bomba.getCelular().getCel().sprites[bomba.getCelular().getCel().aparencia], bomba.getCelular().getCel().posX,
					bomba.getCelular().getCel().posY, null);
			
			g.drawImage(tela.getJogoPanel().getCabecalhoSprite().sprites[tela.getJogoPanel().getCabecalhoSprite().aparencia], 
					tela.getJogoPanel().getCabecalhoSprite().posX, tela.getJogoPanel().getCabecalhoSprite().posY, null);
			
		
			g.drawImage(bomba.getCelular().getAjuda().sprites[bomba.getCelular().getAjuda().aparencia], 
					bomba.getCelular().getAjuda().posX, bomba.getCelular().getAjuda().posY, null);
			if(Util.TUTORIAL) {
				g.drawImage(tela.getJogoPanel().getTutorial().sprites[tela.getJogoPanel().getTutorial().aparencia], 
						tela.getJogoPanel().getTutorial().posX, tela.getJogoPanel().getTutorial().posY, null);
				g.drawImage(tela.getJogoPanel().getBotaoIr().sprites[tela.getJogoPanel().getBotaoIr().aparencia], 
						tela.getJogoPanel().getBotaoIr().posX, tela.getJogoPanel().getBotaoIr().posY, null);
//				g.drawImage(tela.getJogoPanel().getBotaoLink().sprites[tela.getJogoPanel().getBotaoLink().aparencia], 
//						tela.getJogoPanel().getBotaoLink().posX, tela.getJogoPanel().getBotaoLink().posY, null);
				
				
			}
			
			if(Util.flagMostrarDica) {
				g.drawImage(bomba.getModuloFios().getDica().sprites[bomba.getModuloFios().getDica().aparencia], 
						bomba.getModuloFios().getDica().posX, bomba.getModuloFios().getDica().posY, null);
			}
			
			if(Util.MOSTRAR_MENSAGEM) {
				g.drawImage(tela.getMensagem().sprites[tela.getMensagem().aparencia], tela.getMensagem().posX, tela.getMensagem().posY, null);
				g.drawImage(tela.getBotaoFecharMsg().sprites[tela.getBotaoFecharMsg().aparencia], tela.getBotaoFecharMsg().posX, 
						tela.getBotaoFecharMsg().posY, null);
				g.drawImage(tela.getBotaoNaoMsg().sprites[tela.getBotaoNaoMsg().aparencia], tela.getBotaoNaoMsg().posX, 
						tela.getBotaoNaoMsg().posY, null);
				g.drawImage(tela.getBotaoSimMsg().sprites[tela.getBotaoSimMsg().aparencia], tela.getBotaoSimMsg().posX, 
						tela.getBotaoSimMsg().posY, null);
				//OK
			}
			
			if(!Util.TUTORIAL) {
				g.drawImage(tela.getJogoPanel().getSomSprite().sprites[tela.getJogoPanel().getSomSprite().aparencia], 
						tela.getJogoPanel().getSomSprite().posX, tela.getJogoPanel().getSomSprite().posY, null);
				
				
				
				tela.getJogoPanel().getPerguntaModuloQuiz().repaint();
				
				
//				tela.getJogoPanel().getSaidaCod().repaint();
				
				tela.getJogoPanel().getSairButton().repaint();
				
				//tela.getJogoPanel().getChatCelular().repaint();
				tela.getJogoPanel().getEnviarChat().repaint();
				tela.getJogoPanel().getChatScroll().repaint();
				tela.getJogoPanel().getSaidaField().repaint();
			}
			
			tela.getJogoPanel().getSairButton().repaint();
			
//			if(Util.flagEasterEgg) {
//				g.setFont(Util.getFont("Anton-Regular", 140));
//				g.setColor(Color.red);
//				g.drawString("LANA IS", 450, 265);
//				g.setFont(Util.getFont("Anton-Regular", 105));
//				g.setColor(Color.white);
//				g.drawString("AWESOME", 450, 485);
//			}
			//VENCEU
			
			
		}else if(tela.getMenuPanel().isVisible()) {
			g.drawImage(tela.getMenuPanel().getMenuBG().getImage(), -35, -35, null);
			
			if(Util.MOSTRAR_MENSAGEM) {
				g.drawImage(tela.getMensagem().sprites[tela.getMensagem().aparencia], tela.getMensagem().posX, tela.getMensagem().posY, null);
				g.drawImage(tela.getBotaoFecharMsg().sprites[tela.getBotaoFecharMsg().aparencia], tela.getBotaoFecharMsg().posX, 
						tela.getBotaoFecharMsg().posY, null);
				g.drawImage(tela.getBotaoNaoMsg().sprites[tela.getBotaoNaoMsg().aparencia], tela.getBotaoNaoMsg().posX, 
						tela.getBotaoNaoMsg().posY, null);
				g.drawImage(tela.getBotaoSimMsg().sprites[tela.getBotaoSimMsg().aparencia], tela.getBotaoSimMsg().posX, 
						tela.getBotaoSimMsg().posY, null);
				//OK
			}
			g.drawImage(tela.getMenuPanel().getSomSprite().sprites[tela.getMenuPanel().getSomSprite().aparencia], 
					tela.getMenuPanel().getSomSprite().posX, tela.getMenuPanel().getSomSprite().posY, null);
			tela.getMenuPanel().getSairButton().repaint();
			tela.getMenuPanel().getPlayButton().repaint();
			
		}else if(tela.getDificuldadePanel().isVisible()) {
			g.drawImage(tela.getDificuldadePanel().getMenuBG().getImage(), -35, -35, null);
			
			if(Util.textoDificuldade) {
				g.drawImage(tela.getDificuldadePanel().getTextoDificuldade().sprites[tela.getDificuldadePanel().getTextoDificuldade().aparencia], 
						tela.getDificuldadePanel().getTextoDificuldade().posX, tela.getDificuldadePanel().getTextoDificuldade().posY, null);
			}
			
			
			tela.getDificuldadePanel().getFacilButton().repaint();
			tela.getDificuldadePanel().getNormalButton().repaint();
			tela.getDificuldadePanel().getSairButton().repaint();
			
		}
		
		if(Util.explodir) {
			if(!tela.getGameOverPanel().isVisible()) {
				tela.getJogoPanel().setVisible(false);
				tela.getGameOverPanel().setVisible(true);
				bomba.explodir();
			}
			
			g.drawImage(tela.getGameOverPanel().getBombaExplosao().getImage(), 0, 0, null);
			if(Util.MOSTRAR_MENSAGEM) {
				g.drawImage(tela.getMensagem().sprites[tela.getMensagem().aparencia], tela.getMensagem().posX, tela.getMensagem().posY, null);
				g.drawImage(tela.getBotaoFecharMsg().sprites[tela.getBotaoFecharMsg().aparencia], tela.getBotaoFecharMsg().posX, 
						tela.getBotaoFecharMsg().posY, null);
				g.drawImage(tela.getBotaoFecharMsg().sprites[tela.getBotaoFecharMsg().aparencia], tela.getBotaoFecharMsg().posX, 
						tela.getBotaoFecharMsg().posY, null);
				g.drawImage(tela.getBotaoSimMsg().sprites[tela.getBotaoSimMsg().aparencia], tela.getBotaoSimMsg().posX, 
						tela.getBotaoSimMsg().posY, null);
				g.drawImage(tela.getBotaoNaoMsg().sprites[tela.getBotaoNaoMsg().aparencia], tela.getBotaoNaoMsg().posX, 
						tela.getBotaoNaoMsg().posY, null);
			}
			tela.getGameOverPanel().getJogarNovamente().repaint();
			tela.getGameOverPanel().getSair().repaint();
		}
		
		if(Util.flagDesarmada) {
			tela.getJogoPanel().setVisible(false);
			tela.getGameOverPanel().setVisible(true);
			g.drawImage(tela.getGameOverPanel().getImagemSucesso().getImage(), -35, -35, null);
			int tempoDec = tela.getJogoPanel().getBomba().getRelogio().getTempo();
			
			g.setFont(Util.getFont("Anton-Regular",40));
			g.setColor(Color.white);
			
			relogio = tela.getJogoPanel().getBomba().getRelogio().calcularHora(Util.TEMPO_JOGO_SEG - tempoDec);
			
			g.drawString(relogio[0]+""+relogio[1]+":"+relogio[2]+""+relogio[3], 670, 635);
			
			if(Util.MOSTRAR_MENSAGEM) {
				g.drawImage(tela.getMensagem().sprites[tela.getMensagem().aparencia], tela.getMensagem().posX, tela.getMensagem().posY, null);
				g.drawImage(tela.getBotaoFecharMsg().sprites[tela.getBotaoFecharMsg().aparencia], tela.getBotaoFecharMsg().posX, 
						tela.getBotaoFecharMsg().posY, null);
				g.drawImage(tela.getBotaoNaoMsg().sprites[tela.getBotaoNaoMsg().aparencia], tela.getBotaoNaoMsg().posX, 
						tela.getBotaoNaoMsg().posY, null);
				g.drawImage(tela.getBotaoSimMsg().sprites[tela.getBotaoSimMsg().aparencia], tela.getBotaoSimMsg().posX, 
						tela.getBotaoSimMsg().posY, null);
				//OK
//				g.drawImage(tela.getBotaoOkMsg().sprites[tela.getBotaoOkMsg().aparencia], tela.getBotaoOkMsg().posX, 
//						tela.getBotaoOkMsg().posY, null);
			}
			
			tela.getGameOverPanel().getJogarNovamente().repaint();
			tela.getGameOverPanel().getSair().repaint();
			
		
		}
		
		
		
		
		g.dispose(); 
//	    g2.dispose();
		tela.getBuffer1().show();
	}
}
