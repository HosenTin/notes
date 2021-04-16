package com.djy.notes.view.AdminView;

import com.djy.notes.bean.Msg;
import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.controller.UserController;
import com.djy.notes.service.impl.UserServiceImpl;
import com.djy.notes.service.inter.UserService;
import com.djy.notes.view.UserView.TableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author djy
 */
public class AdminManagerView extends JFrame {
    private static Vector<String> columns = new Vector<>();
    //表格的列名
    static {
        columns.add("用户名");
        columns.add("用户密码");
        columns.add("电话号码");
        columns.add("邮箱");
        columns.add("个性签名");
        columns.add("是否拉黑");
    }
    private TableModel tableModel = new TableModel();
    private int pageNow = 1;
    private int pageSize = 10;

    public AdminManagerView() {
        initComponents();

        //表格数据初始化
        TableDTO tableDTO = loadTableDTO();
        //关联表格和表格的数据模型
        users.setModel(this.tableModel.buildModel(tableDTO.getData(),columns));
        showPreOrNextAuto(tableDTO.getTotalCount());

        setBounds(100,100,2200,1500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        UserController userController = new UserController();
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNow(pageNow);
        pageRequest.setPageSize(pageSize);
        TableDTO dto =userController.loadTableDTO(pageRequest);
        return dto;
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

    /**
     * 点击发布公告按钮 —— 写公告
     * @param e
     */
    private void publishBtnActionPerformed(ActionEvent e) {
        new AddAnnouncerView(this);
    }

    /**
     * 返回选中的行的笔记标题
     * @return
     */
    private String[] getSelectedUserName() {
        int[] selectedRows = users.getSelectedRows();
        String[] userNames = new String[selectedRows.length];
        for (int i=0;i<selectedRows.length;i++){
            int selectedIndex = selectedRows[i];
            Object userNameObj = users.getValueAt(selectedIndex, 0);
            userNames[i] = String.valueOf(userNameObj.toString());
        }
        return userNames;
    }

    /**
     * 点击拉黑按钮 —— 拉黑用户
     * @param e
     */
    private void blackBtnActionPerformed(ActionEvent e) {
        String[] userNames = getSelectedUserName();
        if (userNames.length == 0) {
            JOptionPane.showMessageDialog(this,"请选择要拉黑的用户");
            return;
        }
        int option = JOptionPane.showConfirmDialog(this,"确认要拉黑选中的用户吗？");
        if(option == JOptionPane.YES_OPTION){
            //确认拉黑
            UserService userService = new UserServiceImpl();
            Msg result = userService.blackUser(userNames);
            if (result.isSuccess()) {
                // 拉黑用户成功
                JOptionPane.showMessageDialog(this, result.getMessage());

                // 刷新表格
                reloadTable();

            }else {
                // 拉黑用户失败
                JOptionPane.showMessageDialog(this, result.getMessage());
            }
        }
    }

    /**
     * 点击取消拉黑按钮 —— 取消拉黑用户
     * @param e
     */
    private void notblackBtnActionPerformed(ActionEvent e) {
        String[] userNames = getSelectedUserName();
        if (userNames.length == 0) {
            JOptionPane.showMessageDialog(this,"请选择要取消拉黑的用户");
            return;
        }
        int option = JOptionPane.showConfirmDialog(this,"确认要取消拉黑选中的用户吗？");
        if(option == JOptionPane.YES_OPTION){
            //确认拉黑
            UserService userService = new UserServiceImpl();
            Msg result = userService.notblackUser(userNames);
            if (result.isSuccess()) {
                // 取消拉黑用户成功
                JOptionPane.showMessageDialog(this, result.getMessage());

                // 刷新表格
                reloadTable();

            }else {
                // 取消拉黑用户失败
                JOptionPane.showMessageDialog(this, result.getMessage());
            }
        }
    }

    /**
     * 点击查看笔记信息按钮 —— 笔记信息界面
     * @param e
     */
    private void notesInfoBtnActionPerformed(ActionEvent e) {
        new AdminNotes(this);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.AdminView.AdminManagerView");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        publishBtn = new JButton();
        scrollPane1 = new JScrollPane();
        users = new JTable();
        notesInfoBtn = new JButton();
        notblackBtn = new JButton();
        blackBtn = new JButton();
        preBtn = new JButton();
        nextBtn = new JButton();

        //======== this ========
        setTitle(bundle.getString("AdminManagerView.this.title"));
        Container contentPane = getContentPane();

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- publishBtn ----
                publishBtn.setText(bundle.getString("AdminManagerView.publishBtn.text"));
                publishBtn.addActionListener(e -> publishBtnActionPerformed(e));
                contentPanel.add(publishBtn);
                publishBtn.setBounds(0, 0, publishBtn.getPreferredSize().width, 35);

                //======== scrollPane1 ========
                {

                    //---- users ----
                    users.setModel(new DefaultTableModel(
                        new Object[][] {
                            {null, null, "", null, null, null},
                            {null, null, null, null, null, null},
                        },
                        new String[] {
                            "\u7528\u6237\u540d", "\u7528\u6237\u5bc6\u7801", "\u624b\u673a\u53f7\u7801", "\u90ae\u7bb1", "\u4e2a\u6027\u7b7e\u540d", "\u7528\u6237\u72b6\u6001(false:\u9ed1\u540d\u5355\u7528\u6237)"
                        }
                    ));
                    scrollPane1.setViewportView(users);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(0, 50, 985, 415);

                //---- notesInfoBtn ----
                notesInfoBtn.setText(bundle.getString("AdminManagerView.notesInfoBtn.text"));
                notesInfoBtn.addActionListener(e -> notesInfoBtnActionPerformed(e));
                contentPanel.add(notesInfoBtn);
                notesInfoBtn.setBounds(810, 0, 175, notesInfoBtn.getPreferredSize().height);

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

            //---- notblackBtn ----
            notblackBtn.setText(bundle.getString("AdminManagerView.notblackBtn.text"));
            notblackBtn.addActionListener(e -> notblackBtnActionPerformed(e));

            //---- blackBtn ----
            blackBtn.setText(bundle.getString("AdminManagerView.blackBtn.text"));
            blackBtn.addActionListener(e -> blackBtnActionPerformed(e));

            //---- preBtn ----
            preBtn.setText(bundle.getString("AdminManagerView.preBtn.text"));
            preBtn.addActionListener(e -> preBtnActionPerformed(e));

            //---- nextBtn ----
            nextBtn.setText(bundle.getString("AdminManagerView.nextBtn.text"));
            nextBtn.addActionListener(e -> nextBtnActionPerformed(e));

            GroupLayout dialogPaneLayout = new GroupLayout(dialogPane);
            dialogPane.setLayout(dialogPaneLayout);
            dialogPaneLayout.setHorizontalGroup(
                dialogPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(GroupLayout.Alignment.LEADING, dialogPaneLayout.createSequentialGroup()
                        .addGap(403, 403, 403)
                        .addComponent(preBtn)
                        .addGap(42, 42, 42)
                        .addComponent(nextBtn)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                        .addComponent(blackBtn)
                        .addGap(31, 31, 31)
                        .addComponent(notblackBtn, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(GroupLayout.Alignment.LEADING, dialogPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(8, Short.MAX_VALUE))
            );
            dialogPaneLayout.setVerticalGroup(
                dialogPaneLayout.createParallelGroup()
                    .addGroup(dialogPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(dialogPaneLayout.createParallelGroup()
                            .addGroup(dialogPaneLayout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(dialogPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(blackBtn)
                                    .addComponent(notblackBtn))
                                .addGap(519, 519, 519))
                            .addGroup(dialogPaneLayout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dialogPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(nextBtn)
                                    .addComponent(preBtn))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(dialogPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(dialogPane, GroupLayout.PREFERRED_SIZE, 531, GroupLayout.PREFERRED_SIZE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JButton publishBtn;
    private JScrollPane scrollPane1;
    private JTable users;
    private JButton notesInfoBtn;
    private JButton notblackBtn;
    private JButton blackBtn;
    private JButton preBtn;
    private JButton nextBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
