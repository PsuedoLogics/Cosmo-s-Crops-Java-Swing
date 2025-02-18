import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Input implements KeyListener{
    public boolean isClicked = false;
    public String currentAction = "";

    RenderPanel renderPanel;

    public int mouseX, mouseY;


    MouseListener mouseListener = new MouseListener(){
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            isClicked = true;

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            isClicked = false;

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_E:
                if(currentAction.isEmpty()){
                    currentAction = "FARMING";
                    break;
                }
                if(currentAction.equals("FARMING")){
                    currentAction = "PLANTING";
                    break;
                }
                if(currentAction.equals("PLANTING")){
                    currentAction = "BUILDING";
                    break;
                }
                if(currentAction.equals("BUILDING")){
                    currentAction = "HARVEST";
                    break;
                }
                if(currentAction.equals("HARVEST")){
                    currentAction = "FARMING";
                    break;
                }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    Input(RenderPanel renderPanel){
        this.renderPanel = renderPanel;
    }




}

