package com.djy.notes.view.UserView;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

//与table关联，更新model——>table更新
public class TableModel extends DefaultTableModel {
    /**
     * Vector<线程安全>
     * Vector<Object> : 一行数据
     * Vector<Vector<Object>> ： 多行数据
     * @param data
     * @param columns
     * @return
     */
    public TableModel buildModel(Vector<Vector<Object>> data,Vector<String> columns){
        //设置model的表格数据和列的名称
        setDataVector(data,columns);
        return this;
    }

    /**
     * 更新表格数据
     * @param data
     * @param columns
     */
    public void updateModel(Vector<Vector<Object>> data, Vector<String> columns) {
        setDataVector(data,columns);
    }

    //控制表格里的单元格为不能编辑
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
