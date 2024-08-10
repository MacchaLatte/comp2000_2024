import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Cell {
    private int x, y, size;
    private boolean highlighted;
    private Color color; // Add a color property

    public Cell(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.highlighted = false;
        this.color = Color.WHITE; // Default color
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
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
