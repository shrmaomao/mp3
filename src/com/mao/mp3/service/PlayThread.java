package com.mao.mp3.service;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class PlayThread extends Thread{
    private PlayService ps;

    public PlayThread(PlayService ps) {
        this.ps=ps;
    }


    @Override
    public void run() {
        try {
            ps.play();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
