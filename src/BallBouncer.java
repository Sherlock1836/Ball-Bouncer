import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.Timer;

public class BallBouncer {
	
	//Ball construction variables
	private static final int MAX_DIAMETER = 150;
	private static final int MAX_DX = 5;
	private static final int MIN_DX = -5;
	private static final int MAX_DY = 5;
	private static final int MIN_DY = -5;
	private static int numBalls;
	private static boolean changeColors;
	private static boolean changeSizes;
	//Counter used for the timing of size and color changes
	//Alternatively, another Timer object could be used with a different delay
	private static int counter = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Frame setup (size overridden later just needs initialization for ball position generation)
		JFrame frame = new JFrame("Ball Bouncer");
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);  //set initial frame location to center so balls spawn in center
		
		Random rand = new Random();         //Important to have only one Random object so randoms are "truly" random
		
		Scanner scn = new Scanner(System.in);
		System.out.println("How many balls would you like?");  //somewhere around 100 balls looks pretty cool but even higher is crazy
		numBalls = Integer.parseInt(scn.nextLine());
		System.out.println("Would you like them to change colors? Y/N");
		changeColors = scn.nextLine().charAt(0) == 'Y';
		System.out.println("Would you like them to change size? Y/N");
		changeSizes = scn.nextLine().charAt(0) == 'Y';
		scn.close();
		
		
		//creates an array of balls and fills it with new balls that have random attributes
		Ball[] balls = new Ball[numBalls];  

		//FIX THIS
		for(int i = 0; i < balls.length; ++i) {
			int diameter = rand.nextInt(15, MAX_DIAMETER + 1);
			int x = rand.nextInt(frame.getWidth() - diameter);
			int y = rand.nextInt(frame.getHeight() - diameter);
			int dx = MIN_DX + rand.nextInt(MAX_DX - MIN_DX + 1);
			int dy = MIN_DY + rand.nextInt(MAX_DY - MIN_DY + 1);
			while((dy == 0) || (dx == 0)) {
				dx = MIN_DX + rand.nextInt(MAX_DX - MIN_DX + 1);
				dy = MIN_DY + rand.nextInt(MAX_DY - MIN_DY + 1);
			}
			balls[i] = new Ball(x, y, diameter, dx, dy);
			balls[i].resetBallColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
		}
		
		//create a ball drawer and add to frame
		//ORDER OF STATEMENTS IS EXTREMELY IMPORTANT FOR THIS PART
		BallDrawer b = new BallDrawer(balls);
		frame.add(b);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //sets frame to fullscreen
		frame.setUndecorated(true);  //adds to screensaver feel by getting rid of the top bar
		frame.setVisible(true);   //always add components before setting frame visible
		
		class TimerListener implements ActionListener {         
			public void actionPerformed(ActionEvent event) {
				//frame limits are passed to moveBalls so they can be used later to check for bounces
				//BallDrawer has a moveBalls method because it needs to paint each ball and repaint when balls move
				b.moveBalls(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
				//counter is used for the timing of size and color changes, but another Timer object might be better	
				for (Ball ball : balls) {
						if((counter % 15) == 0 && changeColors == true)
							ball.resetBallColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
						if(changeSizes == true) {
							if(counter < 15) {
								ball.setDiameter(ball.getDiameter() + 2);
							} else if(counter >= 15) {
								ball.setDiameter(ball.getDiameter() - 2);
							}
						}
					}
					++counter;
					if(counter == 30)
						counter = 0;
			}      
		}
		//set up timer
		ActionListener listener = new TimerListener();      
		final int DELAY = 10; // Milliseconds between timer ticks      
		Timer ticker = new Timer(DELAY, listener);      
		ticker.start();
	}
}
