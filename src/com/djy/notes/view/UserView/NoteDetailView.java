package com.djy.notes.view.UserView;

import java.awt.event.*;
import com.djy.notes.controller.NoteController;
import com.djy.notes.entity.Note;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author djy
 */
public class NoteDetailView extends JDialog {

    //定义一个全局变量存放获取到的笔记标题
    private String noterTitle ;

    public NoteDetailView(Window owner,String selectedNoteTitle) {
        super(owner);
        initComponents();

        NoteController noteController = new NoteController();
        Note note = noteController.selectByNoteTitle(selectedNoteTitle);

        setTitle("正在查看："+note.getNoteTitle());

        noterTitle = note.getNoteTitle();

        noteTitleField.setText(note.getNoteTitle());
        noteContentArea.setText(note.getNoteContent());

        //设置窗体宽高
        setBounds(100,100,1000,700);
        //设置窗体居中
        setLocationRelativeTo(null);
        //使用插件时窗体默认不可见，设置成可见
        setVisible(true);
        //设置点击关闭按钮时程序结束
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    /**
     * 点击查看评论按钮——查看评论
     */
    private void commentBtnActionPerformed(ActionEvent e) {
        new CommentView(this,noterTitle);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.UserView.NoteDetailView");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        contentPanel2 = new JPanel();
        label1 = new JLabel();
        noteTitleField = new JTextField();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        noteContentArea = new JTextArea();
        commentBtn = new JButton();

        //======== this ========
        setTitle(bundle.getString("NoteDetailView.this.title"));
        Container contentPane = getContentPane();

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new GridBagLayout());
            ((GridBagLayout)dialogPane.getLayout()).columnWidths = new int[] {0, 0};
            ((GridBagLayout)dialogPane.getLayout()).rowHeights = new int[] {0, 0, 0};
            ((GridBagLayout)dialogPane.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
            ((GridBagLayout)dialogPane.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0E-4};

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //======== contentPanel2 ========
                {
                    contentPanel2.setLayout(null);

                    //---- label1 ----
                    label1.setText(bundle.getString("NoteDetailView.label1.text"));
                    label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                    contentPanel2.add(label1);
                    label1.setBounds(25, 15, 85, 27);

                    //---- noteTitleField ----
                    noteTitleField.setEnabled(false);
                    contentPanel2.add(noteTitleField);
                    noteTitleField.setBounds(205, 15, 285, 30);

                    //---- label2 ----
                    label2.setText(bundle.getString("NoteDetailView.label2.text"));
                    label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                    contentPanel2.add(label2);
                    label2.setBounds(25, 65, 80, 27);

                    //======== scrollPane1 ========
                    {

                        //---- noteContentArea ----
                        noteContentArea.setEnabled(false);
                        scrollPane1.setViewportView(noteContentArea);
                    }
                    contentPanel2.add(scrollPane1);
                    scrollPane1.setBounds(125, 70, 475, 345);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < contentPanel2.getComponentCount(); i++) {
                            Rectangle bounds = contentPanel2.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = contentPanel2.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        contentPanel2.setMinimumSize(preferredSize);
                        contentPanel2.setPreferredSize(preferredSize);
                    }
                }
                contentPanel.add(contentPanel2);
                contentPanel2.setBounds(5, 0, 615, 425);

                //---- commentBtn ----
                commentBtn.setText(bundle.getString("NoteDetailView.commentBtn.text"));
                commentBtn.addActionListener(e -> commentBtnActionPerformed(e));
                contentPanel.add(commentBtn);
                commentBtn.setBounds(new Rectangle(new Point(530, 425), commentBtn.getPreferredSize()));

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
            dialogPane.add(contentPanel, new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(dialogPane, GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(dialogPane, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel contentPanel2;
    private JLabel label1;
    private JTextField noteTitleField;
    private JLabel label2;
    private JScrollPane scrollPane1;
    private JTextArea noteContentArea;
    private JButton commentBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
