package com.djy.notes.view.UserView;

import com.djy.notes.bean.Msg;
import com.djy.notes.controller.UserController;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author djy
 */
public class RegisterView extends JDialog {
    //JDialog:在JFrame基础之上存在 —— 参数
    public RegisterView(Frame owner) {
        super(owner);
        //初始化登录界面
        initComponents();
        //设置窗体宽高
        setBounds(100,100,700,500);
        //设置窗体居中
        setLocationRelativeTo(null);
        //使用插件时窗体默认不可见，设置成可见
        setVisible(true);
        //销毁当前注册弹窗
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * 点击确认注册按钮调用此方法
     * @param e
     */
    private void okButtonActionPerformed(ActionEvent e) {
        //获取用户注册信息
        String userName = userNameField.getText();
        //密码框只能获取到字符数组
        char[] pwd = pwdField.getPassword();
        String telephone =telephoneField.getText();
        String email =emailField.getText();
        String sign =signField.getText();

        //将字符数组的密码转换成String类型
        String password = new String(pwd);

        //判断用户输入用户名或密码是否为空
        if(userName == null||"".equals(userName)||password == null||"".equals(password)){
            //此静态方法在父级组件的基础之上存在
            JOptionPane.showMessageDialog(RegisterView.this,"用户名或密码不能为空");
            return;
        }



        //调用Controller层与View层直接交互
        UserController userController = new UserController();
        Msg result = userController.addUser(userName,password,telephone,email,sign);

        if(result.isSuccess()){
            //注册成功
            JOptionPane.showMessageDialog(RegisterView.this,result.getMessage());
            //销毁注册页面
            this.dispose();
        }else{
            //注册失败
            JOptionPane.showMessageDialog(RegisterView.this,result.getMessage());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.UserView.RegisterView");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        userNameField = new JTextField();
        label3 = new JLabel();
        pwdField = new JPasswordField();
        label4 = new JLabel();
        telephoneField = new JTextField();
        label5 = new JLabel();
        emailField = new JTextField();
        label6 = new JLabel();
        signField = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        setTitle(bundle.getString("RegisterView.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- label1 ----
                label1.setText(bundle.getString("RegisterView.label1.text"));
                label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 26));
                contentPanel.add(label1);
                label1.setBounds(200, 0, 115, 30);

                //---- label2 ----
                label2.setText(bundle.getString("RegisterView.label2.text"));
                label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label2);
                label2.setBounds(90, 60, 61, label2.getPreferredSize().height);

                //---- userNameField ----
                userNameField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(userNameField);
                userNameField.setBounds(170, 60, 250, 30);

                //---- label3 ----
                label3.setText(bundle.getString("RegisterView.label3.text"));
                label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label3);
                label3.setBounds(100, 115, 40, 22);

                //---- pwdField ----
                pwdField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
                contentPanel.add(pwdField);
                pwdField.setBounds(170, 115, 250, 30);

                //---- label4 ----
                label4.setText(bundle.getString("RegisterView.label4.text"));
                label4.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label4);
                label4.setBounds(80, 165, 80, 22);

                //---- telephoneField ----
                telephoneField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(telephoneField);
                telephoneField.setBounds(170, 165, 250, 30);

                //---- label5 ----
                label5.setText(bundle.getString("RegisterView.label5.text"));
                label5.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label5);
                label5.setBounds(80, 220, 85, 22);

                //---- emailField ----
                emailField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(emailField);
                emailField.setBounds(170, 220, 250, 30);

                //---- label6 ----
                label6.setText(bundle.getString("RegisterView.label6.text"));
                label6.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label6);
                label6.setBounds(80, 275, 85, 27);

                //---- signField ----
                signField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(signField);
                signField.setBounds(170, 275, 330, 30);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < contentPanel.getComponentCount(); i++) {
                        Rectangle bounds = contentPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = contentPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    contentPanel.setMinimumSize(preferredSize);
                    contentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                //---- okButton ----
                okButton.setText("\u786e\u8ba4\u6ce8\u518c");
                okButton.addActionListener(e -> okButtonActionPerformed(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label2;
    private JTextField userNameField;
    private JLabel label3;
    private JPasswordField pwdField;
    private JLabel label4;
    private JTextField telephoneField;
    private JLabel label5;
    private JTextField emailField;
    private JLabel label6;
    private JTextField signField;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
