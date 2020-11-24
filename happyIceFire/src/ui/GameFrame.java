package ui;

import javax.swing.*;
import java.applet.AudioClip;

/**
 *  游戏的窗体
 *  Java中的窗体类 : JFrame
 *  自定义窗体步骤 :
 *  1. 写一个类， 继承 JFrame
 *  2. 写一个构造方法，初始化窗体的属性
 */
public class GameFrame extends JFrame {

    public boolean gameover;

    public static GameFrame instance = new GameFrame();

    public static GameFrame getInstance() {
        return instance;
    }


    private GameFrame() {


        //设置标题 方法来源于JFrame类
        setTitle("工具人大战小怪兽");
        //设置大小 setSize()
        setSize(1024, 768);
        //设置位置居中
        setLocationRelativeTo(null);
        //设置不允许玩家改变界面大小
        setResizable(false);
        //设置默认的关闭选项
        //关闭窗体的时候退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void closeGameFrame() {
        dispose();
        //System.out.println("游戏窗体已关闭");


    }
}
