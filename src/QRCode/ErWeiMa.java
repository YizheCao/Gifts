package QRCode;

import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ErWeiMa extends JFrame {
    private String imgPath;// 二维码保存路径
    private String content;// 二维码内容
    public JFrame frame = null;

    public ErWeiMa(String imgPath, String content){
        this.imgPath = imgPath;
        this.content = content;

        frame = new JFrame();
        frame.setTitle("二维码");
        frame.setSize(600, 600);
        /* 图形化界面居中 */
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth() - frame.getWidth()) / 2;
        int y = (int)(toolkit.getScreenSize().getHeight() - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        /* 改图标 */
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\project\\eclipse\\Gifts\\src/image/gift.png");
        frame.setIconImage(icon);
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.setVisible(true);
    }

    public boolean createCode(int width, int height) {
        boolean flag = true;
        try {
            Qrcode qrcode = new Qrcode();// 创建Qrcode对象
            // 排错率可选(%)-L(7):M(15):Q(25):H(30)
            qrcode.setQrcodeErrorCorrect('M');
            // 编码模式-Numeric(M-数字)：Binary(B-二进制):KanJi(K-汉字):Alphanumeric(A-英文字母)
            qrcode.setQrcodeEncodeMode('B');
            qrcode.setQrcodeVersion(3);// 设置版本（可选）

            width = width >= 100 ? width : 100;// 宽度至少100
            height = height >= 100 ? height: 100;// 高度至少100
            // 创建画布和画图设备
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            Graphics2D draw = img.createGraphics();
            draw.setBackground(Color.WHITE);// 设置背景色
            draw.clearRect(0, 0, width, height);// 清空原始内容
            draw.setColor(Color.BLACK);// 设置前景色

            int posOff = 2;// 设置偏移量，避免输出点重叠
            // 设置内容编码
            byte[] codeContent = this.content.getBytes("utf-8");
            // 生成二维数组,500是内容大小，根据自己的内容大小进行设定
            if (codeContent.length > 0 && codeContent.length < 500) {
                boolean[][] qrcodeOut = qrcode.calQrcode(codeContent);
                // 将内容写入到图片中
                for (int i = 0; i < qrcodeOut.length; i++) {
                    for (int j = 0; j < qrcodeOut.length; j++) {
                        // 如果当前位置有像素点
                        if (qrcodeOut[j][i]){
                            // 写入图片
                            draw.fillRect(j * 16 + posOff, i * 16 + posOff, 16, 16);
                        }
                    }
                }
            }

            draw.dispose();//关闭画图设备
            img.flush();//刷新缓冲区
            File file = new File(imgPath);
            ImageIO.write(img, "png", file);//保存图片
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public static void main(String[] args){
        String imgPath = "D:\\project\\eclipse\\Gifts\\src/image";
        String content = "小可爱21岁生日快乐";
        ErWeiMa erWeiMa = new ErWeiMa(imgPath, content);
        boolean result =  erWeiMa.createCode(480, 480);
        if (result) {
            ImageIcon img = new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/code.png");
            JLabel imageLabel = new JLabel(img);
            erWeiMa.frame.add(imageLabel);
        }
    }
}
