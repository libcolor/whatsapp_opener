

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
