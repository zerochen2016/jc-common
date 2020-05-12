package jc.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * util of image
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class ImageUtil {

	// 验证码字符集  
    private static final char[] chars = {   
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',   
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',  
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',  
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',   
        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};  
    // 字符数量  
    private static final int SIZE = 4;  
    // 干扰线数量  
    private static final int LINES = 0;  
    // 宽度  
    private static final int WIDTH = 80;  
    // 高度  
    private static final int HEIGHT = 35;  
    // 字体大小  
    private static final int FONT_SIZE = 30;  
  
    /** 
     * 生成随机验证码及图片 
     * Object[0]：验证码字符串； 
     * Object[1]：验证码图片。 
     */  
    public static Object[] createVerifyImage() {  
        StringBuffer sb = new StringBuffer();  
        // 1.创建空白图片  
        BufferedImage image = new BufferedImage(  
            WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);  
        // 2.获取图片画笔  
        Graphics graphic = image.getGraphics();  
        // 3.设置画笔颜色  
        graphic.setColor(Color.WHITE);  
        // 4.绘制矩形背景  
        graphic.fillRect(0, 0, WIDTH, HEIGHT);  
        // 5.画随机字符  
        Random ran = new Random();  
        for (int i = 0; i <SIZE; i++) {  
            // 取随机字符索引  
            int n = ran.nextInt(chars.length);  
            // 设置随机颜色  
            graphic.setColor(getRandomColor());  
            // 设置字体大小 
            graphic.setFont(new Font(  
                null, Font.BOLD + Font.ITALIC, FONT_SIZE));  
            // 画字符  
            graphic.drawString(  
                chars[n] + "", i * WIDTH / SIZE, HEIGHT*2/3);  
            // 记录字符  
            sb.append(chars[n]);  
        }  
        // 6.画干扰线  
        for (int i = 0; i < LINES; i++) {  
            // 设置随机颜色  
            graphic.setColor(getRandomColor());  
            // 随机画线  
            graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT),  
                    ran.nextInt(WIDTH), ran.nextInt(HEIGHT));  
        }  
        // 7.返回验证码和图片  
        return new Object[]{sb.toString(), image};  
    }  
  
    /** 
     * 随机取色 
     */  
    public static Color getRandomColor() {  
        Random ran = new Random();  
        Color color = new Color(ran.nextInt(256),   
                ran.nextInt(256), ran.nextInt(256));  
        return color;  
    }  
    
    /**
     * 添加水印
     * @param srcImgPath 图片路径
     * @param watermarkContent 水印内容
     * @param color 颜色
     * @param font 字体
     */
	public static void addWatermark(String srcImgPath, String watermarkContent,Color color,Font font) {
		
		try {
			//获取图片文件
			File srcImgfile = new File(srcImgPath);
			//把文件转换成图片
			Image srcImg = ImageIO.read(srcImgfile);
			//获取图片的宽和高
			int srcImgwidth = srcImg.getWidth(null);
			int srcImgheight = srcImg.getHeight(null);
			
			//画水印需要一个画板    创建一个画板
			BufferedImage buffImg = new BufferedImage(srcImgwidth,srcImgheight,BufferedImage.TYPE_INT_RGB);
			//创建一个2D的图像
			Graphics2D g = buffImg.createGraphics();
			//画出来
			g.drawImage(srcImg, 0, 0, srcImgwidth, srcImgheight,null);
			
			//设置水印的颜色
			g.setColor(color);
			
			//设置水印的字体
			g.setFont(font);
			
			//设置水印坐标
			int x = srcImgwidth * 19/20 - getwatermarkLength(watermarkContent, g);
			int y = srcImgheight * 9/10;
			
			//根据获取的坐标 在相应的位置画出水印
			g.drawString(watermarkContent, x, y);
			
			//释放画板的资源
			g.dispose();
			
			//输出新的图片
			FileOutputStream outputStream = new FileOutputStream(srcImgfile);
			
			//创建新的图片
			ImageIO.write(buffImg, "jpg", outputStream);
			
			
			//刷新流
			outputStream.flush();
			//关闭流
			outputStream.close();
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取水印坐标
	 * @param watermarkContent 水印内容
	 * @param g
	 * @return
	 */
	public static int getwatermarkLength(String watermarkContent,Graphics2D g) {
		return	g.getFontMetrics(g.getFont()).charsWidth(watermarkContent.toCharArray(), 0, watermarkContent.length());
				
	}
	
	public static void main(String[] args) {
		
		Font font = new Font("微软雅黑",Font.PLAIN,20);
		
		String srcImgPath = "图片路径";
		
		String watermarkContent = "水印";
		Color color = new Color(255, 255, 255,128);
		
		ImageUtil.addWatermark(srcImgPath, watermarkContent, color, font);
		
	}
    
}
