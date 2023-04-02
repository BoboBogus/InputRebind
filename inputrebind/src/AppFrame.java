import javax.swing.*;

import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class AppFrame extends JFrame implements NativeKeyListener, NativeMouseInputListener{

    JTabbedPane Tabbed;
    int tabIndex;

    Rebinder rebinder;
    List<RebindPanel> panels = new ArrayList<RebindPanel>();

    public AppFrame() throws Exception {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(settings.windowX, settings.windowY));
        setBackground(Color.DARK_GRAY);
        Tabbed = new JTabbedPane();
        createStarter();
        rebinder = new Rebinder();
        setVisible(true);
    }
    public void addRebind() throws AWTException{
        RebindPanel rebind = new RebindPanel(rebinder, tabIndex);
        panels.add(rebind);
        Tabbed.addTab("Rebind", rebind);
        tabIndex++;
    }
    public void removeBind(){
            rebinder.sets.clear();
            Tabbed.removeAll();
            panels.clear();
            createStarter();
            tabIndex = 0;
    }
    public void createStarter(){
        JPanel starter= new JPanel();
                starter.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                starter.setPreferredSize(new Dimension(settings.windowX, settings.windowY));
                starter.setBackground(Color.DARK_GRAY);
                JButton button = new JButton("+ Bind");
                button.addActionListener(new ActionListener(){  
                    public void actionPerformed(ActionEvent e){  
                        try {
                            addRebind();
                        } catch (AWTException e1) {
                            e1.printStackTrace();
                        }
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
    }

    
}
