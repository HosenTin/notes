package com.djy.notes.view.UserView;

import com.djy.notes.bean.Msg;
import com.djy.notes.controller.NoteController;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author djy
 */
public class AddNoteView extends JDialog {
    private NoteManageView noteManageView;

    public AddNoteView(Frame owner) {
        super(owner);
        initComponents();
        noteManageView = (NoteManageView)owner;
        setBounds(100,100,1000,700);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * 点击确认新增笔记按钮 —— 新增笔记
     * @param e
     */
    private void addBtnActionPerformed(ActionEvent e) {

        String noteTitle = noteTitleField.getText();
        String noteContent = noteContentArea.getText();
        boolean overt = publicBtn.isSelected();

        NoteController noteController = new NoteController();
        Msg result = noteController.addNote(noteTitle,noteContent,overt);

        if (result.isSuccess()) {
            // 新建笔记成功
            JOptionPane.showMessageDialog(this,
                    result.getMessage());

            // 刷新表格
            noteManageView.reloadTable();

            // 让新增笔记页面销毁
            this.dispose();
        }else {
            // 新增笔记失败
            JOptionPane.showMessageDialog(this,
                    result.getMessage());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.UserView.AddNoteView");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        noteTitleField = new JTextField();
        scrollPane1 = new JScrollPane();
        noteContentArea = new JTextArea();
        addBtn = new JButton();
        publicBtn = new JRadioButton();
        privateBtn = new JRadioButton();
        label3 = new JLabel();

        //======== this ========
        setTitle(bundle.getString("AddNoteView.this.title"));
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
                label1.setText(bundle.getString("AddNoteView.label1.text"));
                label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label1);
                label1.setBounds(0, 0, 85, label1.getPreferredSize().height);

                //---- label2 ----
                label2.setText(bundle.getString("AddNoteView.label2.text"));
                label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label2);
                label2.setBounds(0, 40, 80, label2.getPreferredSize().height);
                contentPanel.add(noteTitleField);
                noteTitleField.setBounds(175, 0, 285, noteTitleField.getPreferredSize().height);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(noteContentArea);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(85, 45, 465, 360);

                //---- addBtn ----
                addBtn.setText(bundle.getString("AddNoteView.addBtn.text"));
                addBtn.addActionListener(e -> addBtnActionPerformed(e));
                contentPanel.add(addBtn);
                addBtn.setBounds(new Rectangle(new Point(505, 440), addBtn.getPreferredSize()));

                //---- publicBtn ----
                publicBtn.setText(bundle.getString("AddNoteView.publicBtn.text"));
                publicBtn.setSelected(true);
                contentPanel.add(publicBtn);
                publicBtn.setBounds(new Rectangle(new Point(85, 435), publicBtn.getPreferredSize()));

                //---- privateBtn ----
                privateBtn.setText(bundle.getString("AddNoteView.privateBtn.text"));
                contentPanel.add(privateBtn);
                privateBtn.setBounds(new Rectangle(new Point(155, 435), privateBtn.getPreferredSize()));

                //---- label3 ----
                label3.setText(bundle.getString("AddNoteView.label3.text"));
                label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
                contentPanel.add(label3);
                label3.setBounds(25, 435, 45, label3.getPreferredSize().height);

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

        //---- buttonGroup1 ----
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(publicBtn);
        buttonGroup1.add(privateBtn);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label2;
    private JTextField noteTitleField;
    private JScrollPane scrollPane1;
    private JTextArea noteContentArea;
    private JButton addBtn;
    private JRadioButton publicBtn;
    private JRadioButton privateBtn;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
