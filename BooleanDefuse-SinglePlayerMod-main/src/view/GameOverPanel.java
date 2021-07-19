package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Util;

public class GameOverPanel extends JPanel{
	private ImageIcon bombaExplosao, imagemSucesso;
	private JButton jogarNovamente,sair;
	
	public GameOverPanel() {
		setSize(Util.LARGURA, Util.ALTURA);
        setLayout(null);
        
        bombaExplosao = new ImageIcon("BooleanDefuse-files/Imagens//bombaGameOver.png");
        imagemSucesso = new ImageIcon("BooleanDefuse-files/Imagens//tela ganhou2.png");
        
        jogarNovamente = new JButton();
        jogarNovamente.setIcon(new ImageIcon("BooleanDefuse-files/Imagens//reiniciar.png"));
        jogarNovamente.setBackground(Color.red);
        jogarNovamente.setToolTipText("Jogar Novamente");
        
        sair = new JButton();
        sair.setIcon(new ImageIcon("BooleanDefuse-files/Imagens//sair.png"));
        sair.setBackground(Color.red);
        sair.setToolTipText("Voltar para o menu");
        
        add(jogarNovamente).setBounds(50, 650, 60, 60);
        add(sair).setBounds(1246, 650, 60, 60);
        
        setVisible(false);
	}

	public ImageIcon getBombaExplosao() {
		return bombaExplosao;
	}

	public void setBombaExplosao(ImageIcon bombaExplosao) {
		this.bombaExplosao = bombaExplosao;
	}

	public JButton getJogarNovamente() {
		return jogarNovamente;
	}

	public JButton getSair() {
		return sair;
	}

	public ImageIcon getImagemSucesso() {
		return imagemSucesso;
	}

	
	
}
