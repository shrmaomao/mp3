package com.mao.mp3.service.event;

import com.mao.mp3.ui.Windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MouseListener implements ActionListener {
    private Windows windows;

    public   MouseListener (Windows windows){
        this.windows = windows;
    }
    public  void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("openFile")){
            windows.clickOpenFile();
        }
        if(e.getActionCommand().equals("play")){
            windows.clickPlay();
        }
    }
}
