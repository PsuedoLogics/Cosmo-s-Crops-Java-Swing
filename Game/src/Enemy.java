import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Enemy {
    public Image image = new ImageIcon("src/Enemy.png").getImage();
    Point spawnPoint = new Point();
    RenderPanel rp;
    Random rand = new Random();
    Point target;
    int temp;

    int speed = 2;

    Timer timer;

    Enemy(RenderPanel rp){
        this.rp = rp;
        spawnPoint.x = 10;
        spawnPoint.y = rand.nextInt(0,rp.getHeight());

        temp = rp.getWidth();

        for(Point p: rp.appleTreePositions){
            int horizontalDistance = p.x - spawnPoint.x ;

            if(horizontalDistance < temp){
                target = p;
                System.out.println("target: " + target);

            }

        }

            timer = new Timer(5, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(spawnPoint.x != target.x && spawnPoint.y != target.y) {
                    move();
                }
                else
                    timer.stop();
            }
        });
        timer.start();

    }

    void move(){


            double dx = target.x - spawnPoint.x;
            double dy = target.y - spawnPoint.y;
            double dist = Math.sqrt(dx * dx + dy * dy);
            if (dist > 0) {
                dx /= dist;
                dy /= dist;
            }

            spawnPoint.x += dx * speed;
            spawnPoint.y += dy * speed;


    }



}
