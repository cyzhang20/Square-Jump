import java.util.*;
import java.awt.Color;
import java.awt.Graphics;

public class ParticleSystem
{
  private ArrayList<Particle> particles;
  private int life = 50;
  
  public ParticleSystem(int n, double x, double y, int width, Color color)
  {
    
     particles = new ArrayList<Particle>(n);
     for (int i = 0; i < n; i++)
     {
       double speed = Math.random() * 3;
       double dir = Math.random() * 2* Math.PI;
       particles.add(new Particle(x, y, speed * Math.cos(dir), speed * Math.sin(dir), width, life, color));
     }
  }
  
  public int size()
  {
    return particles.size();
  }
  
  public Particle getParticle(int i)
  {
    return particles.get(i);
  }
  
  public void remove(int i)
  {
    particles.remove(i);
  }
  
  public boolean update(Graphics g)
  {
    for(int i = 0; i< particles.size(); i++)
    {
      Particle temp = particles.get(i);
      if(!temp.update())
        temp.drawParticle(g);
      else
        particles.remove(temp);
    }
    if(particles.size() == 0)
      return false;
    else
      return true;
  }
  
}