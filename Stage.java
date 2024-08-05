import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Stage {
    private Grid grid;
    private List<Actor> actors;

    public Stage() {
        grid = new Grid();
        actors = new ArrayList<>();
        actors.add(new Cat(grid.cells[1][1]));
        actors.add(new Dog(grid.cells[2][2]));
        actors.add(new Bird(grid.cells[3][3]));
    }

    public void paint(Graphics g, Point p) {
        grid.paint(g, p);
        for (Actor actor : actors) {
            actor.paint(g);
        }
    }
}