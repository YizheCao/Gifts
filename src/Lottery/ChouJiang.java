package Lottery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

public class ChouJiang extends JFrame implements ActionListener {
    final int row = 3;//定义行
    final int col = 3;//定义列
    private int width = 150;
    private int height = 90;
    Thread thread = null;
    Container container = null;
    JLabel label = null;
    JLabel score = null;
    JPanel panel = null;
    JButton[][] board_button = new JButton[row][col];//定义九个宫格,写奖品
    float[][] board_value = new float[row][col];//定义九宫格对应的value，判断是否抽中
    int cishu = 1;

    public ChouJiang(){
        label = new JLabel("当前获奖：");
        score = new JLabel();
        panel = new JPanel();
        panel.add(label);
        panel.add(score);
        container = new Container();

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
        board_button[0][0].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/five.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        board_value[0][0] = 5;
        board_button[0][1].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/pointFive.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        board_value[0][1] = 0.5f;
        board_button[0][2].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/one.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        board_value[0][2] = 1;
        board_button[1][0].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/pointFive.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        board_value[1][0] = 0.5f;
        board_button[1][1].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/start.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        board_value[1][1] = 0;
        board_button[1][2].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/pointFive.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        board_value[1][2] = 0.5f;
        board_button[2][0].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/one.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        board_value[2][0] = 1;
        board_button[2][1].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/pointFive.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        board_value[2][1] = 0.5f;
        board_button[2][2].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/one.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        board_value[2][2] = 1;

        this.setTitle("抽奖");
        this.setSize(480, 350);
        /* 图形化界面居中 */
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth() - this.getWidth()) / 2;
        int y = (int)(toolkit.getScreenSize().getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        /*改图标*/
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\project\\eclipse\\Gifts\\src/image/gift.png");
        this.setIconImage(icon);
        this.add(panel,BorderLayout.NORTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(cishu > 0) {
            JButton btn = (JButton) e.getSource();
            if (btn.equals(board_button[1][1])) {
                board_button[0][0].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/five.jpg").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
                board_button[0][1].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/pointFive.jpg").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
                board_button[0][2].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/one.jpg").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
                board_button[1][0].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/pointFive.jpg").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
                board_button[1][1].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/start.jpg").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
                board_button[1][2].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/pointFive.jpg").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
                board_button[2][0].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/one.jpg").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
                board_button[2][1].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/pointFive.jpg").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
                board_button[2][2].setIcon(new ImageIcon(new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/one.jpg").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));

                thread = new Thread(new start());
                thread.start();
            }
            cishu--;
        }
        else{
            JOptionPane.showMessageDialog(null, "对不起，你的机会用完了！");
        }
    }

    public class start implements Runnable{
        @Override
        public void run() {
            int result = new Random().nextInt(9);
            int flag = 0;
            int result_row = 0;
            int result_col = 0;
            if(result == 4){
                result++;
            }
            switch (result){
                case 0:
                    result_row = 0;
                    result_col = 0;
                    break;
                case 1:
                    result_row = 0;
                    result_col = 1;
                    break;
                case 2:
                    result_row = 0;
                    result_col = 2;
                    break;
                case 3:
                    result_row = 1;
                    result_col = 0;
                    break;
                case 5:
                    result_row = 1;
                    result_col = 2;
                    break;
                case 6:
                    result_row = 2;
                    result_col = 0;
                    break;
                case 7:
                    result_row = 2;
                    result_col = 1;
                    break;
                case 8:
                    result_row = 2;
                    result_col = 2;
                    break;
            }
            try {
                for (int flow = 0; flow < 1; flow++) {
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < col; j++) {
                            board_button[i][j].setBackground(Color.RED);
                            Thread.sleep(100);
                            board_button[i][j].setBackground(Color.WHITE);
                            Thread.sleep(100);
                        }
                    }
                }
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if(flag < result) {
                            board_button[i][j].setBackground(Color.RED);
                            Thread.sleep(100);
                            board_button[i][j].setBackground(Color.WHITE);
                            Thread.sleep(100);
                            flag++;
                        }
                    }
                }
                board_button[result_row][result_col].setBackground(Color.RED);
                score.setText(board_value[result_row][result_col] + "元");

                FileWriter fw = null;
                try {
                    File f = new File("D:\\project\\eclipse\\Gifts\\src\\Score\\defen.txt");
                    fw = new FileWriter(f, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                PrintWriter pw = new PrintWriter(fw);
                pw.println(board_value[result_row][result_col]);
                pw.flush();
                try {
                    fw.flush();
                    pw.close();
                    fw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                String str1 = "恭喜获得" + board_value[result_row][result_col] + "元";
                JOptionPane.showMessageDialog(null, str1);
            }
            catch (Exception e){

            }
        }
    }

    public static void main(String[] args){
        new ChouJiang();
    }
}
