package com.djy.notes.view.UserView;

import com.djy.notes.bean.Msg;
import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.controller.NoteController;
import com.djy.notes.service.impl.NoteServiceImpl;
import com.djy.notes.service.impl.UserServiceImpl;
import com.djy.notes.service.inter.NoteService;
import com.djy.notes.service.inter.UserService;
import com.djy.notes.util.FileUtil;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author djy
 */
public class NoteManageView extends JFrame {

    private static Vector<String> columns = new Vector<>();
    //表格的列名
    static {
        columns.add("笔记标题");
        columns.add("创建用户");
        columns.add("发布时间");
    }
    private TableModel tableModel = new TableModel();
    private int pageNow = 1;
    private int pageSize = 10;

    public NoteManageView() {
        //初始化
        initComponents();
        // 设置title
        setTitle(getTitle()+",当前登录用户:"+ FileUtil.getUserName());
        // 添加菜单栏
        JMenu jMenu = new JMenu("功能列表");
        jMenuBar.add(jMenu);

        JMenuItem jMenuItem = new JMenuItem("修改个人信息");
        jMenu.add(jMenuItem);
        jMenuItem.addActionListener(e ->{
            //查看/修改用户个人信息窗口
            new ChangeInfoView(NoteManageView.this,FileUtil.getUserName());
        });

        //表格数据初始化
        TableDTO tableDTO = loadTableDTO();
        //关联表格和表格的数据模型
        notes.setModel(this.tableModel.buildModel(tableDTO.getData(),columns));
        showPreOrNextAuto(tableDTO.getTotalCount());

        //设置窗体宽高
        setBounds(100,100,1500,800);
        //设置窗体居中
        setLocationRelativeTo(null);
        //使用插件时窗体默认不可见，设置成可见
        setVisible(true);
        //设置点击关闭按钮时程序结束
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * 返回用户是否被拉黑（布尔值）
     */
    private Boolean selectedBlack(String userName){

        UserService userService = new UserServiceImpl();

        Boolean bool = userService.selectedBlack(userName);

        return bool;
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
        NoteController noteController = new NoteController();
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNow(pageNow);
        pageRequest.setPageSize(pageSize);
        TableDTO dto =noteController.loadTableDTOD(pageRequest);
        return dto;
    }

    /**
     * 点击新增笔记按钮 —— 写笔记
     * 在新增笔记时判断用户是否被拉黑（调用selectedBlack方法）
     * @param e
     */
    private void addBtnActionPerformed(ActionEvent e) {
        if(selectedBlack(FileUtil.getUserName()) == true) {
            new AddNoteView(this);
        }else{
            JOptionPane.showMessageDialog(NoteManageView.this,"您已被拉黑，无法新增笔记");
        }
    }

    /**
     * 点击修改笔记按钮——修改笔记
     * @param e
     */
    private void updateBtnActionPerformed(ActionEvent e) {
        /**
         * 可能选择多个笔记标题
         */
        String[] noteTitles = getSelectedNoteTitle();
        if (noteTitles.length != 1) {
            JOptionPane.showMessageDialog(this,"一次只能修改一个笔记");
            return;
        }
        new UpdateNoteView(this, noteTitles[0]);

    }

    /**
     * 返回选中的行的笔记标题
     * @return
     */
    private String[] getSelectedNoteTitle() {
        //获取选中的行数
        int[] selectedRows = notes.getSelectedRows();
        String[] noteTitles = new String[selectedRows.length];
        for (int i=0;i<selectedRows.length;i++){
            int selectedIndex = selectedRows[i];
            //获取笔记标题列的值
            Object noteTitleObj = notes.getValueAt(selectedIndex, 0);
            noteTitles[i] = String.valueOf(noteTitleObj.toString());
        }
        return noteTitles;
    }

    /**
     * 点击删除笔记按钮——删除笔记
     * @param e
     */
    private void deleteBtnActionPerformed(ActionEvent e) {
        String[] noteTitles = getSelectedNoteTitle();
        if (noteTitles.length == 0) {
            JOptionPane.showMessageDialog(this,"请选择要删除的笔记");
            return;
        }
        int option = JOptionPane.showConfirmDialog(this,"确认要删除选中的笔记吗？");
        if(option == JOptionPane.YES_OPTION){
            //确认删除
            NoteService noteService = new NoteServiceImpl();
            Msg result = noteService.deleteNote(noteTitles);
            if (result.isSuccess()) {
                // 删除笔记成功
                JOptionPane.showMessageDialog(this, result.getMessage());
                // 刷新表格
                reloadTable();
            }else {
                // 新增笔记失败
                JOptionPane.showMessageDialog(this, result.getMessage());
            }
        }
    }

    /**
     * 点击查询按钮
     * @param e
     */
    private void searchBtnActionPerformed(ActionEvent e) {
        new SearchNoteView(this);
    }

    /**
     * 点击查看笔记详情按钮——查看笔记详情
     * @param e
     */
    private void detailBtnActionPerformed(ActionEvent e) {
        String[] noteTitles = getSelectedNoteTitle();
        if (noteTitles.length != 1) {
            JOptionPane.showMessageDialog(this,"一次只能查看一个笔记详情");
            return;
        }
        new NoteDetailView(this,noteTitles[0]);
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

    /**
     * 点击公告按钮——查看公告界面
     * @param e
     */
    private void announceBtnActionPerformed(ActionEvent e) {
        new AnnouncerView(this);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.UserView.NoteManageView");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        jMenuBar = new JMenuBar();
        announceBtn = new JButton();
        addBtn = new JButton();
        deleteBtn = new JButton();
        updateBtn = new JButton();
        searchBtn = new JButton();
        detailBtn = new JButton();
        scrollPane1 = new JScrollPane();
        notes = new JTable();
        preBtn = new JButton();
        nextBtn = new JButton();

        //======== this ========
        setTitle(bundle.getString("NoteManageView.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);
                contentPanel.add(jMenuBar);
                jMenuBar.setBounds(0, 0, 1050, 20);

                //---- announceBtn ----
                announceBtn.setText(bundle.getString("NoteManageView.announceBtn.text"));
                announceBtn.addActionListener(e -> announceBtnActionPerformed(e));
                contentPanel.add(announceBtn);
                announceBtn.setBounds(45, 25, 78, 30);

                //---- addBtn ----
                addBtn.setText(bundle.getString("NoteManageView.addBtn.text"));
                addBtn.addActionListener(e -> addBtnActionPerformed(e));
                contentPanel.add(addBtn);
                addBtn.setBounds(140, 25, 88, 30);

                //---- deleteBtn ----
                deleteBtn.setText(bundle.getString("NoteManageView.deleteBtn.text"));
                deleteBtn.addActionListener(e -> deleteBtnActionPerformed(e));
                contentPanel.add(deleteBtn);
                deleteBtn.setBounds(245, 25, 95, 30);

                //---- updateBtn ----
                updateBtn.setText(bundle.getString("NoteManageView.updateBtn.text"));
                updateBtn.addActionListener(e -> updateBtnActionPerformed(e));
                contentPanel.add(updateBtn);
                updateBtn.setBounds(355, 25, 95, 30);

                //---- searchBtn ----
                searchBtn.setText(bundle.getString("NoteManageView.searchBtn.text"));
                searchBtn.addActionListener(e -> searchBtnActionPerformed(e));
                contentPanel.add(searchBtn);
                searchBtn.setBounds(730, 25, 65, 30);

                //---- detailBtn ----
                detailBtn.setText(bundle.getString("NoteManageView.detailBtn.text"));
                detailBtn.addActionListener(e -> detailBtnActionPerformed(e));
                contentPanel.add(detailBtn);
                detailBtn.setBounds(840, 25, 175, 30);

                //======== scrollPane1 ========
                {

                    //---- notes ----
                    notes.setModel(new DefaultTableModel(
                        new Object[][] {
                            {null, null, null},
                            {null, null, null},
                        },
                        new String[] {
                            "\u7b14\u8bb0\u6807\u9898", "\u521b\u5efa\u7528\u6237", "\u53d1\u5e03\u65f6\u95f4"
                        }
                    ));
                    scrollPane1.setViewportView(notes);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(35, 80, 985, 430);

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

            //---- preBtn ----
            preBtn.setText(bundle.getString("NoteManageView.preBtn.text"));
            preBtn.addActionListener(e -> preBtnActionPerformed(e));

            //---- nextBtn ----
            nextBtn.setText(bundle.getString("NoteManageView.nextBtn.text"));
            nextBtn.addActionListener(e -> nextBtnActionPerformed(e));

            GroupLayout dialogPaneLayout = new GroupLayout(dialogPane);
            dialogPane.setLayout(dialogPaneLayout);
            dialogPaneLayout.setHorizontalGroup(
                dialogPaneLayout.createParallelGroup()
                    .addGroup(dialogPaneLayout.createSequentialGroup()
                        .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 1054, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(dialogPaneLayout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(preBtn)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nextBtn)
                        .addGap(30, 30, 30))
            );
            dialogPaneLayout.setVerticalGroup(
                dialogPaneLayout.createParallelGroup()
                    .addGroup(dialogPaneLayout.createSequentialGroup()
                        .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(dialogPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(nextBtn)
                            .addComponent(preBtn))
                        .addGap(22, 22, 22))
            );
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JMenuBar jMenuBar;
    private JButton announceBtn;
    private JButton addBtn;
    private JButton deleteBtn;
    private JButton updateBtn;
    private JButton searchBtn;
    private JButton detailBtn;
    private JScrollPane scrollPane1;
    private JTable notes;
    private JButton preBtn;
    private JButton nextBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
