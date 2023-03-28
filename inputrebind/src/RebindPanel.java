import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class RebindPanel extends JPanel implements NativeKeyListener{

    boolean listenBind;
    boolean listenReplace;
    String Bind;
    String Replace;

    JButton buttonBind;
    JButton buttonReplace;

    public RebindPanel(){
        
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        GlobalScreen.addNativeKeyListener(this);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(settings.windowX, settings.windowY));
        setBackground(Color.DARK_GRAY);
        buttonBind = new JButton("BIND");
        buttonBind.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                listenBind = true;
                buttonBind.setText("waiting for input");
                    }  
                });  
        
        add(buttonBind, BorderLayout.LINE_START);

        buttonReplace = new JButton("BIND REPLACE");
                buttonReplace.addActionListener(new ActionListener(){  
                    public void actionPerformed(ActionEvent e){  
                        listenReplace = true;
                        buttonReplace.setText("waiting for input");
                            }  
                        });  
                    
        add(buttonReplace, BorderLayout.LINE_END);

        JCheckBox enabled = new JCheckBox("enable");
        add(enabled, BorderLayout.CENTER);
        setVisible(true);


    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        if(listenBind){
            Bind = NativeKeyEvent.getKeyText(e.getKeyCode());
            buttonBind.setText(Bind);
            listenBind = false;
        }
        if(listenReplace){
            Replace = NativeKeyEvent.getKeyText(e.getKeyCode());
            buttonReplace.setText(Replace);
            listenReplace = false;
        }
        System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
        
    }
}