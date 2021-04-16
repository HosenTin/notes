package com.djy.notes.view.UserView;

import com.djy.notes.bean.Msg;
import com.djy.notes.controller.NoteController;
import com.djy.notes.entity.Note;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author djy
 */
public class UpdateNoteView extends JDialog {

    private int noteIdToUpdate;

    private NoteManageView noteManageView;

    public UpdateNoteView(Frame owner,String selectedNoteTitle) {
        super(owner);
        initComponents();

        noteManageView = (NoteManageView) owner;

        NoteController noteController = new NoteController();
        //通过笔记标题查询 —— 返回一个note对象
        Note note = noteController.selectByNoteTitle(selectedNoteTitle);
        //保存更新笔记的id
        noteIdToUpdate = note.getNoteId();
        //数据回显
        noteTitleField.setText(note.getNoteTitle());
        noteContentArea.setText(note.getNoteContent());

        if(note.isOvert()){
            //如果是公开的，publicBtn选中
            publicBtn.setSelected(true);
        }else{
            privateBtn.setSelected(true);
        }
        //设置窗体宽高
        setBounds(100,100,1000,700);
        //设置窗体居中
        setLocationRelativeTo(null);
        //使用插件时窗体默认不可见，设置成可见
        setVisible(true);
        //设置点击关闭按钮时程序结束
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void updateBtnActionPerformed(ActionEvent e) {

        NoteController noteController = new NoteController();

        //修改后的笔记标题和内容
        String noteTitle = noteTitleField.getText();
        String noteContent = noteContentArea.getText();

        Msg result = noteController.updateNote(noteTitle,noteContent,publicBtn.isSelected(),noteIdToUpdate);

        if (result.isSuccess()) {
            // 修改笔记成功
            JOptionPane.showMessageDialog(this, result.getMessage());

            // 刷新表格
            noteManageView.reloadTable();

            // 让修改笔记页面销毁
            this.dispose();
        }else {
            // 修改笔记失败
            JOptionPane.showMessageDialog(this, result.getMessage());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.UserView.UpdateNoteView");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        noteTitleField = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        publicBtn = new JRadioButton();
        privateBtn = new JRadioButton();
        scrollPane1 = new JScrollPane();
        noteContentArea = new JTextArea();
        buttonBar = new JPanel();
        updateBtn = new JButton();

        //======== this ========
        setTitle(bundle.getString("UpdateNoteView.this.title"));
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
                label1.setText(bundle.getString("UpdateNoteView.label1.text"));
                label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label1);
                label1.setBounds(0, 0, 85, 27);
                contentPanel.add(noteTitleField);
                noteTitleField.setBounds(200, 0, 285, 30);

                //---- label2 ----
                label2.setText(bundle.getString("UpdateNoteView.label2.text"));
                label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                contentPanel.add(label2);
                label2.setBounds(0, 40, 80, 27);

                //---- label3 ----
                label3.setText(bundle.getString("UpdateNoteView.label3.text"));
                label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
                contentPanel.add(label3);
                label3.setBounds(75, 390, 45, 22);

                //---- publicBtn ----
                publicBtn.setText(bundle.getString("UpdateNoteView.publicBtn.text"));
                publicBtn.setSelected(true);
                contentPanel.add(publicBtn);
                publicBtn.setBounds(130, 390, 55, 21);

                //---- privateBtn ----
                privateBtn.setText(bundle.getString("UpdateNoteView.privateBtn.text"));
                contentPanel.add(privateBtn);
                privateBtn.setBounds(200, 390, 60, 21);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(noteContentArea);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(110, 55, 525, 325);

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

                //---- updateBtn ----
                updateBtn.setText(bundle.getString("UpdateNoteView.updateBtn.text"));
                updateBtn.addActionListener(e -> updateBtnActionPerformed(e));
                buttonBar.add(updateBtn, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
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
    private JTextField noteTitleField;
    private JLabel label2;
    private JLabel label3;
    private JRadioButton publicBtn;
    private JRadioButton privateBtn;
    private JScrollPane scrollPane1;
    private JTextArea noteContentArea;
    private JPanel buttonBar;
    private JButton updateBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
