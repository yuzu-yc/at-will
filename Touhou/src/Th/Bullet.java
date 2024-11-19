package Th;
import javax.swing.*;
import java.awt.*;

public class Bullet {
    protected int x;
    protected int y;
    protected int speed;
    protected int angle; // 子弹的发射角度
    protected int  BType;  // 子弹类型
    protected Image shootImage;
    public  Bullet(){}
    public Bullet(int x, int y,int speed,int BType) {
        //自机灵梦用
        this.x = x+10;
        this.y = y;
      this.speed=speed;
        this.BType=1;
        // 初始化shootImage变量
        switch (BType) {
            case 1 -> shootImage = new ImageIcon(".idea/resource/pictures/shoot1.png").getImage();
            case 2 -> shootImage = new ImageIcon(".idea/resource/pictures/shoot2.png").getImage();
            case 3 -> shootImage = new ImageIcon(".idea/resource/pictures/shoot3.png").getImage();
            case 4 -> shootImage = new ImageIcon(".idea/resource/pictures/shoot4.png").getImage();
            case 5 -> shootImage = new ImageIcon(".idea/resource/pictures/shoot5.png").getImage();
            case 6 -> shootImage = new ImageIcon(".idea/resource/pictures/shoot6.png").getImage();
            case 8 -> shootImage = new ImageIcon(".idea/resource/pictures/shoot8.png").getImage();
        }
    }

    public void move() {
        // 移动子弹的位置
        y -= speed;
    }

    public void draw(Graphics g) {

        g.drawImage(shootImage,x,y,null);
    }

    public Rectangle getBounds() {
        // 获取并返回子弹的位置以及子弹的矩形边界，用于碰撞检测
        return new Rectangle(x,y,
                shootImage.getWidth(null),
                shootImage.getHeight(null));
    }
    public Point getCenter() {
        int centerX = x + shootImage.getWidth(null) / 2;
        int centerY = y + shootImage.getHeight(null) / 2;
        return new Point(centerX, centerY);
    }

}
 class DownBullet extends Bullet {
    //敌机用

    public DownBullet(int x, int y, int speed,int angle,int BType) {

        this.x = x+10;
        this.y = y;
        this.speed=speed;
        this.angle=angle;
        this.BType=BType;
// 初始化shootImage变量
        switch (BType) {
            case 1 -> shootImage = new ImageIcon(".idea/resource/pictures/shoot1.png").getImage();
            case 2 -> shootImage = new ImageIcon(".idea/resource/pictures/shoot2.png").getImage();
            case 3 -> shootImage = new ImageIcon(".idea/resource/pictures/shoot3.png").getImage();
            case 4 -> shootImage = new ImageIcon(".idea/resource/pictures/shoot4.png").getImage();
            case 5 -> shootImage = new ImageIcon(".idea/resource/pictures/shoot5.png").getImage();
            case 6 -> shootImage = new ImageIcon(".idea/resource/pictures/shoot6.png").getImage();
            case 8 -> shootImage = new ImageIcon(".idea/resource/pictures/shoot8.png").getImage();
        }
    }
    public void move() {
        double radians = Math.toRadians(angle);//散射子弹（转弧度）
        double dx = speed * Math.cos(radians);
        double dy = speed * Math.sin(radians);
        // 移动子弹的位置
        x += dx;
        y += dy;
    }

}