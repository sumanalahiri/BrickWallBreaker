import java.awt.*;

/**
 * Represents a single brick in the Brick Wall Breaker game.
 * Each brick has a strength value that determines how many hits
 * are required to destroy it. The brick changes color intensity
 * based on its strength and flashes briefly when hit.
 */
public class Brick extends ObjectGame {

    private int strength;       // Number of hits required to destroy
    private boolean visible;    // Whether the brick is still present
    private int flashTicks = 0; // Frames for flashing effect when hit

    /**
     * Creates a new brick at the specified position and size with given strength.
     *
     * @param x the x-coordinate of the brick
     * @param y the y-coordinate of the brick
     * @param width the width of the brick
     * @param height the height of the brick
     * @param strength the number of hits required to destroy the brick (1–3)
     */
    public Brick(int x, int y, int width, int height, int strength) {
        super(x, y, width, height, pickColor(strength));
        this.strength = strength;
        this.visible = true;
    }

    /**
     * Chooses a color based on brick strength:
     * 3 = red, 2 = orange, 1 = yellow.
     *
     * @param strength the brick's hit resistance
     * @return the corresponding color
     */
    private static Color pickColor(int strength) {
        if (strength >= 3) {
            return new Color(187, 52, 37); // red
        }
        if (strength == 2) {
            return new Color(233, 128, 46); // orange
        }
        return new Color(247, 222, 60);                     // yellow
    }

    /**
     * Updates the brick’s visual state each frame.
     * Used for flash effect after being hit.
     */
    @Override
    public void update() {
        if (flashTicks > 0) {
            flashTicks--;
        }
    }

    /**
     * Draws the brick if visible.
     * Brighter color is displayed briefly when recently hit.
     *
     * @param g the Graphics object used for drawing
     */
    @Override
    public void draw(Graphics g) {
        if (!visible) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        Color drawColor = color;

        // Temporary brightness effect when hit
        if (flashTicks > 0) {
            drawColor = color.brighter();
        }

        g2.setColor(drawColor);
        g2.fillRect(x, y, width, height);

        // Outline for clarity
        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(x, y, width, height);
    }

    /**
     * Handles a hit from the ball.
     * Decreases strength, updates color, and marks as invisible when destroyed.
     *
     * @return true if the brick was destroyed, false otherwise
     */
    public boolean hit() {
        if (!visible) {
            return false;
        }

        strength--;
        flashTicks = 6; // short visual flash

        if (strength <= 0) {
            visible = false;
            return true; // destroyed
        }

        // Update color based on remaining strength
        color = pickColor(strength);
        return false;
    }

    /**
     * Returns whether the brick is still visible.
     *
     * @return true if the brick is visible, false otherwise
     */
    public boolean isVisible() {
        return visible;
    }
}
