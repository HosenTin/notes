package com.djy.notes.view.UserView;

import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.controller.AnnounceController;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author djy
 */
public class AnnouncerView extends JDialog {
    private static Vector<String> columns = new Vector<>();

    static {
        columns.add("公告标题");
        columns.add("发布时间");
    }
    private TableModel tableModel = new TableModel();
    private int pageNow = 1;
    private int pageSize = 10;


    public AnnouncerView(Frame owner) {
        super(owner);
        initComponents();
        setBounds(100,100,1000,700);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        TableDTO tableDTO = loadTableDTO();
        announces.setModel(this.tableModel.buildModel(tableDTO.getData(),columns));
        showPreOrNextAuto(tableDTO.getTotalCount());
    }

    /**
     * 返回选中的行的笔记标题
     * @return
     */
    private String[] getSelectedAnnounceTitle() {
        int[] selectedRows = announces.getSelectedRows();
        String[] announceTitles = new String[selectedRows.length];
        for (int i=0;i<selectedRows.length;i++){
            int selectedIndex = selectedRows[i];
            Object announceTitleObj = announces.getValueAt(selectedIndex, 0);
            announceTitles[i] = String.valueOf(announceTitleObj.toString());
        }
        return announceTitles;
    }

    /**
     * 点击查看公告详情按钮 —— 查看公告详情
     * @param e
     */
    private void announceDetailBtnActionPerformed(ActionEvent e) {
        String[] announceTitles = getSelectedAnnounceTitle();
        if (announceTitles.length != 1) {
            JOptionPane.showMessageDialog(this,"一次只能查看一个公告详情");
            return;
        }
        new AnnounceDetailView(this,announceTitles[0]);
    }

    /**
     * 刷新表格数据
     */
    public void reloadTable(){
        TableDTO tableDTO = loadTableDTO();
        tableModel.updateModel(tableDTO.getData(),columns);
        showPreOrNextAuto(tableDTO.getTotalCount());
    }

    /**
     * 加载表格数据
     * @return
     */
    private TableDTO loadTableDTO() {
        AnnounceController announceController = new AnnounceController();
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNow(pageNow);
        pageRequest.setPageSize(pageSize);
        TableDTO dto =announceController.loadTableDTO(pageRequest);
        return dto;
    }

    /**
     * 控制显示上一页/下一页按钮
     * 第一页不显示上一页按钮，最后一页不显示下一页按钮，如果只有一页则不显示上一页和下一页的按钮
     * @param  totalCount:总条数
     */
    private void showPreOrNextAuto(int totalCount){
        if (pageNow ==1){
            preBtn.setVisible(false);
        } else {
            preBtn.setVisible(true);
        }
        //总页数
        int pageCount = 0;

        if (totalCount % pageSize == 0) {
            pageCount = totalCount / pageSize;
        } else {
            pageCount = totalCount/pageSize + 1;
        }
        if (pageNow == pageCount) {
            nextBtn.setVisible(false);
        }else {
            nextBtn.setVisible(true);
        }

    }

    private void preBtnActionPerformed(ActionEvent e) {
        this.setPageNow(this.getPageNow() - 1);
        reloadTable();
    }

    private void nextBtnActionPerformed(ActionEvent e) {
        this.setPageNow(this.getPageNow() + 1);
        reloadTable();
    }
    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.UserView.AnnouncerView");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        announces = new JTable();
        announceDetailBtn = new JButton();
        nextBtn = new JButton();
        preBtn = new JButton();

        //======== this ========
        setTitle(bundle.getString("AnnouncerView.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //======== scrollPane1 ========
                {

                    //---- announces ----
                    announces.setModel(new DefaultTableModel(
                        new Object[][] {
                            {null, null},
                            {null, null},
                        },
                        new String[] {
                            "\u516c\u544a\u6807\u9898", "\u53d1\u5e03\u65f6\u95f4"
                        }
                    ));
                    scrollPane1.setViewportView(announces);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(65, 0, 785, 455);

                //---- announceDetailBtn ----
                announceDetailBtn.setText(bundle.getString("AnnouncerView.announceDetailBtn.text"));
                announceDetailBtn.addActionListener(e -> announceDetailBtnActionPerformed(e));
                contentPanel.add(announceDetailBtn);
                announceDetailBtn.setBounds(770, 510, 142, announceDetailBtn.getPreferredSize().height);

                //---- nextBtn ----
                nextBtn.setText(bundle.getString("AnnouncerView.nextBtn.text"));
                nextBtn.addActionListener(e -> nextBtnActionPerformed(e));
                contentPanel.add(nextBtn);
                nextBtn.setBounds(505, 465, 78, 30);

                //---- preBtn ----
                preBtn.setText(bundle.getString("AnnouncerView.preBtn.text"));
                preBtn.addActionListener(e -> preBtnActionPerformed(e));
                contentPanel.add(preBtn);
                preBtn.setBounds(390, 465, 78, 30);

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
    private JScrollPane scrollPane1;
    private JTable announces;
    private JButton announceDetailBtn;
    private JButton nextBtn;
    private JButton preBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
