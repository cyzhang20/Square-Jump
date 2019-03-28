public class MovingSquareSprite extends HeavySprite
{
  public MovingSquareSprite(double left, double top, int width, int height, String image, double vx, double vy)
  {
    super(left, top, width, height, image, vx, vy);
  }
  
  public void step(World world)
  {
    super.setVX(super.getVX());
    super.step(world);
    
    if(super.getLeft() > world.getWidth())
    {
      world.increaseLevel();
      //System.out.println("reached end of world");
      //System.out.println(super.getLeft());
      world.reset();
    }
  }
  

  
}