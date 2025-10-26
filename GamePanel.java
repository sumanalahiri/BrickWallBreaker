import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


/**
 * The main panel for the Brick Wall Breaker game.
 * Handles game updates, rendering, user input, and collisions.
 * 
 * Features:
 * - Red, orange, and yellow bricks requiring different hits to destroy.
 * - Blue paddle controlled by left/right arrow keys.
 * - Basketball image as the ball.
 * - Bird obstacle image moving horizontally across the screen.
 * - Score tracking, pause, and restart functionality.
 * 
 * This class integrates all game objects and defines the main game loop.
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Timer timer = new Timer(16, this); // ~60 FPS
    private Ball ball;
    private Paddle paddle;
    private BirdObstacle bird;
    private ArrayList<Brick> bricks = new ArrayList<>();
    private int score = 0;
    private boolean left;
    private boolean right;
    private boolean paused;
    private boolean gameOver;

    /**
     * Constructs the game panel, initializes game elements,
     * sets up background, and starts the game timer.
     */
    public GamePanel() {
        setPreferredSize(new Dimension(720, 540));
        setBackground(new Color(153, 204, 102)); // grassy green
        setFocusable(true);
        addKeyListener(this);

        paddle = new Paddle(300, 480, 120, 15, 720);
        ball = new Ball(340, 460, 20, new Color(230, 120, 50));
        bird = new BirdObstacle(100, 200, 50, 40, 3, 40, 680);

        // Create brick layout: red (3 hits), orange (2 hits), yellow (1 hit)
        int y = 60;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 9; c++) {
                bricks.add(new Brick(60 + c * 70, y + r * 30, 60, 20, 3 - r));
            }
        }

        timer.start();
    }

    /**
     * Updates the game state every frame (called by Timer).
     * Handles movement, collisions, scoring, and win/lose conditions.
     * 
     * @param e the ActionEvent triggered by the timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (paused || gameOver) {
            repaint();
            return;
        }

        // Paddle control
        if (left) {
            paddle.moveLeft();
        }
        if (right) {
            paddle.moveRight();
        }

        // Update all moving objects
        paddle.update();
        ball.update();
        bird.update();

        // Collisions
        if (ball.getBounds().intersects(paddle.getBounds())) {
            ball.bounceY();
        }
        if (ball.getBounds().intersects(bird.getBounds())) {
            ball.bounceY();
        }

        // Check collisions with bricks
        for (Brick b : bricks) {
            if (ball.getBounds().intersects(b.getBounds()) && b.isVisible()) {
                ball.bounceY();
                if (b.hit()) {
                    score += 10;
                } else {
                    score += 5;
                }
                ball.increaseSpeed();
                break; // one brick per frame
            }
        }
        bricks.removeIf(b -> !b.isVisible());

        // Check if ball is out of bounds (game over)
        if (ball.isOutOfBounds(getHeight())) {
            gameOver = true;
        }

        repaint();
    }

    /**
     * Draws all game elements (bricks, paddle, ball, bird, and score).
     * Also shows overlay messages for game over and pause.
     * 
     * @param g the Graphics object for drawing components
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw all visible elements
        for (Brick b : bricks) {
            b.draw(g);
        }
        bird.draw(g);
        paddle.draw(g);
        ball.draw(g);

        // Scoreboard
        g.setColor(new Color(255, 214, 96));
        g.setFont(new Font("SansSerif", Font.BOLD, 18));
        g.drawString("Score: " + score, 600, 30);

        // Game over overlay
        if (gameOver) {
            g.setColor(new Color(150, 0, 0, 180));
            g.fillRoundRect(200, 180, 320, 160, 20, 20);
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER", 295, 230);
            g.drawString("Final Score: " + score, 300, 260);
            g.drawString("Press ENTER to restart", 260, 290);
        }
    }

    // --- Key controls ---

    /** Unused (required by KeyListener). */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Handles key presses for movement, pause, and restart.
     * 
     * @param e the KeyEvent triggered by user input
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> left = true;
            case KeyEvent.VK_RIGHT -> right = true;
            case KeyEvent.VK_P -> paused = !paused;
            case KeyEvent.VK_ENTER -> {
                if (gameOver) {
                    resetGame();
                }
            }
        }
    }

    /**
     * Handles key releases for smooth paddle control.
     * 
     * @param e the KeyEvent triggered by user input
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }

    /**
     * Resets the game after a game-over event.
     * Restores bricks, resets the score, and repositions the ball.
     */
    private void resetGame() {
        score = 0;
        gameOver = false;
        bricks.clear();

        int y = 60;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 9; c++) {
                bricks.add(new Brick(60 + c * 70, y + r * 30, 60, 20, 3 - r));
            }
        }

        ball.setX(340);
        ball.setY(460);
    }
}
