import java.awt.Color;
import java.awt.Graphics;

public class Cat extends Actor {
    public Cat(Cell cell) {
        super(cell);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(cell.x + 10, cell.y + 10, Cell.size - 20, Cell.size - 20);
        g.drawRect(cell.x + 5, cell.y + 5, Cell.size - 10, Cell.size - 10);
    }
}

