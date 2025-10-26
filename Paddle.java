import java.awt.*;

/**
 * Represents the player's paddle in the Brick Wall Breaker game.
 * The paddle moves horizontally based on player input (arrow keys)
 * and prevents the ball from falling out of bounds.
 */
public class Paddle extends ObjectGame {

    private int windowWidth; // screen boundary to keep paddle within frame
    private final int speed = 8; // pixels per frame

    /**
     * Constructs a Paddle object.
     *
     * @param x the x-coordinate of the paddle
     * @param y the y-coordinate of the paddle
     * @param width the paddle's width
     * @param height the paddle's height
     * @param windowWidth the total width of the game window (for boundary checks)
     */
    public Paddle(int x, int y, int width, int height, int windowWidth) {
        super(x, y, width, height, new Color(66, 135, 245)); // blue paddle
        this.windowWidth = windowWidth;
    }

    /**
     * Updates the paddle's position while ensuring it stays
     * within the game window.
     */
    @Override
    public void update() {
        x += dx;

        // Prevent paddle from leaving screen boundaries
        if (x < 0) {
            x = 0;
        } else if (x + width > windowWidth) {
            x = windowWidth - width;
        }
    }

    /**
     * Draws the paddle on the screen.
     *
     * @param g the Graphics object used for drawing
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, 10, 10);

        // Outline for clarity
        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y, width, height, 10, 10);
    }

    /**
     * Moves the paddle left by setting horizontal velocity.
     */
    public void moveLeft() {
        dx = -speed;
    }

    /**
     * Moves the paddle right by setting horizontal velocity.
     */
    public void moveRight() {
        dx = speed;
    }

    /**
     * Stops the paddle's horizontal movement.
     * Typically called when the user releases the movement keys.
     */
    public void stop() {
        dx = 0;
    }
}
