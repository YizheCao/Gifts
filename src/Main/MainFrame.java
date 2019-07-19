package Main;

import Fire.YanHua;
import Heart.MyFrame;
import Lottery.ChouJiang;
import MineSweeping.SaoLei;
import Mora.CaiQuan;
import Problem.WenTi;
import QRCode.ErWeiMa;
import Snake.Panel;
import TicTacToe.JingChessBoard;

import javax.media.Manager;
import javax.media.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainFrame extends JFrame implements ActionListener {
    final int cishu = 20;
    JLabel label = null;
    JLabel rule = null;
    JTextField textField = null;
    JButton submit = null;
    JButton getScore = null;
    JButton music = null;
    JButton pause = null;
    JPanel panel = null;
    JPanel panel_music = null;
    File music_file = new File("D:\\project\\eclipse\\Gifts\\src\\music\\HappyBirthday.wav");
    Player player = null;
    public int count = cishu;
    public String fileName = "D:\\project\\eclipse\\Gifts\\src\\Score\\defen.txt";

    public MainFrame() throws Exception{
        player = Manager.createPlayer(music_file.toURL());

        label = new JLabel("输入数字：");
        rule = new JLabel("<html>规则：本游戏总共有9个关卡，<br>随便输入一个小于1024的非负数，进入某个关卡，通过关卡可以进行抽奖，最终兑换红包，总共20次机会。</html>");
        textField = new JTextField();
        submit = new JButton("确定");
        submit.addActionListener(this);
        getScore = new JButton("当前奖金");
        getScore.addActionListener(this);
        music = new JButton("music");
        music.addActionListener(this);
        pause = new JButton("停止");
        pause.addActionListener(this);

        panel = new JPanel();
        panel.setLayout(null);
        panel.add(label);
        label.setBounds(110,20,80,20);
        panel.add(rule);
        rule.setBounds(110,100,180,100);
        panel.add(textField);
        textField.setBounds(190,20,80,20);
        panel.add(submit);
        submit.setBounds(160,65,60,30);
        panel.add(getScore);
        getScore.setBounds(280,65,90,30);
        panel_music = new JPanel();
        panel_music.setBorder(BorderFactory.createTitledBorder("生日歌"));
        panel_music.add(music);
        panel_music.add(pause);

        this.setTitle("给小可爱");
        this.setSize(400, 300);
        /* 图形化界面居中 */
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth() - this.getWidth()) / 2;
        int y = (int)(toolkit.getScreenSize().getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);
        /*改图标*/
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\project\\eclipse\\Gifts\\src/image/gift.png");
        this.setIconImage(icon);
        this.add(panel,BorderLayout.CENTER);
        this.add(panel_music,BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit) {
            if(textField.getText().equals("")){
                JOptionPane.showMessageDialog(null, "别调皮，输入数字！");
            }
            else{
                //彩蛋：-1加一次机会，1024重置
                int number = Integer.parseInt(textField.getText());
                //System.out.println("开始前：" + count);
                if(number >= 0 && number < 1024){
                    int remainder = number % 9;
                    if(count > 0){
                        switch (remainder){
                            case 0:
                                new WenTi();
                                count--;
                                break;
                            case 1:
                                new JingChessBoard();
                                count--;
                                break;
                            case 2:
                                new YanHua();
                                break;
                            case 3:
                                JFrame frame = new JFrame("贪吃蛇");
                                frame.setBounds(200, 20, 900, 700);
                                frame.setResizable(false);

                                Snake.Panel panel = new Panel();
                                frame.add(panel);

                                /* 改图标 */
                                Image icon = Toolkit.getDefaultToolkit().getImage("D:\\project\\eclipse\\Gifts\\src/image/gift.png");
                                frame.setIconImage(icon);
                                frame.setVisible(true);
                                frame.setDefaultCloseOperation(HIDE_ON_CLOSE);

                                count--;
                                break;
                            case 4:
                                new CaiQuan();
                                count--;
                                break;
                            case 5:
                                new SaoLei();
                                count--;
                                break;
                            case 6:
                                new MyFrame();
                                break;
                            case 7:
                                String imgPath = "D:\\project\\eclipse\\Gifts\\src/image/code.png";
                                String content = "小可爱21岁生日快乐";
                                ErWeiMa erWeiMa = new ErWeiMa(imgPath, content);
                                boolean result =  erWeiMa.createCode(480, 480);
                                if (result) {
                                    ImageIcon img = new ImageIcon("D:\\project\\eclipse\\Gifts\\src/image/code.png");
                                    JLabel imageLabel = new JLabel(img);
                                    erWeiMa.frame.add(imageLabel);
                                }
                                break;
                            case 8:
                                new ChouJiang();
                                count--;
                                break;
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "对不起，你的机会用完了！");
                    }
                }
                else if(number == 1024){
                    count = cishu;
                    textField.setText("");

                    FileWriter fw = null;
                    File file = new File(fileName);
                    try {
                        if(!file.exists()) {
                            file.createNewFile();
                            System.out.println("文件不存在");
                        }
                        FileWriter fileWriter = new FileWriter(file);
                        fileWriter.write("");
                        fileWriter.flush();
                        fileWriter.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "已重置，Happy起来！");
                }
                else if(number == -1){
                    count++;
                    JOptionPane.showMessageDialog(null, "哈哈哈，你的次数已加一！");
                }
                else{
                    textField.setText("");
                    JOptionPane.showMessageDialog(null, "请输入小于1024的非负数！");
                }
                //System.out.println("开始后：" + count);
            }
        }
        else if(e.getSource() == music) {
            player.start();
        }
        else if(e.getSource() == pause){
            player.stop();
        }
        else if(e.getSource() == getScore){
            /** 字符输入流 */
            BufferedReader br = null;
            String line = "";
            float bonus = 0;
            try {
                br = new BufferedReader(new FileReader(fileName));
                while((line = br.readLine()) != null) {
                    bonus = bonus + Float.parseFloat(line);
                }
            }
            catch (IOException e1){
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "哇，你的奖金有" + bonus + "元！");
        }
    }

    public static void main(String[] args) throws Exception {
        String fileName = "D:\\project\\eclipse\\Gifts\\src\\Score\\defen.txt";
        File file = new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
                System.out.println("文件不存在");
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        new MainFrame();
    }
}
