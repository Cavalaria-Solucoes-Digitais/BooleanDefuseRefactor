package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Sons;
import model.Sprite;
import model.Util;

public class DificuldadePanel extends JPanel{

	private ImageIcon menuBG;
	private JButton facilButton,normalButton,sairButton;
	private Sprite textoDificuldade;

	public DificuldadePanel() {
//		tocar();
		setSize(Util.LARGURA, Util.ALTURA);
		setLayout(null);

		menuBG = new ImageIcon("BooleanDefuse-files/Imagens//escolha de dificuldade 1.png");

		normalButton = new JButton("JÁ JOGUEI ANTES");
		normalButton.setForeground(Color.white);
		normalButton.setBackground(Color.RED);
		normalButton.setFont(Util.getFont("Anton-Regular", 20));
		
		facilButton = new JButton("NÃO, É A MINHA PRIMEIRA VEZ");
		facilButton.setForeground(Color.white);
		facilButton.setBackground(Color.RED);
		facilButton.setFont(Util.getFont("Anton-Regular", 20));
		
		sairButton = new JButton();
		sairButton.setIcon(new ImageIcon("BooleanDefuse-files/Imagens//sair.png"));
		sairButton.setBackground(Color.RED);
		sairButton.setToolTipText("Voltar para o menu");
		
		try {
			textoDificuldade = new Sprite("BooleanDefuse-files/Imagens//sprite dificuldade 2.png", 1, 394, 109, 2, 1, 930, 385);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		add(facilButton).setBounds(520, 350, 260, 60);
		add(normalButton).setBounds(550, 450, 200, 60);
//		add(sairButton).setBounds(1240, 5, 60, 60);
		add(sairButton).setBounds(1150, 5, 60, 60);

		setVisible(false);
	}

	public void tocar() {
		Sons.tocar("BooleanDefuse-files/Sons//tema.wav");
	}

	public void parar() {
		Sons.pararDeTocar();
	}
	public ImageIcon getMenuBG() {
		return menuBG;
	}

	public JButton getnormalButton() {
		return normalButton;
	}

	public JButton getSairButton() {
		return sairButton;
	}

	public JButton getFacilButton() {
		return facilButton;
	}

	public JButton getNormalButton() {
		return normalButton;
	}

	public Sprite getTextoDificuldade() {
		return textoDificuldade;
	}

	

}
