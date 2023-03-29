package com.mao.mp3.service;

import javax.sound.sampled.*;
import java.io.*;

public class PlayService {
    public  void play(String path) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        //获取音乐对象

        File file=new File(path);
        //得到音频格式
        //AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);

        //把file文件转化为输入流
        InputStream is =new FileInputStream(file);
        //获取音频输入流
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);
        //获取音频文件格式-mp3格式
        AudioFormat format = audioInputStream.getFormat();
        //把mp3格式换成PCM-格式，无损格式
        AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                format.getSampleRate(),16,format.getChannels(),
                format.getChannels() * 2,format.getSampleRate(),false);
        //以新的编码方式作为输入流
        AudioInputStream decodedAudioStream = AudioSystem.getAudioInputStream(decodedFormat,audioInputStream);
        //通过decodedFormat得到数据信息
        DataLine.Info info = new DataLine.Info(SourceDataLine.class,decodedFormat);
        //通过音频信息得到Line，得到源数据行
        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
        //打开声卡，把cd放到机器里面，从而可以播放
        line.open(decodedFormat);
        //开始播放
        line.start();
        //一个一个字节读取数据，然后写到line中
        byte [] AUDIO_BUFER = new byte[100];
        int readLenth = 0;
        while (true) {
            readLenth =decodedAudioStream.read(AUDIO_BUFER,0,AUDIO_BUFER.length);
            if (readLenth < 0){
                break;
            }
            line.write(AUDIO_BUFER,0,readLenth);
        }

        //清空缓存区
        line.drain();
        line.stop();
        line.close();
    }
}
