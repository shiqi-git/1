package ui;



import javax.swing.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.List;
import java.util.Timer;


/**
 *  Java中的游戏面板 : JPanel
 *  自定义游戏面包
 *  写一个类，继承JPanel
 *  写一个构造方法，初始化面板属性
 *
 *  画图片的步骤
 *  1.在类中定义图片，并取名
 *  2.在构造方法中，调用工具初始化图片
 *  3.在画图方法，paint中，画图片
 */
public class GamePanel extends JPanel
{
    //1.定义背景图片
    BufferedImage bg, overp,startp;

    AudioClip apdie, aover, amdie,bgmusic;



    //创建玩家角色对象
    Player player = new Player();

    //创建小怪兽对象
//    Monster monster = new Monster();
    List<Monster> monsters = new ArrayList<Monster>();

    //创建攻击类
    List<Assault> as = new ArrayList<Assault>();

    //定义分数
    int score;

    //设置游戏的开关
    boolean gameover ; // 游戏开始时，gameover 为false

    public void savescore(int score,String filePath){
        int i=1;
        //声明输出流对象
        BufferedWriter bw = null;

        try {
            //建立输出流和目标文件之间的联系
            bw = new BufferedWriter(new FileWriter(filePath,true));
            //bw.write("得分");
            //bw.newLine();
            //写出字符串 ，格式：“101 \t  jack \t  34 ”
            bw.write(""+score);
            //每写一行换行
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            //关闭流（释放资源）
            if(null!=bw){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void action()
    {

        int count = 0;
        //创建并启动一个线程，控制游戏场景中活体运动
     
        
        new Thread()
        {
            public void run()
            {
            	//  写一个死循环，让游戏一直运行
            	int time = 25;

                
                while (true)
                {
                    if (!gameover)
                    {
                        player.step();
                        //调用怪兽出现的方法
                        monsterEnter();

                        //调用小怪兽移动的方法
                        monsterMove();

                        //发动攻击
                        launchAssault();

                        //攻击移动
                        assaultMove();

                        //判断攻击是否击中小怪兽
                        assaultMt();

                        //判断小怪兽是否撞到玩家角色
                        hit();
                    }
                    	
                    if(score<=50){
                    	//每执行一次，休眠
                        //线程休眠，sleep(毫秒数)
                    	time=25;
                        try 
                        {
                            Thread.sleep(time);
                        } 
                        catch (InterruptedException e) 
                        {
                            e.printStackTrace();
                        }
                    }else if(score>50 && score<=100){
                    	time=20;
                    	//每执行一次，休眠
                        //线程休眠，sleep(毫秒数)
                        try 
                        {
                            Thread.sleep(time);
                        } 
                        catch (InterruptedException e) 
                        {
                            e.printStackTrace();
                        }
                    }else if(score>100 && score<=300){
                        time=15;
                        //每执行一次，休眠
                        //线程休眠，sleep(毫秒数)
                        try
                        {
                            Thread.sleep(time);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }else if(score>300 && score<=500){
                        time=10;
                        //每执行一次，休眠
                        //线程休眠，sleep(毫秒数)
                        try
                        {
                            Thread.sleep(time);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    //重绘，刷新
                    repaint();
                    
                   // System.out.println("****time"+time);
                    if(gameover){
                        GameFrame.getInstance().closeGameFrame();
                       // System.out.println("游戏界面窗体关闭------");
                    	//System.out.println("****终止线程");
                    	break;
                    }
                }
                
                
                
                
                
            }
        }.start();


    }

    /**
     * 检测小怪兽是否撞到玩家角色
     */
    private void hit()
    {
        for (int i = 0; i < monsters.size(); i++)
        {
            //获取小怪兽
            Monster monster = monsters.get(i);
            //如果小怪兽角色被撞到
            if (monster.hit(player))
            {
                apdie.play();
                //删除小怪兽
                monsters.remove(monster);
                //玩家血量减少
                player.hp--;
                score += 10;
                //当玩家血量为0时

                if (player.hp <= 0  )
                {

                    //游戏结束
                    gameover = true;
                    bgmusic.stop();
                    aover.play();

                    //第1处
                   // GameFrame.getInstance().closeGameFrame();
                    //System.out.println("*********1***********");
//                    new GameOverFrame(score);

//                    GameOverFrame.getInstance(score);


                    File file = new File("D:\\历史记录.txt");
                    try{
                        if(!file.exists()){
                            file.createNewFile();
                            BufferedWriter bm = null;
                            try {
                                //建立输出流和目标文件之间的联系
                                bm = new BufferedWriter(new FileWriter("D:\\历史记录.txt"));
                                bm.write("历史记录");
                                //每写一行换行
                                bm.newLine();

                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally{
                                //关闭流（释放资源）
                                if(null!=bm){
                                    try {
                                        bm.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }}
                    }catch(IOException e){
                        e.printStackTrace();
                    }
//                  System.out.println(score);
                    savescore(score, "D:\\历史记录.txt");


                    new GameOverFrame(score);


                }
            }
        }
    }

    /**
     * 检测攻击是否集中怪兽
     */
    private void assaultMt()
    {
        //遍历每一次攻击
        for (int i = 0; i < as.size(); i++)
        {
            //获取每一次攻击
            Assault assault = as.get(i);
            //每拿到一次攻击，就判断一下这次攻击是否击中了怪兽
            biu(assault);
        }
    }

    /**
     * 判断攻击是否击中怪兽
     * @param assault
     */
    private void biu(Assault assault)
    {
        for (int i = 0; i < monsters.size(); i++)
        {
            Monster monster = monsters.get(i);
            //判断攻击是否击中怪兽
            if (monster.biuBy(assault))
            {
                //如果怪兽被击中
                //1.发出死亡的声音
                amdie.play();
                //2.变成死亡的画面

                //3.消失
                monsters.remove(monster);

                //3.攻击消失
                as.remove(assault);

                //4.增加分数（伤害）
                score += 10;
            }

        }
    }


    //攻击移动的方法
    private void assaultMove()
    {
        for (int i = 0; i < as.size(); i++)
        {
            Assault a = as.get(i);
            a.move();
        }
    }

    /**
     * 发动攻击的方法
     */
    int aindex = 0;//计数器，记录攻击launch方法执行的次数
    private void launchAssault()
    {
        aindex++;
        if (aindex >= 15)
        {
            //创建攻击
            Assault assault = new Assault(player.x, player.y);

            as.add(assault);
            aindex = 0;
        }


    }

    /**
     * 小怪物移动的方法
     */
    protected void monsterMove()
    {
        for (int i = 0; i < monsters.size(); i++)
        {
            Monster m = monsters.get(i);
            m.move();

        }
    }

    /**
     * 小怪兽入场的方法
     * 在死循环中调用，方法将一直重复执行
     */

    int index = 0;//记录方法执行的次数，每执行n次
    private void monsterEnter()
    {
        //记录执行的次数
        index ++;
        if (index >= 28)
        {
            Monster monster = new Monster();
            monsters.add(monster);
            //将Index重新置为0
            index = 0;
        }
    }



    public GamePanel(GameFrame frame)
    {
        //设置背景
        setBackground(Color.lightGray);

        apdie = App.getSound("src/sound/弹性碰撞、偏Q.wav");
        amdie = App.getSound("src/sound/拔塞子Q弹声02.wav");
        aover = App.getSound("src/sound/score.wav");
        bgmusic = App.getSound("src/sound/游戏音乐_Carlo_耳聆网.wav");

        bgmusic.loop();

        player = new Player();


        //初始化图片
        bg = App.getImg("/img/bg02.png");
        overp = App.getImg("/img/gameover03.png");
        startp = App.getImg("/img/start03.png");

        //使用鼠标监听器
        MouseAdapter adapter = new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                //点击鼠标，会执行的代码


                //如果游戏结束，点击屏幕重新开始游戏
               /* if (gameover)
                {
                    //重新开始游戏
                    player = new Player();
                    //重置游戏开关
                    gameover = false;
                    //分数清0
                    score = 0 ;
                    //清空怪兽和攻击
                    monsters.clear();
                    as.clear();
                    //随机背景图
                    Random rd = new Random();
                    int index = rd.nextInt(7) + 1; //0-5 1-6
                    bg = App.getImg("/img/bg0" + index + ".png");
                    bgmusic.loop();

                    //刷新界面
                    repaint();
                }*/
            }
         /*   public void mouseMoved(MouseEvent e)
            {
                //当鼠标移动时触发的方法
                //角色坐标等于鼠标坐标
                int mx = e.getX();
                int my = e.getY();

                //让角色移动到鼠标上
                player.moveToMouser(mx, my);

                //刷新界面，将角色绘制到新的位置上
                //重新调用paint方法
                repaint();

            }*/
        };
        //将适配器添加到监听器中
        addMouseListener(adapter);
        addMouseMotionListener(adapter);

        //使用键盘监听器
        //创建键盘适配器
        KeyAdapter kd = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //当按下键盘按键，触发的方法
                //获取键盘上按键的数字


                    int keyCode = e.getKeyCode();
                    if (keyCode == KeyEvent.VK_UP &&  player.y>=30)
                    {
                        //角色向上移
                        player.moveUp();

                    }else if (keyCode == KeyEvent.VK_DOWN &&player.y<=560 )
                    {
                        //角色下移
                        player.moveDown();
                    }else if (keyCode == KeyEvent.VK_LEFT &&player.x>=30 )
                    {
                        //角色左移
                        player.moveLeft();
                    }else if (keyCode == KeyEvent.VK_RIGHT &&player.x<=920)
                    {
                        //角色右移
                        player.moveRight();
                    }
//                    repaint();



            }
        };
        //将键盘加到窗体键盘监听器中
        frame.addKeyListener(kd);


    }

    /**
     * 专用画图方法
     * Graphics g 画笔
     * paint 方法的写法， 先打一个paint出来，然后按键盘的Alt + ?
     *
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //在paint中画图是由顺序的，先画的会被后面画的覆盖

        g.drawImage(startp,0,0,null);

        //画背景图片
        g.drawImage(bg, 0, 0, null);

        //画玩家角色图片
        player.paintPlayer(g);

        //画小怪兽图片,遍历小怪物对象集合
        for (int i = 0; i < monsters.size(); i++)
        {
            Monster monster = monsters.get(i);
            g.drawImage(monster.img, monster.x, monster.y - 10, null);
        }

        //画出攻击
        for (int i = 0; i < as.size(); i++)
        {
            Assault assault = as.get(i);
            g.drawImage(assault.img, assault.x, assault.y, null);
        }

        //画分数
        g.setColor(Color.red);
        g.setFont(new Font("幼圆", Font.BOLD, 30));
        g.drawString("伤害 ：" + score, 700,30);

        g.setColor(Color.RED);
        g.setFont(new Font("幼圆", Font.BOLD, 30));
        g.drawString("生命：", 50,35);

        //画暂停按键


        //画玩家角色血量
        for (int i = 0; i < player.hp; i++)
        {
            g.drawImage(player.image, 150 + i * 35, 10, 30,30,null);
        }


    }
}
