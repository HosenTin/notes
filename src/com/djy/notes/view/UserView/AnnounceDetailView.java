package com.djy.notes.view.UserView;

import com.djy.notes.controller.AnnounceController;
import com.djy.notes.entity.Announce;


import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * @author djy
 */
public class AnnounceDetailView extends JDialog {
    public AnnounceDetailView(Window owner,String selectedAnnounceTitle) {
        super(owner);
        initComponents();
        AnnounceController announceController = new AnnounceController();
        Announce announce = announceController.selectByAnnounceTitle(selectedAnnounceTitle);

        setTitle("正在查看："+announce.getAnnounceTitle()+" 的详情");
        /**
         * 获取公告标题和内容，设置在对应的位置
         */
        announceTitleField.setText(announce.getAnnounceTitle());
        announceContentArea.setText(announce.getAnnounceContent());

        setBounds(100,100,1000,700);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.UserView.AnnounceDetailView");
        label1 = new JLabel();
        announceTitleField = new JTextField();
        label2 = new JLabel();
        announceContentArea = new JTextArea();

        //======== this ========
        setTitle(bundle.getString("AnnounceDetailView.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText(bundle.getString("AnnounceDetailView.label1.text"));
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
        contentPane.add(label1);
        label1.setBounds(120, 25, 85, 35);

        //---- announceTitleField ----
        announceTitleField.setEnabled(false);
        contentPane.add(announceTitleField);
        announceTitleField.setBounds(345, 30, 285, 30);

        //---- label2 ----
        label2.setText(bundle.getString("AnnounceDetailView.label2.text"));
        label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
        contentPane.add(label2);
        label2.setBounds(120, 100, 80, 35);

        //---- announceContentArea ----
        announceContentArea.setEnabled(false);
        contentPane.add(announceContentArea);
        announceContentArea.setBounds(245, 110, 510, 410);

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
    private JTextField announceTitleField;
    private JLabel label2;
    private JTextArea announceContentArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
