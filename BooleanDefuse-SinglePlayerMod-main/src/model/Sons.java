package model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import view.Mensagem;

public class Sons {
	public static Clip clip;
	public Clip clipInstance;
	File soundFile;
	AudioInputStream sound;
	DataLine.Info info;
	
	public Sons(String urlSom) {
		try {
			soundFile = new File(urlSom);
            sound = AudioSystem.getAudioInputStream(soundFile);
            info = new DataLine.Info(Clip.class, sound.getFormat());
            clipInstance = (Clip) AudioSystem.getLine(info);
    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
        	Mensagem.mostrar("Erro ao reproduzir arquivo de audio! Verifque se o arquivo do endereço \""+urlSom+"\" está na pasta!", Util.ERRRO);
    }
	}
	
	public static void tocar(String urlSom){
		try {
				if(Util.SOM) {
					File soundFile = new File(urlSom);
		            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
		            DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
		            clip = (Clip) AudioSystem.getLine(info);
		            clip.open(sound);
		            clip.start();
				}
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            	Mensagem.mostrar("Erro ao reproduzir arquivo de audio! Verifque se o arquivo do endereço \""+urlSom+"\" está na pasta!", Util.ERRRO);
        }
	}
    
	public static void pararDeTocar(){
		if(Util.SOM)
			clip.close();
    }
	
	public void tocarInstance(){
		try {
			clipInstance.open(sound);
			clipInstance.start();
        } catch (IOException | LineUnavailableException e) {
            	Mensagem.mostrar("Erro ao reproduzir arquivo de audio", Util.ERRRO);
        }
	}
    
	public void pararDeTocarInstance(){
		clipInstance.close();
    }
}
