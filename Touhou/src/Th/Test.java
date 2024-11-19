package Th;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import static Th.Enemy.getEnemyKilled;
public class Test {
    private static final int rate = 60; // 帧率
    private static boolean isUpKeyPressed = false;
    private static boolean isLeftKeyPressed = false;
    private static boolean isDownKeyPressed = false;
    private static boolean isRightKeyPressed = false;
    private static boolean isZKeyPressed = false;
    private static boolean isShiftKeyPressed = false;
    protected static  int p1_w=1100;private static final int p1_l=1080;//界面一的宽高
    private static final int p2_w=420;private static final int p2_l=1080;//界面二的宽高
    static ArrayList<Enemy> enemies = new ArrayList<>();
    private static long lastEnemyTime = 0;
    private static Timer enemyTimer; // 将 enemyTimer 声明为成员变量\
    private static Timer Dtimer; // 将 enemyTimer 声明为成员变量\
    private static Timer checkBossDefeatedTimer;
    private static Enemy enemyEX;
    protected static JLabel jl2; // 将jl2声明为成员变量
    protected static JLabel jl3; // 将jl2声明为成员变量
    private static JLabel jLabel;//解决boss一直为空的方法
    public static void main(String[] args) {
        Music m1=new Music();
        m1.playMusic(".idea/resource/music/TH1.wav");
        Music m2=new Music();
        Music m3=new Music();
        Music m4=new Music();
        JFrame f = new JFrame("东方永夜抄(简)");
        f.setLocation(0, 0);
        f.setSize(1920, 1080);
        f.setLayout(null);

        JPanel p1 = new JPanel();//游戏界面
        JPanel p2 = new JPanel();//右边自己
        JPanel p3 = new JPanel();//结束

        p1.setBounds(0, 0, p1_w, p1_l);
        p2.setBounds(1100, 0, p2_w, p2_l);
        p3.setBounds(0, 0, 1920, 1080);

        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);

        JButton b1 = new JButton("START");
        b1.setFont(new Font("宋体",Font.BOLD,25));
        JButton b2 = new JButton("Introduction");
        b2.setFont(new Font("宋体",Font.BOLD,20));
        JButton b3 = new JButton("Again");
        b3.setBounds(600,260,180,60);
        b3.setFont(new Font("宋体",Font.BOLD,25));


        p2.setLayout(null);//解决了按钮加入在面板二中的覆盖以及start全覆盖p2的问题。
        b1.setBounds(1180, 360, 180, 60);
        b2.setBounds(1180, 480, 180, 60);

        f.add(b1);
        f.add(b2);
        f.add(p1);
        f.add(p2);
        f.add(p3);

        ImageIcon backgroundImage = new ImageIcon(".idea/resource/pictures/P1.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        // 添加游戏介绍的内容，如自机和敌机的图片、故事等等

// 可以使用JLabel来显示图片，或使用其他组件来展示文本内容
        ImageIcon Haku=new ImageIcon(".idea/resource/pictures/Remu2D.png");
        JLabel j1=new JLabel(Haku) ;
        ImageIcon Kagu=new ImageIcon(".idea/resource/pictures/Kaguya2D.png");
        JLabel j2=new JLabel(Kagu) ;
        j1.setBounds(100,100,256,512);
        j2.setBounds(400,100,512,512);
backgroundLabel.add(j1);backgroundLabel.add(j2);

        JTextArea textArea = new JTextArea();
        textArea.setText("introduction:「东方永夜抄　～ Imperishable Night.」ZUN独自开发的为少女弹幕射击游戏（STG）。\n" +
                "玩家需要操纵自机躲避华丽弹幕并且击败她。注意灵活的躲才是精华。\n\n" +
                "游戏操作键：移动默认四箭头,Z为开火键，shift为低速且显示判定撞击点（默认高速状态且不显示），\n\n" +
                "辉夜为「竹取物语」那位「辉夜姬」\n" +
                "随其生命的递减，她似慌乱，五大难题的弹幕也逐渐乱丢，难度逐渐增加呢！\n" +
                "该项目多采用触手猴marasy钢琴翻弹：如道中的曲子：ヴォヤージュ1970 ，展示原作辉夜的主题曲：竹取飛翔～Lunatic Princess");
    // 设置字体大小和颜色
        Font font = new Font("微软雅黑", Font.BOLD, 20);
        textArea.setFont(font);
        textArea.setForeground(Color.orange);
        textArea.setEditable(false); // 设置为只读
        textArea.setOpaque(false); // 设置文本域透明
       textArea.setBounds(0, 612, 1920,1080); // 设置面板的位置和大小
        f.add(textArea); // 将滚动面板添加到父容器中（这里的f是JFrame对象）
   backgroundLabel.setBounds(0, 0, f.getWidth(), f.getHeight());
        f.add(backgroundLabel);
        // 设置按钮初始状态
        b1.setBackground(Color.CYAN);
        b2.setBackground(Color.WHITE);

        b1.setFocusable(true);
        b2.setFocusable(false);


        // 添加键盘事件处理
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) {
                    // 上键操作
                    b1.setBackground(Color.CYAN);
                    b2.setBackground(Color.WHITE);
                    b1.setFocusable(true);
                    b2.setFocusable(false);
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    // 下键操作
                    b1.setBackground(Color.WHITE);
                    b2.setBackground(Color.CYAN);
                    b1.setFocusable(false);
                    b2.setFocusable(true);
                } else if (keyCode == KeyEvent.VK_ENTER) {
                    // 回车键操作
                    if (b1.isFocusable()) {

                        // 选择了Start按钮
                        System.out.println("选择了Start按钮");
                        m1.pauseMusic();

                        m2.playMusic(".idea/resource/music/Th2.wav");
                        // 切换到新界面
                        CardLayout cardLayout = (CardLayout) p1.getLayout();
                        cardLayout.show(p1, "newPanel");
                        p1.setVisible(true);
                        CardLayout cardLayout2 = (CardLayout) p2.getLayout();
                        cardLayout2.show(p2, "newPanel2");
                        p2.setVisible(true);

                        // 取消按钮控制并隐藏按钮
                        b1.setFocusable(false);
                        b2.setFocusable(false);
                        b1.setVisible(false);
                        b2.setVisible(false);
                    } else if (b2.isFocusable()) {
                        // 选择了Introduction按钮
                        System.out.println("选择了Introduction按钮");
                        // 取消按钮控制并隐藏按钮
                        b1.setFocusable(false);
                        b2.setFocusable(false);
                        b1.setVisible(false);
                        b2.setVisible(false);


                    }

                }
            }
        });

// 创建自机面板并添加到新界面面板中
        Player player = new Player();
        player.setSize( p1_w, p1_l); // 设置自机面板的,width和heigh为移动范围

        JPanel newPanel = new JPanel();
        newPanel.setLayout(null); // 设置布局管理器为null，以便自由定位组件
        newPanel.add(player);
        player.setOpaque(false);

         enemyTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (System.currentTimeMillis() - lastEnemyTime > 2000) {

                    if (getEnemyKilled() >= 15) { // 根据你的需要设置生成最终 BOSS 的条件
                        for (Enemy enemy : enemies) {
                            enemy.Edie();
                            newPanel.remove(enemy); // 从父容器中移除敌机
                        }
                        m2.pauseMusic();
                        m3.playMusic(".idea/resource/music/Th3.wav");

                        enemyEX = new Enemy(3500,newPanel, player, 500, 40, 0, 1, 3);
                        enemyEX.setBounds(0, 0, p1_w, p1_l);
                        newPanel.add(enemyEX);
                        enemyEX.setOpaque(false);
                        newPanel.setComponentZOrder(enemyEX, 0); // 将敌机置顶层or
                        enemies.add(enemyEX);
                        newPanel.repaint();

                        // 停止定时器
                        enemyTimer.stop();


                    } else {
                        Enemy enemy = new Enemy(newPanel, player);
                        enemy.setBounds(10, 10, p1_w, p1_l);
                        newPanel.add(enemy);
                        enemies.add(enemy);
                        newPanel.setComponentZOrder(enemy, 0); // 将敌机面板置于顶层
                        enemy.setOpaque(false);

                        Enemy enemy2 = new Enemy(newPanel, player, 1040, 150, 2, -1, 2);
                        enemy2.setBounds(0, 0, p1_w, p1_l);
                        newPanel.add(enemy2);
                        enemies.add(enemy2);
                        newPanel.setComponentZOrder(enemy2, 0); // 将敌机面板置于顶层
                        enemy2.setOpaque(false);



                        // 更新上一次生成敌机的时间
                        lastEnemyTime = System.currentTimeMillis();
                        newPanel.repaint();
                    }
                }
            }
        });

        enemyTimer.stop();
// 重新计算触发频率
        enemyTimer.setDelay(3000);
        enemyTimer.start();


        checkBossDefeatedTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 检查 BOSS 是否被打败

                if (enemyEX != null && !enemyEX.isEisAlive()) {
                    // 游戏结束逻辑
                    m3.pauseMusic();
                    m4.playMusic(".idea/resource/music/end.wav");
                    // 切换到新界面

                    p1.setVisible(false);p2.setVisible(false);//KEY
                    jLabel.setText("恭喜通关,Thanks For Playing!");
                    CardLayout cardLayout3 = (CardLayout) p3.getLayout();

                    cardLayout3.show(p3, "newPanel3");
                    p3.setVisible(true);

                    b3.setVisible(true);

                    // 停止定时器
                    checkBossDefeatedTimer.stop();
                }
            }
        });
        checkBossDefeatedTimer.start();


Dtimer =new Timer(1000/rate , new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!player.isAlive()) {
            // 自机死亡逻辑
            m2.pauseMusic();m3.pauseMusic();
            m4.playMusic(".idea/resource/music/end.wav");
            // 切换到新界面
            p1.setVisible(false);p2.setVisible(false);
            CardLayout cardLayout3 = (CardLayout) p3.getLayout();
            cardLayout3.show(p3, "newPanel3");
            p3.setVisible(true);

            b3.setVisible(true);
            Dtimer.stop();

        }
    }
});
Dtimer.start();

        Timer timer1 = new Timer(1000 /rate, new ActionListener() {//定时器每隔16.67毫秒就会触发一次
            @Override
            public void actionPerformed(ActionEvent e) {


                if (isZKeyPressed) {//共通
                    player.Shoot();
                }
                if (isShiftKeyPressed && isZKeyPressed) {//Z与Shift同时发
                    player.SpeedSlow();
                }
                if (isUpKeyPressed && isLeftKeyPressed) {
                    player.moveUL();

                } else if (isUpKeyPressed && isRightKeyPressed) {
                    player.moveUR();

                } else if (isDownKeyPressed && isLeftKeyPressed) {
                    player.moveDL();

                } else if (isDownKeyPressed && isRightKeyPressed) {
                    player.moveDR();

                } else if (isUpKeyPressed) {
                    player.moveUp();


                } else if (isDownKeyPressed) {
                    player.moveDown();


                } else if (isLeftKeyPressed) {
                    player.moveLeft();

                } else if (isRightKeyPressed) {
                    player.moveRight();

                }

                player.repaint();

            }
        });
        timer1.start();


        f.addKeyListener(new KeyAdapter() {


            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode==KeyEvent.VK_SHIFT){
                    player.SpeedSlow();//按下shift切换为低速度
                    player.isShowHitPoint();
                }
                if (keyCode == KeyEvent.VK_SHIFT) {
                    isShiftKeyPressed = true;
                }
                if (keyCode == KeyEvent.VK_UP) {
                    isUpKeyPressed = true;

                } else if (keyCode == KeyEvent.VK_DOWN) {
                    isDownKeyPressed = true;

                } else if (keyCode == KeyEvent.VK_LEFT) {
                    isLeftKeyPressed = true;

                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    isRightKeyPressed = true;

                }

                else if (keyCode == KeyEvent.VK_Z) {
                    isZKeyPressed = true;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_SHIFT) {
                    isShiftKeyPressed=false;
                    player.isnotShowHitPoint();//松开不按不显示
                    player.SpeedFast();//松开shift转变为原来的高速
                }
                if (keyCode == KeyEvent.VK_UP) {
                    isUpKeyPressed = false;
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    isDownKeyPressed = false;
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    isLeftKeyPressed = false;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    isRightKeyPressed = false;
                }

                else if (keyCode == KeyEvent.VK_Z) {
                    isZKeyPressed = false;
                }
            }
        });


        ImageIcon np1 = new ImageIcon(".idea/resource/pictures/P2.jpg");
        JLabel backgroundLabel1 = new JLabel(np1);
        backgroundLabel1.setBounds(0, 0, p1_w, p1_l);
        newPanel.add(backgroundLabel1);

        p1.setLayout(new CardLayout());
        p1.add(newPanel, "newPanel");

        p1.setVisible(false);  // 隐藏当前界面



        // 创建新界面的面板2
        JPanel newPanel2 = new JPanel();
        ImageIcon np2=new ImageIcon(".idea/resource/pictures/P3.jpg");
      JLabel backgroundLabel2=new JLabel(np2) ;

         jl2=new JLabel("player："+ player.getHealth());
        jl2.setForeground(Color.red);
        jl2.setFont(new Font("宋体",Font.BOLD,50));
        jl2.setBounds(50,200,300,50);
        backgroundLabel2.add(jl2);
        jl3=new JLabel("EnemyKilled："+ getEnemyKilled());
        jl3.setForeground(Color.red);
        jl3.setFont(new Font("宋体",Font.BOLD,35));
        jl3.setBounds(50,350,300,30);
        backgroundLabel2.add(jl3);
        backgroundLabel2.setBounds(1100,0,p2_w,p2_l);
        p2.setLayout(new CardLayout());


        newPanel2.add(backgroundLabel2);
        p2.add(newPanel2,"newPanel2");
        p2.setVisible(false);// 隐藏当前界面，原因就在这里，会把在其面板的组件也覆盖



// 创建新界面的面板3
        JPanel newPanel3 = new JPanel();
        ImageIcon np3=new ImageIcon(".idea/resource/pictures/P4.jpg");
        JLabel backgroundLabel3=new JLabel(np3) ;
         jLabel = new JLabel("很遗憾,但Thanks For Playing!");//默认

       jLabel.setForeground(Color.red);
       jLabel.setFont(new Font("宋体",Font.BOLD,35));
       jLabel.setBounds(500,200,700,35);

        backgroundLabel3.add(jLabel);backgroundLabel3.add(b3);
        backgroundLabel3.setBounds(0,0,1920,1080);

        newPanel3.add(backgroundLabel3);
        p3.setLayout(new CardLayout());
        p3.add(newPanel3,"newPanel3");
        p3.setVisible(false);


        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 选择了Again按钮
                System.out.println("选择了Again按钮");
                m4.pauseMusic();
                m2.playMusic(".idea/resource/music/Th2.wav");



              player.resetCondition();//重置自机的所有状态

                for (Enemy enemy : enemies) {
                   enemy.Edie(); newPanel.remove(enemy);
                }
                enemies.clear();
               Enemy.resetEnemyKilled();
                enemyEX = null;

                lastEnemyTime = 0;
                jLabel.setText("很遗憾,但Thanks For Playing!");//默认

                // 重新启动定时器
                enemyTimer.restart();
                checkBossDefeatedTimer.restart();
                Dtimer.start();//死亡行为共享


                // 切换到Start的
                CardLayout cardLayout = (CardLayout) p1.getLayout();
                cardLayout.show(p1, "newPanel");
                newPanel3.setVisible(false);
                p1.setVisible(true);

                CardLayout cardLayout2 = (CardLayout) p2.getLayout();
                cardLayout2.show(p2, "newPanel2");
                p2.setVisible(true);

                // 取消按钮控制并隐藏按钮
                b3.setFocusable(false);
                b3.setVisible(false);
            }
        });




        f.setVisible(true);
        f.setFocusable(true);
        f.requestFocus();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
