package com.djy.notes.view.AdminView;

import com.djy.notes.controller.NoteController;
import com.djy.notes.entity.Note;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * @author djy
 */
public class AdminNoteDetailView extends JDialog {
    public AdminNoteDetailView(Window owner,String selectedNoteTitle) {
        super(owner);
        initComponents();

        NoteController noteController = new NoteController();
        Note note = noteController.selectByNoteTitle(selectedNoteTitle);

        setTitle("正在查看："+note.getNoteTitle());

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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.AdminView.AdminNoteDetailView");
        label1 = new JLabel();
        noteTitleField = new JTextField();
        label2 = new JLabel();
        noteContentArea = new JTextArea();

        //======== this ========
        setTitle(bundle.getString("AdminNoteDetailView.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText(bundle.getString("AdminNoteDetailView.label1.text"));
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
        contentPane.add(label1);
        label1.setBounds(35, 30, 85, 27);

        //---- noteTitleField ----
        noteTitleField.setEnabled(false);
        contentPane.add(noteTitleField);
        noteTitleField.setBounds(215, 30, 285, 30);

        //---- label2 ----
        label2.setText(bundle.getString("AdminNoteDetailView.label2.text"));
        label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
        contentPane.add(label2);
        label2.setBounds(35, 80, 80, 27);

        //---- noteContentArea ----
        noteContentArea.setEnabled(false);
        contentPane.add(noteContentArea);
        noteContentArea.setBounds(135, 85, 473, 343);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField noteTitleField;
    private JLabel label2;
    private JTextArea noteContentArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
