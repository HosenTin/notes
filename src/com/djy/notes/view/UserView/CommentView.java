package com.djy.notes.view.UserView;

import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.controller.CommentController;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author djy
 */
public class CommentView extends JDialog {
    private static Vector<String> columns = new Vector<>();
    //表格的列名
    static {
        columns.add("楼层");
        columns.add("评论内容");
        columns.add("评论用户");
        columns.add("所评论的笔记");
    }
    private TableModel tableModel = new TableModel();
    private int pageNow = 1;
    private int pageSize = 10;

    //定义一个全局变量存放获取到的笔记标题
    private String selectedNoterTitle;

    public CommentView(Window owner,String noterTitle) {
        super(owner);
        initComponents();

        setTitle("正在查看："+noterTitle+"的评论");
        selectedNoterTitle = noterTitle;
        //表格数据初始化
        TableDTO tableDTO = loadTableDTO();
        //关联表格和表格的数据模型
        comments.setModel(this.tableModel.buildModel(tableDTO.getData(),columns));
        showPreOrNextAuto(tableDTO.getTotalCount());
        //设置窗体宽高
        setBounds(100,100,1000,700);
        //设置窗体居中
        setLocationRelativeTo(null);
        //使用插件时窗体默认不可见，设置成可见
        setVisible(true);
        //设置点击关闭按钮时程序结束
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }
    //点击发布评论按钮
    private void addCommentBtnActionPerformed(ActionEvent e) {
        new AddCommentView(this,selectedNoterTitle);
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
        CommentController commentController = new CommentController();
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNow(pageNow);
        pageRequest.setPageSize(pageSize);
        TableDTO dto =commentController.loadTableDTO(pageRequest);
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.djy.notes.view.UserView.CommentView");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        addCommentBtn = new JButton();
        scrollPane1 = new JScrollPane();
        comments = new JTable();
        nextBtn = new JButton();
        preBtn = new JButton();

        //======== this ========
        setTitle(bundle.getString("CommentView.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- addCommentBtn ----
                addCommentBtn.setText(bundle.getString("CommentView.addCommentBtn.text"));
                addCommentBtn.addActionListener(e -> addCommentBtnActionPerformed(e));
                contentPanel.add(addCommentBtn);
                addCommentBtn.setBounds(770, 425, 105, addCommentBtn.getPreferredSize().height);

                //======== scrollPane1 ========
                {

                    //---- comments ----
                    comments.setModel(new DefaultTableModel(
                        new Object[][] {
                            {null, null, null, null},
                            {null, null, null, null},
                        },
                        new String[] {
                            "\u697c\u5c42", "\u8bc4\u8bba\u5185\u5bb9", "\u8bc4\u8bba\u7528\u6237", "\u6240\u8bc4\u8bba\u7684\u7b14\u8bb0"
                        }
                    ));
                    scrollPane1.setViewportView(comments);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(105, 15, 655, 400);

                //---- nextBtn ----
                nextBtn.setText(bundle.getString("CommentView.nextBtn.text"));
                nextBtn.addActionListener(e -> nextBtnActionPerformed(e));
                contentPanel.add(nextBtn);
                nextBtn.setBounds(470, 420, 78, 30);

                //---- preBtn ----
                preBtn.setText(bundle.getString("CommentView.preBtn.text"));
                preBtn.addActionListener(e -> preBtnActionPerformed(e));
                contentPanel.add(preBtn);
                preBtn.setBounds(340, 420, 78, 30);

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
    private JButton addCommentBtn;
    private JScrollPane scrollPane1;
    private JTable comments;
    private JButton nextBtn;
    private JButton preBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
