package ui;

import java.awt.image.BufferedImage;

/**
 * 游戏场景中的每种角色
 */
public abstract class Character
{
    //图片
    BufferedImage img;
    //坐标
    int x;
    int y;
    //宽高
    static  int w;
    int h;

    /**
     * 检查是否出界
     * @return true 出界与否
     */
    public abstract boolean outOfBounds();
}
