package com.djy.notes.view.UserView;

import com.djy.notes.bean.Msg;
import com.djy.notes.controller.UserController;
import com.djy.notes.entity.User;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author djy
 */
public class ChangeInfoView extends JDialog {
    public ChangeInfoView(Frame owner,String selectedUserName) {
        super(owner);
        initComponents();

        UserController userController = new UserController();
        //通过用户名查询 —— 返回一个user对象
        User user = userController.selectByUserName(selectedUserName);

        //数据回显
        pwdField.setText(user.getPassword());
        telephoneField.setText(user.getTelephone());
        emailField.setText(user.getEmail());
        signField.setText(user.getSign());
        //设置窗体宽高
        setBounds(100,100,1200,700);
        //设置窗体居中
        setLocationRelativeTo(null);
        //使用插件时窗体默认不可见，设置成可见
        setVisible(true);
        //销毁当前注册弹窗
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void updateUserBtnActionPerformed(ActionEvent e) {
        UserController userController = new UserController();

        /**
         * 获取用户个人信息
         * 密码用文本框显式展示
         */
        String password = pwdField.getText();
        String telephone =telephoneField.getText();
        String email =emailField.getText();
        String sign =signField.getText();

        //判断用户输入用户名或密码是否为空
        if(password == null||"".equals(password)){
            JOptionPane.showMessageDialog(ChangeInfoView.this,"密码不能为空");
            return;
        }

        Msg result = userController.changeInfo(password,telephone,email,sign);

        if (result.isSuccess()) {
            // 修改成功
            JOptionPane.showMessageDialog(this,
                    result.getMessage());
            // 让修改页面销毁
            this.dispose();
        }else {
            // 修改失败
            JOptionPane.showMessageDialog(this,
                    result.getMessage());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.UserView.ChangeInfoView");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        pwdField = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        telephoneField = new JTextField();
        label5 = new JLabel();
        emailField = new JTextField();
        label6 = new JLabel();
        signField = new JTextField();
        label1 = new JLabel();
        updateUserBtn = new JButton();

        //======== this ========
        setTitle(bundle.getString("ChangeInfoView.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- pwdField ----
                pwdField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(pwdField);
                pwdField.setBounds(325, 65, 250, 30);

                //---- label3 ----
                label3.setText(bundle.getString("ChangeInfoView.label3.text"));
                label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label3);
                label3.setBounds(255, 65, 40, 22);

                //---- label4 ----
                label4.setText(bundle.getString("ChangeInfoView.label4.text"));
                label4.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label4);
                label4.setBounds(235, 130, 80, 22);

                //---- telephoneField ----
                telephoneField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(telephoneField);
                telephoneField.setBounds(325, 130, 250, 30);

                //---- label5 ----
                label5.setText(bundle.getString("ChangeInfoView.label5.text"));
                label5.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label5);
                label5.setBounds(235, 200, 85, 22);

                //---- emailField ----
                emailField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(emailField);
                emailField.setBounds(325, 200, 250, 30);

                //---- label6 ----
                label6.setText(bundle.getString("ChangeInfoView.label6.text"));
                label6.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label6);
                label6.setBounds(235, 265, 85, 27);

                //---- signField ----
                signField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(signField);
                signField.setBounds(325, 265, 330, 30);

                //---- label1 ----
                label1.setText(bundle.getString("ChangeInfoView.label1.text"));
                label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 26));
                contentPanel.add(label1);
                label1.setBounds(340, 0, 185, 30);

                //---- updateUserBtn ----
                updateUserBtn.setText(bundle.getString("ChangeInfoView.updateUserBtn.text"));
                updateUserBtn.addActionListener(e -> updateUserBtnActionPerformed(e));
                contentPanel.add(updateUserBtn);
                updateUserBtn.setBounds(770, 460, 98, updateUserBtn.getPreferredSize().height);

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
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JTextField pwdField;
    private JLabel label3;
    private JLabel label4;
    private JTextField telephoneField;
    private JLabel label5;
    private JTextField emailField;
    private JLabel label6;
    private JTextField signField;
    private JLabel label1;
    private JButton updateUserBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
