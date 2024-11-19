package Th;

import javax.sound.sampled.*;
public class Music {
    private Clip clip;

    public void playMusic(String filePath) {
        try {
            // 创建音频输入流
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new java.io.File(filePath));
            // 获取音频格式
            AudioFormat audioFormat = audioInputStream.getFormat();
            // 创建数据行信息对象
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
            // 获取数据行
            clip = (Clip) AudioSystem.getLine(info);
            // 打开数据行并开始播放音频流
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
    public void resumeMusic() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }

}
}