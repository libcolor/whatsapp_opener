

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


    public CellularAutomataMaze(){
        GOL = new boolean[Width][Height];
        //set up the frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        //set up the panel inside the frame
        panel = new JPanel();
        mat = new JPanel[GOL.length][GOL[0].length];
        panel.setLayout(new GridLayout(mat.length,mat[0].length,1,1));

        for(int i =0;i< mat.length;i++){
            for(int j =0;j< mat[i].length;j++){
                mat[i][j] = new JPanel();
                mat[i][j].setOpaque(true);

                //this is if you want to start with a random amount of alive
                if(MiddleCenter){
                    if((i>((Height/2)-centerRange))&&(i<((Height/2)+centerRange)) && (j>((Width/2)-centerRange)) &&(j<((Width/2)+centerRange))){
                        mat[i][j].setBackground(AliveColors[(int) (Math.random() * (AliveColors.length))]);
                    }
                    else{
                        mat[i][j].setBackground(Dead);
                    }
                }
                else {
                    if (StartRandom) {
                        if (Math.random() < ChanceOfAliveOnStart) {
                            mat[i][j].setBackground(AliveColors[(int) (Math.random() * (AliveColors.length))]);
                        } else {
                            mat[i][j].setBackground(Dead);
                        }
                    } else {
                        //this is if you want to start either all dead or all alive
                        if (startAlive) {
                            mat[i][j].setBackground(AliveColors[(int) (Math.random() * (AliveColors.length))]);
                        } else {
                            mat[i][j].setBackground(Dead);
                        }


                    }
                }
                panel.add(mat[i][j]);
            }

        }
        //paint2();
        frame.add(panel);
        //set up the start button
        Bar = new JMenuBar();
        Options = new JMenu("Options");
        start = new JMenuItem("Start");

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean begin = false;


                if(start.getText().toString().equals("Start")){

                    start.setText("END");


                    new Thread(new Runnable() {
                        public void run() {
                            while(start.getText().equals("END")) {

                                ConwayGame();



                            }
                        }
                    }).start();
                }
                else{
                    start.setText("Start");


                }
                // Runs outside of the Swing UI thread

            }
        });

        Options.add(start);
        Bar.add(Options);
        frame.setJMenuBar(Bar);

        frame.setSize(500,500);
        frame.setVisible(true);

        //set up the board for conways game




    }

    /**
     * tries calling the run funciton that implements the rules of conways game and alters the panels colors
     * based on the living or dead idea
     */
    public void ConwayGame() {
        int i = 0;
        while (i++ < 1) {
            runWithRules(new int[] {1,2,3,4},new int[] {3},1);
            try {
                java.lang.Thread.sleep((long)(waitTime*1000));
            } catch (InterruptedException e) {
                System.err.println("sleep for conways game");
            }
        }

    }

    /**
     *
     * @param mat is the matrix that holds all of the panels which are acting as the objects for the game of Life
     * @param p the point that is getting its neighbors checked
     * @return all of the neighbors that are alive of point p
     *
     * neighbors are adjacent panels including diagonals