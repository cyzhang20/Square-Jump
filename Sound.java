import sun.audio.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Sound implements Runnable
{
  private boolean running = false;
  private Thread thread;
  //
  private boolean playSong = false;
  private boolean loop = false;
  
  private AudioInputStream audioStream;
  
  private final int BUFFER_SIZE = 128000;
  private String soundFile;
  
  private AudioFormat audioFormat;
  private SourceDataLine sourceLine;
  
  
  public Sound(String soundFile)
  {
    this.soundFile=soundFile;
    this.start();
  }
  
  public void start()
  {
    if(running)
      return;
    this.thread = new Thread(this);
    this.running = true;
    this.thread.start();
  }
  
  public void run()
  {
    while(running)
    {
      try{
        Thread.sleep(10);
      }catch (InterruptedException e) {
        return;
      }
      if(audioStream == null&&this.playSong )
      {
        //InputStream inputStream=getClass().getResourceAsStream(soundFile);
        URL url=getClass().getResource(soundFile);
        try
        {
          this.audioStream = AudioSystem.getAudioInputStream(url);
          audioFormat = this.audioStream.getFormat();
          
          DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
          try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
          } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
          } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
          }
          sourceLine.start();
          
          while(running) 
          {
            Thread.sleep(10);
            int nBytesRead = 0;
            byte[] abData = new byte[BUFFER_SIZE];
            
            while (nBytesRead != -1) {
              try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
              } catch (IOException e) {
                e.printStackTrace();
              }
              if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
              }
            }
            if(loop) {
              audioStream = AudioSystem.getAudioInputStream(url);     
            }
          }
          sourceLine.stop();
          sourceLine.drain();
          sourceLine.close();
          System.gc();
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
      }
    }
  }

  public void playBackGround() // call to play .wav file
  {
    this.playSong = true;
    this.audioStream = null;
  }
  
  public void playLoop() // call to play .wav file
  {
    this.playSong = true;
    this.audioStream = null;
    this.loop=true;
  }
  
  public void disposeSound()
  {
    
    this.playSong = false;
    this.audioStream = null;
  }

}