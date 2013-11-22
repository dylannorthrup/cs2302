import java.awt.*;

public class Bullet extends BaseVectorShape {
  // Add age attribute so we can constrain how long a bullet lives
  int age = 0;
  
	// bounding rectangle
	public Rectangle getBounds() {
		Rectangle r;
		r = new Rectangle((int)getX(), (int)getY(), 1, 1);
		return r;
	}
	
	// Constructor
	Bullet() {
		setShape(new Rectangle(0, 0, 1, 1));
		setAlive(false);
	}
}
