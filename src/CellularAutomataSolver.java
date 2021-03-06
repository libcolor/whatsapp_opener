import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * The problem is that the history of the walls is not preserved aka once you start knocking down walls it starts the maze over
 */

public class CellularAutomataSolver {
    JPanel[][] mat;
    CellularAutomataMaze maze;
    Stack<Point> walls = new Stack<Point>();
    boolean solved = false;

    public CellularAutomataSolver() {
        maze = new CellularAutomataMaze();

        maze.runWithRules(new int[] {2,3,4,5},new int[] {2,3},700);
        Point Start = getStart(maze.mat);
        maze.repaintSingle(Start.x,Start.y,C