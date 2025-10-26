import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Represents the ball in the game.
 * Inherits from ObjectGame and handles movement, bouncing, and speed control.
 */
public class Ball extends ObjectGame {
    private double speedX;
    private double speedY;
    private final double speedIncrement = 1.05; // ball speeds up by 5% after each brick hit
    private final double maxSpeed = 10.0;

    /**
     * Creates a new ball object.
     * @param x initial x position
     * @param y initial y position
     * @param size diameter of the ball
     * @param color color of the ball
     */
    public Ball(int x, int y, int size, Color color) {
        super(x, y, size, size, color);
        this.speedX = 3.0; // initial horizontal speed
        this.speedY = -3.0; // initial upward speed
    }

    /**
     * Updates the ball’s position based on its current velocity.
     * Handles screen-edge collisions (bouncing horizontally and vertically).
     */
    @Override
    public void update() {
        x += (int) Math.round(speedX);
        y += (int) Math.round(speedY);

        // Bounce off left and right edges
        if (x <= 0 || x + width >= 720) {
            bounceX();
        }

        // Bounce off top edge
        if (y <= 0) {
            bounceY();
        }
    }

    /**
     * Draws the ball as a filled circle.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }

    /**
     * Reverses the ball’s horizontal direction (left/right bounce).
     */
    public void bounceX() {
        speedX = -speedX;
    }

    /**
     * Reverses the ball’s vertical direction (up/down bounce).
     */
    public void bounceY() {
        speedY = -speedY;
    }

    /**
     * Increases the ball’s speed slightly after hitting a brick.
     */
    public void increaseSpeed() {
        speedX *= speedIncrement;
        speedY *= speedIncrement;

        // cap speed to prevent chaos
        double magnitude = Math.sqrt(speedX * speedX + speedY * speedY);
        if (magnitude > maxSpeed) {
            double scale = maxSpeed / magnitude;
            speedX *= scale;
            speedY *= scale;
        }
    }

    /**
     * Adjusts the ball’s horizontal direction slightly
     * depending on where it hits the paddle.
     */
    public void adjustAngle(double delta) {
        speedX += delta;
    }

    /**
     * Returns true if the ball falls below the bottom edge (game over condition).
     */
    public boolean isOutOfBounds(int panelHeight) {
        return y > panelHeight;
    }

    /**
     * Returns the bottom edge of the ball (used for collision logic).
     */
    public int getBottom() {
        return y + height;
    }

    /**
     * Returns a Rectangle object representing the ball’s bounds for collision detection.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
