import javax.swing.*;
import java.awt.*;

public class Window{
    String Title;
    RenderPanel panel;

    Window(String Title){
        this.Title = Title;
        panel = new RenderPanel();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame(Title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize);
        frame.setLocationRelativeTo(null);

        frame.add(panel);
        frame.setVisible(true);



    }
}
