package Problem;

import Lottery.ChouJiang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WenTi extends JFrame implements ActionListener {
    JPanel panelDanXuan;
    JPanel panel_label;
    JPanel panel_button;
    JLabel labTiMu;
    JButton btSubmit;
    //创建一个按钮组
    ButtonGroup option;
    JRadioButton optionA;
    JRadioButton optionB;
    JRadioButton optionC;
    JRadioButton optionD;
    int cishu = 1;

    public WenTi(){
        panelDanXuan = new JPanel();
        panel_label = new JPanel();
        panel_button = new JPanel();
        labTiMu = new JLabel("问题：我们在一起是哪一天？");
        btSubmit = new JButton("提交");
        btSubmit.addActionListener(this);
        option = new ButtonGroup();
        optionA = new JRadioButton("A.2016年9月");
        optionB = new JRadioButton("B.2017年9月");
        optionC = new JRadioButton("C.2016年8月");
        optionD = new JRadioButton("D.2017年8月");

        //把按钮加到同一个按钮组
        option.add(optionA);
        option.add(optionB);
        option.add(optionC);
        option.add(optionD);

        //将按钮按列排列
        panelDanXuan.setLayout(new BoxLayout(panelDanXuan,BoxLayout.Y_AXIS));
        panelDanXuan.add(optionA);
        panelDanXuan.add(optionB);
        panelDanXuan.add(optionC);
        panelDanXuan.add(optionD);
        //设置边框
        panelDanXuan.setBorder(BorderFactory.createTitledBorder("单选"));
        panel_label.add(labTiMu);
        panel_button.add(btSubmit);

        this.setTitle("答题");
        this.setSize(300,230);
        /* 图形化界面居中 */
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth() - this.getWidth()) / 2;
        int y = (int)(toolkit.getScreenSize().getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);
        /*改图标*/
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\project\\eclipse\\Gifts\\src/image/gift.png");
        this.setIconImage(icon);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.add(panel_label,BorderLayout.NORTH);
        this.add(panelDanXuan,BorderLayout.CENTER);
        this.add(panel_button,BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(cishu > 0) {
            if (e.getSource() == btSubmit) {
                if (optionA.isSelected() == true) {
                    JOptionPane.showMessageDialog(null, "答对了，去抽奖吧！");
                    new ChouJiang();
                } else if (optionB.isSelected() == true) {
                    JOptionPane.showMessageDialog(null, "卧槽，竟然答错了！");
                } else if (optionC.isSelected() == true) {
                    JOptionPane.showMessageDialog(null, "卧槽，竟然答错了！");
                } else if (optionD.isSelected() == true) {
                    JOptionPane.showMessageDialog(null, "卧槽，竟然答错了！");
                } else {
                    JOptionPane.showMessageDialog(null, "不好意思，你没有选答案！");
                }
                cishu--;
                //重置按钮的值
                option.clearSelection();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "对不起，你没机会了！");
        }
    }

    public static void main(String[] args) throws Exception {
        new WenTi();
    }
}
