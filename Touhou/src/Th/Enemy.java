package Th;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import static Th.Test.*;

public class Enemy extends JPanel {//与自机大致相同
    private Player player;//用于在本类中引入其他类作用域解决自机的子弹无定义
    private JPanel newPanel;
    private Image enemyImage;
    private int enemyX;//最上面为0，下面为高度,草我就是说为什么向上时向下
    private int enemyY;
    private int enemySpeed=2;
    private int direction=1; // 移动方向，1表示向右，-1表示向左
    private int EType;//敌机图像选择

    private boolean EisAlive =true;
    private boolean isBoss=false; // 是否是 Boss
    private int health=10;//敌机小生命
    private int maxHealth;
    private static int enemyKilled = 0;

    protected static void resetEnemyKilled() {
        enemyKilled = 0;
        jl3.setText("EnemyKilled:"+enemyKilled);
    }
    public static int getEnemyKilled() {
        return enemyKilled;
    }
    public int getHealth() {
        return health;
    }
    protected   ArrayList<DownBullet> Ebullets;
    public ArrayList<DownBullet> getEbullets() {
        return Ebullets;
    }
    public void removeBullet(Bullet bullet) {//移除子弹
        Ebullets.remove(bullet);
    }
    public boolean isEisAlive() {
        return EisAlive;
    }
    public void Edie() {
        EisAlive = false;
    }
    private boolean isCounted = false; // 记录敌机是否已经被计数过
    public boolean isCounted() {
        return isCounted;
    }
    public void setCounted(boolean counted) {
        isCounted = counted;
    }

    private long lastShootTime = 0; // 记录上次发射子弹的时间
    public Enemy(JPanel newPanel, Player player) {
        this.newPanel=newPanel;
        this.player = player;
        enemyX = 10; // 设置di机初始位置的x坐标,1050时达到右灵界
        enemyY = 0; // 设置di机初始位置的y坐标也为边界位置
        enemyImage = new ImageIcon(".idea/resource/pictures/蓝妖精L.png").getImage();
        Ebullets = new ArrayList<>();
        // 创建定时器，用于更新敌机位置
        shoot();

    }


public Enemy(JPanel newPanel, Player player, int enemyX, int enemyY, int enemySpeed, int direction, int EType){//创造其他敌机的构造方法
    this.newPanel=newPanel;
        this.player = player;
        this.enemyX=enemyX;
        this.enemyY=enemyY;
        this.enemySpeed=enemySpeed;
    this.direction=direction;
    this.EType=EType;
    Ebullets = new ArrayList<>();
       shoot();//为敌机发射一次还是多次的方法

   }
    public Enemy(int health, JPanel newPanel, Player player, int enemyX, int enemyY, int enemySpeed, int direction, int EType){//创造其他敌机的构造方法
        this.newPanel=newPanel;
        this.player = player;
        this.isBoss = true;
        this.health=health;//区分boss
        maxHealth=health;//比例
        this.enemyX=enemyX;
        this.enemyY=enemyY;
        this.enemySpeed=enemySpeed;
        this.direction=direction;
        this.EType=EType;
        Ebullets = new ArrayList<>();

        shoot2();//默认

        // 定时器
        Timer enemyTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (EisAlive) {
                    if (getHealth() <= maxHealth * 0.95) {
                        shoot1();
                    }
                    if (getHealth() <= maxHealth * 0.85) {
                        shoot();
                    }
                    if (getHealth() <= maxHealth * 0.7) {
                        shoot3();
                    }
                }
                if (getHealth() <= maxHealth * 0.6) {
                    shoot4();
                }
                if (getHealth() <= maxHealth * 0.45) {
                    shoot2();
                }
                if (getHealth() <= maxHealth * 0.3) {
                    shoot5();
                }
            }
        });
        enemyTimer.start();

    }

    public void shoot() {

        int bulletX = enemyX + 40;  // 根据敌机位置确定子弹的初始位置
        int bulletY = enemyY+50;
        for (int i = 0; i < 7; i++) {
            int Angle = (int) (Math.random() * 220) ;//弹幕角度
            DownBullet bullet = new DownBullet(bulletX, bulletY, 6, Angle,4);
            Ebullets.add(bullet);
        }

    }
    public void shoot1() {
        int bulletX = enemyX + 20;
        int bulletY = enemyY+50;
        for (int i = 0; i < 25; i++) {
            int Angle = (int) (Math.random() * 200) ;
            DownBullet bullet = new DownBullet(bulletX, bulletY, 12, Angle,2);
            Ebullets.add(bullet);
        }

    }
    public void shoot2() {
        int bulletX = enemyX + 20;
        int bulletY = enemyY+50;
        for (int i = 0; i < 35; i++) {
            int Angle = (int) (Math.random() * 190) ;
            DownBullet bullet = new DownBullet(bulletX, bulletY, 13, Angle,5);
            Ebullets.add(bullet);
        }

    }
    public void shoot3() {
        int bulletX = enemyX + 20;
        int bulletY = enemyY+50;
        for (int i = 0; i < 30; i++) {
            int Angle = (int) (Math.random() * 200) ;
            DownBullet bullet = new DownBullet(bulletX, bulletY, 15, Angle,3);
            Ebullets.add(bullet);
        }}
    public void shoot4() {
        int bulletX = enemyX + 60;
        int bulletY = enemyY+50;
        for (int i = 0; i < 120; i++) {
            int Angle = (int) (Math.random() * 180) ;
            DownBullet bullet = new DownBullet(bulletX, bulletY, 8, Angle,6);
            Ebullets.add(bullet);
        }}
    public void shoot5() {
        int bulletX = enemyX + 60;
        int bulletY = enemyY+50;
        for (int i = 0; i < 20; i++) {
            int Angle = (int) (Math.random() * 190) - 10;
            DownBullet bullet = new DownBullet(bulletX, bulletY, 17, Angle,8);
            Ebullets.add(bullet);
        }}

    public Rectangle getBounds() {
        if (enemyImage != null) {
            return new Rectangle(enemyX, enemyY, enemyImage.getWidth(null), enemyImage.getHeight(null));
        } else {
            return new Rectangle(enemyX, enemyY, 0, 0);
        }//为空
    }


    public void move() {
        enemyX +=  enemySpeed* direction;
        if (enemyX <= 0 || enemyX >= 1050) {
            EisAlive = false; // 达到边界时设置为不存活，即消失
            newPanel.remove(this);
        }

        // 判断是否达到边界
        if (enemyX <= 0 || enemyX>= 1050) {
            direction *= -1;
       }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShootTime >= 500) {
            shoot();
            lastShootTime = currentTime; // 更新上次发射子弹时间
        }

    }


    protected void paintComponent (Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
        switch (EType) {
            case 1 -> enemyImage = new ImageIcon(".idea/resource/pictures/蓝妖精L.png").getImage();
            case 2 -> enemyImage = new ImageIcon(".idea/resource/pictures/红妖精R.png").getImage();
            case 3 -> enemyImage = new ImageIcon(".idea/resource/pictures/Kaguya.png").getImage();


            // 可添加其他敌机类型的图像路径
        }
        // 绘制血条
        drawHealthBar(g2d);
            g2d.drawImage(enemyImage, enemyX,enemyY, null);


        if (EisAlive) {
            move();
            for (DownBullet bullet : Ebullets) {
                bullet.move();
                bullet.draw(g2d);
            }
        }

        Iterator<Bullet> bulletIterator = player.getPbullets().iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            for (Enemy enemy : enemies) {

                if (enemy.isEisAlive()) {//自机子弹打敌机
                    if (bullet.getBounds().intersects(enemy.getBounds())) {
                        enemy.decreaseHealth();
                        enemy.removeBullet(bullet);
                        bulletIterator.remove();
                        break;
                    }

                } else if (!enemy.isCounted()) {
                    // 如果敌机已经死亡，就增加enemyKilled变量的值。  未判断敌机是否已计过，因此每次循环都会增加enemyKilled变量的值。
                    enemyKilled++;
                    jl3.setText("EnemyKilled:"+enemyKilled);
                    enemy.setCounted(true);
                    System.out.println("1"+enemyKilled);
                }
            }
        }



    }

    public void decreaseHealth() {

        health--;
        if (health <= 0) {
            Edie();
           newPanel.remove(this); // 从面板中移除敌机，this指的是上面die的
            repaint();
        }
    }

    private void drawHealthBar(Graphics2D g2d) {
        if (isBoss) {
            int barWidth = p1_w; // 血条的宽度
            int barHeight =7;

            double healthPercentage = (double) health / maxHealth; // 计算当前血量占总血量的百分比
            int currentHealthWidth = (int) (barWidth * healthPercentage);
            g2d.setColor(Color.GREEN);
            g2d.fillRect(0,0, currentHealthWidth, barHeight); // 绘制绿色血条
        }
    }

}
