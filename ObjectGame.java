import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Represents a generic object in the game with position, size, color, and movement.
 * Child classes should implement method for updating the object's position,
 * velocity or state. Should also implement the draw method.
 */
public abstract class ObjectGame {
    protected int x; 
    protected int y; 
    protected int width;
    protected int height; 
    protected int dx; // movement in x direction
    protected int dy; // movement in y direction
    protected Color color; 

    /**
     * Creates a new game object with the specified position, size, and color.
     * 
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     * @param width the width of the object
     * @param height the height of the object
     * @param color the colour of the object
     */
    public ObjectGame(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.dx = 0; // default no movement in x
        this.dy = 0; // default no movement in y
    }

    public abstract void update(); // update object position, velocity, or state

    public abstract void draw(Graphics g); // draw the object on screen


    // collision detection, 
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Updates the object's position by adding the movement in the x and y direction
     * to its coordinates (x, y).
     */
    public void move() {
        x += dx;
        y += dy;
    }

    // Setters and getters
    public int getX() { 
        return x; 
    }

    public int getY() { 
        return y; 
    }

    public void setX(int x) { 
        this.x = x; 
    }

    public void setY(int y) { 
        this.y = y; 
    }
    
    public int getWidth() { 
        return width; 
    }

    public int getHeight() { 
        return height; 
    }
    
    public int getDX() { 
        return dx; 
    }

    public int getDY() { 
        return dy; 
    }

    public void setDX(int dx) { 
        this.dx = dx; 
    }

    public void setDY(int dy) { 
        this.dy = dy; 
    }

    public Color getColor() { 
        return color; 
    }

    public void setColor(Color color) { 
        this.color = color; 
    }
}
