package com.djy.notes.view.AdminView;

import com.djy.notes.bean.Msg;
import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.controller.NoteController;
import com.djy.notes.service.impl.NoteServiceImpl;
import com.djy.notes.service.inter.NoteService;
import com.djy.notes.view.UserView.NoteDetailView;
import com.djy.notes.view.UserView.TableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author djy
 */
public class AdminNotes extends JDialog {
    private static Vector<String> columns = new Vector<>();
    static {
        columns.add("笔记标题");
        columns.add("创建用户");
        columns.add("发布时间");
    }
    private TableModel tableModel = new TableModel();
    private int pageNow = 1;
    private int pageSize = 10;
    public AdminNotes(Window owner) {
        super(owner);
        initComponents();

        //表格数据初始化
        TableDTO tableDTO = loadTableDTO();
        //关联表格和表格的数据模型
        notes.setModel(this.tableModel.buildModel(tableDTO.getData(),columns));
        showPreOrNextAuto(tableDTO.getTotalCount());

        setBounds(100,100,1500,800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
        TableDTO dto =noteController.loadTableDTO(pageRequest);
        return dto;
    }

    /**
     * 返回选中的行的笔记标题
     * @return
     */
    private String[] getSelectedNoteTitle() {
        int[] selectedRows = notes.getSelectedRows();
        String[] noteTitles = new String[selectedRows.length];
        for (int i=0;i<selectedRows.length;i++){
            int selectedIndex = selectedRows[i];
            Object noteTitleObj = notes.getValueAt(selectedIndex, 0);
            noteTitles[i] = String.valueOf(noteTitleObj.toString());
        }
        return noteTitles;
    }

    /**
     * 点击查看笔记信息按钮，查看笔记信息
     * @param e
     */
    private void noteDetailBtnActionPerformed(ActionEvent e) {
        String[] noteTitles = getSelectedNoteTitle();
        if (noteTitles.length != 1) {
            JOptionPane.showMessageDialog(this,"一次只能查看一个笔记详情");
            return;
        }
        new AdminNoteDetailView(this,noteTitles[0]);
    }

    /**
     * 点击删除笔记 —— 对违规笔记进行删除
     * @param e
     */
    private void deleteNoteBtnActionPerformed(ActionEvent e) {
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
                JOptionPane.showMessageDialog(this,
                        result.getMessage());

                // 刷新表格
                reloadTable();

            }else {
                // 新增笔记失败
                JOptionPane.showMessageDialog(this,
                        result.getMessage());
            }
        }
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
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.AdminView.AdminNotes");
        preBtn = new JButton();
        nextBtn = new JButton();
        deleteNoteBtn = new JButton();
        noteDetailBtn = new JButton();
        scrollPane1 = new JScrollPane();
        notes = new JTable();

        //======== this ========
        setTitle(bundle.getString("AdminNotes.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- preBtn ----
        preBtn.setText(bundle.getString("AdminNotes.preBtn.text"));
        preBtn.addActionListener(e -> preBtnActionPerformed(e));
        contentPane.add(preBtn);
        preBtn.setBounds(740, 540, 78, 30);

        //---- nextBtn ----
        nextBtn.setText(bundle.getString("AdminNotes.nextBtn.text"));
        nextBtn.addActionListener(e -> nextBtnActionPerformed(e));
        contentPane.add(nextBtn);
        nextBtn.setBounds(835, 540, 78, 30);

        //---- deleteNoteBtn ----
        deleteNoteBtn.setText(bundle.getString("AdminNotes.deleteNoteBtn.text"));
        deleteNoteBtn.addActionListener(e -> deleteNoteBtnActionPerformed(e));
        contentPane.add(deleteNoteBtn);
        deleteNoteBtn.setBounds(610, 5, 130, 35);

        //---- noteDetailBtn ----
        noteDetailBtn.setText(bundle.getString("AdminNotes.noteDetailBtn.text"));
        noteDetailBtn.addActionListener(e -> noteDetailBtnActionPerformed(e));
        contentPane.add(noteDetailBtn);
        noteDetailBtn.setBounds(760, 5, 157, 35);

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
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(155, 55, 760, 470);

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
    private JButton preBtn;
    private JButton nextBtn;
    private JButton deleteNoteBtn;
    private JButton noteDetailBtn;
    private JScrollPane scrollPane1;
    private JTable notes;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
