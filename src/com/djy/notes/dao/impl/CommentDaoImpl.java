package com.djy.notes.dao.impl;

import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.dao.inter.CommentDao;
import com.djy.notes.entity.Comment;
import com.djy.notes.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class CommentDaoImpl implements CommentDao {

    @Override
    public boolean addComment(Comment comment) {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into comment (note_title,user_name,comment_content) values(?,?,?) ");
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = JdbcUtil.getConn();
            if (conn == null) {
                return false;
            }

            pst = conn.prepareStatement(sql.toString());
            pst.setString(1,comment.getNoteTitle());
            pst.setString(2,comment.getUserName());
            pst.setString(3,comment.getCommentContent());

            int i = pst.executeUpdate();
            if(i>0){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closePst(pst);
            JdbcUtil.closeConn(conn);
        }
        return false;
    }

    @Override
    public TableDTO loadTableDTO(PageRequest pageRequest) {
        StringBuilder sql = new StringBuilder();

        sql.append(" select * from comment order by comment_id asc limit ").append(pageRequest.getStart()).append(",").append(pageRequest.getPageSize());

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        TableDTO returnDTO = new TableDTO();

        try {

            conn = JdbcUtil.getConn();
            if (conn == null) {
                return returnDTO;
            }
            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();

            returnDTO.setData(fillData(rs));


            sql.setLength(0);

            sql.append("select count(*) from comment ");


            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                returnDTO.setTotalCount(count);
            }
            return returnDTO;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeRs(rs);
            JdbcUtil.closePst(pst);
            JdbcUtil.closeConn(conn);
        }
        return null;
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws Exception {
        Vector<Vector<Object>> vectors = new Vector<>();
        while (rs.next()) {
            //开始新一行
            Vector<Object> oneRow = new Vector<>();
            int comment_id = rs.getInt("comment_id");
            String comment_content = rs.getString("comment_content");
            String user_name = rs.getString("user_name");
            String note_title = rs.getString("note_title");

            // 加入的顺序要和列定义顺序一致
            oneRow.add(comment_id);
            oneRow.add(comment_content);
            oneRow.add(user_name);
            oneRow.add(note_title);
            // 加到多行集合中
            vectors.addElement(oneRow);
        }
        return vectors;
    }
}
