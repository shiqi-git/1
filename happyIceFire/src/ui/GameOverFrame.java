package ui;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static ui.RankFrame.readFromFile;

public class GameOverFrame extends JFrame implements MouseListener {

	JLabel again;
    JLabel ranking;
    AudioClip overmusic;
    BufferedImage bg;
    //public int score;



    
    public GameOverFrame( int score)
    {
    	// this.score = score;
    	again=new JLabel(new ImageIcon("src/img/again.png"));
        again.setBounds(290,580,150,50);
        //again.setEnabled(false);
        again.setOpaque(true);
        this.add(again);
        again.addMouseListener(this);//参数是GameOverFrame的对象，this表示当前对象

        ranking = new JLabel(new ImageIcon("src/img/history.png"));
        ranking.setBounds(490,580,150,50);
        //ranking.setEnabled(false);
        ranking.setOpaque(true);
        this.add(ranking);
        ranking.addMouseListener(this);
        
        Menuback menuback=new Menuback(score);
        this.add(menuback);  //添加面板到窗体

        
        this.setSize(1024,768);  //设置窗体大小
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);  //设置默认关闭
        this.setLocationRelativeTo(null);   //设置居中
       // this.setIconImage(new ImageIcon("src/imge/gameover03.png").getImage()); //设置图片
        this.setUndecorated(false);//设置窗体菜单栏不可见
        this.setVisible(true);//显示窗体
        
        overmusic = App.getSound("src/sound/score.wav");
        overmusic.play();
    }
  
    
    public static void main(String[] args)
    {
            new GameOverFrame(8);
    }
    		

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标点击事件
		if(e.getSource().equals(again))
		{
            dispose();
           // new GameFrame();

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
		}

		else if(e.getSource().equals(ranking))
		{
            try {
                new RankFrame().addComponents();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            //dispose();



        }

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标松开事件

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标移入事件

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标移出事件

	}

}

class Menuback extends JPanel{
    Image bg;
 //   JLabel start04;
    int score  ;
    public Menuback(int score){
        this.score = score;
    	bg = App.getImg("/img/overbg.png");
    }


    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(bg,0,0,1024,768,null);

        g.setColor(Color.BLACK);
        g.setFont(new Font("幼圆", Font.BOLD, 60));
        g.drawString("最终分数" , 350,240);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("幼圆", Font.BOLD, 100));
        g.drawString(" " + score , 350,400);

    }
}

