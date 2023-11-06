import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class BallDrawer extends JComponent{
	
	private static final long serialVersionUID = 1L;  //this line exists solely to get rid of a yellow squiggle
	private Graphics2D g2D;
	private Ball[] balls;
	
	public BallDrawer(Ball[] balls) {
		this.balls = balls;  //assigns a reference to the shapes array in BallBouncer for use in paint component
	}
	
	public void paintComponent(Graphics g) {
		//gets color of each ball and fills each with its color
		g2D = (Graphics2D) g;
		for(Ball ball : balls) {
			g2D.setColor(ball.getBallColor());
			g2D.fill(ball.getShape());
		}
	}
	
	public void moveBalls(int xLim, int yLim) {
		//update coorrdinates of each ball and repaint
		for(Ball ball : balls) {
			ball.move(xLim, yLim);
		}
		repaint();
	}
}
