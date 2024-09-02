import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlyCheems extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 360;
    int boardHeight = 670;

        //IMAGES

        Image backgroundImg;
        Image cheemImg;
        Image topPipeImg;
        Image bottomPipeImg;

        //Cheems
            int cheemX = boardWidth/8;
            int cheemY = boardHeight/2;
            int cheemWidth = 38 ;
            int cheemHeight = 43;


    class Cheems{
                int x = cheemX;
                int y = cheemY;
                int width = cheemWidth;
                int height = cheemHeight;
                Image img;

                Cheems(Image img){
                    this.img = img;
                }
            }

            //Pipes
            int pipeX = boardWidth;
            int pipeY = 0;
            int pipeWidth = 45;
            int pipeHeight = 410;

            class Pipe{
                int x = pipeX;
                int y = pipeY;
                int width = pipeWidth;
                int height = pipeHeight;
                Image img;
                boolean passed = false;

                Pipe(Image img){
                    this.img = img;
                }
            }


            //game logic
            Cheems cheems;
            int velocityX = -4;
            int velocityY = 0;
            int gravity = 1;

            ArrayList<Pipe> pipes;
            Random random = new Random();

            Timer gameLoop;
            Timer placePipesTimer;
            boolean gameOver = false;
            double score = 0;


         FlyCheems(){
            setPreferredSize(new Dimension(boardWidth,boardHeight));
            //setBackground(Color.blue);
             setFocusable(true);
             addKeyListener(this);

            //LOAD IMAGES
            backgroundImg =new ImageIcon(getClass().getResource("./spa.png")).getImage();
            cheemImg = new ImageIcon(getClass().getResource("./doge.png")).getImage();
            topPipeImg = new ImageIcon(getClass().getResource("./top.png")).getImage();
            bottomPipeImg = new ImageIcon(getClass().getResource("./bot.png")).getImage();

            //cheems
            cheems = new Cheems(cheemImg);
            pipes = new ArrayList<Pipe>();

            //place pipes timer
             placePipesTimer = new Timer(1500, new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     placePipes();
                 }
             });

             placePipesTimer.start();

            //game timer
            gameLoop = new Timer(200/6,this);
            gameLoop.start();
        }


        public void placePipes() {

             int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));
             int openingSpace = boardHeight/4;

             Pipe topPipe = new Pipe(topPipeImg);
             topPipe.y = randomPipeY;
             pipes.add(topPipe);

             Pipe bottomPipe = new Pipe(bottomPipeImg);
             bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
            pipes.add(bottomPipe);
    }


        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            draw(g);
        }

        public void draw(Graphics g) {
            //background
            g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);

            //bird
            g.drawImage(cheems.img, cheems.x, cheems.y, cheemWidth, cheemHeight, null);

        //pipes
            for (int i=0; i< pipes.size();i++){
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.img, pipe.x,pipe.y,pipe.width,pipe.height,null);
            }

        //score
            g.setColor(Color.white);
            g.setFont(new Font("MARCH",Font.PLAIN, 32));
            if(gameOver){
                g.drawString("Game Over:" + String.valueOf((int) score), 10, 35);
            }
            else {
                g.drawString(String.valueOf((int) score), 10, 35);
            }


        }


        public void move(){

            velocityY += gravity;
             cheems.y += velocityY;
             cheems.y = Math.max(cheems.y,0);

             //pipes
            for (int i = 0;i < pipes.size();i++){
                Pipe pipe = pipes.get(i);
                pipe.x += velocityX;

                if (!pipe.passed && cheems.x > pipe.x + pipe.width){
                    pipe.passed = true;
                    score += 0.5;
                }

                if (collision(cheems, pipe)){
                    gameOver = true;
                }
            }

            if(cheems.y > boardHeight){
                gameOver = true;
            }
        }

        public boolean collision(Cheems a, Pipe b){
             return a.x < b.x + b.width &&
                    a.x + a.width > b.x &&
                    a.y < b.y + b.height &&
                    a.y + a.height > b.y;
        }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        move();
        repaint();
        if (gameOver) {
            placePipesTimer.stop();
            gameLoop.stop();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_SPACE){
            velocityY = -9;
            if(gameOver){

                cheems.y = cheemY;
                velocityY = 0;
                pipes.clear();
                score = 0;
                gameOver = false;
                gameLoop.start();
                placePipesTimer.start();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    }

