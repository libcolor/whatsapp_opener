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
        maze.repaintSingle(Start.x,Start.y,Color.pink);

        Point End = getEnd(maze.mat);
        maze.repaintSingle(End.x,End.y,Color.GREEN);
        mat = maze.mat;
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){}
        int count = 0;
        int fullCount=0;


        boolean flag = false;

        while(!flag){
            walls.clear();
            solved = false;
            for(Point v: getWalls(Start)){
                walls.add(v);
            }
            int res = venture(Start,false,Color.BLACK);
            if(res==1){
                flag = true;
            }

            while (!solved) {
                flag=false;
                fullCount++;

                if (res == 0) {
                    if (!walls.isEmpty()) {

                        Point val = walls.pop();
                        maze.repaintSingle(val.x, val.y, Color.RED);
                        res = venture(val, true, Color.BLACK);

                        if (res == 0) {
                            count++;
                            flag = true;
                            maze.repaintSingle(val.x, val.y, Color.GRAY);
                        }

                    }

                }


            }


            ResetBesidesRED();
        }
        ResetBesidesRED();

        maze.repaintSingle(Start.x,Start.y,Color.pink);
        maze.repaintSingle(End.x,End.y,Color.cyan);
    }
    public static void main(String[] args){
        new CellularAutomataSolver();


    }

    public Point getStart(JPanel[][] mat){
        boolean flag = true;
        while(flag){
            int x = (int)(Math.random()*mat.length);
            int y = (int)(Math.random()*mat[0].length);
            if(mat[x][y].getBackground()==Color.WHITE){
                return new Point(x,y);
            }
        }
        return new Point(0,0);
    }

    public Point getEnd(JPanel[][] mat){
        boolean flag = true;
        while(flag){
            int x = (int)(Math.random()*mat.length);
            int y = (int)(Math.random()*mat[0].length);
            if(mat[x][y].getBackground()==Color.WHITE){
                return new Point(x,y);
            }
        }
        return new Point(0,0);
    }

    public Point[] getAdjacent(Point p) {
        Stack<Point> pos = new Stack<>();
        try {
            if (mat[p.x - 1][p.y].getBackground() == Color.white||mat[p.x - 1][p.y].getBackground() == Color.GREEN) {
                pos.push(new Point(p.x - 1, p.y));
            }
        } catch (Exception e) {
        }
        try {
            if (mat[p.x][p.y + 1].getBackground() == Color.white||mat[p.x][p.y+1].getBackground() == Color.GREEN) {
                pos.push(new Point(p.x, p.y + 1));
            }
        } catch (Exception e) {
        }
        try {
            if (mat[p.x + 1][p.y].getBackground() == Co