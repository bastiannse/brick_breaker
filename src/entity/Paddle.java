package entity;

import java.awt.Color; 
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import bricks.LargePaddle;
import bricks.Modifier;
import main.GameWindow;

public class Paddle implements GameEntity {

	private double drawx;
	private double targetx; //coordinata dove il paddle cerca di andare
	private  int YPOS = 400;//dove viene posizionato
	private int paddleWidth , paddleHeight;
	private Color c; 
	private boolean largePaddleActive;
	private int targetLargePaddleCount, largePaddleCount;
	private BufferedImage myImg;
	
	public Paddle(BufferedImage img) {
		drawx = GameWindow.WIDTH / 2 ; 
		targetx = drawx;
		paddleWidth = 80;
		paddleHeight = 20;
		c = Color.DARK_GRAY;
		largePaddleActive = false;
		targetLargePaddleCount = 500;
		largePaddleCount = 0;
		myImg = img;
	}

    public void update() {
		//aggiunge 1/10 della distanza tra tagetx e drawx a drawq.
		drawx += (targetx - drawx) * .2;
		if(largePaddleActive) {
			largePaddleCount++;
			if(largePaddleCount > targetLargePaddleCount) {
				largePaddleActive = false;
				largePaddleCount = 0;
				paddleWidth = paddleWidth / 2;
				
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(c);
		//g2.fillRoundRect((int)drawx, YPOS, paddleWidth, paddleHeight, 20, 20);
		g2.drawImage(myImg, (int)drawx, YPOS, paddleWidth, paddleHeight,null);
	}
	
	public Rectangle getRect() {
		return new Rectangle((int)drawx, YPOS, paddleWidth, paddleHeight);
	}
	
	public void mouseMoved(int mousex) {
		//target comunica con mouse in update
		targetx = mousex - paddleWidth / 2;
		
	}

	
	public void largePaddle() {
		
		if(!largePaddleActive) {
			paddleWidth = paddleWidth * 2;
			largePaddleActive = true; 
		}
		
	}
	
	public void modify(Modifier m) {
		
		if(m instanceof LargePaddle) {
			largePaddle();
		}
		
	}

}