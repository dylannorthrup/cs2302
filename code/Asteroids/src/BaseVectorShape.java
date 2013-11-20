import java.awt.Shape;

// Base vector shape class for polygonal shapes
public class BaseVectorShape {
	private Shape shape;
	private boolean alive;
	private double x,y;
	private double velX, velY;
	private double moveAngle, faceAngle;
	
	// getters
	public Shape getShape() { return shape; }
	public boolean isAlive() { return alive; }
	public double getX() { return x; }
	public double getY() { return y; }
	public double getVelX() { return velX; }
	public double getVelY() { return velY; }
	public double getMoveAngle() { return moveAngle; }
	public double getFaceAngle() { return faceAngle; }
	
	// setters
	public void setShape(Shape shape) { this.shape = shape; }
    public void setAlive(boolean alive) { this.alive = alive; }
    public void setX(double x) { this.x = x; }
    public void incX(double i) { this.x += i; }
    public void setY(double y) { this.y = y; }
    public void incY(double i) { this.y += i; }
    public void setVelX(double velX) { this.velX = velX; }
    public void incVelX(double i) { this.velX += i; }
    public void setVelY(double velY) { this.velY = velY; }
    public void incVelY(double i) { this.velY += i; }
    public void decVelX(double i) {
      if (velX < 1 && velX > -1 && velX != 0) {
        System.out.println("Setting velX to 0");
        this.velX = 0;
      } else if (velX > 0) {
        this.velX -= i;
      } else if (velX < 0){
        this.velX += i;
      } 
    }
    public void decVelY(double i) { 
      i = Math.abs(i);
      if (velY < 1 && velY > -1 && velY != 0) {
        System.out.println("Setting velY to 0");
        this.velY = 0;
      } else if (velY > 0) {
        System.out.println("Reducing VelY (" + velY + ") by " + i);
        this.velY -= i;
      } else if (velY < 0){
        System.out.println("Increasing VelY (" + velY + ") by " + i);
        this.velY += i;        
      }
    }
    public void setFaceAngle(double angle) { this.faceAngle = angle; }
    public void incFaceAngle(double i) { this.faceAngle += i; }
    public void setMoveAngle(double angle) { this.moveAngle = angle; }
    public void incMoveAngle(double i) { this.moveAngle += i; }

    //default constructor
    BaseVectorShape() {
        setShape(null);
        setAlive(false);
        setX(0.0);
        setY(0.0);
        setVelX(0.0);
        setVelY(0.0);
        setMoveAngle(0.0);
        setFaceAngle(0.0);
    }
	
}
