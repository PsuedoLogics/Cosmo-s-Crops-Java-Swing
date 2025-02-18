import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class RenderPanel extends JPanel implements ActionListener {

    Image tilledLand = new ImageIcon("src/tilled.png").getImage();
    Image fence = new ImageIcon("src/fence.png").getImage();
    Image backgroundImage = new ImageIcon("src/Background.png").getImage();

    ArrayList<Point> tilledLandPositions = new ArrayList<>();
    ArrayList<Point> appleTreePositions = new ArrayList<>();
    ArrayList<AppleTree> appleTreesList = new ArrayList<>();
    ArrayList<Point> fencePositions = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();

    Image tempBackgroundImage;
    ImageIcon scaledBackgroundImageIcon;

    Input input = new Input(this);

    int gridSize = 64;

    int snappedX, snappedY;

    int appleSeeds = 1, money = 1000, apples;

    Enemy enemy;

    RenderPanel(){
        this.setLayout(null);
        this.setBackground(Color.ORANGE);
        this.setFocusable(true);
        addKeyListener(input);
        addMouseListener(input.mouseListener);
        Timer timer = new Timer(5, this);
        timer.start();
    }


    public void paint(Graphics g){

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //BackgroundImage scaling and drawing to screen, IF statement to check if size of screen changed / otherwise do not resize
       if(scaledBackgroundImageIcon == null || scaledBackgroundImageIcon.getIconWidth() != this.getWidth() || scaledBackgroundImageIcon.getIconHeight() != this.getHeight()) {
            tempBackgroundImage = backgroundImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            scaledBackgroundImageIcon = new ImageIcon(tempBackgroundImage);
        }
        g2d.drawImage(scaledBackgroundImageIcon.getImage(), 0, 0, null);

        for(Point point : tilledLandPositions){
            g2d.drawImage(tilledLand, point.x, point.y, null);
        }

        for(Point point : fencePositions){
            g2d.drawImage(fence, point.x, point.y, null);
        }

        for(int i = 0; i < appleTreePositions.size(); i++)
        {
            Point treePosition = appleTreePositions.get(i);
            Image treeImage = appleTreesList.get(i).currentImage;

            g2d.drawString(appleTreesList.get(i).growthStage + "", treePosition.x, treePosition.y);

            g2d.drawImage(treeImage, treePosition.x, treePosition.y, null);
        }

        for(Enemy e: enemies)
            g2d.drawImage(e.image, e.spawnPoint.x, e.spawnPoint.y, null);

        g2d.drawString("CURRENT ACTION:" + input.currentAction + " ('E' to switch)" , 10,20);
        g2d.drawString("APPLE SEEDS: " + appleSeeds, 10,40);
        g2d.drawString("MONEY: $" + money + " ($10 to till | $5 per fence)", 10,60);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.equals(input.currentAction, "FARMING") && input.isClicked && money >= 10) {

            GetGrid();

            Point newTilledLandPosition = new Point(snappedX, snappedY);

            if(!tilledLandPositions.contains(newTilledLandPosition) && !fencePositions.contains(newTilledLandPosition)) {
                tilledLandPositions.add(newTilledLandPosition);
                money -= 10;
            }
        }

        if(Objects.equals(input.currentAction, "PLANTING") && input.isClicked  && appleSeeds > 0) {

            GetGrid();

            Point newAppleTreePosition = new Point(snappedX, snappedY);

            AppleTree appleTree = new AppleTree();

            //if there is no apple tree there AND if there is a piece of tilled land
            if(!appleTreePositions.contains(newAppleTreePosition) && tilledLandPositions.contains(newAppleTreePosition)) {
                appleTreePositions.add(newAppleTreePosition);
                appleTreesList.add(appleTree);
                appleSeeds--;
            }

        }

        if(Objects.equals(input.currentAction, "BUILDING") && input.isClicked && money >= 5) {

            GetGrid();

            Point newFencePosition = new Point(snappedX, snappedY);

            if(!fencePositions.contains(newFencePosition) && !tilledLandPositions.contains(newFencePosition)) {
                fencePositions.add(newFencePosition);
                money -= 5;
            }
        }

        if(Objects.equals(input.currentAction, "HARVEST") && input.isClicked) {

            GetGrid();

            Point harvestPoint = new Point(snappedX, snappedY);

            if(appleTreePositions.contains(harvestPoint)){
                int index = appleTreePositions.indexOf(harvestPoint);
                if(appleTreesList.get(index).growthStage == 3){

                    AppleTree currentTree = appleTreesList.get(index);

                    for(int i = 0; i < currentTree.seedsInHarvest; i++)
                        appleSeeds++;

                    money += currentTree.moneyForTree;

                    appleTreesList.remove(index);
                    appleTreePositions.remove(index);

                }
            }

            if(!appleTreePositions.isEmpty() && enemies.isEmpty()){
                enemy = new Enemy(this);
                enemies.add(enemy);
                System.out.println("ADDING ENEMY");

            }


        }

        repaint();
    }

    void GetGrid(){
        snappedX = (input.mouseX / gridSize) * gridSize;
        snappedY = (input.mouseY / gridSize) * gridSize;
    }


}
