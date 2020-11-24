package ui;

/**
 * 攻击类
 */
public class Assault extends Character
{
    /**
     * 构造方法
     *  px : 游戏角色横坐标
     *  py : 游戏角色纵坐标
     */
    public Assault(int px, int py)
    {


        img = App.getImg("/img/orange.png");

        w = img.getWidth();
        h = img.getHeight();

        //确定发动的攻击的初始位置
        x = px + 40 ;
        y = py + 80;
    }

    /**
     * 攻击移动的方法
     */
    public void move()
    {
        x += 10;
    }

    @Override
    public boolean outOfBounds() {
        return x < -w;
    }
}
