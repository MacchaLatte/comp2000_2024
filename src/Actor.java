import java.awt.Graphics;

public abstract class Actor {
    protected Cell cell;

    public Actor(Cell cell) {
        this.cell = cell;
    }

    public abstract void paint(Graphics g);
}