package ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Character
{
    int hp;
    private BufferedImage[] images;//图片数组
    public BufferedImage image;//当前显示的图片
    private int index;


    /**
     * 构造方法
     */
    public Player()
    {
        initPerson();
        image = images[0];

        index=0;

        x = 200;
        y = 550;

        w = image.getWidth() * 2;
        h = image.getHeight()* 2;

        //确定开始时，玩家角色血量
        hp = 3;
    }

    /**
     * 让角色移动到鼠标位置上
     * @param mx
     * @param my
     */
  /*  public void moveToMouser(int mx , int my)
    {
        x = mx - w/2;
        y = my - h/2;
    }
*/
    /**
     * 向下移动
     */
    public void moveDown()
    {
        y += 50 ;
    }

    /**
     * 向上移动
     */
    public void moveUp()
    {
        y -= 50 ;
    }

    /**
     * 向左移动
     */
    public void moveLeft()
    {
        x -= 55 ;
    }

    /**
     * 向右移动
     */
    public void moveRight()
    {
        x += 50 ;
    }


    public void initPerson(){
        //给图片数组images赋值
        images=new BufferedImage[10];
        for(int i= 0;i< images.length;i++)
        {
                images[i]= App.getImg("/img/player00" + i + ".png");
        }
    }

    //玩家移动
    public void step(){
        //图片切换
        image = images[index++ / 15 %  images.length];
        //坐标移动
    }

    //绘制玩家的方法
    public void paintPlayer(Graphics g){
        g.drawImage(image, x, y,null);
    }

    @Override
    public boolean outOfBounds() {
        return x < -w || x > w || y < -h || y > h;
    }
}