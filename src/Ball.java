import java.awt.Color;
import java.awt.geom.Ellipse2D;



public class Ball {
	//necessary ball variables + objects
	private Ellipse2D shape;
	private int diameter;
	private Color ballColor;
	private int dx;
	private int dy;
	
	public Ball(int x, int y, int diameter, int dx, int dy) {
		this.diameter = diameter;
		this.dx = dx;
		this.dy = dy;
		shape = new Ellipse2D.Double(x, y, diameter, diameter);  //this shape is what the graphics handler actually fills
	}
	
	public void move(int xLim, int yLim) {
		//check for bounces on edge of frame
		if(shape.getX() <= 0 || shape.getX() >= xLim - diameter)
			dx = -dx;
		if(shape.getY() <= 0 || shape.getY() >= yLim - diameter)
			dy = -dy;
		shape.setFrame(shape.getX() + dx, shape.getY() + dy, diameter, diameter); //update coordinates
	}

	public Ellipse2D getShape() {
		return shape;
	}

	public int getDiameter() {
		return diameter;
	}

	public Color getBallColor() {
		return ballColor;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	public void setShape(Ellipse2D shape) {
		this.shape = shape;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public void resetBallColor(Color ballColor) {
		this.ballColor = ballColor;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
	
}
