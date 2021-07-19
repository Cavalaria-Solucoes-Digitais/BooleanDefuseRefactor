package view;

import javax.swing.JOptionPane;

import model.Util;

public class Mensagem {
	public static void mostrar(String mensagem, int tipo) {
		switch (tipo) {
		case Util.ERRRO:
			JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);			
			break;
			
		case Util.SUCESSO:
			JOptionPane.showMessageDialog(null, mensagem, "Sucesso", JOptionPane.WARNING_MESSAGE);			
			break;
		case Util.INFO:
			JOptionPane.showMessageDialog(null, mensagem, "Info", JOptionPane.INFORMATION_MESSAGE);			
			break;
			
		}
	}
	
	public static int mostrarPergunta (String mensagem) {
		Object[] options = { "Sim", "Não" };
		return JOptionPane.showOptionDialog(null, mensagem, "Confirmação", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}
	
	public static void dicas(int modulo) {
		switch (modulo) {
			case 0:
				JOptionPane.showMessageDialog(null, "                                Módulo fios\r\n" + 
						"\r\n" + 
						"Para desarmar este módulo você precisa cortar alguns\r\n" + 
						"fios específicos.Para saber quais terão que ser cortados,\r\n" + 
						"diga ao seu parceir@ qual a posição de cada um deles e\r\n" + 
						"aguarde que ele resolvaas equações booleanas atreladas\r\n" + 
						"a eles e diga se deve ou não cortá-los.\r\n" + 
						"", "Dica", JOptionPane.QUESTION_MESSAGE);	
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "                                Módulo Quiz\r\n" + 
						"\r\n" + 
						"Para desarmar este módulo basta dizer ao seu parceir@\r\n"
						+ "quais os\\n nomes que estão em caixa alta. Ele dirá\r\n" + 
						"os operadores lógicos erelacionais que estão atrelados\r\n" + 
						"a eles. Por fim, substitua os nomes pelos respectivos\r\n" + 
						"operadores e resolva a equação, pressionando por fim o\r\n"
						+ "botão correspondente ao resultado.", "Dica", JOptionPane.QUESTION_MESSAGE);
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "                     Módulo Teste de Mesa\r\n" + 
						"\r\n" + 
						"Para desarmar este módulo basta dizer ao seu parceir@\r\n" + 
						"qual a letra grega que está contida no display, além\r\n" + 
						"dos números gravados abaixo. Como resposta ele lhe dirá\r\n" + 
						"um número que deverá ser inserido no campo com o nome saída.\r\n" + 
						"", "Dica", JOptionPane.QUESTION_MESSAGE);
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "                        Módulo Morse\r\n" + 
						"\r\n" + 
						"Para desarmar este módulo basta dizer ao seu parceir@\r\n" + 
						"o código morse que está sendo mostrado no papel para \r\n" + 
						"que ele procure a tradução no manual. Cada linha \r\n" + 
						"corresponde a um dos campos de texto. Após preencher \r\n" + 
						"os três campos, no quarto deve ser colocado a resposta \r\n" + 
						"da equação formada (V ou F).\r\n" + 
						"", "Dica", JOptionPane.QUESTION_MESSAGE);
				break;
			default:
				break;
		}
	}
	
}
