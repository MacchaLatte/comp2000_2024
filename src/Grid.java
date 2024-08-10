import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class Grid {
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
        // Reset previous highlights
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.setHighlighted(false);
            }
        }
        highlightedCells.clear();

        // Highlight the cell under the mouse
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

    public void colorCell(Point mousePos, Color color) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (cells[row][col].contains(mousePos)) {
                    cells[row][col].setColor(color);
                    return;
                }
            }
        }
    }
}
