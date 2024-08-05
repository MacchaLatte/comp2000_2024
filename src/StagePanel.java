import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Point;

public class StagePanel extends JPanel {
    private Stage stage;
    private Point p;
  
    public StagePanel() {
      stage = new Stage();
      p = new Point(0, 0);
      addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        @Override
        public void mouseMoved(java.awt.event.MouseEvent e) {
          p = e.getPoint();
          repaint();
        }
      });
    }
  
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      stage.paint(g, p);
    }
  }
  