import java.awt.Color;
import java.awt.Graphics;

public class Dog extends Actor {
    public Dog(Cell cell) {
        super(cell);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(cell.x + 10, cell.y + 10, Cell.size - 20, Cell.size - 20);
        g.drawRect(cell.x + 5, cell.y + 5, Cell.size - 10, Cell.size - 10);
    }
}