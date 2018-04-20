package cn.roylion.magictower.magictower.configuration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Roylion on 2018/3/28.
 */
public class Img {

    private static HashMap<String,Image> images;

    static{
        images = new HashMap<String,Image>();
    }

    /**
     * 加载图片资源
     * @param filePath  文件路径
     * @return
     * @throws IOException
     */
    public static Image getImg(String filePath) {
        if(images.containsKey(filePath)){
            return images.get(filePath);
        } else {
            BufferedImage image = null;
            try {
                image = ImageIO.read(Img.class.getClassLoader().getResourceAsStream("image/"+filePath));
                images.put(filePath,image);
                return image;
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("图片加载失败！");
            }
        }
    }

    public static Image[] getImgs(String code,int num){
        if (num==1) {
            return new Image[]{Img.getImg(code+".bmp")};
        }
        Image[] images = new Image[num];
        for (int i = 0; i < num; i++) {
            images[i] = Img.getImg(code+"-"+(i+1)+".bmp");
        }
        return images;
    }

    public static Image resize(Image image, int width, int height){
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        newImage.getGraphics().drawImage(image,0,0,width,height,null);
        return newImage;
    }


}
