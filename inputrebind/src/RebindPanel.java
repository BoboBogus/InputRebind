import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class RebindPanel extends JPanel{
    JButton buttonBind;
    JButton buttonReplace;

    KeyEvent Bind;
    KeyEvent Replace;

    boolean activated;

    RebindSet set;

    public RebindPanel(Rebinder rebind, int index) throws AWTException{
        set = new RebindSet();
        set.index = index;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setPreferredSize(new Dimension(settings.windowX, settings.windowY));
        setBackground(Color.DARK_GRAY);
        buttonBind = new JButton("BIND");
        buttonBind.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                buttonBind.setText("waiting for input");
                try {
                    rebind.listen(set, false, buttonBind);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                    }  
                });  
        
        add(buttonBind, c);
        
        c.gridx =1;
        c.gridy =0;
        buttonReplace = new JButton("BIND REPLACE");
                buttonReplace.addActionListener(new ActionListener(){  
                    public void actionPerformed(ActionEvent e){  
                                    buttonReplace.setText("waiting for input");
                                    try {
                                        rebind.listen(set, true, buttonReplace);
                                    } catch (InterruptedException e1) {
                                        e1.printStackTrace();
                                    }
                            }  
                        });  
                    
        add(buttonReplace, c);
        
        c.gridx =0;
        c.gridy =2;

        JCheckBox enabled = new JCheckBox("enable");
        enabled.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(enabled.isSelected()){
                    if (!connect(rebind)){ 
                        enabled.setText("BIND UNAVAILABLE");
                    }else{
                        enabled.setText("BIND COMPLETE");
                    }
                }
                else{
                    for(int i = 0; i <rebind.sets.size(); i++){
                        if(rebind.sets.get(i).index == index){
                            rebind.sets.remove(i);
                        }
                    }
                }
                
            }

        });
        add(enabled, c);
        setVisible(true);
    }

    boolean connect(Rebinder rebind){
        if(set.Bind < 0|| set.Replace < 0 ){
            return false;
        }
        rebind.sets.add(set);
        return true;
    }
}