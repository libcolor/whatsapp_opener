

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class CellularAutomataMaze {
    JFrame frame;
    JPanel panel;
    JMenuBar Bar;
    JMenu Options;
    JMenuItem start;
    boolean[][] GOL;
    JPanel[][] mat;
    //Color[] AliveColors = {Color.BLACK,Color.PINK,Color.BLUE,Color.RED,Color.cyan};
    Color[] AliveColors = {Color.BLACK};
    Color Alive = Color.BLACK;
    Color Boundry = Color.BLACK;
    Color Dead = Color.WHITE;
    int Width = 50;
    int Height = 50;
    int centerRange = 7;
    boolean startAlive= false;
    boolean StartRandom= false;
    boolean MiddleCenter = true;
    boolean reboot = false;//restarts the maze when amount of changes is passes a point
    double waitTime = .25;
    double ChanceOfAliveOnStart= 0.35;
