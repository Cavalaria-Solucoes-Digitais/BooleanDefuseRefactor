package view;

import java.awt.Color;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Sons;
import model.Sprite;
import model.Util;

public class MenuPanel extends JPanel{

	private ImageIcon menuBG;
	private JButton playButton,sairButton;
	private Sons tema;
	private Sprite somSprite;
	

	public MenuPanel() {
//		tocar();
		setSize(Util.LARGURA, Util.ALTURA);
		setLayout(null);
		
		try {
			somSprite = new Sprite("BooleanDefuse-files/Imagens/SOM 2.png", 0, 51, 50, 1, 2, 1240, 5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		menuBG = new ImageIcon("BooleanDefuse-files/Imagens/BACKGROUND menu.png");

		playButton = new JButton();
		playButton.setIcon(new ImageIcon("BooleanDefuse-files/Imagens/reproduzir.png"));
		playButton.setBackground(Color.RED);
		playButton.setToolTipText("Jogar");
		
		
		sairButton = new JButton();
		sairButton.setIcon(new ImageIcon("BooleanDefuse-files/Imagens/desligar.png"));
		sairButton.setBackground(Color.RED);
		sairButton.setToolTipText("Fechar");
		
		tema = new Sons("BooleanDefuse-files/Sons/tema.wav");
		tema.tocarInstance();

		add(playButton).setBounds(50, 650, 60, 60);
		add(sairButton).setBounds(1246, 650, 60, 60);
		

		setVisible(true);
	}
	
	public void reiniciarTema() {
		setTema(new Sons("BooleanDefuse-files/Sons/tema.wav"));
		if(Util.SOM)
			tema.tocarInstance();
	}

	public void tocar() {
		Sons.tocar("BooleanDefuse-files/Sons/tema.wav");
	}

	public void parar() {
		Sons.pararDeTocar();
	}
	public ImageIcon getMenuBG() {
		return menuBG;
	}

	public JButton getPlayButton() {
		return playButton;
	}

	public JButton getSairButton() {
		return sairButton;
	}

	public Sons getTema() {
		return tema;
	}

	public void setTema(Sons tema) {
		this.tema = tema;
	}

	public Sprite getSomSprite() {
		return somSprite;
	}

	


}
