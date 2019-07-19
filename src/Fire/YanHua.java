package Fire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YanHua extends JFrame implements ActionListener {
    final int row = 1;//定义行
    final int col = 1;//定义列
    private int width = 600;
    private int height = 400;
    JButton[][] board_button = new JButton[row][col];
    JLabel label = null;
    JPanel panel = null;
    Container container = null;

    public YanHua(){
        label = new JLabel("点一下图片！");
        panel = new JPanel();
        panel.add(label);
        container = new Container();

        //添加container容器，放一系列按钮
        this.add(container, BorderLayout.CENTER);//加入容器放在CENTER布局位置
        container.setLayout(new GridLayout(row,col));//向container添加Grid网格布局
        JButton button = new JButton();//初始化当前按钮
        board_button[0][0] = button;
        board_button[0][0].addActionListener(this);
        container.add(board_button[0][0]);//将button放到容器里面
        board_button[0][0].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/egaoBirthday.gif").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));

        this.setTitle("生日快乐");
        this.setSize(600, 450);
        /* 图形化界面居中 */
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth() - this.getWidth()) / 2;
        int y = (int)(toolkit.getScreenSize().getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);
        /*改图标*/
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\project\\eclipse\\Gifts\\src/image/gift.png");
        this.setIconImage(icon);
        this.add(panel,BorderLayout.NORTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board_button[0][0].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/love.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
    }

    public static void main(String[] args){
        new YanHua();
    }
}
