import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PongGame extends JFrame {
    private int player1Y = 250;
    private int player2Y = 250;
    private int ballX = 300;
    private int ballY = 200;
    private int ballXDirection = -1;
    private int ballYDirection = -2;
    private int player1Score = 0;
    private int player2Score = 0;
    private final int PADDLE_HEIGHT = 100;
    private final int PADDLE_WIDTH = 20;
    private final int BALL_DIAMETER = 20;

    public PongGame() {
        setTitle("Pong Game");
        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(new PaddleMovementListener());
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 600, 400);
        g.setColor(Color.BLACK);
        g.fillRect(0, player1Y, PADDLE_WIDTH, PADDLE_HEIGHT);
        g.fillRect(580, player2Y, PADDLE_WIDTH, PADDLE_HEIGHT);
        g.fillOval(ballX, ballY, BALL_DIAMETER, BALL_DIAMETER);
        g.drawString("Player 1: " + player1Score, 50, 50);
        g.drawString("Player 2: " + player2Score, 500, 50);
    }

    public static void main(String[] args) {
        PongGame game = new PongGame();
        game.gameLoop();
    }

    private class PaddleMovementListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                player1Y -= 20;
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                player1Y += 20;
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                player2Y -= 20;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                player2Y += 20;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    public void moveBall() {
        ballX += ballXDirection;
        ballY += ballYDirection;

        if (ballY > 380 || ballY < 0) {
            ballYDirection = -ballYDirection;
        }

        if (ballX < 20) {
            if (ballY > player1Y && ballY < player1Y + 100) {
                ballXDirection = -ballXDirection;
            } else {
                player2Score++;
                reset();
            }
        }

        if (ballX > 580) {
            if (ballY > player2Y && ballY < player2Y + 100) {
                ballXDirection = -ballXDirection;
            } else {
                player1Score++;
                reset();
            }
        }
    }

    public void reset() {
        ballX = 300;
        ballY = 200;
        ballXDirection = -1;
        ballYDirection = -2;
    }

    public void gameLoop() {
        while (true) {
            moveBall();
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}