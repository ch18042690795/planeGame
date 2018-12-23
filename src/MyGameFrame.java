import GameObjects.Explode;
import GameObjects.GameUtil;
import GameObjects.Shell;
import GameObjects.plane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * Created by Administrator on 2018/12/23 0023.
 */
public class MyGameFrame extends JFrame {
    //F:\IntelliJ IDEA 2017.1.4\images
    Shell[] shell = new Shell[50];

    Image bg = GameUtil.getImage("images/bg.jpg");
    Image planes = GameUtil.getImage("images/plane.png");
    int x = 250;
    int y = 250;
    plane p = new plane(planes, x, y);
    Date endTime;
    int period;
    Date startTime = new Date();
    Explode bao;

    @Override
    public void paint(Graphics g) {//自动被调用
      /*  Color c=g.getColor();
        Font font=g.getFont();
        g.setColor(Color.blue);
        g.drawOval(100,100,300,300);
        g.setColor(Color.red);
        g.setFont(new Font("宋体",Font.BOLD,50));
        g.drawString("我在哪？",200,200);
        g.setColor(c);
       g.setFont(font);*/
        Color c = g.getColor();
        g.drawImage(bg, 0, 0, null);
        p.drawSelf(g);
        for (int i = 0; i < shell.length; i++) {
            shell[i].draw(g);
            /*Boolean peng=shell[i].getRect().intersects(p.getRect());*/
            boolean peng = shell[i].getRect().intersects(p.getRect());
            if (peng) {
                p.live = false;
        /*      因为炮弹经过飞机时会不停的出发new对象，
                造成每次都是建一个新对象，导致刚要执行方法时又
                被打断生成新的对象去执行了，无法执行完整draw方法，
                所以前面加一个非空判断，只生成一个对象，这样就可以完整触发方法*/
                if (bao == null) {
                    bao = new Explode(p.x, p.y);
                    endTime = new Date();
                    period = (int) ((endTime.getTime() - startTime.getTime()) / 1000);
                }
                if (!p.live) {
                    g.setColor(Color.red);
                    g.drawString("时间：" + period + "秒", (int) p.x, (int) p.y);
                }


                bao.draw(g);
                g.setColor(c);
            }
        }


    }

    class PaintThread extends Thread {
        public void run() {
            while (true) {
                repaint();

                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            System.out.println(e.getKeyCode());
        }

    }

    public void launchFrame() {
        this.setTitle("陈的游戏");
        this.setVisible(true);
        this.setSize(500, 500);
        this.setLocation(300, 300);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        new PaintThread().start();
        for (int i = 0; i < 50; i++) {
            shell[i] = new Shell();
        }
        this.addKeyListener(new KeyMonitor() {
            @Override
            public void keyPressed(KeyEvent e) {
                p.addDirection(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                p.minusDirection(e);
            }

        });
    }

    public static void main(String[] args) {
        MyGameFrame myGameFrame = new MyGameFrame();
        myGameFrame.launchFrame();
    }
}
