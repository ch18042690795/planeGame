package GameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Administrator on 2018/12/23 0023.
 */
public class GameUtil {
    public static Image getImage(String path){
        BufferedImage bi=null;
        URL u=GameUtil.class.getClassLoader().getResource(path);
        try {
            bi= ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
}
