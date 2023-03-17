package inputrebind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import inputrebind.settings;

public class AppFrame extends JFrame {

    JTabbedPane Tabbed;
    int tabIndex;
    public AppFrame() throws Exception {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(settings.windowX, settings.windowY));
        setBackground(Color.DARK_GRAY);
        Tabbed = new JTabbedPane();

        JPanel starter= new JPanel();
        starter.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        starter.setPreferredSize(new Dimension(settings.windowX, settings.windowY));
        starter.setBackground(Color.DARK_GRAY);
        JButton button = new JButton("+ Bind");
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                addRebind();
                    }  
                });  
        JButton button2 = new JButton("- Bind");
        button2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                removeBind();
                    }  
                });
        c.gridx =0;
        c.gridy =0;
        starter.add(button, c);
        c.gridx =1;
        c.gridy =0;
        starter.add(button2, c);
        starter.setVisible(true);

        Tabbed.addTab("-+-", starter);
        add(Tabbed, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void addRebind(){
        RebindPanel rebind = new RebindPanel();
        Tabbed.addTab("Rebind", rebind);
        tabIndex++;
    }
    public void removeBind(){
        if(tabIndex>0){
            Tabbed.remove(tabIndex);
            tabIndex--;
        }
    }
}
