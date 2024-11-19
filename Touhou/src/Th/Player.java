package Th;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import static Th.Test.jl2;

public class Player extends JPanel {
    private Image playerImage;//本质上引入Image类中的。看18行相当于实例化了一个Image的对象playerImage。
    private final int playerXL=0;//左边界
    private final int playerXR=1050;//右边界
    private final int playerYL=0;//上边界
    private final int playerYH=800;//下边界
    private int playerX;//最上面为0，下面为高度,草我就是说为什么向上时向下
    private int playerY;
    int initialX=500;
    int initialY=800;
    private int playerSpeed; // 自机的移动速度
        private int health=4;
    protected int getHealth() {
        return health;
    }
    private boolean isAlive=true;
    protected boolean isAlive() {
            return isAlive;
        }
      protected void die() {
            isAlive = false;
        }

    private ArrayList<Bullet> Pbullets; // 修改为实例变量
        public  ArrayList<Bullet> getPbullets() {
            return Pbullets;
        }
    private boolean showHitPoint; // 初始为不显示按shift显示， hitpoint
    public void isShowHitPoint() {
         showHitPoint=true;
    }
    //主函数中实现
    public void isnotShowHitPoint() {
        showHitPoint=false;
    }
    public Player() {
        playerX = initialX; // 设置自机初始位置的x坐标,1050时达到右灵界
        playerY = initialY; // 设置自机初始位置的y坐标也为边界位置
        playerSpeed = 10; // 设置自机移动速度
        playerImage = new ImageIcon(".idea/resource/pictures/Remu.png").getImage();
        Pbullets = new ArrayList<>();
        showHitPoint = false; // 初始状态下不显示 hitpoint
    }


    public void SpeedSlow() {playerSpeed = 4;}
    public void SpeedFast() {
        playerSpeed = 14;
    }

        public void Shoot() {
            int bulletX = playerX ;  // 根据自机位置确定子弹的初始位置,
            int bulletY = playerY;
            Bullet bullet = new Bullet(bulletX, bulletY,20,1);  // 创建新的子弹对象因为用的move所以自机的子弹speed用不到
            Pbullets.add(bullet);  // 将子弹添加到列表中
        }
    public void resetCondition() {
        // 将自机的位置重置到初始位置
        playerY=initialY;
        playerX=initialX;
        Pbullets = new ArrayList<>();//子弹也重置
        health=4;
        isAlive=true;
        resetHealthLabel();
    }


    // 自机向上移动
    public void moveUp() {
       if(playerY>=playerXL){
            playerY -= playerSpeed;
        playerImage = new ImageIcon(".idea/resource/pictures/Remu.png").getImage();
        repaint();

    }}
    // 自机向下移动
    public void moveDown() {
        if(playerY<=playerYH){
            playerY += playerSpeed;
        playerImage = new ImageIcon(".idea/resource/pictures/Remu.png").getImage();
        repaint();
    }}
    // 自机向左移动
    public void moveLeft() {
        if (playerX>= playerXL) {
            playerX -= playerSpeed;
            playerImage = new ImageIcon(".idea/resource/pictures/RemuL.png").getImage();
            repaint();
        }
    }
    // 自机向右移动
    public void moveRight() {
        if (playerX <=playerXR) {
            playerX += playerSpeed;
            playerImage = new ImageIcon(".idea/resource/pictures/RemuR.png").getImage();
            repaint();
        }
    }

    public void moveUL(){
        if(playerX>=playerXL&&playerY>=playerYL){
        playerX-=playerSpeed;
        playerY-=playerSpeed;
        playerImage = new ImageIcon(".idea/resource/pictures/RemuL.png").getImage();
        repaint();
    }}
    public void moveUR(){
        if(playerX<=playerXR&&playerY>=playerYL){
        playerX+=playerSpeed;
        playerY-=playerSpeed;
        playerImage = new ImageIcon(".idea/resource/pictures/RemuR.png").getImage();
        repaint();
    }}
    public void moveDL(){
        if(playerX>=playerXL&&playerY<=playerYH){
        playerX-=playerSpeed;
        playerY+=playerSpeed;
        playerImage = new ImageIcon(".idea/resource/pictures/RemuL.png").getImage();
        repaint();
    }}
    public void moveDR() {
        if (playerX <= playerXR&& playerY <=playerYH) {
            playerX += playerSpeed;
            playerY += playerSpeed;
            playerImage = new ImageIcon(".idea/resource/pictures/RemuR.png").getImage();
            repaint();
        }
    }


        public Rectangle getBounds() {
            // 获取并返回敌机的矩形边界，用于碰撞检测
            return new Rectangle(playerX,playerY,playerImage.getWidth(null),
                    playerImage.getHeight(null));
        }

    public Point getHitPoint() {
        // 获取判定点的坐标，位于自机中心
        int centerX = playerX + playerImage.getWidth(null) / 2;
        int centerY = playerY + playerImage.getHeight(null) / 2;
        return new Point(centerX, centerY);
    }

    public int getHitRadius() {
        // 获取判定点的半径
        return 8; // 调整为适当的大小
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // 绘制自机图片
        g2d.drawImage(playerImage, playerX, playerY, null);

        // 绘制自判定点圆hitpoint
        Point hitPoint = getHitPoint();
        int hitRadius = getHitRadius();

        if(showHitPoint) {//只有该显示使才
            g2d.setColor(Color.BLUE);
            g2d.fillOval(hitPoint.x - hitRadius, hitPoint.y - hitRadius,  2*hitRadius, 2*hitRadius);
        }

        for (Bullet bullet : Pbullets) {
            bullet.move();
            bullet.draw(g2d);
        }

        for (Enemy enemy : Test.enemies) {
            if (enemy.isEisAlive()) {
                //唯有存活的敌机,因为我的敌机越界相当于没存活了其子弹也看不见了，但仍进行碰撞检测
            Iterator<DownBullet> iterator = enemy.getEbullets().iterator();
            while (iterator.hasNext()) {
                Bullet bullet = iterator.next();//敌机子弹打自机
                if (getHitPoint().distance(bullet.getCenter()) <= getHitRadius()) {
                    // 圆自机判定点和敌机子弹发生碰撞
                    iterator.remove();
                    DecreaseHealth();
                    break;
                }
            }}
        }
    }
    protected void DecreaseHealth() {
        // 减少自机的血量
        health--;
        jl2.setText("player: " + health); // 更新生命值标签的文本
        System.out.println("自-1");
        if (health <= 0) {
            die(); // 如果血量小于等于0，自机死亡

        }

    }
    public void resetHealthLabel() {
        health = 4; // 将生命值重置为初始值
        jl2.setText("player: " + health); // 更新生命值标签的文本

    }

    }