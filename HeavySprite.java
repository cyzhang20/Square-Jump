public class HeavySprite extends MobileSprite
{
  MobileSprite movingSquare;
  public HeavySprite(double left, double top, int width, int height, String image, double vx, double vy)
  {
    super(left, top, width, height, image, vx, vy);
    
  }
  
  
  public void step(World world)
  {
    movingSquare = world.getMovingSquare();
  
    super.step(world);
    
  }
 
    
  
}