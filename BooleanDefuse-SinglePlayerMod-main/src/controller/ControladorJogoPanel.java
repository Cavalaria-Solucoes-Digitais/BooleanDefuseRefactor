package controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.PaintEvent;
import java.awt.image.BufferedImage;
import java.net.URI;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import model.Bomba;
import model.Sons;
import model.Util;
import view.JogoPanel;
import view.Mensagem;
import view.Tela;

public class ControladorJogoPanel{
	private JogoPanel jogoPanel;
	private Tela tela;
	Controlador c;
	int resposta;
	private String respostaChat;
	private String respostaMesa;
	private URI link;

	public ControladorJogoPanel(JogoPanel jogoPanel,Tela tela) {
		super();
		respostaMesa = "";
		respostaChat = "";
		this.jogoPanel = jogoPanel;
		c = new Controlador();

		this.tela = tela;
		


		
		this.jogoPanel.getSairButton().addActionListener(c);
		
		tela.addMouseListener(c);
		
		jogoPanel.addMouseMotionListener(c);
		jogoPanel.addMouseListener(c);

		jogoPanel.getEnviarChat().addKeyListener(c);
		jogoPanel.getEnviarChat().addMouseListener(c);

		tela.getDificuldadePanel().getFacilButton().addActionListener(c);
		tela.getDificuldadePanel().getNormalButton().addActionListener(c);
		tela.getDificuldadePanel().getSairButton().addActionListener(c);
		tela.getDificuldadePanel().getNormalButton().addMouseListener(c);
		tela.getDificuldadePanel().getFacilButton().addMouseListener(c);

		tela.getGameOverPanel().getSair().addActionListener(c);
		tela.getGameOverPanel().getJogarNovamente().addActionListener(c);
		tela.getGameOverPanel().addMouseListener(c);

		tela.getMenuPanel().getSairButton().addActionListener(c);
		tela.getMenuPanel().getPlayButton().addActionListener(c);
		
		tela.getMenuPanel().addMouseListener(c);
		tela.getMenuPanel().addMouseMotionListener(c);

	}

	private class Controlador implements ActionListener, MouseListener, MouseMotionListener, KeyListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Sons.tocar("BooleanDefuse-files/Sons/botao.wav");
			if(!jogoPanel.getBomba().getModuloQuiz().isStatus()) {
				if(jogoPanel.getBomba().desativarBomba()) {
					tela.getGameOverPanel().setVisible(true);
				}
			}			

			if(e.getSource()==tela.getGameOverPanel().getJogarNovamente()) {
				conferirPartidasJogadas();
				
				tela.getGameOverPanel().setVisible(false);
				Util.explodir = false;
				Util.flagDesarmada = false;
				Util.flagMostrarDica = false;
				jogoPanel.getBomba().reiniciarBomba();
				iniciarPartida();
				jogoPanel.setVisible(true);
				Sons.tocar("BooleanDefuse-files/Sons/cap_priece.wav");		
			}else if(e.getSource()==tela.getMenuPanel().getPlayButton()) {
				jogoPanel.getBomba().reiniciarBomba();
				tela.getMenuPanel().setVisible(false);
				tela.getDificuldadePanel().setVisible(true);
				
			}else if(e.getSource()==tela.getGameOverPanel().getSair()) {
				tela.getMensagem().aparencia = 1;
				Util.MOSTRAR_MENSAGEM = true;
			}else if(e.getSource()==tela.getMenuPanel().getSairButton()) {
				tela.getMensagem().aparencia = 0;
				Util.MOSTRAR_MENSAGEM = true;
			}else if(e.getSource()==tela.getJogoPanel().getSairButton()) {	
				tela.getMensagem().aparencia = 1;
				Util.MOSTRAR_MENSAGEM = true;
			}else if(e.getSource()==tela.getDificuldadePanel().getFacilButton()) {
				Sons.tocar("BooleanDefuse-files/Sons/botao.wav");
				Util.DIFICULDADE_FACIL = true;
				Util.TUTORIAL = true;
				iniciarPartida();			
			}else if(e.getSource()==tela.getDificuldadePanel().getNormalButton()) {
				Sons.tocar("BooleanDefuse-files/Sons/botao.wav");
				Util.DIFICULDADE_FACIL = false;
				Util.TUTORIAL = false;
				iniciarPartida();
			}else if(e.getSource()==tela.getDificuldadePanel().getSairButton()) {
				tela.getDificuldadePanel().setVisible(false);
				tela.getMenuPanel().setVisible(true);
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			Util.flagEasterEgg = false;

			if(Util.DIFICULDADE_FACIL) {
				if(jogoPanel.getBomba().getModuloFios().colisaoAjuda(e.getX(), e.getY())) {
					Sons.tocar("BooleanDefuse-files/Sons/botao.wav");
					if(e.getClickCount()==3)
						Util.flagEasterEgg = true;
					if(!Util.flagMostrarDica || (Util.flagMostrarDica && jogoPanel.getBomba().getModuloFios().getDica().aparencia != 0)) {
						jogoPanel.getBomba().getModuloFios().getDica().aparencia = 0;
						jogoPanel.getBomba().getModuloFios().getDica().posX = 104;
						jogoPanel.getBomba().getModuloFios().getDica().posY = 165;
						Util.flagMostrarDica = true;
					}else {
						Util.flagMostrarDica = false;
					}
				}else if(jogoPanel.getBomba().getModuloQuiz().colisaoAjuda(e.getX(), e.getY())) {
					//					Mensagem.dicas(1);
					Sons.tocar("BooleanDefuse-files/Sons/botao.wav");
					if(!Util.flagMostrarDica || (Util.flagMostrarDica && jogoPanel.getBomba().getModuloFios().getDica().aparencia != 1)) {
						jogoPanel.getBomba().getModuloFios().getDica().aparencia = 1;
						jogoPanel.getBomba().getModuloFios().getDica().posX = 234;
						jogoPanel.getBomba().getModuloFios().getDica().posY = 126;
						Util.flagMostrarDica = true;
					}else {
						Util.flagMostrarDica = false;
					}
				}else if(jogoPanel.getBomba().getModuloTesteMesa().colisaoAjuda(e.getX(), e.getY())) {
					//					Mensagem.dicas(2);
					Sons.tocar("BooleanDefuse-files/Sons/botao.wav");
					if(!Util.flagMostrarDica || (Util.flagMostrarDica && jogoPanel.getBomba().getModuloFios().getDica().aparencia != 2)) {
						jogoPanel.getBomba().getModuloFios().getDica().aparencia = 2;
						jogoPanel.getBomba().getModuloFios().getDica().posX = 720;
						jogoPanel.getBomba().getModuloFios().getDica().posY = 165;
						Util.flagMostrarDica = true;

					}else {
						Util.flagMostrarDica = false;
					}
				}else if(jogoPanel.getBomba().getModuloMorse().colisaoAjuda(e.getX(), e.getY())) {
					//					Mensagem.dicas(3);
					Sons.tocar("BooleanDefuse-files/Sons/botao.wav");
					if(!Util.flagMostrarDica || (Util.flagMostrarDica && jogoPanel.getBomba().getModuloFios().getDica().aparencia != 3)) {
						jogoPanel.getBomba().getModuloFios().getDica().aparencia = 3;
						jogoPanel.getBomba().getModuloFios().getDica().posX = 104;
						jogoPanel.getBomba().getModuloFios().getDica().posY = 325;	
						Util.flagMostrarDica = true;
					}else {
						Util.flagMostrarDica = false;
					}

				}else if(tela.getJogoPanel().getBomba().getCelular().colisaoAjuda(e.getX(), e.getY())) {
					Sons.tocar("BooleanDefuse-files/Sons/botao.wav");
					if(!Util.flagMostrarDica || (Util.flagMostrarDica && jogoPanel.getBomba().getCelular().getAjuda().aparencia != 4)) {
						jogoPanel.getBomba().getModuloFios().getDica().aparencia = 4;
						jogoPanel.getBomba().getModuloFios().getDica().posX = 1060;
						jogoPanel.getBomba().getModuloFios().getDica().posY = 145;
						Util.flagMostrarDica = true;
					}else {
						Util.flagMostrarDica = false;
					}
				}else {
					Util.flagMostrarDica = false;
				}
			}
			if ((Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_IR) && Util.TUTORIAL) {
				Sons.tocar("BooleanDefuse-files/Sons/botao.wav");
				Util.DIFICULDADE_FACIL = true;
				Util.TUTORIAL = false;
				tela.getJogoPanel().getBomba().iniciarBomba();
				Sons.tocar("BooleanDefuse-files/Sons/cap_priece.wav");
				initMsgm();
			}else if ((Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_LINK) && Util.TUTORIAL) {
				try{
		            link = new URI("https://www.youtube.com/watch?v=WonqHRwDi54");
		            Desktop.getDesktop().browse(link);
		        }catch(Exception erro){
		            System.out.println(erro);
		        }
			}

			if(!Util.TUTORIAL) {
				if(tela.getJogoPanel().isVisible()) {
					if(e.getSource() == jogoPanel.getEnviarChat() && 
							jogoPanel.getEnviarChat().getText().equals(Util.TEXTO_DEFAULT_CHAT_ENVIAR)) {
						jogoPanel.getEnviarChat().setText("");
					}
					jogoPanel.getBomba().getModuloFios().cortarFios(e.getX(), e.getY());
					jogoPanel.getBomba().desativarBomba();
					if(jogoPanel.getBomba().explodir()) {
						jogoPanel.setVisible(false);
						tela.getGameOverPanel().setVisible(true);
					} 

					if((Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_FALSO) && tela.getJogoPanel().isVisible()) {
						if(jogoPanel.getBomba().getModuloQuiz().corrigirResposta(false)) {
							jogoPanel.getBomba().getModuloQuiz().getLedStatus().aparencia=1;
							Sons.tocar("BooleanDefuse-files/Sons/acerto.wav");
						}else {
							Sons.tocar("BooleanDefuse-files/Sons/erro.wav");
							Util.explodir = true;
							jogoPanel.getBomba().explodir();
							jogoPanel.getBomba().getModuloQuiz().setPergunta(jogoPanel.getBomba().getModuloQuiz().selecionarPergunta());
							jogoPanel.getPerguntaModuloQuiz().setText(jogoPanel.getBomba().getModuloQuiz().getPergunta().getPergunta());
						}
					}else if ((Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_VERDADEIRO) && tela.getJogoPanel().isVisible()) {
						if(jogoPanel.getBomba().getModuloQuiz().corrigirResposta(true)) {
							jogoPanel.getBomba().getModuloQuiz().getLedStatus().aparencia=1;
							Sons.tocar("BooleanDefuse-files/Sons/acerto.wav");
						}else {
							Sons.tocar("BooleanDefuse-files/Sons/erro.wav");
							Util.explodir = true;
							jogoPanel.getBomba().explodir();
							jogoPanel.getBomba().getModuloQuiz().setPergunta(jogoPanel.getBomba().getModuloQuiz().selecionarPergunta());
							jogoPanel.getPerguntaModuloQuiz().setText(jogoPanel.getBomba().getModuloQuiz().getPergunta().getPergunta());
//							if(jogoPanel.getBomba().getRelogio().getTempo()<=10) {
//								jogoPanel.getBomba().getRelogio().setTempo(0);
//							}else {
//								jogoPanel.getBomba().getRelogio().setTempo(jogoPanel.getBomba().getRelogio().getTempo()-10);
//								jogoPanel.getBomba().getModuloQuiz().setPergunta(jogoPanel.getBomba().getModuloQuiz().selecionarPergunta());
//								jogoPanel.getPerguntaModuloQuiz().setText(jogoPanel.getBomba().getModuloQuiz().getPergunta().getPergunta());
//							}

						}
					}else if((Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_UP_MORSE_VALOR1) && tela.getJogoPanel().isVisible()) {
						if(jogoPanel.getValor1().aparencia<12) {
							jogoPanel.getValor1().aparencia++;
						}else {
							jogoPanel.getValor1().aparencia=0;
						}
						jogoPanel.getBomba().getModuloMorse().corrigirResposta(mapValoresBotoes(jogoPanel.getValor1().aparencia),
								mapValoresBotoes(jogoPanel.getValor2().aparencia), mapOperadoresBotoes(jogoPanel.getOperador().aparencia),
								mapValoresBotoes(jogoPanel.getResultado().aparencia));
					}else if((Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_DOWN_MORSE_VALOR1) && tela.getJogoPanel().isVisible()) {
						if(jogoPanel.getValor1().aparencia>0) {
							jogoPanel.getValor1().aparencia--;
						}else {
							jogoPanel.getValor1().aparencia=12;
						}
						jogoPanel.getBomba().getModuloMorse().corrigirResposta(mapValoresBotoes(jogoPanel.getValor1().aparencia),
								mapValoresBotoes(jogoPanel.getValor2().aparencia), mapOperadoresBotoes(jogoPanel.getOperador().aparencia),
								mapValoresBotoes(jogoPanel.getResultado().aparencia));
					}else if((Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_UP_MORSE_OPERADOR) && tela.getJogoPanel().isVisible()) {
						if(jogoPanel.getOperador().aparencia<6) {
							jogoPanel.getOperador().aparencia++;
						}else {
							jogoPanel.getOperador().aparencia=0;
						}
						jogoPanel.getBomba().getModuloMorse().corrigirResposta(mapValoresBotoes(jogoPanel.getValor1().aparencia),
								mapValoresBotoes(jogoPanel.getValor2().aparencia), mapOperadoresBotoes(jogoPanel.getOperador().aparencia),
								mapValoresBotoes(jogoPanel.getResultado().aparencia));
					}else if((Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_DOWN_MORSE_OPERADOR) && tela.getJogoPanel().isVisible()) {
						if(jogoPanel.getOperador().aparencia>0) {
							jogoPanel.getOperador().aparencia--;
						}else {
							jogoPanel.getOperador().aparencia=6;
						}
						jogoPanel.getBomba().getModuloMorse().corrigirResposta(mapValoresBotoes(jogoPanel.getValor1().aparencia),
								mapValoresBotoes(jogoPanel.getValor2().aparencia), mapOperadoresBotoes(jogoPanel.getOperador().aparencia),
								mapValoresBotoes(jogoPanel.getResultado().aparencia));
					}else if(Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_UP_MORSE_VALOR2) {
						if(jogoPanel.getValor2().aparencia<12) {
							jogoPanel.getValor2().aparencia++;
						}else {
							jogoPanel.getValor2().aparencia=0;
						}
						jogoPanel.getBomba().getModuloMorse().corrigirResposta(mapValoresBotoes(jogoPanel.getValor1().aparencia),
								mapValoresBotoes(jogoPanel.getValor2().aparencia), mapOperadoresBotoes(jogoPanel.getOperador().aparencia),
								mapValoresBotoes(jogoPanel.getResultado().aparencia));
					}else if(Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_DOWN_MORSE_VALOR2) {
						if(jogoPanel.getValor2().aparencia>0) {
							jogoPanel.getValor2().aparencia--;
						}else {
							jogoPanel.getValor2().aparencia=12;
						}
						jogoPanel.getBomba().getModuloMorse().corrigirResposta(mapValoresBotoes(jogoPanel.getValor1().aparencia),
								mapValoresBotoes(jogoPanel.getValor2().aparencia), mapOperadoresBotoes(jogoPanel.getOperador().aparencia),
								mapValoresBotoes(jogoPanel.getResultado().aparencia));
					}else if(Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_UP_MORSE_RESULTADO) {
						if(jogoPanel.getResultado().aparencia<12) {
							jogoPanel.getResultado().aparencia++;
						}else {
							jogoPanel.getResultado().aparencia=0;
						}
						jogoPanel.getBomba().getModuloMorse().corrigirResposta(mapValoresBotoes(jogoPanel.getValor1().aparencia),
								mapValoresBotoes(jogoPanel.getValor2().aparencia), mapOperadoresBotoes(jogoPanel.getOperador().aparencia),
								mapValoresBotoes(jogoPanel.getResultado().aparencia));
					}else if(Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_DOWN_MORSE_RESULTADO) {
						if(jogoPanel.getResultado().aparencia>0) {
							jogoPanel.getResultado().aparencia--;
						}else {
							jogoPanel.getResultado().aparencia=12;
						}
//						jogoPanel.getBomba().getModuloMorse().corrigirResposta(mapValoresBotoes(jogoPanel.getValor1().aparencia),
//								mapValoresBotoes(jogoPanel.getValor2().aparencia), mapOperadoresBotoes(jogoPanel.getOperador().aparencia),
//								mapValoresBotoes(jogoPanel.getResultado().aparencia));
					}
					
					else if(Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_DOWN_MESA_SAIDA1) {
						if(Util.indiceSaidaMesa > 0) {
							Util.indiceSaidaMesa--;
						}else {
							Util.indiceSaidaMesa = jogoPanel.getValoresSaida().size()-1;
						}
						actionBotoesMesa();
					}else if(Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_UP_MESA_SAIDA1) {
						if(Util.indiceSaidaMesa < 999) {
							Util.indiceSaidaMesa++;
						}else {
							Util.indiceSaidaMesa = 0;
						}
						actionBotoesMesa();
					}
					else if(Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_SOM) {
						if(jogoPanel.getSomSprite().aparencia == 0) {
							jogoPanel.getSomSprite().aparencia = 1;
							tela.getMenuPanel().getSomSprite().aparencia = 1;
							Util.SOM = false;
							tela.getMenuPanel().getTema().pararDeTocarInstance();
							
						}else {
							jogoPanel.getSomSprite().aparencia = 0;
							tela.getMenuPanel().getSomSprite().aparencia = 0;
							Util.SOM = true;
							if(tela.getMenuPanel().isVisible()) {
								tela.getMenuPanel().reiniciarTema();
							}
						}
					}else if(Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_VERIFICAR_INDEX) {
						verificarRespostaTesteMesa();
					}
				}

			}
			if(Util.MOSTRAR_MENSAGEM) {
				if(Util.colisaoSprites(e.getX(), e.getY()) == Util.FECHAR_MENSAGEM) {
					Util.MOSTRAR_MENSAGEM = false;
				}else if(Util.colisaoSprites(e.getX(), e.getY()) == Util.NAO_MENSAGEM) {
					Util.MOSTRAR_MENSAGEM = false;
				}else if((Util.colisaoSprites(e.getX(), e.getY()) == Util.SIM_MENSAGEM) && tela.getMenuPanel().isVisible()) {
					System.exit(0);
				}else if((Util.colisaoSprites(e.getX(), e.getY()) == Util.SIM_MENSAGEM) && tela.getJogoPanel().isVisible()) {
					tela.getJogoPanel().getBomba().parar();
					tela.getJogoPanel().setVisible(false);
					tela.getMenuPanel().getTema().tocarInstance();
					tela.getMenuPanel().setVisible(true);
					Util.flagDesarmada = false;
					tela.getMenuPanel().reiniciarTema();
					Util.MOSTRAR_MENSAGEM = false;
				}else if((Util.colisaoSprites(e.getX(), e.getY()) == Util.SIM_MENSAGEM) && tela.getGameOverPanel().isVisible()) {
					tela.getJogoPanel().getBomba().parar();
					tela.getJogoPanel().setVisible(false);
					tela.getGameOverPanel().setVisible(false);
					tela.getMenuPanel().getTema().tocarInstance();
					tela.getMenuPanel().setVisible(true);
					Util.flagDesarmada = false;
					Util.explodir = false;
					tela.getMenuPanel().reiniciarTema();
					Util.MOSTRAR_MENSAGEM = false;
				}
			}else if((Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_SOM) && 
					(tela.getMenuPanel().isVisible())) {
				if(jogoPanel.getSomSprite().aparencia == 0) {
					jogoPanel.getSomSprite().aparencia = 1;
					tela.getMenuPanel().getSomSprite().aparencia = 1;
					Util.SOM = false;
					tela.getMenuPanel().getTema().pararDeTocarInstance();
					
				}else {
					jogoPanel.getSomSprite().aparencia = 0;
					tela.getMenuPanel().getSomSprite().aparencia = 0;
					Util.SOM = true;
					if(tela.getMenuPanel().isVisible()) {
						tela.getMenuPanel().reiniciarTema();
					}
				}
			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			if(arg0.getSource()==tela.getDificuldadePanel().getFacilButton()) {
				Util.textoDificuldade = true;
				tela.getDificuldadePanel().getTextoDificuldade().aparencia = 0;
			}else if(arg0.getSource()==tela.getDificuldadePanel().getNormalButton()) {
				Util.textoDificuldade = true;
				tela.getDificuldadePanel().getTextoDificuldade().aparencia = 1;
			}

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			//			if((arg0.getSource()==jogoPanel.getVerdadeiroButton()) || arg0.getSource()==jogoPanel.getFalsoButton())
			//				jogoPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));				
			//			else 
			if(arg0.getSource()==tela.getDificuldadePanel().getFacilButton()) {
				Util.textoDificuldade = false;
			}else if(arg0.getSource()==tela.getDificuldadePanel().getNormalButton()) {
				Util.textoDificuldade = false;
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(!Util.TUTORIAL) {
				jogoPanel.getBomba().getModuloFios().colisaoFios(e.getX(), e.getY());
				if(jogoPanel.getBomba().getModuloFios().emCimaDoFio(e.getX(), e.getY())) {
					jogoPanel.getBomba().getModuloFios().getAlicate().aparencia = 0;
					jogoPanel.getBomba().getModuloFios().getAlicate().posX = e.getX();
					jogoPanel.getBomba().getModuloFios().getAlicate().posY = e.getY();
					jogoPanel.setCursor(jogoPanel.getToolkit().createCustomCursor(
							new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
							"null"));	
				}else {
					jogoPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					jogoPanel.getBomba().getModuloFios().getAlicate().aparencia = 1;
				}

				if(Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_VERDADEIRO) {
					jogoPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}else {
					jogoPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}

				if((Util.colisaoSprites(e.getX(), e.getY()) != 666)) {
					jogoPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					if(Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_SOM) {
						tela.getMenuPanel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					}
				}else {
					jogoPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					tela.getMenuPanel().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}

				
			}
			if(Util.DIFICULDADE_FACIL) {
				jogoPanel.getBomba().getModuloFios().colisaoAjuda(e.getX(), e.getY());
				jogoPanel.getBomba().getModuloMorse().colisaoAjuda(e.getX(), e.getY());
				jogoPanel.getBomba().getModuloQuiz().colisaoAjuda(e.getX(), e.getY());
				jogoPanel.getBomba().getModuloTesteMesa().colisaoAjuda(e.getX(), e.getY());
			}
			
			if((Util.colisaoSprites(e.getX(), e.getY()) == Util.BOTAO_LINK)) {
				jogoPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	
			}else {
				jogoPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getSource() == jogoPanel.getEnviarChat()) {
				if(e.getKeyCode()==e.VK_ENTER) {
					respostaChat = jogoPanel.getEnviarChat().getText();
					jogoPanel.getChatCelular().setText(jogoPanel.getBomba().getCelular().novaMensagem(respostaChat,true));
					
					if(respostaChat.equalsIgnoreCase("ok") && jogoPanel.getBomba().getCelular().getChat().isModFios()) {
						Util.fiosOk++;
						jogoPanel.getBomba().getModuloFios().acaoCorreta();
						jogoPanel.getBomba().explodir();
						jogoPanel.getEnviarChat().setText("");
					}else if(respostaChat.equalsIgnoreCase("ok") && jogoPanel.getBomba().getCelular().getChat().isModMorse()){
						boolean rep = jogoPanel.getBomba().getModuloMorse().corrigirResposta(mapValoresBotoes(jogoPanel.getValor1().aparencia),
								mapValoresBotoes(jogoPanel.getValor2().aparencia), mapOperadoresBotoes(jogoPanel.getOperador().aparencia),
								mapValoresBotoes(jogoPanel.getResultado().aparencia));
						if(!rep) {
							Util.explodir = true;
							jogoPanel.getBomba().explodir();
						}
					}
					jogoPanel.getBomba().desativarBomba();
					jogoPanel.getChatCelular().setText(jogoPanel.getBomba().getCelular().novaMensagem(jogoPanel.getBomba().getCelular().
							getChat().conversa(respostaChat), false));
					jogoPanel.getEnviarChat().setText("");
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {


		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		public void conferirPartidasJogadas() {
			if(!Util.DIFICULDADE_FACIL) {
				jogoPanel.getBomba().getModuloFios().getAjuda().aparencia=2;
				jogoPanel.getBomba().getModuloMorse().getAjuda().aparencia=2;
				jogoPanel.getBomba().getModuloQuiz().getAjuda().aparencia=2;
				jogoPanel.getBomba().getModuloTesteMesa().getAjuda().aparencia=2;
				jogoPanel.getBomba().getCelular().getAjuda().aparencia=2;
			}
		}

		public void iniciarPartida() {
			conferirPartidasJogadas();
					
			tela.getMenuPanel().parar();
			tela.getDificuldadePanel().setVisible(false);
			tela.getMenuPanel().getTema().pararDeTocarInstance();

			respostaMesa = "";
			
			tela.getJogoPanel().getValor1().aparencia = 0;
			tela.getJogoPanel().getValor2().aparencia = 0;
			tela.getJogoPanel().getOperador().aparencia = 0;
			tela.getJogoPanel().getResultado().aparencia = 0;
			Util.indiceSaidaMesa = 0;
			tela.getJogoPanel().getSaidaField().setText("0");

			Util.flagMostrarDica = false;
			Util.fiosOk = -1;
			Util.FIOS_CORTADOS = 0;

			tela.getJogoPanel().setVisible(true);

			if(!Util.TUTORIAL) {
				tela.getJogoPanel().getBomba().iniciarBomba();
				Sons.tocar("BooleanDefuse-files/Sons/cap_priece.wav");
				
			}
			jogoPanel.getBomba().getModuloQuiz().setPergunta(jogoPanel.getBomba().getModuloQuiz().selecionarPergunta());
			jogoPanel.getPerguntaModuloQuiz().setText(jogoPanel.getBomba().getModuloQuiz().getPergunta().getPergunta());
			initMsgm();
		}
		
		public void initMsgm() {
			
			jogoPanel.getBomba().getCelular().novaMensagem("Jigsaw na linha! Você tem 10 minutos para desarmar essa bomba, ou voará pelos ares."
					+ " Então é bom que esteja "
					+ "com papel e caneta. O mais importante é você prestar atenção em tudo que eu falar, caso queira sair dessa vivo!!!", false);
			jogoPanel.getChatCelular().setText(jogoPanel.getBomba().getCelular().novaMensagem("Observe que a BOMBA C-4 está ligada em "
					+ "um circuito composto por 4 módulos independentes: Módulo Fios, Módulo Quiz, Módulo Teste de Mesa e Módulo Morse.  "
					+ "Caso você desarme incorretamente um módulo, você acionará a bomba. "
					+ "<br><br>"
					+ "Então, preciso saber por onde você quer começar. Digite o número respectivo ao módulo que você quer desarmar primeiro.  "
					+ "<br><br>Por exemplo: 1 para iniciar o módulo Fios.<br><br>"
					+ "<b>1</b> para: <b>Fios</b><br>"
					+ "<b>2</b> para: <b>Quiz</b><br>"
					+ "<b>3</b> para: <b>Teste de Mesa</b><br>"
					+ "<b>4</b> para: <b>Morse</b><br>"
					+ "", false));
//			jogoPanel.getChatScroll().setAutoscrolls(false);
		}

		public void actionBotoesMesa() {
			jogoPanel.getSaidaField().setText(jogoPanel.getValoresSaida().get(Util.indiceSaidaMesa));
		}
		
		public void verificarRespostaTesteMesa() {
			respostaMesa = jogoPanel.getSaidaField().getText();
			if(jogoPanel.getBomba().getModuloTesteMesa().corrigir(respostaMesa)) {
				jogoPanel.getBomba().getModuloTesteMesa().setStatus(true);
				jogoPanel.getBomba().desativarBomba();
			}else {
				Util.explodir = true;
				jogoPanel.getBomba().explodir();
			}
		}

		public String mapValoresBotoes(int aparencia) {
			switch (aparencia) {
			case 1:
				return "0";
			case 2:
				return "1";
			case 3:
				return "2";
			case 4:
				return "3";
			case 5:
				return "4";
			case 6:
				return "5";
			case 7:
				return "6";
			case 8:
				return "7";
			case 9:
				return "8";
			case 10:
				return "9";
			case 11:
				return "F";
			case 12:
				return "V";
			default:
				return "";
			}
		}
		public String mapOperadoresBotoes(int aparencia) {
			switch (aparencia) {
			case 1:
				return ">";
			case 2:
				return "<";
			case 3:
				return "==";
			case 4:
				return "!=";
			case 5:
				return "&&";
			case 6:
				return "||";
			default:
				return "";
			}
		}
	}




}
