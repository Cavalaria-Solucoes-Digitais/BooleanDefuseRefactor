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
	//Posição Fio selecionada
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
				return "Para desarmar este módulo preciso que você me informe a <b>cor do fio</b> e a <b>posição</b> em que ele está. "
				+ "Primeiro me diga, qual a cor do primeiro fio? <br><br> Caso envie a cor errada do fio digite <b>corrigir</b> e depois envie"
				+ " a cor correta.";
			} case "2": {
				if(Util.statusQuiz) {
					return menu();
				}
				modQuiz = true;
				return "Para desarmar este módulo você deve me informar o nome das pessoas (Ex.: VAUGHAN, LOVELACE, CURIE, EINSTEIN, "
				+ "NEWTON, TESLA, JOBS), que eu lhe enviarei o operador lógico associado ao mesmo. Quando souber todos os "
				+ "operadores, você deve realizar a expressão lógica gerada e apertar o botão correspondente ao resultado. "
				+ "<br><br>Diga-me qual o primeiro nome da pessoa exibida?";
			}case "3":{
				if(Util.statusMesa) {
					return menu();
				}
				modTesteMesa = true;
				return "Para desarmar este módulo, primeiro preciso que você me diga qual o símbolo que aparece no display, informe "
				+ "uma das opções: "
				+ "<br><br>"
				+ "<b>1</b> para <b>&alpha;</b><br>"
				+ "<b>2</b> para <b>&beta;</b><br>"
				+ "<b>3</b> para <b>&Omega;</b>.";
			}case "4":{
				if(Util.statusMorse) {
					return menu();
				}
				modMorse = true;
				return "Para desarmar este módulo, preciso que você me envie o código Morse de cada uma das linhas no "
				+ "papel (sequência de traços “-“ e pontos “.”), como, por exemplo: ----...-"  
				+ "<br><br>Após cada linha pressione ENTER, que eu vou lhe enviar a tradução. A medida em que eu lhe informar cada "
				+ "tradução, informe-a no campo do módulo corresponde.. \r\n"
				+ "<br><br>"
				+ "<b>Vamos lá, me envie o primeiro código Morse da primeira linha.</b>";
			}
			default:
				return "Eu preciso que você envie um número entre 1 e 4...";
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
				return "Muito bem! Diga a cor do próximo fio.";
			}else {
				if(resposta.equalsIgnoreCase("amarelo")) {
					amarelo = true;
				}else if(resposta.equalsIgnoreCase("verde")){
					verde = true;
				}else if(resposta.equalsIgnoreCase("vermelho")) {
					vermelho = true;
				}else {
					return "Não entendi a cor do fio. Preciso que você digite para mim a cor corretamente. "
							+ "Por exemplo: <b>amarelo, vermelho</b> ou <b>verde</b>.";
				}
				return "O fio está em uma placa que conecta números e letras. Me informe o respectivo número e letra nos "
				+ "quais o fio está conectado. <b>(Por exemplo, 1A, 2B, 3C)</b>.";
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

			if(equacaoFio.equals("Não entendi o símbolo. Preciso que você digite para mim o número e letra correspondente a ligação do fio. "
					+ "Não utilize espaços. Por exemplo, <b>1A</b>.")) {
				return equacaoFio;
			}else if(equacaoFio.equals("Certo, qual a <b>cor correta do fio</b>?")) {
				return equacaoFio;
			}
			return "Resolva a equação abaixo, caso a resposta seja <b>verdadeiro (true)</b> então <b>corte o fio</b>, caso contrário passe para "
			+ "o próximo. Caso corte um fio que não deveria ser cortado a bomba explode. Caso não corte um fio que deveria ser cortado a bomba"
			+ " também explode. <br><br>"
			+ "É importante que você digite para mim <b>OK</b> ao solucionar o desarme (corte ou não do fio)."
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


		return "Não entendi o símbolo. Preciso que você digite para mim o número e letra correspondente a ligação do fio. "
		+ "Não utilize espaços. Por exemplo, <b>1A</b>.";
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

		return "Não entendi o símbolo. Preciso que você digite para mim o número e letra correspondente a ligação do fio. "
		+ "Não utilize espaços. Por exemplo, <b>1A</b>.";
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

		return "Não entendi o símbolo. Preciso que você digite para mim o número e letra correspondente a ligação do fio. "
		+ "Não utilize espaços. Por exemplo, <b>1A</b>.";
	}

	private String conversaQuiz(String nome) {
		if(!Util.statusQuiz) {
			if(palavrasQuiz(nome)!=null) {
				return palavrasQuiz(nome)+"<br><br>Digite o próximo nome ou \"OK\" caso tenha desarmado o dispositivo!";
			}else {
				return "Desculpe este nome não está em minha base de dados. Envie um nome válido!<br><br> "
						+ "(Ex.: VAUGHAN, LOVELACE, CURIE, EINSTEIN, NEWTON, TESLA, JOBS)";
			}
			
		}else if(nome.equalsIgnoreCase("ok")) {
			modQuiz = false;
			return menu();
		}else {
			return "Desculpe, não entendi o que você enviou! Envie <b>ok</b> para continuar.";
		}
	}

	private String palavrasQuiz(String nome) {
		if(nome.equalsIgnoreCase("EINSTEIN")) {
			return "EINSTEIN é equivalente a \"&gt;\".";
		}else if(nome.equalsIgnoreCase("NEWTON")) {
			return "NEWTON é equivalente a \"&lt;\".";
		}else if(nome.equalsIgnoreCase("CURIE")) {
			return "CURIE é equivalente a \"==\".";
		}else if(nome.equalsIgnoreCase("TESLA")) {
			return "TESLA é equivalente a \"!=\".";
		}else if(nome.equalsIgnoreCase("JOBS")) {
			return "JOBS é equivalente a \"!\".";
		}else if(nome.equalsIgnoreCase("LOVELACE")) {
			return "LOVELACE é equivalente a \"&&\".";
		}else if(nome.equalsIgnoreCase("VAUGHAN")) {
			return "VAUGHAN é equivalente a \"||\".";
		}

		return null;

	}

	private String conversaTesteMesa(String resposta) {
		if(!Util.statusMesa) {
			switch (resposta) {
			case "1":{
				return "<p>Te enviei um código-fonte para você solucionar um Teste de Mesa. Associe os valores que aparecem "
						+ "nos campos abaixo do display as variáveis de input. Informe no campo Saída do módulo o retorno "
						+ "encontrado. Digite para mim <b>OK</b> ao finalizar para que eu possa verificar o desarme do módulo.</p>"
						+ "<img src=\""+this.getClass().getClassLoader().getResource("resource/code01.png").toString()+"\" alt=\"code01\" border=\"0\">";
			}	
			case "2":{
				return "<p>Te enviei um código-fonte para você solucionar um Teste de Mesa. Associe os valores que aparecem "
						+ "nos campos abaixo do display as variáveis de input. Informe no campo Saída do módulo o retorno "
						+ "encontrado. Digite para mim <b>OK</b> ao finalizar para que eu possa verificar o desarme do módulo.</p>"
						+ "<img src=\""+this.getClass().getClassLoader().getResource("resource/code02.png").toString()+"\" alt=\"code02\" border=\"0\">";
			}
			case "3":{
				return "<p>Te enviei um código-fonte para você solucionar um Teste de Mesa. Associe os valores que aparecem "
						+ "nos campos abaixo do display as variáveis de input. Informe no campo Saída do módulo o retorno "
						+ "encontrado. Digite para mim <b>OK</b> ao finalizar para que eu possa verificar o desarme do módulo.</p>"
						+"<img src=\""+this.getClass().getClassLoader()
						   .getResource("resource/code03.png").toString()+"\" alt=\"code03\" border=\"0\">";
			}case "ok":{
				return "O módulo ainda não foi desativado. Envie <b>OK<b> apenas quando o módulo estiver desativado.";
			}default:{
				return "Não entendi o símbolo. Preciso que você digite para mim a opção corretamente. <br>"
						+ "1 para: <b>&alpha;</b><br>"
						+ "2 para: <b>&beta;</b><br>"
						+ "3 para: <b>&Omega;</b><br>";
			}
			}
		}		

		//		return "O módulo ainda não foi desativado. Envie <b>OK<b> apenas quando o módulo estiver desativado.";

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
				return "O módulo ainda não foi desativado. Envie <b>OK</b> apenas quando o módulo estiver desativado.";
			}

			return "Desculpe, não entendi o código que você me enviou! Verificar novamente e digite para mim o código encontrado.";
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
			return "Digite a próxima linha para que eu possa traduzir.";
		}else {
			return "Agora informe no último campo o resultado da expressão, <b>V</b> para verdadeiro e <b>F</b> para falso. "
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
			return "Parabéns, módulo desativado com sucesso! Vamos para o próximo. Digite o número respectivo ao módulo "
			+ "que deseja desarmar. <br><br>";
		case 2:
			return "Módulo desativado com sucesso! Digite o número respectivo ao próximo módulo que deseja desarmar.";
		default:
			return "Módulo desativado! Você está indo muito bem! Falta pouco para você desarmar a bomba.  Digite o número "
			+ "respectivo ao último módulo que deseja desarmar.";
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
