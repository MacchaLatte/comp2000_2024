import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main extends JFrame {
    public static void main(String[] args) {
        Main window = new Main();
        window.setVisible(true);
    }

    class Canvas extends JPanel {
        Grid grid = new Grid(20, 20, 35, 10);

        public Canvas() {
            setPreferredSize(new Dimension(720, 720));
            Timer timer = new Timer(16, e -> repaint()); // Approximately 60 FPS
            timer.start();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            grid.paint(g);
        }
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();
        this.setContentPane(canvas);
        this.pack();
    }
}
