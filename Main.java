import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Entry point for the Brick Wall Breaker game.
 * Creates the main window and starts the GamePanel.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Brick Wall Breaker");
            GamePanel panel = new GamePanel();

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
