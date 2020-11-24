package ui;

import java.util.Random;

/**
 * 小怪兽类
 *
 * 小怪物很多   存放多个数据->1.数组 2.集合
 * 数量未知
 */
public class Monster extends Character
{
    public Monster()
    {
        //定义随机数类
        Random rd = new Random();

       // 生成一个随机数来选取图片[0,2] [1,3]
        int index = rd.nextInt(3)+1;
        //确定显示的小怪物的图片
        img = App.getImg("/img/monster"+ index+".png");



        w = img.getWidth();
        h = img.getHeight();

        //确定小怪物出现的位置
        x = 1024 - w;
        y = rd.nextInt(768 - h);


    }

    /**
     * 小怪物移动的方法
     */
    public void move()
    {
        x -= 10;
    }

    //判断怪兽是否被攻击击中
    public boolean biuBy(Assault assault)
    {
        boolean hit = x <= assault.x + assault.w &&
                       x >= assault.x - w &&
                       y <= assault.y + assault.h &&
                       y >= assault.y - h;
                ;
        return hit;
    }

    public boolean hit(Player player)
    {
        boolean hit = x <= player.x + player.w - 25 &&
                       x >= player.x - w + 25 &&
                       y <= player.y + player.h - 100&&
                       y >= player.y - h + 10 ;
        return hit;
    }

    @Override
    public boolean outOfBounds() {
        return x < -w;
    }
}
