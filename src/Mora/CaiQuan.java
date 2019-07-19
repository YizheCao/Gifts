package Mora;

import Lottery.ChouJiang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CaiQuan extends JFrame implements ActionListener {
    final int row = 2;//定义行
    final int col = 3;//定义列
    private int width = 190;
    private int height = 188;
    private int result_col = 0;
    Thread thread = null;
    Container container = null;
    JLabel label = null;
    JPanel panel = null;
    JButton[][] board_button = new JButton[row][col];
    int[][] board_value = new int[row][col];
    int cishu = 1;

    public CaiQuan(){
        container = new Container();
        label = new JLabel("规则：上方是电脑，电脑随机出；下方是玩家，点击按钮玩家出");
        panel = new JPanel();
        panel.add(label);

        //添加container容器，放一系列按钮
        this.add(container, BorderLayout.CENTER);//加入容器放在CENTER布局位置
        container.setLayout(new GridLayout(row,col));//向container添加Grid网格布局
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                JButton button = new JButton();//初始化当前按钮
                board_button[i][j] = button;
                board_button[i][j].addActionListener(this);
                container.add(board_button[i][j]);//将button放到容器里面
            }
        }
        board_button[0][0].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/bu.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        board_value[0][0] = 1;
        board_button[0][1].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/jiandao.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        board_value[0][1] = 2;
        board_button[0][2].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/shitou.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        board_value[0][2] = 3;
        board_button[1][0].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/bu.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        board_value[1][0] = 1;
        board_button[1][1].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/jiandao.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        board_value[1][1] = 2;
        board_button[1][2].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/shitou.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        board_value[1][2] = 3;

        this.setTitle("猜拳");
        this.setSize(600, 450);
        /* 图形化界面居中 */
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth() - this.getWidth()) / 2;
        int y = (int)(toolkit.getScreenSize().getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);
        /*改图标*/
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\project\\eclipse\\Gifts\\src/image/gift.png");
        this.setIconImage(icon);
        this.setVisible(true);
        this.add(panel,BorderLayout.NORTH);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                board_button[i][j].setBackground(Color.WHITE);
            }
        }

        if(cishu > 0) {
            JButton btn = (JButton) e.getSource();
            if (btn.equals(board_button[1][0])) {
                thread = new Thread(new start1());
                thread.start();
                board_button[1][0].setBackground(Color.BLACK);
            } else if (btn.equals(board_button[1][1])) {
                thread = new Thread(new start2());
                thread.start();
                board_button[1][1].setBackground(Color.BLACK);
            } else if (btn.equals(board_button[1][2])) {
                thread = new Thread(new start3());
                thread.start();
                board_button[1][2].setBackground(Color.BLACK);
            }
            cishu--;
        }
        else{
            JOptionPane.showMessageDialog(null, "不好意思，你没机会了！");
        }
    }

    public class start1 implements Runnable {
        @Override
        public void run() {
            int result = new Random().nextInt(3);
            int flag = 0;
            switch (result) {
                case 0:
                    result_col = 0;
                    break;
                case 1:
                    result_col = 1;
                    break;
                case 2:
                    result_col = 2;
                    break;
            }
            try {
                for (int flow = 0; flow < 1; flow++) {
                    for (int j = 0; j < col; j++) {
                        board_button[0][j].setBackground(Color.RED);
                        Thread.sleep(100);
                        board_button[0][j].setBackground(Color.WHITE);
                        Thread.sleep(100);
                    }
                }
                for (int j = 0; j < col; j++) {
                    if(flag < result) {
                        board_button[0][j].setBackground(Color.RED);
                        Thread.sleep(100);
                        board_button[0][j].setBackground(Color.WHITE);
                        Thread.sleep(100);
                        flag++;
                    }
                }
                board_button[0][result_col].setBackground(Color.RED);
                if(board_value[0][result_col] == 1){
                    JOptionPane.showMessageDialog(null, "阿偶，平局！");
                }
                else if(board_value[0][result_col] == 2){
                    JOptionPane.showMessageDialog(null, "不好意思，你输了！");
                }
                else if(board_value[0][result_col] == 3){
                    JOptionPane.showMessageDialog(null, "恭喜你，你赢了！去抽奖吧！");
                    new ChouJiang();
                }
            }
            catch (Exception e){

            }
        }
    }

    public class start2 implements Runnable {
        @Override
        public void run() {
            int result = new Random().nextInt(3);
            int flag = 0;
            switch (result) {
                case 0:
                    result_col = 0;
                    break;
                case 1:
                    result_col = 1;
                    break;
                case 2:
                    result_col = 2;
                    break;
            }
            try {
                for (int flow = 0; flow < 1; flow++) {
                    for (int j = 0; j < col; j++) {
                        board_button[0][j].setBackground(Color.RED);
                        Thread.sleep(100);
                        board_button[0][j].setBackground(Color.WHITE);
                        Thread.sleep(100);
                    }
                }
                for (int j = 0; j < col; j++) {
                    if(flag < result) {
                        board_button[0][j].setBackground(Color.RED);
                        Thread.sleep(100);
                        board_button[0][j].setBackground(Color.WHITE);
                        Thread.sleep(100);
                        flag++;
                    }
                }
                board_button[0][result_col].setBackground(Color.RED);
                if(board_value[0][result_col] == 1){
                    JOptionPane.showMessageDialog(null, "恭喜你，你赢了！去抽奖吧！");
                    new ChouJiang();
                }
                else if(board_value[0][result_col] == 2){
                    JOptionPane.showMessageDialog(null, "阿偶，平局！");
                }
                else if(board_value[0][result_col] == 3){
                    JOptionPane.showMessageDialog(null, "不好意思，你输了！");
                }
            }
            catch (Exception e){

            }
        }
    }

    public class start3 implements Runnable {
        @Override
        public void run() {
            int result = new Random().nextInt(3);
            int flag = 0;
            switch (result) {
                case 0:
                    result_col = 0;
                    break;
                case 1:
                    result_col = 1;
                    break;
                case 2:
                    result_col = 2;
                    break;
            }
            try {
                for (int flow = 0; flow < 1; flow++) {
                    for (int j = 0; j < col; j++) {
                        board_button[0][j].setBackground(Color.RED);
                        Thread.sleep(100);
                        board_button[0][j].setBackground(Color.WHITE);
                        Thread.sleep(100);
                    }
                }
                for (int j = 0; j < col; j++) {
                    if(flag < result) {
                        board_button[0][j].setBackground(Color.RED);
                        Thread.sleep(100);
                        board_button[0][j].setBackground(Color.WHITE);
                        Thread.sleep(100);
                        flag++;
                    }
                }
                board_button[0][result_col].setBackground(Color.RED);
                if(board_value[0][result_col] == 1){
                    JOptionPane.showMessageDialog(null, "不好意思，你输了！！");
                }
                else if(board_value[0][result_col] == 2){
                    JOptionPane.showMessageDialog(null, "恭喜你，你赢了！去抽奖吧！");
                    new ChouJiang();
                }
                else if(board_value[0][result_col] == 3){
                    JOptionPane.showMessageDialog(null, "阿偶，平局");
                }
            }
            catch (Exception e){

            }
        }
    }

    public static void main(String[] args){
        new CaiQuan();
    }
}
