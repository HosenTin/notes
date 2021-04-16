package com.djy.notes.view.AdminView;

import com.djy.notes.bean.Msg;
import com.djy.notes.controller.AnnounceController;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author djy
 */
public class AddAnnouncerView extends JDialog {

    public AddAnnouncerView(Frame owner) {
        super(owner);
        initComponents();

        setBounds(100,100,1000,700);       //设置窗体宽高
        setLocationRelativeTo(null);                        //设置窗体居中
        setVisible(true);                                   //使用插件时窗体默认不可见，设置成可见
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);     //设置点击关闭按钮时程序结束
    }

    /**
     * 点击发布公告按钮 —— 发布公告
     * @param e
     */
    private void publishBtnActionPerformed(ActionEvent e) {
        String announceTitle = announceTitleField.getText();
        String announceContent = announceContentArea.getText();

        AnnounceController announceController = new AnnounceController();
        Msg result = announceController.addAnnounce(announceTitle,announceContent);

        if (result.isSuccess()) {
            // 新建公告成功
            JOptionPane.showMessageDialog(this,result.getMessage());
            // 让新增公告页面销毁
            this.dispose();
        }else {
            // 新增笔记失败
            JOptionPane.showMessageDialog(this,result.getMessage());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.AdminView.AddAnnouncerView");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        announceTitleField = new JTextField();
        label2 = new JLabel();
        announceContentArea = new JTextArea();
        label1 = new JLabel();
        buttonBar = new JPanel();
        publishBtn = new JButton();

        //======== this ========
        setTitle(bundle.getString("AddAnnouncerView.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);
                contentPanel.add(announceTitleField);
                announceTitleField.setBounds(215, 45, 285, 30);

                //---- label2 ----
                label2.setText(bundle.getString("AddAnnouncerView.label2.text"));
                label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label2);
                label2.setBounds(20, 120, 80, 35);
                contentPanel.add(announceContentArea);
                announceContentArea.setBounds(145, 130, 463, 350);

                //---- label1 ----
                label1.setText(bundle.getString("AddAnnouncerView.label1.text"));
                label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label1);
                label1.setBounds(20, 40, 85, 35);

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

                //---- publishBtn ----
                publishBtn.setText(bundle.getString("AddAnnouncerView.publishBtn.text"));
                publishBtn.addActionListener(e -> publishBtnActionPerformed(e));
                buttonBar.add(publishBtn, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JTextField announceTitleField;
    private JLabel label2;
    private JTextArea announceContentArea;
    private JLabel label1;
    private JPanel buttonBar;
    private JButton publishBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
