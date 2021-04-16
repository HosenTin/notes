package com.djy.notes.view.UserView;

import com.djy.notes.bean.Msg;
import com.djy.notes.controller.AdminController;
import com.djy.notes.controller.UserController;
import com.djy.notes.entity.User;
import com.djy.notes.util.FileUtil;
import com.djy.notes.view.AdminView.AdminManagerView;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author djy
 */
public class LoginView extends JFrame {
    public LoginView() {
        //初始化登录界面
        initComponents();
        //设置窗体宽高
        setBounds(100,100,900,700);
        //设置窗体居中
        setLocationRelativeTo(null);
        //使用插件时窗体默认不可见，设置成可见
        setVisible(true);
        //设置点击关闭按钮时程序结束
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 点击登录按钮时调用该方法
     * @param e
     */
    private void loginBtnActionPerformed(ActionEvent e) {
        //获取用户登录信息
        String name = nameField.getText();
        //密码框获取到的是字符数组
        char[] pwd = passwordField.getPassword();

        //判断用户输入用户名或密码是否为空
        if(name == null||"".equals(name)||pwd == null||"".equals(pwd)){
            JOptionPane.showMessageDialog(LoginView.this,"名称或密码不能为空");
            return;
        }

        //将字符数组的密码转换成String类型
        String password = new String(pwd);

        //获取选择的权限
        String selectedItem = (String)roleBox.getSelectedItem();

        if("用户".equals(selectedItem)){
            //调用Controller层与View层直接交互
            UserController userController = new UserController();
            Msg result = userController.login(name,password);

            if(result.isSuccess()){
                //将用户信息保存起来
                int userId = Integer.parseInt(result.getMessage());
                User userToSave = new User();
                userToSave.setUserId(userId);
                userToSave.setUserName(name);
                //将用户信息写入文件
                FileUtil.write(userToSave);

                //跳转到语鹊笔记管理系统主页面
                new NoteManageView();

                //销毁登录页面
                this.dispose();
            }else{
                //登录失败
                JOptionPane.showMessageDialog(this,result.getMessage());
            }
        }else{
            //调用Controller层与View层直接交互
            AdminController adminController = new AdminController();
            Msg result = adminController.login(name,password);

            if(result.isSuccess()){

                //跳转到管理员页面
                new AdminManagerView();

                //销毁登录页面
                this.dispose();
            }else{
                //登录失败
                JOptionPane.showMessageDialog(this,result.getMessage());
            }
        }

    }
    /**
     * 点击注册按钮时调用该方法
     * @param e
     */
    private void registerBtnActionPerformed(ActionEvent e) {
        new RegisterView(this);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.UserView.LoginView");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        nameField = new JTextField();
        label3 = new JLabel();
        passwordField = new JPasswordField();
        label4 = new JLabel();
        roleBox = new JComboBox<>();
        buttonBar = new JPanel();
        loginBtn = new JButton();
        registerBtn = new JButton();

        //======== this ========
        setTitle(bundle.getString("LoginView.this.title"));
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
                label1.setText(bundle.getString("LoginView.label1.text"));
                label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 24));
                label1.setForeground(new Color(255, 51, 0));
                contentPanel.add(label1);
                label1.setBounds(100, 0, 330, 52);

                //---- label2 ----
                label2.setText(bundle.getString("LoginView.label2.text"));
                label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label2);
                label2.setBounds(75, 100, 60, 33);

                //---- nameField ----
                nameField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(nameField);
                nameField.setBounds(150, 105, 240, 30);

                //---- label3 ----
                label3.setText(bundle.getString("LoginView.label3.text"));
                label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label3);
                label3.setBounds(85, 165, 45, 32);

                //---- passwordField ----
                passwordField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
                contentPanel.add(passwordField);
                passwordField.setBounds(150, 170, 240, 30);

                //---- label4 ----
                label4.setText(bundle.getString("LoginView.label4.text"));
                label4.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label4);
                label4.setBounds(85, 230, 45, 28);

                //---- roleBox ----
                roleBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u7528\u6237",
                    "\u7ba1\u7406\u5458"
                }));
                roleBox.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(roleBox);
                roleBox.setBounds(150, 230, 105, 30);

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
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- loginBtn ----
                loginBtn.setText(bundle.getString("LoginView.loginBtn.text"));
                loginBtn.addActionListener(e -> loginBtnActionPerformed(e));
                buttonBar.add(loginBtn, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- registerBtn ----
                registerBtn.setText(bundle.getString("LoginView.registerBtn.text"));
                registerBtn.addActionListener(e -> registerBtnActionPerformed(e));
                buttonBar.add(registerBtn, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
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
    private JTextField nameField;
    private JLabel label3;
    private JPasswordField passwordField;
    private JLabel label4;
    private JComboBox<String> roleBox;
    private JPanel buttonBar;
    private JButton loginBtn;
    private JButton registerBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
