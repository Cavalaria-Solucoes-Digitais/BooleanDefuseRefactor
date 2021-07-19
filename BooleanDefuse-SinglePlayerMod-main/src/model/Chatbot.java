package model;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import view.Mensagem;

public class Chatbot {
	Desktop desktop;
	private String msg;
	//modulos
	private boolean modFios, modMorse, modQuiz, modTesteMesa;
	//Cor fios
	private boolean amarelo, verde, vermelho; 
	//Posi��o Fio selecionada
	private String equacaoFio;
	//Quiz
	private int numPalavrasQuiz;

	private boolean statusLinha1Morse = false;
	private boolean statusLinha2Morse = false;

	private String msgMorse;

	public Chatbot() {

		desktop = Desktop.getDesktop();
		init();

	}

	public void init() {
		modFios = false;
		modMorse = false;
		modQuiz = false;
		modTesteMesa = false;

		amarelo = false;
		verde = false;
		vermelho = false;

		statusLinha1Morse = false;
		statusLinha2Morse = false;

		equacaoFio = "";
		msgMorse = "";
		Util.modulosDesativados = 0;

	}

	public String conversa(String resposta) {
		if(!(modFios || modMorse || modQuiz || modTesteMesa)) {
			switch (resposta) {
			case "1": {
				if(Util.statusFios) {
					return menu();
				}
				modFios = true;
				return "Para desarmar este m�dulo preciso que voc� me informe a <b>cor do fio</b> e a <b>posi��o</b> em que ele est�. "
				+ "Primeiro me diga, qual a cor do primeiro fio? <br><br> Caso envie a cor errada do fio digite <b>corrigir</b> e depois envie"
				+ " a cor correta.";
			} case "2": {
				if(Util.statusQuiz) {
					return menu();
				}
				modQuiz = true;
				return "Para desarmar este m�dulo voc� deve me informar o nome das pessoas (Ex.: VAUGHAN, LOVELACE, CURIE, EINSTEIN, "
				+ "NEWTON, TESLA, JOBS), que eu lhe enviarei o operador l�gico associado ao mesmo. Quando souber todos os "
				+ "operadores, voc� deve realizar a express�o l�gica gerada e apertar o bot�o correspondente ao resultado. "
				+ "<br><br>Diga-me qual o primeiro nome da pessoa exibida?";
			}case "3":{
				if(Util.statusMesa) {
					return menu();
				}
				modTesteMesa = true;
				return "Para desarmar este m�dulo, primeiro preciso que voc� me diga qual o s�mbolo que aparece no display, informe "
				+ "uma das op��es: "
				+ "<br><br>"
				+ "<b>1</b> para <b>&alpha;</b><br>"
				+ "<b>2</b> para <b>&beta;</b><br>"
				+ "<b>3</b> para <b>&Omega;</b>.";
			}case "4":{
				if(Util.statusMorse) {
					return menu();
				}
				modMorse = true;
				return "Para desarmar este m�dulo, preciso que voc� me envie o c�digo Morse de cada uma das linhas no "
				+ "papel (sequ�ncia de tra�os �-� e pontos �.�), como, por exemplo: ----...-"  
				+ "<br><br>Ap�s cada linha pressione ENTER, que eu vou lhe enviar a tradu��o. A medida em que eu lhe informar cada "
				+ "tradu��o, informe-a no campo do m�dulo corresponde.. \r\n"
				+ "<br><br>"
				+ "<b>Vamos l�, me envie o primeiro c�digo Morse da primeira linha.</b>";
			}
			default:
				return "Eu preciso que voc� envie um n�mero entre 1 e 4...";
			} 
		}else if(modFios) {
			return conversaFios(resposta);
		}else if(modQuiz) {
			return conversaQuiz(resposta);
		}else if(modTesteMesa) {
			return conversaTesteMesa(resposta);
		}else {
			return conversaMorse(resposta);
		}



	}

	private String conversaFios(String resposta) {
		if(!(amarelo || verde || vermelho)) {
			if(Util.statusFios) {
				modFios = false;
				return menu();
			}else if(resposta.equalsIgnoreCase("ok")) {
				if(Util.statusFios || Util.fiosOk==2) {
					modFios = false;
					return menu();
				}
				return "Muito bem! Diga a cor do pr�ximo fio.";
			}else {
				if(resposta.equalsIgnoreCase("amarelo")) {
					amarelo = true;
				}else if(resposta.equalsIgnoreCase("verde")){
					verde = true;
				}else if(resposta.equalsIgnoreCase("vermelho")) {
					vermelho = true;
				}else {
					return "N�o entendi a cor do fio. Preciso que voc� digite para mim a cor corretamente. "
							+ "Por exemplo: <b>amarelo, vermelho</b> ou <b>verde</b>.";
				}
				return "O fio est� em uma placa que conecta n�meros e letras. Me informe o respectivo n�mero e letra nos "
				+ "quais o fio est� conectado. <b>(Por exemplo, 1A, 2B, 3C)</b>.";
			}
		}else {
			if(resposta.equalsIgnoreCase("Corrigir")){
				amarelo = false;
				verde = false;
				vermelho = false;
				equacaoFio =  "Certo, qual a <b>cor correta do fio</b>?";
			}else if(amarelo) {
				equacaoFio = fiosAmarelos(resposta);
			}else if(verde) {
				equacaoFio = fiosVerdes(resposta);
			}else if(vermelho) {
				equacaoFio = fiosVermelhos(resposta);				
			}

			if(equacaoFio.equals("N�o entendi o s�mbolo. Preciso que voc� digite para mim o n�mero e letra correspondente a liga��o do fio. "
					+ "N�o utilize espa�os. Por exemplo, <b>1A</b>.")) {
				return equacaoFio;
			}else if(equacaoFio.equals("Certo, qual a <b>cor correta do fio</b>?")) {
				return equacaoFio;
			}
			return "Resolva a equa��o abaixo, caso a resposta seja <b>verdadeiro (true)</b> ent�o <b>corte o fio</b>, caso contr�rio passe para "
			+ "o pr�ximo. Caso corte um fio que n�o deveria ser cortado a bomba explode. Caso n�o corte um fio que deveria ser cortado a bomba"
			+ " tamb�m explode. <br><br>"
			+ "� importante que voc� digite para mim <b>OK</b> ao solucionar o desarme (corte ou n�o do fio)."
			+ equacaoFio;
		}
	}

	private String fiosAmarelos(String posicao) {

		if(posicao.equalsIgnoreCase("1a")) {
			amarelo = false;
			return "<br><br>(((true && false) || true) != ((true && false) || (!false)))";
		}else if(posicao.equalsIgnoreCase("1b")) {
			amarelo = false;
			return "<br><br>(((!(false && true)) || false) == ((false || true) && true))";
		}else if(posicao.equalsIgnoreCase("1c")) {
			amarelo = false;
			return "<br><br>((((false && true) || false) && ((false || true) && true)))";
		}else if(posicao.equalsIgnoreCase("2a")) {
			amarelo = false;
			return "<br><br>(((false && (true != false)) && (false || true && !true)))";
		}else if(posicao.equalsIgnoreCase("2b")) {
			amarelo = false;
			return "<br><br>(((!(5 > 4)) || false) == ((false || true) && 4==3))";
		}else if(posicao.equalsIgnoreCase("2c")) {
			amarelo = false;
			return "<br><br>(((!((20/5) > 4)) && (4>=4)) == ((false || true) && 4==3))";
		}else if(posicao.equalsIgnoreCase("3a")) {
			amarelo = false;
			return "<br><br>((((!((20/5+9) > 4)) && (4>=4)) == ((false || true) && (4*5)==35)))";
		}else if(posicao.equalsIgnoreCase("3b")) {
			amarelo = false;
			return "<br><br>((!((!((20/5+9) > 4)) && (4>=4)) == !(!(false || true) && (4*5)==35)))";
		}else if(posicao.equalsIgnoreCase("3c")) {
			amarelo = false;
			return "<br><br>!((((false && true) || false) && !(!(false || false) && true)))";
		}


		return "N�o entendi o s�mbolo. Preciso que voc� digite para mim o n�mero e letra correspondente a liga��o do fio. "
		+ "N�o utilize espa�os. Por exemplo, <b>1A</b>.";
	}

	private String fiosVerdes(String posicao) {

		if(posicao.equalsIgnoreCase("1a")) {
			verde = false;
			return "<br><br>!((!false || !true) && !(false && false)) != (!(!true))";
		}else if(posicao.equalsIgnoreCase("1b")) {
			verde = false;
			return "<br><br>!(((!false || !true) && !(false && false)) != (!(true)))";
		}else if(posicao.equalsIgnoreCase("1c")) {
			verde = false;
			return "<br><br>((((!((20/5+9) > 4)) && !(4>=4)) != ((false || true) && (4*5)==35)))";
		}else if(posicao.equalsIgnoreCase("2a")) {
			verde = false;
			return "<br><br>(!false && !true) && !(true || true) && (!false || !true) && (true || true))";
		}else if(posicao.equalsIgnoreCase("2b")) {
			verde = false;
			return "<br><br>(((true && false) || true) != ((true && false) && (!false)))";
		}else if(posicao.equalsIgnoreCase("2c")) {
			verde = false;
			return "<br><br>((!((!((20/5+9) > 4)) && (4>=4)) == !(!(false || true) && (4*5)==35)))";
		}else if(posicao.equalsIgnoreCase("3a")) {
			verde = false;
			return "<br><br>(((!(false && true)) == false) == ((false || true) && true))";
		}else if(posicao.equalsIgnoreCase("3b")) {
			verde = false;
			return "<br><br>((((!((20/5+9) > 4)) && (4>=4)) == ((false || true) && (4*5)==35)))";
		}else if(posicao.equalsIgnoreCase("3c")) {
			verde = false;
			return "<br><br>((((false && true) || false) && ((false || true) && true)))";
		}

		return "N�o entendi o s�mbolo. Preciso que voc� digite para mim o n�mero e letra correspondente a liga��o do fio. "
		+ "N�o utilize espa�os. Por exemplo, <b>1A</b>.";
	}

	private String fiosVermelhos(String posicao) {

		if(posicao.equalsIgnoreCase("1a")) {
			vermelho = false;
			return "<br><br>(((!(5 > 4)) || false) == ((false || true) && 4==3))";
		}else if(posicao.equalsIgnoreCase("1b")) {
			vermelho = false;
			return "<br><br>((((!((20/5+9) > 4)) && (4>=4)) == ((false || true) && (4*5)==35)))";
		}else if(posicao.equalsIgnoreCase("1c")) {
			vermelho = false;
			return "<br><br>!((!false || !true) && !(false && false)) != (!(!true))";
		}else if(posicao.equalsIgnoreCase("2a")) {
			vermelho = false;
			return "<br><br>(((!((20/5) > 4)) && (4>=4)) == ((false || true) && 4==3))";
		}else if(posicao.equalsIgnoreCase("2b")) {
			vermelho = false;
			return "<br><br>(((false && (true != false)) && (false || true && !true))";
		}else if(posicao.equalsIgnoreCase("2c")) {
			vermelho = false;
			return "<br><br>((!((!((20/5+9) > 4)) && (4>=4)) == !(!(false || true) && (4*5)==35)))";
		}else if(posicao.equalsIgnoreCase("3a")) {
			vermelho = false;
			return "<br><br>((((false && true) || false) && ((false || true) && true)))";
		}else if(posicao.equalsIgnoreCase("3b")) {
			vermelho = false;
			return "<br><br>(((!(false && true)) == false) == ((false || true) && true))";
		}else if(posicao.equalsIgnoreCase("3c")) {
			vermelho = false;
			return "<br><br>(!false && !true) && !(true || true) && (!false || !true) && (true || true))";
		}

		return "N�o entendi o s�mbolo. Preciso que voc� digite para mim o n�mero e letra correspondente a liga��o do fio. "
		+ "N�o utilize espa�os. Por exemplo, <b>1A</b>.";
	}

	private String conversaQuiz(String nome) {
		if(!Util.statusQuiz) {
			if(palavrasQuiz(nome)!=null) {
				return palavrasQuiz(nome)+"<br><br>Digite o pr�ximo nome ou \"OK\" caso tenha desarmado o dispositivo!";
			}else {
				return "Desculpe este nome n�o est� em minha base de dados. Envie um nome v�lido!<br><br> "
						+ "(Ex.: VAUGHAN, LOVELACE, CURIE, EINSTEIN, NEWTON, TESLA, JOBS)";
			}
			
		}else if(nome.equalsIgnoreCase("ok")) {
			modQuiz = false;
			return menu();
		}else {
			return "Desculpe, n�o entendi o que voc� enviou! Envie <b>ok</b> para continuar.";
		}
	}

	private String palavrasQuiz(String nome) {
		if(nome.equalsIgnoreCase("EINSTEIN")) {
			return "EINSTEIN � equivalente a \"&gt;\".";
		}else if(nome.equalsIgnoreCase("NEWTON")) {
			return "NEWTON � equivalente a \"&lt;\".";
		}else if(nome.equalsIgnoreCase("CURIE")) {
			return "CURIE � equivalente a \"==\".";
		}else if(nome.equalsIgnoreCase("TESLA")) {
			return "TESLA � equivalente a \"!=\".";
		}else if(nome.equalsIgnoreCase("JOBS")) {
			return "JOBS � equivalente a \"!\".";
		}else if(nome.equalsIgnoreCase("LOVELACE")) {
			return "LOVELACE � equivalente a \"&&\".";
		}else if(nome.equalsIgnoreCase("VAUGHAN")) {
			return "VAUGHAN � equivalente a \"||\".";
		}

		return null;

	}

	private String conversaTesteMesa(String resposta) {
		if(!Util.statusMesa) {
			switch (resposta) {
			case "1":{
				return "<p>Te enviei um c�digo-fonte para voc� solucionar um Teste de Mesa. Associe os valores que aparecem "
						+ "nos campos abaixo do display as vari�veis de input. Informe no campo Sa�da do m�dulo o retorno "
						+ "encontrado. Digite para mim <b>OK</b> ao finalizar para que eu possa verificar o desarme do m�dulo.</p>"
						+ "<img src=\""+this.getClass().getClassLoader().getResource("resource/code01.png").toString()+"\" alt=\"code01\" border=\"0\">";
			}	
			case "2":{
				return "<p>Te enviei um c�digo-fonte para voc� solucionar um Teste de Mesa. Associe os valores que aparecem "
						+ "nos campos abaixo do display as vari�veis de input. Informe no campo Sa�da do m�dulo o retorno "
						+ "encontrado. Digite para mim <b>OK</b> ao finalizar para que eu possa verificar o desarme do m�dulo.</p>"
						+ "<img src=\""+this.getClass().getClassLoader().getResource("resource/code02.png").toString()+"\" alt=\"code02\" border=\"0\">";
			}
			case "3":{
				return "<p>Te enviei um c�digo-fonte para voc� solucionar um Teste de Mesa. Associe os valores que aparecem "
						+ "nos campos abaixo do display as vari�veis de input. Informe no campo Sa�da do m�dulo o retorno "
						+ "encontrado. Digite para mim <b>OK</b> ao finalizar para que eu possa verificar o desarme do m�dulo.</p>"
						+"<img src=\""+this.getClass().getClassLoader()
						   .getResource("resource/code03.png").toString()+"\" alt=\"code03\" border=\"0\">";
			}case "ok":{
				return "O m�dulo ainda n�o foi desativado. Envie <b>OK<b> apenas quando o m�dulo estiver desativado.";
			}default:{
				return "N�o entendi o s�mbolo. Preciso que voc� digite para mim a op��o corretamente. <br>"
						+ "1 para: <b>&alpha;</b><br>"
						+ "2 para: <b>&beta;</b><br>"
						+ "3 para: <b>&Omega;</b><br>";
			}
			}
		}		

		//		return "O m�dulo ainda n�o foi desativado. Envie <b>OK<b> apenas quando o m�dulo estiver desativado.";

		modTesteMesa = false;
		return menu();
	}

	private String conversaMorse(String resposta) {
		msgMorse = definirMsgMorse();
		if(!Util.statusMorse) {

			if(resposta.equalsIgnoreCase("-- .- .. --- .-.")) {
				linhaMorseTraduzida();
				return "Isso significa \"&gt;\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase("-- . -. --- .-.")) {
				linhaMorseTraduzida();
				return "Isso significa \"&lt;\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase(".. --. ..- .- .-../.. --. ..- .- .-..")) {
				linhaMorseTraduzida();
				return "Isso significa \"==\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase("-.-.--/.. --. ..- .- .-..")) {
				linhaMorseTraduzida();
				return "Isso significa \"!=\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase(".")) {
				linhaMorseTraduzida();
				return "Isso significa \"&&\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase("--- ..-")) {
				linhaMorseTraduzida();
				return "Isso significa \"||\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase("-----")) {
				linhaMorseTraduzida();
				return "Isso significa \"0\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase(".----")) {
				linhaMorseTraduzida();
				return "Isso significa \"1\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase("..---")) {
				linhaMorseTraduzida();
				return "Isso significa \"2\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase("...--")) {
				linhaMorseTraduzida();
				return "Isso significa \"3\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase("....-")) {
				linhaMorseTraduzida();
				return "Isso significa \"4\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase(".....")) {
				linhaMorseTraduzida();
				return "Isso significa \"5\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase("-....")) {
				linhaMorseTraduzida();
				return "Isso significa \"6\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase("--...")) {
				linhaMorseTraduzida();
				return "Isso significa \"7\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase("---..")) {
				linhaMorseTraduzida();
				return "Isso significa \"8\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase("----.")) {
				linhaMorseTraduzida();
				return "Isso significa \"9\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase("..-.")) {
				linhaMorseTraduzida();
				return "Isso significa \"F\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase("...-")) {
				linhaMorseTraduzida();
				return "Isso significa \"V\". "+msgMorse;
			}else if(resposta.equalsIgnoreCase("ok")) {
				linhaMorseTraduzida();
				return "O m�dulo ainda n�o foi desativado. Envie <b>OK</b> apenas quando o m�dulo estiver desativado.";
			}

			return "Desculpe, n�o entendi o c�digo que voc� me enviou! Verificar novamente e digite para mim o c�digo encontrado.";
		}

		statusLinha1Morse = false;
		statusLinha2Morse = false;
		modMorse = false;

		return menu();
	}

	public void linhaMorseTraduzida() {
		if(!statusLinha1Morse) {
			statusLinha1Morse = true;
		}else if(!statusLinha2Morse) {
			statusLinha2Morse = true;
		}
	}

	public String definirMsgMorse() {
		if((!statusLinha1Morse) || (!statusLinha2Morse)) {
			return "Digite a pr�xima linha para que eu possa traduzir.";
		}else {
			return "Agora informe no �ltimo campo o resultado da express�o, <b>V</b> para verdadeiro e <b>F</b> para falso. "
					+ "Para finalizar digite <b>OK</b> para mim.";
		}
	}

	public String menu() {
		return msgMenu() 
				+"<br><br>" 
				+ (Util.statusFios?"":"<b>1</b> para: <b>Fios</b><br>")
				+ (Util.statusQuiz?"":"<b>2</b> para: <b>Quiz</b><br>")
				+ (Util.statusMesa?"":"<b>3</b> para: <b>Teste de Mesa</b><br>")
				+ (Util.statusMorse?"":"<b>4</b> para: <b>Morse</b><br>");
	}

	public String msgMenu() {
		switch (Util.modulosDesativados) {
		case 1:
			return "Parab�ns, m�dulo desativado com sucesso! Vamos para o pr�ximo. Digite o n�mero respectivo ao m�dulo "
			+ "que deseja desarmar. <br><br>";
		case 2:
			return "M�dulo desativado com sucesso! Digite o n�mero respectivo ao pr�ximo m�dulo que deseja desarmar.";
		default:
			return "M�dulo desativado! Voc� est� indo muito bem! Falta pouco para voc� desarmar a bomba.  Digite o n�mero "
			+ "respectivo ao �ltimo m�dulo que deseja desarmar.";
		}
	}

	public Desktop getDesktop() {
		return desktop;
	}

	public void setDesktop(Desktop desktop) {
		this.desktop = desktop;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isModFios() {
		return modFios;
	}

	public void setModFios(boolean modFios) {
		this.modFios = modFios;
	}

	public boolean isModMorse() {
		return modMorse;
	}

	public void setModMorse(boolean modMorse) {
		this.modMorse = modMorse;
	}

	public boolean isModQuiz() {
		return modQuiz;
	}

	public void setModQuiz(boolean modQuiz) {
		this.modQuiz = modQuiz;
	}

	public boolean isModTesteMesa() {
		return modTesteMesa;
	}

	public void setModTesteMesa(boolean modTesteMesa) {
		this.modTesteMesa = modTesteMesa;
	}

	public boolean isAmarelo() {
		return amarelo;
	}

	public void setAmarelo(boolean amarelo) {
		this.amarelo = amarelo;
	}

	public boolean isVerde() {
		return verde;
	}

	public void setVerde(boolean verde) {
		this.verde = verde;
	}

	public boolean isVermelho() {
		return vermelho;
	}

	public void setVermelho(boolean vermelho) {
		this.vermelho = vermelho;
	}

	public String getEquacaoFio() {
		return equacaoFio;
	}

	public void setEquacaoFio(String equacaoFio) {
		this.equacaoFio = equacaoFio;
	}

	public int getNumPalavrasQuiz() {
		return numPalavrasQuiz;
	}

	public void setNumPalavrasQuiz(int numPalavrasQuiz) {
		this.numPalavrasQuiz = numPalavrasQuiz;
	}

	public boolean isStatusLinha1Morse() {
		return statusLinha1Morse;
	}

	public void setStatusLinha1Morse(boolean statusLinha1Morse) {
		this.statusLinha1Morse = statusLinha1Morse;
	}

	public boolean isStatusLinha2Morse() {
		return statusLinha2Morse;
	}

	public void setStatusLinha2Morse(boolean statusLinha2Morse) {
		this.statusLinha2Morse = statusLinha2Morse;
	}

	public String getMsgMorse() {
		return msgMorse;
	}

	public void setMsgMorse(String msgMorse) {
		this.msgMorse = msgMorse;
	}
	
	
}
