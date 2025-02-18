import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AppleTree{

    Image seedling = new ImageIcon("src/AppleTree.png").getImage();
    Image teen = new ImageIcon("src/AppleTreeTeen.png").getImage();
    Image mature = new ImageIcon("src/AppleTreeMature.png").getImage();
    Image apple = new ImageIcon("src/Apple.png").getImage();
    Image appleSeeds = new ImageIcon("src/AppleSeeds.png").getImage();


    Image currentImage = seedling;
    Timer timer;
    int growthStage = 0;

    int seedsInHarvest, applesInHarvest, moneyForTree;

    AppleTree() {
        //1 minute timer
       timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    grow();

                    if(growthStage == 3)
                        timer.stop();

            }

        });
       timer.start();


    }

    void grow(){
        growthStage++;
        if(growthStage == 1)
            currentImage = seedling;
        if(growthStage == 2)
            currentImage = teen;
        if(growthStage == 3) {
            currentImage = mature;
            setHarvestAttributes();
        }
    }

    void setHarvestAttributes(){
        Random rand = new Random();
        seedsInHarvest = rand.nextInt(1,4);
        applesInHarvest = rand.nextInt(2,4);

        moneyForTree = applesInHarvest * 10;

    }

}
