package GameObjects;

import java.awt.*;

/**
 * Created by Administrator on 2018/12/23 0023.
 */
public class GameObject {
    public Image img;
    public  double x,y;
    public int speed;
    public int width,height ;

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public  Rectangle   getRect(){
        return  new Rectangle((int)x, (int)y, width, height);
    }
}
