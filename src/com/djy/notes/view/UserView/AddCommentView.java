package com.djy.notes.view.UserView;

import com.djy.notes.bean.Msg;
import com.djy.notes.controller.CommentController;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author djy
 */
public class AddCommentView extends JDialog {
    //定义一个全局变量存放获取到的笔记标题
    private String selectedNoterTitler;
    private CommentView commentView;

    public AddCommentView(Window owner,String selectedNoterTitle) {
        super(owner);
        initComponents();
        commentView = (CommentView) owner;
        selectedNoterTitler = selectedNoterTitle;
        setBounds(100,100,1000,700);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * 点击发布评论按钮 —— 发布评论
     * @param e
     */

    private void publishCommentBtnActionPerformed(ActionEvent e) {
        String newComment = commentArea.getText();

        CommentController commentController = new CommentController();

        Msg result = commentController.addComment(newComment,selectedNoterTitler);

        if (result.isSuccess()) {
            // 发布评论成功
            JOptionPane.showMessageDialog(this,result.getMessage());
            // 刷新表格
            commentView.reloadTable();
            // 让发布评论页面销毁
            this.dispose();
        }else {
            // 发布评论失败
            JOptionPane.showMessageDialog(this,result.getMessage());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.UserView.AddCommentView");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        publishCommentBtn = new JButton();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        commentArea = new JTextArea();

        //======== this ========
        setTitle(bundle.getString("AddCommentView.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- publishCommentBtn ----
                publishCommentBtn.setText(bundle.getString("AddCommentView.publishCommentBtn.text"));
                publishCommentBtn.addActionListener(e -> publishCommentBtnActionPerformed(e));
                contentPanel.add(publishCommentBtn);
                publishCommentBtn.setBounds(825, 410, 120, publishCommentBtn.getPreferredSize().height);

                //---- label1 ----
                label1.setText(bundle.getString("AddCommentView.label1.text"));
                label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
                contentPanel.add(label1);
                label1.setBounds(155, 15, 85, 45);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(commentArea);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(240, 30, 540, 305);

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
    private JButton publishCommentBtn;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTextArea commentArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
