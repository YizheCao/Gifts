package Heart;

import javax.swing.*;
import java.awt.*;

public class ILoveYou extends JFrame {
    private static final int WIDTH = 450;
    private static final int HEIGHT = 450;

    public ILoveYou(){
        this.setTitle("小心心");
        this.setBackground(Color.BLACK);//背景色
        this.setBounds(500,500,WIDTH,HEIGHT);//设置窗体自己的大小
        /* 图形化界面居中 */
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth() - this.getWidth()) / 2;
        int y = (int)(toolkit.getScreenSize().getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//点击关闭
        /*改图标*/
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\project\\eclipse\\Gifts\\src/image/gift.png");
        this.setIconImage(icon);
        this.setVisible(true);//设置窗口可见
    }

    //需要重写paint方法
    public void paint(Graphics g){
        for(int count=1;count<=10;count++){
            for(int r=1;r<=100-count;r++){//控制从小变大
                Image image = this.createImage(WIDTH,HEIGHT);
                Graphics pic = image.getGraphics();
                this.drawHeart(pic,WIDTH/2,HEIGHT/2-200+r,r);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //每一次画完后 让当前的心留在这里
                for(int show=1;show<=count;show++){
                    this.drawHeart(pic,WIDTH/2,HEIGHT/2-100-show,100-show);
                }
                pic.drawString("我也喜欢你",WIDTH/2-30,HEIGHT/2);
                g.drawImage(image,0,0,this);
            }
        }
    }

    /**
     * 画心形图案
     * @param pic
     * @param x
     * @param y
     * @param r
     */
    private void  drawHeart(Graphics pic,int x,int y,int r){
        int offset = (int)(r/1.414);//直角边长
        int c1x = x - (r - offset)/2;
        int c1y = y - (r - offset)/2;
        int c2x = c1x - 2 * offset;
        int c2y = c1y + 2 * offset;
        int c3x = x + (r - offset)/2;
        int c3y = y - (r - offset)/2;
        int c4x = c3x + 2 * offset;
        int c4y = c3y + 2 * offset;
        int ex = x;
        int ey = c2y + (x - c2x);
        int leftArcX = c1x - r - offset;
        int leftArcY = c1y - (r - offset);
        int rightArcX = c1x;
        int rightArcY = leftArcY;
        //画图
        pic.setColor(Color.MAGENTA);
        pic.drawLine(x,y,c1x,c1y);//O C1
        pic.drawLine(x,y,c3x,c3y);//O C3
        pic.drawLine(c2x,c2y,ex,ey);//
        pic.drawLine(c4x,c4y,ex,ey);//C4 e
        pic.drawArc(leftArcX,leftArcY,r*2,r*2,45,180);
        pic.drawArc(rightArcX,rightArcY,r*2,r*2,315,180);
    }
}
