import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

class Cell {
    private int x, y, size;
    private boolean highlighted;

    public Cell(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.highlighted = false;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public void paint(Graphics g) {
        if (highlighted) {
            g.setColor(Color.GRAY);
            g.fillRect(x, y, size, size);
        }
        g.setColor(Color.BLACK);
        g.drawRect(x, y, size, size);
    }

    public boolean contains(Point p) {
        return p.x >= x && p.x < x + size && p.y >= y && p.y < y + size;
    }
}

class Grid {
    private int rows, cols, cellSize, offset;
    private Cell[][] cells;
    private Set<Cell> highlightedCells;

    public Grid(int rows, int cols, int cellSize, int offset) {
        this.rows = rows;
        this.cols = cols;
        this.cellSize = cellSize;
        this.offset = offset;
        cells = new Cell[rows][cols];
        highlightedCells = new HashSet<>();
        initializeCells();
    }

    private void initializeCells() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = offset + col * cellSize;
                int y = offset + row * cellSize;
                cells[row][col] = new Cell(x, y, cellSize);
            }
        }
    }

    public void paint(Graphics g) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col].paint(g);
            }
        }
    }

    public void highlightCell(Point mousePos) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (cells[row][col].contains(mousePos)) {
                    cells[row][col].setHighlighted(true);
                    highlightedCells.add(cells[row][col]);
                    return;
                }
            }
        }
    }
}

public class GridDrawer extends JPanel {
    private Grid grid;

    public GridDrawer() {
        grid = new Grid(20, 20, 35, 10);
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                Point mousePos = e.getPoint();
                grid.highlightCell(mousePos);
                repaint();
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        grid.paint(g);
    }

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
