import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.awt.*;
import java.io.IOException;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;

import java.awt.event.*;

public class RebindPanel extends JPanel implements NativeKeyListener, NativeMouseInputListener{

    boolean listenBind;
    boolean listenReplace;
    int Bind;
    int Replace;

    JButton buttonBind;
    JButton buttonReplace;

    boolean enable;
    Robot robot;
    int isKey;

    public RebindPanel() throws AWTException{
        robot = new Robot();
        
        
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        GlobalScreen.addNativeKeyListener(this);
        GlobalScreen.addNativeMouseListener(this);
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
        enabled.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                enable = enabled.isSelected();
            }

        });
        add(enabled, BorderLayout.CENTER);
        setVisible(true);


    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        if(listenBind){
            Bind = e.getKeyCode();
            buttonBind.setText(NativeKeyEvent.getKeyText(Bind));
            listenBind = false;

        }
        if(listenReplace){
            isKey = 1;
            Replace = (e.getKeyCode());
            buttonReplace.setText(String.valueOf(Replace));
            listenReplace = false;
        }
        if (enable){
            if(e.getKeyCode() == Bind){
                if(isKey == 1){
                    robot.keyPress(Replace);
                }
                else if (isKey == 2){
                    robot.mousePress(Replace);
                }
            }
        }
            System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
            
        
        
    }

	public void nativeMousePressed(NativeMouseEvent e) {
        if(listenBind){
            Bind =  e.getButton();
            buttonBind.setText("Mouse "+e.getButton());
            listenBind = false;

        }
        if(listenReplace){
            isKey = 2;
            Replace = InputEvent.getMaskForButton(e.getButton());
            buttonReplace.setText("Mouse "+e.getButton());
            listenReplace = false;
        }

        if (enable){
            if(e.getButton() == Bind){
                System.out.println("isKey+ "+ isKey);
                if(isKey == 1){
                    robot.keyPress(Replace);
                }
                else if (isKey == 2){
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                }
            }
        }
        
                    System.out.println("Mouse"+e.getButton());
	}

	public void nativeMouseReleased(NativeMouseEvent e) {
        if (enable){
            if(e.getButton() == Bind){
                if(isKey == 1){
                    robot.keyPress(Replace);
                    }
                    else if (isKey == 2){
                        robot.mouseRelease(Replace);
                    }
            }
        }
	}

    public void nativeKeyRelease(NativeKeyEvent e){
        if (enable){
            if(e.getKeyCode() == Bind){
                if(isKey == 1){
                    robot.keyRelease(Replace);
                    }
                    else if (isKey == 2){
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    }
            }
        }
    }

}