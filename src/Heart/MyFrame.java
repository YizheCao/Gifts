package Heart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends JFrame {
    //设计一个构造方法
    //调用其他方法，直接构造GUI界面
    public MyFrame(){
        this.setOtherAttribute();
        this.addElements();
        this.addListener();
        this.setMyself();
    }

    //创建一个面板对象
    private JPanel mainPanel = new JPanel();
    //创建几个组件
    private JLabel pictureLabel = new JLabel();//用来存放图片
    private JLabel questionLabel = new JLabel("你是不是喜欢我???");//用来存放问题
    private JButton yesButton = new JButton("YES");
    private JButton noButton = new JButton("NO");

    /**
     * 用来处理图片
     * 图片路径 宽度 高度
     * @param path
     * @param width
     * @param height
     * @return
     */
    private ImageIcon drawImage(String path,int width,int height){
        ImageIcon imageIcon = new ImageIcon(path);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        return imageIcon;
    }

    /**
     * 处理各种组件的状态
     */
    private void setOtherAttribute(){
        //设置一下每个组件的 颜色 边框 字体 图片等等
        //将panel默认的布局方式清除掉
        mainPanel.setLayout(null);
        //设置图片的位置及边框颜色
        pictureLabel.setBounds(40,40,160,200);
        pictureLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        pictureLabel.setIcon(this.drawImage("D:\\project\\eclipse\\Gifts\\src\\image\\meng.jpg",160,200));
        //设置问题的位置及边框颜色
        questionLabel.setBounds(220,80,260,40);
        questionLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        questionLabel.setFont(new Font("微软雅黑",Font.BOLD,22));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置字体位置
        //设置yes按钮的位置及边框颜色
        yesButton.setBounds(240,160,100,40);
        yesButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        yesButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        yesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //设置no按钮的位置及边框颜色
        noButton.setBounds(360,160,100,40);
        noButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        noButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        noButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /**
     * 处理所有组件之间互相添加
     */
    private void addElements(){
        //将所有的组件添加到Panel里面，这个Panel再添加到Frame里
        mainPanel.add(pictureLabel);
        mainPanel.add(questionLabel);
        mainPanel.add(yesButton);
        mainPanel.add(noButton);
        this.add(mainPanel);
    }

    /**
     * 用来给按钮添加事件监听
     */
    private void addListener(){
        //YES按钮的鼠标监听事件
        yesButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new ILoveYou();
            }

            public void mouseExited(MouseEvent e) {
                yesButton.setFont(new Font("微软雅黑",Font.BOLD,14));
                yesButton.setForeground(Color.BLACK);
            }
        });
        yesButton.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                yesButton.setFont(new Font("微软雅黑",Font.BOLD,16));
                yesButton.setForeground(Color.RED);
            }
        });
        //NO按钮的鼠标监听事件
        noButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //System.out.println("鼠标单击");
                new ILoveYou();
            }

            public void mouseExited(MouseEvent e) {
                noButton.setText("NO");
                noButton.setFont(new Font("微软雅黑",Font.BOLD,16));
                noButton.setForeground(Color.BLACK);
            }
        });
        noButton.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                noButton.setText("YES");
                noButton.setFont(new Font("微软雅黑",Font.BOLD,16));
                noButton.setForeground(Color.RED);
            }
        });
        //设置关闭时的事件监听
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int value = JOptionPane.showConfirmDialog(MyFrame.this,"你要把小可爱关掉吗，嘤嘤嘤");
                if (value == 0){
                    JOptionPane.showMessageDialog(MyFrame.this,"关掉就有用吗");
                }else {
                    JOptionPane.showMessageDialog(MyFrame.this,"是不是舍不得我呀");
                }
            }
        });
    }

    /**
     * 处理当前窗体自身的状态
     */
    private  void setMyself(){
        this.setTitle("表白");
        //设置窗体对象的一些属性
        //设置窗体的默认大小
        //左上角的坐标点,窗体的宽度，高度
        this.setBounds(400,300,500,300);
        //设置窗体不可以被随意拖拽大小
        this.setResizable(false);
        //设置窗体单击右上角关闭按钮的时候 不做任何操作
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //设置可见性
        //需要最后设置，以保证所有组件的可见性
        /* 图形化界面居中 */
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth() - this.getWidth()) / 2;
        int y = (int)(toolkit.getScreenSize().getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);
        /*改图标*/
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\project\\eclipse\\Gifts\\src/image/gift.png");
        this.setIconImage(icon);
        this.setVisible(true);
    }
}
