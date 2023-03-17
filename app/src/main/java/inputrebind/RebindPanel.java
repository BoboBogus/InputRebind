package inputrebind;

import javax.swing.*;
import java.awt.*;

public class RebindPanel extends JPanel{
    public RebindPanel(){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(settings.windowX, settings.windowY));
        setBackground(Color.DARK_GRAY);
        JButton button = new JButton();
        
        add(button, BorderLayout.CENTER);
        setVisible(true);
    }
}

