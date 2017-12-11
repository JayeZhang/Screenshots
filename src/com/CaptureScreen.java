package com;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;

public class CaptureScreen {


    public static void captureScreen(String fileName, String folder) throws Exception {

        Robot robot = new Robot();
        //获取屏幕分辨率
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //以屏幕的尺寸创建个矩形
        Rectangle screenRectangle = new Rectangle(screenSize);
        //截图（截取整个屏幕图片）
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        //保存路径
        File screenFile = new File(fileName);
        if (!screenFile.exists()) {
            screenFile.mkdir();
        }
        File f = new File(screenFile, folder);
        ImageIO.write(image, "png", f);

    }

    public static void main(String[] args) {
        try {

            int time = 10;
            Random random = new Random();
            while(true){
                for(int i = 0; i < time; i++){
                    Thread.sleep(1000);// 睡眠1秒。
                    if(i + 1 >= time){
                        time=random.nextInt(3000)+1500;
                        String itme1 = DateUtil.getCurrentTime();
                        captureScreen("logo",itme1+".png");
                        break;
                    }
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}