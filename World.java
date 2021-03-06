import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import sun.audio.*;
import java.io.*;
import java.text.DecimalFormat;
import java.lang.Double;
import java.net.*;
import javax.sound.sampled.*;


public class World
{
  public static void main(String[] args)
  {
    Display display = new Display(1200, 500, 3);
    
    display.run();
   
  }
  
  private ArrayList<Sprite> sprites;
  private int width;
  private int height;
  private MobileSprite movingSquare;
  boolean space;
  private int level = 0;
  private double speed = 0;
  private ParticleSystem particleSystem;
  private Sprite background;
  private Sprite endScreen;
  boolean time = false;
  private long start;
  private long elapsedTimeMillis;
  private boolean win = false;
  private DecimalFormat formatter = new DecimalFormat("0.0");
  private TreeSet<Double> scores = new TreeSet<Double>();
  private int timesDied = 0;
  
  public World(int w, int h, double speed)
  {
    
    this.speed = speed;
    Sound x = new Sound("/Off Limits.wav");
    x.playLoop();
    
    /*backgroundSound = getClass().getResource("Off Limits.wav");
     
    Applet.newAudioClip(backgroundSound).loop();*/
    

    width = w;
    height = h;
    sprites = new ArrayList<Sprite>();
    double dir;
    background = new Sprite(140, 55, 900, 445, "secondBackground.png");
    sprites.add(background);
    

  }
  
  public void stepAll()
  {

    for (int i = 0; i < sprites.size(); i++)
    {
      Sprite s = sprites.get(i);
      if(sprites.get(i) != movingSquare && sprites.get(i) != background)
      {
        if(sprites.get(i).overlap(movingSquare))
        {    
          /*mirrorShattering = getClass().getResource("mirrorShattering.wav");
          Applet.newAudioClip(mirrorShattering).play();*/
          
          Sound x = new Sound("/mirrorShattering.wav");
          x.playBackGround();
          particleSystem = new ParticleSystem(15, movingSquare.getLeft() + movingSquare.getWidth(), 
                                                movingSquare.getTop() + movingSquare.getHeight()/2, 8, 
                                                Color.red);
          movingSquare.setTop(450);
          movingSquare.setLeft(0);
          timesDied++;
          
    
        }
      }
        s.step(this);
      }
  }
  
  public MobileSprite getMovingSquare()
  {
    return movingSquare;
  }
  
  
  public int getWidth()
  {
    return width;
  }
  
  public int getHeight()
  {
    return height;
  }
  
  public int getNumSprites()
  {
    return sprites.size();
  }
  
  public Sprite getSprite(int index)
  {
    return sprites.get(index);
  }

  public void mouseClicked(int x, int y)
  {
    //System.out.println("mouseClicked:  " + x + ", " + y);
  }
  
  public boolean keyPressed(int key)
  {
    //cannot jump during the jump. set inital upward speed
    if(key == 32 && level != 0)
    {
      if(space==false) //space
        movingSquare.setVY(-15.0);
      
      space = true;
      
      return key == 32;
    }
    else if(key == 10 && level == 0) //enter
    {
      level = 1;
      time = false;
      timesDied = 0;
      sprites.remove(background);
      sprites.remove(movingSquare);
      /*movingSquare = new MovingSquareSprite(0, 450, 50, 50, "square.png",
                                speed, 0);
      sprites.add(movingSquare);
      sprites.add(new Sprite(550,
                             450, 50, 50, "square.png"));*/
      this.reset();
      
    }
    else if(key == 16) //shift
    {
      win = false;
      level = 0;
      this.reset();
      sprites.remove(movingSquare);
    }
    return key == 32;
  }
  
  public boolean keyPressed2() //space
  { 
      return space == true;
  }
  
  public void setSpaceVal(boolean x)
  {
    if(x)
      space = true;
    else
      space = false;
  }
  
  public void keyReleased(int key)
  {
    //System.out.println("keyReleased:  " + key);
  }
  
  public void increaseLevel()
  {
    level++;
  }
  
  public int level()
  {
    return level;
  }
  
  public String getTitle()
  {
    return "Square Jump";
  }
  public void reset()
  {
    
    while(sprites.size() > 1 && sprites.size() != 0)
    {
      sprites.remove(1);
    }

    if(level == 0)
    {      
      background = new Sprite(140, 55, 900, 445, "secondBackground.png");
      sprites.add(background);
    }
    else if(level == 1)
    {
      start = System.currentTimeMillis();
      movingSquare = new MovingSquareSprite(0, 450, 50, 50, "square.png",
                                speed, 0);
      sprites.add(movingSquare);
      sprites.add(new Sprite(550,
                             450, 50, 50, "square.png"));
      
      sprites.add(new Sprite(0, 10, 250, 75, "level1.png"));
    }
    else if(level == 2)
    {
      sprites.add(new Sprite(300, 450,
                             50, 50, "square.png"));
      sprites.add(new Sprite(300,
                             405, 50, 50, "square.png"));
      
      
      sprites.add(new Sprite(500, 450,
                             50, 50, "square.png"));
      sprites.add(new Sprite(500,
                           405, 50, 50, "square.png"));
      
      
      sprites.add(new Sprite(700, 450,
                             50, 50, "square.png"));
      sprites.add(new Sprite(700,
                           405, 50, 50, "square.png"));
      
      sprites.add(new Sprite(0, 10, 250, 75, "level2.png"));
    }
    else if(level == 3)
    {
      sprites.add(new Sprite(300, 450,
                             50, 50, "square.png"));
      sprites.add(new Sprite(300, 402,
                             50, 50, "square.png"));
      sprites.add(new Sprite(300, 358,
                             50, 50, "square.png")); //356
      
      sprites.add(new Sprite(300, 0,
                             50, 50, "square.png"));
      sprites.add(new Sprite(300, 48,
                             50, 50, "square.png"));
      sprites.add(new Sprite(300, 96,
                             50, 50, "square.png"));
      sprites.add(new Sprite(300, 144,
                             50, 50, "square.png"));
      sprites.add(new Sprite(300, 175,
                             50, 50, "square.png"));
      //////////////////////////////////////////////
      sprites.add(new Sprite(800, 450,
                             50, 50, "square.png"));
      sprites.add(new Sprite(800, 402,
                             50, 50, "square.png"));
      sprites.add(new Sprite(800, 358,
                             50, 50, "square.png")); //356
      
      sprites.add(new Sprite(800, 0,
                             50, 50, "square.png"));
      sprites.add(new Sprite(800, 48,
                             50, 50, "square.png"));
      sprites.add(new Sprite(800, 96,
                             50, 50, "square.png"));
      sprites.add(new Sprite(800, 144,
                             50, 50, "square.png"));
      sprites.add(new Sprite(800, 175,
                             50, 50, "square.png"));
      
      sprites.add(new Sprite(0, 10, 250, 75, "level3.png"));
    }
    else if(level == 4)
    {

      sprites.add(new Sprite(250, 450,
                             50, 50, "square.png"));
      sprites.add(new Sprite(250,
                             425, 50, 50, "square.png"));
      
      sprites.add(new Sprite(380,
                             397, 50, 50, "square.png"));
      
      sprites.add(new Sprite(510, 450,
                             50, 50, "square.png"));
      sprites.add(new Sprite(510,
                             425, 50, 50, "square.png"));
      
      sprites.add(new Sprite(660,
                             397, 50, 50, "square.png"));
      
      sprites.add(new Sprite(790, 450,
                             50, 50, "square.png"));
      sprites.add(new Sprite(790,
                             425, 50, 50, "square.png"));
      
      sprites.add(new Sprite(940,
                             397, 50, 50, "square.png"));
      
      sprites.add(new Sprite(0, 10, 250, 75, "level4.png"));

    }
    else if(level == 5)
    {
 
      endScreen = new Sprite(350, 30, 450, 200, "youWin.png");
      particleSystem = new ParticleSystem(250, endScreen.getLeft() + endScreen.getWidth()/2, 
                                                endScreen.getTop() + endScreen.getHeight()/2, 8, 
                                                Color.red);
      sprites.add(endScreen);
      win = true;
      level --;
    }
    movingSquare.setLeft(0);
  }
  /*if(time == false && level == 5)
      {
        long elapsedTimeMillis = System.currentTimeMillis()-start;
        time = true;
        long min = (elapsedTimeMillis/1000) / 60;
        long sLeft =(elapsedTimeMillis/1000) % 60;
        JOptionPane.showMessageDialog(null, "Your Time: " + min + "min " + sLeft+ "s", "TITLE", JOptionPane.PLAIN_MESSAGE);
      }*/

  public void paintComponent(Graphics g)
  {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, width, height);
    for(int i = 0; i < sprites.size(); i++)
    {
      Sprite sprite = sprites.get(i);
      g.drawImage(Display.getImage(sprite.getImage()),
                  (int)sprite.getLeft(), (int)sprite.getTop(),
                  sprite.getWidth(), sprite.getHeight(), null);
    }
    
    
    if(level >= 1)
    {
      g.setColor(Color.RED);
      g.setFont(new Font("TimesRoman", Font.BOLD, 20));
      
      g.drawString("# Of Deaths: " + timesDied , 1000, 30);
      
      if(!win)
      {
        elapsedTimeMillis = System.currentTimeMillis()-start;
      }
      else
      {
        scores.add(new Double(elapsedTimeMillis));
        Iterator<Double>itr = scores.iterator();
        int i = 1;
        g.drawString("Top Times", 1050, 80);
        while(itr.hasNext() && i <= 5)
        {
          g.drawString(i + ".  " + formatter.format(itr.next()*1.0/1000) + "s", 1050, 80 + 22*i);
          i++;
        }
      }
      
      g.drawString("Time: " + formatter.format(elapsedTimeMillis*1.0/1000) + "s", 855, 30);
    }
    g.setColor(Color.BLACK);
    
    if(particleSystem != null)
    {
      for(int i = 0; i< particleSystem.size(); i++)
      {
        Particle temp = particleSystem.getParticle(i);
        if(!temp.update())
          temp.drawParticle(g);
        else
          particleSystem.remove(i);
        
      }
      
    }
  }

  }
