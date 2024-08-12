import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Queue;

public class GridDrawer extends JPanel {
    private Grid grid;
    private Queue<Point> mouseTrails;
    private static final int TRAIL_LENGTH = 50; // Number of frames to keep trails

    public GridDrawer() {
        grid = new Grid(20, 20, 35, 10);
        mouseTrails = new LinkedList<>();

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point mousePos = e.getPoint();
                grid.highlightCell(mousePos);
                addMouseTrail(mousePos); // Add the new mouse position to the trail
                repaint();
            }
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point mousePos = e.getPoint();
                grid.colorCell(mousePos, Color.YELLOW); // Change color on click
                repaint();
            }
        });

        // Timer to repaint and update at regular intervals
        new Timer(16, e -> {
            updateTrails(); // Update trails every frame
            repaint();
        }).start(); // Approximately 60 FPS
    }

    private void addMouseTrail(Point mousePos) {
        mouseTrails.add(new Point(mousePos)); // Add the new point
    }

    private void updateTrails() {
        if (mouseTrails.size() > TRAIL_LENGTH) {
            mouseTrails.poll(); // Remove the oldest trail point if the queue exceeds TRAIL_LENGTH
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the grid first
        grid.paint(g);

        // Draw the mouse trails over the grid
        g.setColor(new Color(0, 0, 0, 100)); // Semi-transparent black
        for (Point p : mouseTrails) {
            g.fillOval(p.x - 10, p.y - 10, 20, 20); // Draw circles with radius 10
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(720, 720);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Painting Grid with Mouse Trails");
        GridDrawer gridDrawer = new GridDrawer();

        frame.add(gridDrawer);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
