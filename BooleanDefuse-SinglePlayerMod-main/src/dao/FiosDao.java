package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import model.Fios;
//import model.Teste;
import model.Util;
import view.Mensagem;

public class FiosDao {

	private static XStream xstream = new XStream(new DomDriver());

//	public static  String gerarXml(List<Teste> lista) {
//		return xstream.toXML(lista);
//	}
	
	public static void insert(ArrayList<Fios> fios) {
		 try {
	            File arquivo = new File("BooleanDefuse-files/XML/Fios.xml");
	            PrintWriter escrever = new PrintWriter(arquivo);
	            escrever.write(xstream.toXML(fios));
	            escrever.flush();
	            escrever.close();
	            
	        } catch (FileNotFoundException e) {
	           Mensagem.mostrar("Erro ao criar arquivo Fios.xml", Util.ERRRO);
	        }        
		
	}
	
	public static ArrayList<Fios> getAll(){
		try {
		
            FileReader leitor = new FileReader("BooleanDefuse-files/XML/Fios.xml");
            
            return (ArrayList<Fios>) xstream.fromXML(leitor);
        } catch (FileNotFoundException ex) {
            Mensagem.mostrar("Erro ao ler XML! Verifque se o arquivo do endereço \"BooleanDefuse-files/XML/Fios.xml\" está na pasta!", Util.ERRRO);
        }
         
        return null;         
	}

}
