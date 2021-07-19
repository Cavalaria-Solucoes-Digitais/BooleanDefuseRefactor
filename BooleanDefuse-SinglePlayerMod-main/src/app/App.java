package app;

import java.awt.Color;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.ControladorJogoPanel;
import dao.FiosDao;
import dao.PerguntasDao;
import dao.VocabulosDao;
import model.ModuloTesteMesa;
import model.Relogio;
import model.ThreadDesenhar;

import model.Util;
import model.VocabuloMorse;
import view.DificuldadePanel;
import view.GameOverPanel;
import view.JogoPanel;
import view.Mensagem;
import view.MenuPanel;
import view.Tela;

public class App {
	public static void main(String[] args) {

		Tela tela;
		JogoPanel jogoPainel = new JogoPanel();
		GameOverPanel gameOverPanel = new GameOverPanel();
		MenuPanel menuPanel = new MenuPanel();
		DificuldadePanel dificuldadePanel = new DificuldadePanel();
		
		tela = new Tela(jogoPainel, gameOverPanel,menuPanel, dificuldadePanel);
		
		
		tela.createBufferStrategy(4);
		tela.setBuffer1(tela.getBufferStrategy());

		ThreadDesenhar desenhar = new ThreadDesenhar(tela);
		desenhar.start();


		ControladorJogoPanel cjp = new ControladorJogoPanel(tela.getJogoPanel(),tela);

		//		ArrayList<VocabuloMorse> vocabulosOpLog = new ArrayList<VocabuloMorse>();
		//		ArrayList<VocabuloMorse> vocabulosNum = new ArrayList<VocabuloMorse>();
		//		ArrayList<VocabuloMorse> vocabulosVF = new ArrayList<VocabuloMorse>();
		//		
		//		vocabulosOpLog.add(new VocabuloMorse("--  .-  ..  ---  .-.", ">"));
		//		vocabulosOpLog.add(new VocabuloMorse("--  .   -.  ---  .-.", "<"));
		//		vocabulosOpLog.add(new VocabuloMorse("..  --.  ..-  .-  .-../..  --.  ..-  .-  .-..", "=="));
		//		vocabulosOpLog.add(new VocabuloMorse(" -.-.--/..  --.  ..-  .-  .-..", "!="));
		//		vocabulosOpLog.add(new VocabuloMorse(".", "&&"));
		//		vocabulosOpLog.add(new VocabuloMorse("---  ..-", "||"));
		//		
		//		vocabulosNum.add(new VocabuloMorse("-----", "0"));
		//		vocabulosNum.add(new VocabuloMorse(".----", "1"));
		//		vocabulosNum.add(new VocabuloMorse("..---", "2"));
		//		vocabulosNum.add(new VocabuloMorse("...--", "3"));
		//		vocabulosNum.add(new VocabuloMorse("....-", "4"));
		//		vocabulosNum.add(new VocabuloMorse(".....", "5"));
		//		vocabulosNum.add(new VocabuloMorse("-....", "6"));
		//		vocabulosNum.add(new VocabuloMorse("--...", "7"));
		//		vocabulosNum.add(new VocabuloMorse("---..", "8"));
		//		vocabulosNum.add(new VocabuloMorse("----.", "9"));
		//		
		//		vocabulosVF.add(new VocabuloMorse("..-.", "F"));
		//		vocabulosVF.add(new VocabuloMorse("...-", "V"));
		//		
		//		VocabulosDao.insert(vocabulosOpLog, "vocabulosOpLog");
		//		VocabulosDao.insert(vocabulosNum, "vocabulosNum");
		//		VocabulosDao.insert(vocabulosVF, "vocabulosVF");



		//		Relogio relogio = new Relogio(300);
		//		
		//		relogio.start();

		//		PerguntasDao.insert(TEMPCriarPerguntas.criarPerguntas());
		//		FiosDao.insert(TEMPCriarFios.criarFios());

	}
}
