/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.Bomba;
import model.Sprite;
import model.Util;

/**
 *
 * @author Elvis Nogueira
 */
public class JogoPanel extends JPanel{
    private ImageIcon bg, venceu;
    private Bomba bomba;
    private JButton sairButton;
    private JTextArea perguntaModuloQuiz;
    private JTextPane chatCelular;
    private JScrollPane chatScroll;
    private JTextField enviarChat;
    private ArrayList<String> valoresSaida;
    
    private Sprite cabecalhoSprite, botaoIr; 
    
    //MÓDULO QUIZ
    private Sprite botaoVerdadeiro, botaoFalso;
    
    //MÓDULO MORSE
    private Sprite valor1,operador,valor2, resultado;
    private Sprite upMorseValor1, downMorseValor1,upMorseOperador, downMorseIOperador, upMorseValor2, downMorseValor2,
    		upMorseResultado, downMorseResultado;
    
    //MÓDULO MESA
    private Sprite upSaida1Mesa, downSaida1Mesa, botaoVerificar;
 
	private JTextField saidaField;
	
	
    private Sprite somSprite, tutorial;
    private Sprite botaoLink;
    

    public JogoPanel() {
        setSize(Util.LARGURA, Util.ALTURA);
        setLayout(null);

        
        bg = new ImageIcon("BooleanDefuse-files/Imagens//BACKGROUND.png");
        venceu = new ImageIcon("BooleanDefuse-files/Imagens//VENCEU.png");
       
        bomba = new Bomba();
        
        perguntaModuloQuiz = new JTextArea();
        perguntaModuloQuiz.setSize(190, 30);
        perguntaModuloQuiz.setEditable(false);
        perguntaModuloQuiz.setLineWrap(true);
        perguntaModuloQuiz.setText(bomba.getModuloQuiz().getPergunta().getPergunta());
        perguntaModuloQuiz.setLocation(482, 180);
        valoresSaida = new ArrayList<String>();
        for (int i=0; i<1000;i++) {
        	valoresSaida.add(i+"");
        }
        try {
        	tutorial = new Sprite("BooleanDefuse-files/Imagens//IMG-TUTORIAL2.png", 0, 1389, 
					770, 1, 1, 8, -5);
        	//Quiz
			botaoVerdadeiro = new Sprite("BooleanDefuse-files/Imagens//BOTOES-VERDADEIRO-FALSE.png", 1, 100, 
					34, 1, 2, 472, 240);
			botaoFalso = new Sprite("BooleanDefuse-files/Imagens//BOTOES-VERDADEIRO-FALSE.png", 0, 100, 
					34, 1, 2, 577, 240);
			
			//Morse
			upMorseValor1 = new Sprite("BooleanDefuse-files/Imagens//BOTAO-UP-DOWN.png", 0, 10, 10, 1, 2, 172, 570);
			valor1 = new Sprite("BooleanDefuse-files/Imagens//BOTAO-VALORES.png", 0, 30, 30, 1, 13, 160, 583);
			downMorseValor1 = new Sprite("BooleanDefuse-files/Imagens//BOTAO-UP-DOWN.png", 1, 10, 10, 1, 2, 172, 616);
			
			upMorseOperador = new Sprite("BooleanDefuse-files/Imagens//BOTAO-UP-DOWN.png", 0, 10, 10, 1, 2, 222, 570);
			operador = new Sprite("BooleanDefuse-files/Imagens//botaoOperadores.png", 0, 30, 30, 1, 7, 210, 583);
			downMorseIOperador = new Sprite("BooleanDefuse-files/Imagens//BOTAO-UP-DOWN.png", 1, 10, 10, 1, 2, 222, 616);
			
			upMorseValor2 = new Sprite("BooleanDefuse-files/Imagens//BOTAO-UP-DOWN.png", 0, 10, 10, 1, 2, 272, 570);
			valor2 = new Sprite("BooleanDefuse-files/Imagens//BOTAO-VALORES.png", 0, 30, 30, 1, 13, 260, 583);
			downMorseValor2 = new Sprite("BooleanDefuse-files/Imagens//BOTAO-UP-DOWN.png", 1, 10, 10, 1, 2, 272, 616);
			
			upMorseResultado = new Sprite("BooleanDefuse-files/Imagens//BOTAO-UP-DOWN.png", 0, 10, 10, 1, 2, 372, 570);
			resultado =  new Sprite("BooleanDefuse-files/Imagens//BOTAO-VALORES.png", 0, 30, 30, 1, 13, 360, 583);
			downMorseResultado = new Sprite("BooleanDefuse-files/Imagens//BOTAO-UP-DOWN.png", 1, 10, 10, 1, 2, 372, 616);
			
			//Mesa
			upSaida1Mesa = new Sprite("BooleanDefuse-files/Imagens//BOTAO-UP-DOWN.png", 0, 10, 10, 1, 2, 872, 414);
			downSaida1Mesa = new Sprite("BooleanDefuse-files/Imagens//BOTAO-UP-DOWN.png", 1, 10, 10, 1, 2, 872, 463);
			botaoVerificar = new Sprite("BooleanDefuse-files/Imagens//botao-validar.png", 0, 30, 30, 1, 1, 910, 428);
			
			//Cabeçalho
			cabecalhoSprite = new Sprite("BooleanDefuse-files/Imagens//cabecalho jigsaw.png", 0, 295, 50, 1, 1, 1016, 110);
			
			somSprite = new Sprite("BooleanDefuse-files/Imagens//SOM 2.png", 0, 51, 50, 1, 2,  1240, 5);
			botaoIr = new Sprite("BooleanDefuse-files/Imagens//botaoIr.png", 0, 150, 60, 1, 1, 50, 0);
			
//			botaoLink = new Sprite("BooleanDefuse-files/Imagens//Botao-link.png", 0, 341, 46, 1, 1, 690, 370);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        sairButton = new JButton();
        sairButton.setIcon(new ImageIcon("BooleanDefuse-files/Imagens//sair.png"));
        sairButton.setBackground(Color.red);
        sairButton.setToolTipText("Voltar para o menu");
        
        saidaField = new JTextField("0");
        saidaField.setHorizontalAlignment(saidaField.CENTER);
        saidaField.setBackground(new Color(126,206,23));
        saidaField.setBorder(new LineBorder(Color.BLACK,5));
        saidaField.setEditable(false);
        
        chatCelular = new JTextPane();
        chatCelular.setEditable(false);
        chatCelular.setBorder(new LineBorder(Color.gray));
        chatCelular.setBackground(new Color(229, 221, 213));
        chatCelular.setContentType("text/html");
        
       
        
        bomba.getCelular().novaMensagem("Melinda May na linha! Serei sua especialista então é bom que esteja com papel "
        		+ "e caneta. Sim, e o mais importante "
        		+ "preste atenção em tudo que eu falar!!!", false);
        chatCelular.setText(bomba.getCelular().novaMensagem("Primeiro preciso saber por onde você quer começar. Me envie o número respectivo ao "
        		+ "módulo que você quer desarmar primeiro. <br>1) Fios<br>2) Quiz<br>3) Teste de Mesa<br>4) Morse", false));
        
        chatScroll = new JScrollPane(chatCelular);
       
        enviarChat = new JTextField(Util.TEXTO_DEFAULT_CHAT_ENVIAR);

        add(perguntaModuloQuiz);
        
        add(enviarChat).setBounds(1018, 620, 295, 50);
        add(saidaField).setBounds(842,428,60,30);
      
        
        add(chatScroll).setBounds(1018, 163, 295, 450);
        
        
        
        add(sairButton).setBounds(1145, 3, 60, 60);
        
        setVisible(false);
    }
    
    public boolean colisaoIr(int posXMouse,int posYMouse) {
    	if(colide(botaoIr, posXMouse, posYMouse)) {
			return true;
		}else {
			botaoIr.aparencia=0;
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

	public ImageIcon getBG() {
		return bg;
	}

	public Bomba getBomba() {
		return bomba;
	}

	public void setBomba(Bomba bomba) {
		this.bomba = bomba;
	}

	public JTextArea getPerguntaModuloQuiz() {
		return perguntaModuloQuiz;
	}

	public ImageIcon getBg() {
		return bg;
	}

	public JButton getSairButton() {
		return sairButton;
	}

	public ImageIcon getVenceu() {
		return venceu;
	}

	public JTextPane getChatCelular() {
		return chatCelular;
	}

	public JScrollPane getChatScroll() {
		return chatScroll;
	}

	public JTextField getEnviarChat() {
		return enviarChat;
	}


	public Sprite getBotaoVerdadeiro() {
		return botaoVerdadeiro;
	}

	public Sprite getBotaoFalso() {
		return botaoFalso;
	}

	public Sprite getValor1() {
		return valor1;
	}

	public Sprite getOperador() {
		return operador;
	}

	public Sprite getValor2() {
		return valor2;
	}

	public Sprite getUpMorseValor1() {
		return upMorseValor1;
	}

	public Sprite getDownMorseValor1() {
		return downMorseValor1;
	}

	public Sprite getUpMorseOperador() {
		return upMorseOperador;
	}

	public Sprite getDownMorseIOperador() {
		return downMorseIOperador;
	}

	public Sprite getUpMorseValor2() {
		return upMorseValor2;
	}

	public Sprite getDownMorseValor2() {
		return downMorseValor2;
	}

	public Sprite getResultado() {
		return resultado;
	}

	public Sprite getUpMorseResultado() {
		return upMorseResultado;
	}

	public Sprite getDownMorseResultado() {
		return downMorseResultado;
	}

	public Sprite getUpSaida1Mesa() {
		return upSaida1Mesa;
	}

	public Sprite getDownSaida1Mesa() {
		return downSaida1Mesa;
	}

	public Sprite getCabecalhoSprite() {
		return cabecalhoSprite;
	}

	public Sprite getSomSprite() {
		return somSprite;
	}

	public Sprite getTutorial() {
		return tutorial;
	}

	public Sprite getBotaoIr() {
		return botaoIr;
	}

	public JTextField getSaidaField() {
		return saidaField;
	}

	public ArrayList<String> getValoresSaida() {
		return valoresSaida;
	}

	public Sprite getBotaoVerificar() {
		return botaoVerificar;
	}

	public Sprite getBotaoLink() {
		return botaoLink;
	}  
    
	
	
}
