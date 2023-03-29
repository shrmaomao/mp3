package com.mao.mp3.ui;

import com.mao.mp3.service.PlayService;
import com.mao.mp3.service.event.MouseListener;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Windows extends JFrame {
    //下一首按钮
    private  JButton next1 = new JButton("下一首");
    //上一首按钮
    private  JButton previous= new JButton("上一首");
    //暂停按钮
    private  JButton stop =  new JButton("暂停");
    private String mp3path;
    private ActionListener ml;
    //单首歌文本框
    private JTextField textField;
    //列表，多首歌，待用
    //private  JList lsit;
    //打开按钮
    private  JButton openFile = new JButton("打开");
    //播放按钮
    private JButton play = new JButton("播放");
    public Windows(){
        init();
        //监听
        eventHandle();
    }
        //监听器
    private void eventHandle(){
        ml = new MouseListener(this);
        //下达一个命令叫openfile打开文件
        openFile.setActionCommand("openFile");
        //鼠标事件
        openFile.addActionListener(ml);
        
        //下达一个命令叫play,进行播放
        play.setActionCommand("play");
        play.addActionListener(ml);

    }
    private void init(){
        this.setSize(400,250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setLayout(null);

        textField =new JTextField();
        textField.setBounds(20,10,260,30);
        this.add(textField);

        openFile.setBounds(290,10,60,30);
        this.add(openFile);

        play.setBounds(20,50,60,30);
        this.add(play);

        stop.setBounds(150,50,60,30);
        this.add(stop);

        next1.setBounds(20,100,70,30);
        this.add(next1);

        previous.setBounds(150,100,70,30);
        this.add(previous);

    }
    public  void run(){
        this.setVisible(true);
    }

    public void clickOpenFile() {
        FileDialog fd=new FileDialog(this);
        fd.setVisible(true);
        mp3path = fd.getDirectory()+fd.getFile();
        System.out.println(mp3path);

        this.textField.setText(mp3path);
    }

    public void clickPlay() {
        PlayService ps= new PlayService();
        try {
            ps.play(mp3path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
