/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import view.Mensagem;

/**
 *
 * @author Elvis Nogueira
 */
public class Util {
	public static int partidasJogadas = 0;
    public static final int ATIVADA=1, DESATIVADA=0, EXPLODIR=2;
    public static boolean explodir = false;
    public static final int ALTURA=768, LARGURA=1366;
    public static final int ERRRO = 0, SUCESSO = 1, INFO = 2,QUESTAO = 3;
    public static final int TEMPO_JOGO_SEG = 600;
    public static Font arial = new Font("Arial", Font.BOLD, 17);
    public static Font gabriola = new Font("Gabriola", Font.BOLD, 10);
    public static Color azulBic = new Color(25,78,146);
    public static final int FPS = 2;
	public static final String TEXTO_DEFAULT_CHAT_ENVIAR = " Digite a resposta e pressione ENTER para enviar";
    public static boolean flagDesarmada = false;
    public static boolean flagMostrarDica = false;
    public static boolean flagEasterEgg = false;
    public static boolean DIFICULDADE_FACIL = true;
    public static boolean textoDificuldade = false;
    public static boolean TUTORIAL = false;
    public static int FIOS_CORTADOS = 0;
    
    public static boolean statusFios = false;
    public static boolean statusQuiz = false;
    public static boolean statusMorse = false;
    public static boolean statusMesa = false;
    
    public static int indiceSaidaMesa = 0;
    
    private static final Rectangle verdadeiroRec = new Rectangle(465, 241, 100, 34);
    private static final Rectangle falsoRec = new Rectangle(570, 241, 100, 34);
    private static final Rectangle UP_MORSE_VALOR1 = new Rectangle(163, 568, 15, 30);
    private static final Rectangle DOWN_MORSE_VALOR1 = new Rectangle(163, 611, 15, 30);    
    private static final Rectangle UP_MORSE_OPERADOR = new Rectangle(213, 568, 15, 30);
    private static final Rectangle DOWN_MORSE_OPERADOR = new Rectangle(213, 611, 15, 30);
    private static final Rectangle UP_MORSE_VALOR2 = new Rectangle(263, 568, 15, 30);
    private static final Rectangle DOWN_MORSE_VALOR2 = new Rectangle(263, 611, 15, 30);
    private static final Rectangle UP_MORSE_RESULTADO = new Rectangle(363, 568, 15, 30);
    private static final Rectangle DOWN_MORSE_RESULTADO = new Rectangle(363, 611, 15, 30);
    private static final Rectangle UP_MESA_SAÍDA1 = new Rectangle(863, 412, 15, 30);
    private static final Rectangle DOWN_MESA_SAÍDA1 = new Rectangle(863, 455, 15, 30);
    private static final Rectangle SOM_RECTANGLE = new Rectangle(1230, 10, 51, 50);
    private static final Rectangle IR_JOGO = new Rectangle(30, 0, 150, 60);
    private static final Rectangle BOTAO_VERIFICAR = new Rectangle(900, 423, 30, 30);
    private static final Rectangle BOTAO_FECHAR_MSG = new Rectangle(840, 333, 100, 34);
    private static final Rectangle BOTAO_NAO_MSG = new Rectangle(760, 380, 100, 34);
    private static final Rectangle BOTAO_SIM_MSG = new Rectangle(507, 380, 100, 34);
    private static final Rectangle LINK = new Rectangle(690, 370, 341, 46);
    
    public static final int BOTAO_FALSO = 0;
    public static final int BOTAO_VERDADEIRO = 1;
    public static final int BOTAO_UP_MORSE_VALOR1 = 2;
    public static final int BOTAO_DOWN_MORSE_VALOR1 = 3;
    public static final int BOTAO_UP_MORSE_OPERADOR = 4;
    public static final int BOTAO_DOWN_MORSE_OPERADOR = 5;
    public static final int BOTAO_UP_MORSE_VALOR2 = 6;
    public static final int BOTAO_DOWN_MORSE_VALOR2 = 7;
    public static final int BOTAO_UP_MORSE_RESULTADO = 8;
    public static final int BOTAO_DOWN_MORSE_RESULTADO = 9;
    public static final int BOTAO_UP_MESA_SAIDA1 = 10;
    public static final int BOTAO_DOWN_MESA_SAIDA1 = 11;
//    public static final int BOTAO_UP_MESA_SAIDA2 = 12;
//    public static final int BOTAO_DOWN_MESA_SAIDA2 = 13;
    public static final int BOTAO_SOM = 14;
    public static final int BOTAO_IR = 15;
    public static final int BOTAO_VERIFICAR_INDEX = 16;
    public static final int FECHAR_MENSAGEM = 17;
    public static final int NAO_MENSAGEM = 18;
    public static final int SIM_MENSAGEM = 19;
    public static final int BOTAO_LINK = 20;
    public static boolean MOSTRAR_MENSAGEM = false;
	
    public static boolean SOM = true;
    
    public static int modulosDesativados = 0;
    
    public static int fiosOk = -1;
    public static boolean conferiuFio = false;
    
    
    public static Font getFont( String nomeFonte, int tamanho ){  
        Font font = null;  
        try{
        	font = Font.createFont(Font.TRUETYPE_FONT, new File("BooleanDefuse-files/Fontes/"+nomeFonte+".ttf"));
        }
        catch(IOException|FontFormatException e){
             Mensagem.mostrar("Erro ao carregar fonte!", Util.ERRRO);
        }
            font = font.deriveFont(Font.PLAIN, tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            return font;
    }  
    
    public static int colisaoSprites(int posX, int posY) {
    	Rectangle mouse = new Rectangle(posX, posY, 1, 1);
    	
    	if(falsoRec.intersects(mouse)) {
    		return BOTAO_FALSO; 
    	}else if(verdadeiroRec.intersects(mouse)) {
    		return BOTAO_VERDADEIRO; 
    	}else if(UP_MORSE_VALOR1.intersects(mouse)) {
    		return BOTAO_UP_MORSE_VALOR1;
    	}else if(DOWN_MORSE_VALOR1.intersects(mouse)) {
    		return BOTAO_DOWN_MORSE_VALOR1;
    	}else if(UP_MORSE_OPERADOR.intersects(mouse)) {
    		return BOTAO_UP_MORSE_OPERADOR;
    	}else if(DOWN_MORSE_OPERADOR.intersects(mouse)) {
    		return BOTAO_DOWN_MORSE_OPERADOR;
    	}else if(UP_MORSE_VALOR2.intersects(mouse)) {
    		return BOTAO_UP_MORSE_VALOR2;
    	}else if(DOWN_MORSE_VALOR2.intersects(mouse)) {
    		return BOTAO_DOWN_MORSE_VALOR2;
    	}else if(UP_MORSE_RESULTADO.intersects(mouse)) {
    		return BOTAO_UP_MORSE_RESULTADO;
    	}else if(DOWN_MORSE_RESULTADO.intersects(mouse)) {
    		return BOTAO_DOWN_MORSE_RESULTADO;
    	}else if(DOWN_MESA_SAÍDA1.intersects(mouse)) {
    		return BOTAO_DOWN_MESA_SAIDA1;
    	}else if(UP_MESA_SAÍDA1.intersects(mouse)) {
    		return BOTAO_UP_MESA_SAIDA1;
    	}
    	else if(SOM_RECTANGLE.intersects(mouse)) {
    		return BOTAO_SOM;
    	}else if(IR_JOGO.intersects(mouse)) {    		
    		return BOTAO_IR;
    	}else if(BOTAO_VERIFICAR.intersects(mouse)) {    		
    		return BOTAO_VERIFICAR_INDEX;
    	}else if (BOTAO_FECHAR_MSG.intersects(mouse)) {
			return FECHAR_MENSAGEM;
		}else if (BOTAO_NAO_MSG.intersects(mouse)) {
			return NAO_MENSAGEM;
		}else if(BOTAO_SIM_MSG.intersects(mouse)) {
			return SIM_MENSAGEM;
		}else if(LINK.intersects(mouse)) {
			return BOTAO_LINK;
		}
    	
    	return 666;
    }
    
    
}
