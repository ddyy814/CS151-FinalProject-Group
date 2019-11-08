import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class SnakePlay extends JPanel implements KeyListener, ActionListener {

    private int[] snakeXlength = new int[750];
    private int[] snakeYlength = new int[750];
    private int lengthOfsanke = 3;


    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon headRight;
    private ImageIcon headLeft;
    private ImageIcon headUp;
    private ImageIcon headDown;

    private Timer timer;
    private int delay = 100;
    private int moves = 0;

    private int score = 0;

    private ImageIcon snakeBody;
    private int [] foodXpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,
                                425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,
                                800,825,850};
    private int [] foodYpos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,
                                425,450,475,500,525,550,575,600,625};

    private ImageIcon food;
    private Random random = new Random();

    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);

    public SnakePlay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        if (moves == 0) {
            snakeXlength[2] = 50;
            snakeXlength[1] = 75;
            snakeXlength[0] = 100;

            snakeYlength[2] = 100;
            snakeYlength[1] = 100;
            snakeYlength[0] = 100;
        }
        // draw title image border
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);

        // draw the title image
        Image title = Toolkit.getDefaultToolkit().getImage("title.jpg");
        g2.drawImage(title,25,11,this);


        // draw the scores
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("Scores:" + score, 780,30);

        // draw the length of snake
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("Length:" + lengthOfsanke, 780,50);

        // draw border for gameplay
        g.setColor(Color.WHITE);
        g.drawRect(24,74,851,577);

        // draw background for the gameplay
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(25,75,850,575);

       headRight = new ImageIcon("headRight.png");
       headRight.paintIcon(this,g,snakeXlength[0],snakeYlength[0]);

        for(int i = 0; i < lengthOfsanke; i++) {
            if (i == 0 && right) {
                headRight = new ImageIcon("headRight.png");
                headRight.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            if (i == 0 && left) {
                headLeft = new ImageIcon("headLeft.png");
                headLeft.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            if (i == 0 && up) {
                headUp = new ImageIcon("headUp.png");
                headUp.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            if (i == 0 && down) {
                headDown = new ImageIcon("headDown.png");
                headDown.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
                //   g.dispose();
            }
            if(i != 0){
                snakeBody = new ImageIcon("body.png");
                snakeBody.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
        }

        food = new ImageIcon("food.png");

        if((foodXpos[xpos] == snakeXlength[0]) && foodYpos[ypos] == snakeYlength[0]){
            score++;
            lengthOfsanke++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }
        food.paintIcon(this,g,foodXpos[xpos],foodYpos[ypos]);

        // game over check
        for(int m = 1; m < lengthOfsanke; m++){
            if(snakeXlength[m] == snakeXlength[0] && snakeYlength[m] == snakeYlength[0]){
                right = false;
                left = false;
                up = false;
                down = false;

                g.setColor(Color.BLUE);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Game Over",300,300);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Space to RESTART",350,340);

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right){
            for(int j = lengthOfsanke - 1; j >= 0; j--){
                snakeYlength[j + 1] = snakeYlength[j];
            }
            for (int j = lengthOfsanke; j >=0; j--){
                if(j == 0){
                    snakeXlength[j] = snakeXlength[j] + 25;
                }else{
                    snakeXlength[j] = snakeXlength[j - 1];
                }
                if(snakeXlength[j] > 850){
                    snakeXlength[j] = 25;
                }
            }
            repaint();
        }
        if(left){
            for(int j = lengthOfsanke - 1; j >= 0; j--){
                snakeYlength[j + 1] = snakeYlength[j];
            }
            for (int j = lengthOfsanke; j >=0; j--){
                if(j == 0){
                    snakeXlength[j] = snakeXlength[j] - 25;
                }else{
                    snakeXlength[j] = snakeXlength[j - 1];
                }
                if(snakeXlength[j] < 25){
                    snakeXlength[j] = 850;
                }
            }
            repaint();
        }
        if(up){
            for(int j = lengthOfsanke - 1; j >= 0; j--){
                snakeXlength[j + 1] = snakeXlength[j];
            }
            for (int j = lengthOfsanke; j >=0; j--){
                if(j == 0){
                    snakeYlength[j] = snakeYlength[j] - 25;
                }else{
                    snakeYlength[j] = snakeYlength[j - 1];
                }
                if(snakeYlength[j] < 75){
                    snakeYlength[j] = 625;
                }
            }
            repaint();
        }
        if(down){
            for(int j = lengthOfsanke - 1; j >= 0; j--){
                snakeXlength[j + 1] = snakeXlength[j];
            }
            for (int j = lengthOfsanke; j >=0; j--){
                if(j == 0){
                    snakeYlength[j] = snakeYlength[j] + 25;
                }else{
                    snakeYlength[j] = snakeYlength[j - 1];
                }
                if(snakeYlength[j] > 625){
                    snakeYlength[j] = 75;
                }
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            moves = 0;
            score = 0;
            lengthOfsanke = 3;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            moves++;
            right = true;
            if(!left){
                right = true;
            }else{
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            moves++;
            left = true;
            if(!right){
                left = true;
            }else{
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            moves++;
            up = true;
            if(!down){
                up = true;
            }else{
                up = false;
                down = true;
            }
            left = false;
            right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            moves++;
            down = true;
            if(!up){
                down = true;
            }else{
                down = false;
                up = true;
            }
            left = false;
            right = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
