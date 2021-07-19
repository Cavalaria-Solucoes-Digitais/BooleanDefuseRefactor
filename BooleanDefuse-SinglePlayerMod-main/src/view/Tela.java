/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import model.Sprite;
import model.Util;

/**
 *
 * @author Elvis Nogueira
 */
public class Tela extends JFrame{
    private JogoPanel jogoPanel;
    private GameOverPanel gameOverPanel;
    private DificuldadePanel dificuldadePanel;
    private MenuPanel menuPanel;
    private BufferedImage buffer;;
    BufferStrategy buffer1;
    private Sprite mensagem;
    private Sprite botaoFecharMsg, botaoSimMsg, botaoNaoMsg, botaoOkMsg;
    
    
    
    public Tela(JogoPanel jogoPanel, GameOverPanel gameOverPanel, MenuPanel menuPanel, DificuldadePanel dificuldadePanel) {
    	super("Boolean Defuse");
    	setIconImage(new ImageIcon("BooleanDefuse-files/Imagens/icone.png").getImage());
        setSize(Util.LARGURA, Util.ALTURA);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);
        
       
        
        buffer = new BufferedImage(Util.LARGURA, Util.ALTURA, BufferedImage.TYPE_INT_RGB);
        
        try {
			mensagem = new Sprite("BooleanDefuse-files/Imagens/sprites-mensagens.png", 0, 400, 180, 1, 3, 500, 245);
			botaoFecharMsg = new Sprite("BooleanDefuse-files/Imagens/BOTOES-MENSAGEM.png", 3, 100, 34, 1, 4, 850, 228);
			botaoNaoMsg = new Sprite("BooleanDefuse-files/Imagens/BOTOES-MENSAGEM.png", 2, 100, 34, 1, 4, 770, 375);
			botaoSimMsg = new Sprite("BooleanDefuse-files/Imagens/BOTOES-MENSAGEM.png", 1, 100, 34, 1, 4, 517, 375);
			botaoOkMsg = new Sprite("BooleanDefuse-files/Imagens/BOTOES-MENSAGEM.png", 0, 100, 34, 1, 4, 645, 375);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        this.jogoPanel = jogoPanel;
        this.gameOverPanel = gameOverPanel;
        this.menuPanel = menuPanel;
        this.dificuldadePanel = dificuldadePanel;
        
        add(this.jogoPanel);
        add(menuPanel).setBounds(0, 0, Util.LARGURA, Util.ALTURA);
        add(this.gameOverPanel).setBounds(0, 0, Util.LARGURA, Util.ALTURA);
        add(this.gameOverPanel).setBounds(0, 0, Util.LARGURA, Util.ALTURA);
        add(this.dificuldadePanel).setBounds(0, 0, Util.LARGURA, Util.ALTURA);
        this.jogoPanel.setBounds(0, 0, Util.LARGURA, Util.ALTURA);
        
        
        
        setVisible(true);
        
    }

	public JogoPanel getJogoPanel() {
		return jogoPanel;
	}

	public BufferedImage getBuffer() {
		return buffer;
	}

	public GameOverPanel getGameOverPanel() {
		return gameOverPanel;
	}

	public MenuPanel getMenuPanel() {
		return menuPanel;
	}

	public BufferStrategy getBuffer1() {
		return buffer1;
	}

	public void setBuffer1(BufferStrategy buffer1) {
		this.buffer1 = buffer1;
	}

	public DificuldadePanel getDificuldadePanel() {
		return dificuldadePanel;
	}


	public Sprite getMensagem() {
		return mensagem;
	}

	public Sprite getBotaoFecharMsg() {
		return botaoFecharMsg;
	}

	public Sprite getBotaoSimMsg() {
		return botaoSimMsg;
	}

	public Sprite getBotaoNaoMsg() {
		return botaoNaoMsg;
	}

	public Sprite getBotaoOkMsg() {
		return botaoOkMsg;
	}
    
	
	
}
