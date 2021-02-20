import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * The problem is that the history of the walls is not preserved aka once you start knocking down walls it starts the maze over
 */

public class CellularAutomataSolver {
    JPanel[][] mat;
    CellularAutomataMaze maze;
