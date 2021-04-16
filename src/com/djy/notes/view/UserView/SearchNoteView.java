package com.djy.notes.view.UserView;

import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.controller.NoteController;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author djy
 */
public class SearchNoteView extends JDialog {
    private static Vector<String> columns = new Vector<>();
    static {
        columns.add("笔记标题");
        columns.add("创建用户");
        columns.add("发布时间");
    }
    private TableModel tableModel = new TableModel();
    private int pageNow = 1;
    private int pageSize = 10;
    public SearchNoteView(Window owner) {
        super(owner);
        initComponents();

        //设置输入框的大小
        searchField.setPreferredSize(new Dimension(120,30));

        //表格数据初始化
        TableDTO tableDTO = loadTableDTO();
        //关联表格和表格的数据模型
        note.setModel(this.tableModel.buildModel(tableDTO.getData(),columns));
        showPreOrNextAuto(tableDTO.getTotalCount());

        setBounds(100,100,1200,800);
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
        TableDTO dto =noteController.loadTableDTOJ(pageRequest);
        return dto;
    }

    /**
     * 返回选中的行的笔记标题
     * @return
     */
    private String[] getSelectedNoteTitle() {
        int[] selectedRows = note.getSelectedRows();
        String[] noteTitles = new String[selectedRows.length];
        for (int i=0;i<selectedRows.length;i++){
            int selectedIndex = selectedRows[i];
            Object noteTitleObj = note.getValueAt(selectedIndex, 0);
            noteTitles[i] = String.valueOf(noteTitleObj.toString());
        }
        return noteTitles;
    }

    /**
     * 点击查看笔记信息按钮，查看笔记信息
     * @param e
     */
    private void searchNoteDetailBtnActionPerformed(ActionEvent e) {
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


    private void nextBtnActionPerformed(ActionEvent e) {
        this.setPageNow(this.getPageNow() + 1);
        reloadTable();
    }

    private void preBtnActionPerformed(ActionEvent e) {
        this.setPageNow(this.getPageNow() - 1);
        reloadTable();
    }
    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }

    private void searchyBtnActionPerformed(ActionEvent e) {
        NoteController noteController = new NoteController();
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNow(pageNow);
        pageRequest.setPageSize(pageSize);
        pageRequest.setSearchWord(searchField.getText());
        TableDTO tableDTO = noteController.loadTableDTOJ(pageRequest);
        tableModel.updateModel(tableDTO.getData(),columns);
        showPreOrNextAuto(tableDTO.getTotalCount());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.UserView.SearchNoteView");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        searchNoteDetailBtn = new JButton();
        scrollPane1 = new JScrollPane();
        note = new JTable();
        preBtn = new JButton();
        nextBtn = new JButton();
        searchyBtn = new JButton();
        searchField = new JTextField();

        //======== this ========
        setTitle(bundle.getString("SearchNoteView.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- searchNoteDetailBtn ----
                searchNoteDetailBtn.setText(bundle.getString("SearchNoteView.searchNoteDetailBtn.text"));
                searchNoteDetailBtn.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
                searchNoteDetailBtn.addActionListener(e -> searchNoteDetailBtnActionPerformed(e));
                contentPanel.add(searchNoteDetailBtn);
                searchNoteDetailBtn.setBounds(555, 15, 132, 35);

                //======== scrollPane1 ========
                {

                    //---- note ----
                    note.setModel(new DefaultTableModel(
                        new Object[][] {
                            {null, null, null},
                            {null, null, null},
                        },
                        new String[] {
                            "\u7b14\u8bb0\u6807\u9898", "\u521b\u5efa\u7528\u6237", "\u53d1\u5e03\u65f6\u95f4"
                        }
                    ));
                    scrollPane1.setViewportView(note);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(40, 70, 650, 405);

                //---- preBtn ----
                preBtn.setText(bundle.getString("SearchNoteView.preBtn.text"));
                preBtn.addActionListener(e -> preBtnActionPerformed(e));
                contentPanel.add(preBtn);
                preBtn.setBounds(new Rectangle(new Point(240, 480), preBtn.getPreferredSize()));

                //---- nextBtn ----
                nextBtn.setText(bundle.getString("SearchNoteView.nextBtn.text"));
                nextBtn.addActionListener(e -> nextBtnActionPerformed(e));
                contentPanel.add(nextBtn);
                nextBtn.setBounds(new Rectangle(new Point(410, 480), nextBtn.getPreferredSize()));

                //---- searchyBtn ----
                searchyBtn.setText(bundle.getString("SearchNoteView.searchyBtn.text"));
                searchyBtn.addActionListener(e -> searchyBtnActionPerformed(e));
                contentPanel.add(searchyBtn);
                searchyBtn.setBounds(new Rectangle(new Point(360, 15), searchyBtn.getPreferredSize()));

                //---- searchField ----
                searchField.setToolTipText("\u8f93\u5165\u7528\u6237\u540d\u6216\u7b14\u8bb0\u6807\u9898\u8fdb\u884c\u67e5\u8be2");
                contentPanel.add(searchField);
                searchField.setBounds(40, 15, 310, searchField.getPreferredSize().height);

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
    private JButton searchNoteDetailBtn;
    private JScrollPane scrollPane1;
    private JTable note;
    private JButton preBtn;
    private JButton nextBtn;
    private JButton searchyBtn;
    private JTextField searchField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
