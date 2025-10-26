import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A horizontal moving obstacle represented by a bird image.
 * <p>
 * The bird slides left-to-right within a given range and bounces back
 * at the edges. It extends {@link ObjectGame} so it participates in
 * collision via {@code getBounds()} inherited from the parent.
 * </p>
 *
 * <p>Place a transparent PNG at: <b>assets/bird.png</b>.
 * If the image cannot be loaded, a simple white oval is drawn as a fallback.</p>
 */
public class BirdObstacle extends ObjectGame {

    /** Horizontal speed in pixels per frame (can be negative to start right→left). */
    private int speed;

    /** Minimum and maximum x-coordinates (inclusive bounds) for horizontal travel. */
    private final int minX;
    private final int maxX;

    /** Bird sprite; may be {@code null} if loading fails (fallback drawing will be used). */
    private Image birdImage;

    /**
     * Constructs a moving bird obstacle.
     *
     * @param x      starting x-coordinate
     * @param y      starting y-coordinate (lane height)
     * @param width  drawing width of the bird
     * @param height drawing height of the bird
     * @param speed  initial horizontal speed (positive → right, negative → left)
     * @param minX   left boundary (inclusive) of movement
     * @param maxX   right boundary (inclusive) of movement
     */
    public BirdObstacle(int x, int y, int width, int height, int speed, int minX, int maxX) {
        super(x, y, width, height, Color.WHITE);
        this.speed = speed;
        this.minX = minX;
        this.maxX = maxX;
        loadImage("assets/bird.png");
    }

    /**
     * Loads the bird image from disk.
     *
     * @param path relative path to the image (e.g., {@code assets/bird.png})
     */
    private void loadImage(String path) {
        try {
            birdImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            birdImage = null; // fallback drawing will be used
        }
    }

    /**
     * Updates the bird position and flips direction at travel bounds.
     */
    @Override
    public void update() {
        x += speed;

        // Bounce at the edges of the allowed range
        if (x < minX) {
            x = minX;
            speed = -speed;
        } else if (x + width > maxX) {
            x = maxX - width;
            speed = -speed;
        }
    }

    /**
     * Draws the bird. If the image is unavailable, draws a simple oval fallback.
     *
     * @param g the Graphics context used for drawing
     */
    @Override
    public void draw(Graphics g) {
        if (birdImage != null) {
            g.drawImage(birdImage, x, y, width, height, null);
        } else {
            // Fallback: simple white oval with a dark outline
            g.setColor(Color.WHITE);
            g.fillOval(x, y, width, height);
            g.setColor(new Color(40, 40, 40));
            g.drawOval(x, y, width, height);
        }
    }

    /**
     * Sets a new horizontal speed. Positive moves right; negative moves left.
     *
     * @param newSpeed the desired horizontal speed
     */
    public void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }

    /**
     * @return current horizontal speed in pixels per frame
     */
    public int getSpeed() {
        return speed;
    }
}
