import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridDrawer extends JPanel {
    private Grid grid;

    public GridDrawer() {
        grid = new Grid(20, 20, 35, 10);
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point mousePos = e.getPoint();
                grid.highlightCell(mousePos);
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        grid.paint(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(720, 720);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Painting Grid");
        GridDrawer gridDrawer = new GridDrawer();

        frame.add(gridDrawer);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
