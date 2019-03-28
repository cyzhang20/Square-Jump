import java.awt.Color;
import java.awt.Graphics;

public class Particle 
{
  private double x;
  private double y;
  private double dx;
  private double dy;
  private int length;
  private int width;
  private int life;
  private Color color;
   
   public Particle(double x, double y, double dx, double dy, int width, int life, Color color)
   {
    this.x = x;
    this.y = y;
    this.dx = dx;
    this.dy = dy;
    this.width = width;
    this.life = life;
    this.color = color;
    
   }
   
   public boolean update()
   {
     x += dx;
     y += dy;
     life--;
     
     if(life <= 0)
       return true;
     return false;
   }

   public void drawParticle(Graphics g)
   {
    g.setColor(color);
    g.fillRect((int) x, (int) y, width, width);
   }
}