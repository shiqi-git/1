package ui;

import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WelcomeFrame extends JFrame implements MouseListener {
    //背景图片
    JLabel backgroung;
    AudioClip bgmusic;


    public WelcomeFrame() {


        bgmusic = App.getSound("src/sound/");
        bgmusic.play();

        backgroung = new JLabel(new ImageIcon("src/img/startbg.png"));
        this.add(backgroung, BorderLayout.NORTH);//添加背景



        //设置标题 方法来源于JFrame类
        setTitle("工具人大战小怪兽");
        //设置大小 setSize()
        setSize(1024, 768);
        //设置位置居中
        setLocationRelativeTo(null);

        setVisible(true);
        //设置不允许玩家改变界面大小
        setResizable(false);
        //设置默认的关闭选项
        //关闭窗体的时候退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        backgroung.addMouseListener(this);

    }

    public static void main(String[] args) {

        new WelcomeFrame();
    }


    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getX()>=440 && e.getX()<=630 && e.getY()>=550 && e.getY()<=630) {
           JOptionPane.showMessageDialog(null, "登录成功");
            //关闭当前界面
            dispose();
            //跳转到下一界面

            //创建窗体对象 ： 调用窗体的构造方法，制作窗体
            //GameFrame frame = new GameFrame();
            GameFrame frame = GameFrame.getInstance();

            //创建面板对象 ： 调用面板的构造方法，制作面板
            GamePanel panel = new GamePanel(frame);

            //调用启动游戏的方法
            panel.action();


            //将面板加入窗体中
            frame.add(panel);

            //显示窗体
            frame.setVisible(true);


            dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}