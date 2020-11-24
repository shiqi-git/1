package ui;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 *  处理图片的工具类
 *  工具类中的方法，一般需要加static, 目的是：特点公用的，所有对象都共用这一份。
 *
 */
public class App
{
    /**
     * BufferedImage Java中用来表示图片的类
     * 读取指定位置上的图片
     * @param path
     * @return
     */
    public static BufferedImage getImg(String path)
    {
        //加载图片
        //Java 中的IO流 ， 输送数据的管道
        //App.class 找到App类的路径
        //getResource() 获取资源
        try {
            BufferedImage img = ImageIO.read(App.class.getResource(path));
            //如果找到图片，就将图片返回
            return img;
        } catch (IOException e) {
            //catch 如果找不到图片， 就会捕获找不到图片的原因
            e.printStackTrace();
        }
        return null;
    }

    public static AudioClip getSound(String path)
    {
        File file = new File(path);
        URI uri = file.toURI();

        try {
            URL url = uri.toURL();
            AudioClip audioClip = Applet.newAudioClip(url);
            return audioClip;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
