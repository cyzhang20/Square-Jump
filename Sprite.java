import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.imageio.*;

public class Sprite
{
  private double left;  //the x-coordinate of the left edge of the sprite
  private double top;   //the y-coordinate of the top edge of the sprite
  private int width;
  private int height;
  private String image;
  
  public Sprite(double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    left = theLeft;
    top = theTop;
    width = theWidth;
    height = theHeight;
    setImage(theImage);
  }
  
  public double getLeft()
  {
    return left;
  }
  
  public void setLeft(double l)
  {
    left = l;
  }
  
  public double getTop()
  {
    return top;
  }
  
  public void setTop(double t)
  {
    top = t;
  }
  
  public int getWidth()
  {
    return width;
  }
  
  public void setWidth(int w)
  {
    width = w;
  }
  
  public int getHeight()
  {
    return height;
  }
  
  public void setHeight(int h)
  {
    height = h;
  }
  
  public String getImage()
  {
    return image;
  }
  
  
  public void setImage(String i)
  {
    image = i;
  }
  
  public boolean overlap(Sprite s2)
  {
    double s1Left = this.getLeft();
    double s2Left = s2.getLeft();
    double s1Right = this.getLeft() + this.getWidth();
    double s2Right = s2.getLeft() + s2.getWidth(); 
    double s1Top = this.getTop();
    double s2Top = s2.getTop();
    double s1Bottom = this.getTop() + this.getHeight();
    double s2Bottom = s2.getTop() + s2.getHeight();
    
    /*System.out.println("s1Left & s2Right: " + s1Left + " " + s2Right);
    System.out.println("s2Left < s1Right: " + s2Left + " " + s1Right);
    System.out.println("s1Top < s2Bottom: " + s1Top + " " + s2Bottom);
    System.out.println("s2Top < s1Bottom: " + s2Top + " " + s1Bottom);*/
    if(s1Left < s2Right && s2Left < s1Right && s1Top < s2Bottom && s2Top < s1Bottom)
    {
      return true;
    }
    return false;
  }
  
  public void step(World world)
  {
    //do NOT insert any code here
  }
}
