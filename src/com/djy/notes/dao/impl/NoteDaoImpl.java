package com.djy.notes.dao.impl;

import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.dao.inter.NoteDao;
import com.djy.notes.entity.Note;
import com.djy.notes.util.DateUtil;
import com.djy.notes.util.FileUtil;
import com.djy.notes.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Vector;

public class NoteDaoImpl implements NoteDao{
    /**
     * 新增笔记
     * @param note
     * @return
     */
    @Override
    public boolean addNote(Note note) {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into note(note_title,author_id,note_content,create_time,overt) values(?,?,?,?,?) ");
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = JdbcUtil.getConn();
            if (conn == null) {
                return false;
            }

            pst = conn.prepareStatement(sql.toString());
            pst.setString(1,note.getNoteTitle());
            pst.setInt(2,note.getAuthorId());
            pst.setString(3,note.getNoteContent());
            pst.setTimestamp(4,new Timestamp(note.getCreateTime().getTime()));
            //MySQL里：tinyint —— true为1，false为0
            pst.setBoolean(5,note.isOvert());

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

    /**
     * 插入的sql语句
     * @param sql
     * @param pageRequest
     */
    private void appendWhereCondition(StringBuilder sql,PageRequest pageRequest) {
        Integer currentUserId = FileUtil.getUserId();
        sql.append(" where (author_id = ").append(currentUserId).append(" OR ( author_id != ").append(currentUserId).append(" AND overt = 1) ) ");
        String searchWord = pageRequest.getSearchWord();
        if (searchWord == null || "".equals(searchWord)) {
            return;
        }
        //满足两种情况
        sql.append(" and ( n.note_title = '"+pageRequest.getSearchWord()+"' or u.user_name = '"+pageRequest.getSearchWord()+"' ) ");
    }

    /**
     * 加载表格
     */
    @Override
    public TableDTO loadTableDTO(PageRequest pageRequest) {
        StringBuilder sql = new StringBuilder();
        //联表查询
        sql.append(" select n.*,u.user_name from note n left join user u on n.author_id = u.user_id");
        appendWhereCondition(sql,pageRequest);
        sql.append(" order by create_time desc");
        sql.append(" limit ").append(pageRequest.getStart()).append(",").append(pageRequest.getPageSize());

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        TableDTO returnDTO = new TableDTO();

        try{
            //获取表格记录
            conn = JdbcUtil.getConn();
            if(conn == null){
                return returnDTO;
            }
            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();

            returnDTO.setData(fillData(rs));

            //查询总条数，清空
            sql.setLength(0);

            sql.append("select count(*) from note n left join user u on n.author_id = u.user_id  ");
            appendWhereCondition(sql,pageRequest);

            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();

            if(rs.next()){
                int count = rs.getInt(1);
                returnDTO.setTotalCount(count);
            }
            return returnDTO;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeRs(rs);
            JdbcUtil.closePst(pst);
            JdbcUtil.closeConn(conn);
        }
        return null;
    }

    @Override
    public TableDTO loadTableDTOD(PageRequest pageRequest) {
        Integer currentUserId = FileUtil.getUserId();
        StringBuilder sql = new StringBuilder();
        //联表查询
        sql.append(" select n.*,u.user_name from note n left join user u on n.author_id = u.user_id");
        sql.append(" where author_id = ").append(currentUserId);
        sql.append(" order by create_time desc ");
        sql.append(" limit ").append(pageRequest.getStart()).append(",").append(pageRequest.getPageSize());

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        TableDTO returnDTO = new TableDTO();

        try{
            //获取表格记录
            conn = JdbcUtil.getConn();
            if(conn == null){
                return returnDTO;
            }
            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();

            returnDTO.setData(fillData(rs));

            //查询总条数，清空
            sql.setLength(0);

            sql.append("select count(*) from note n left join user u on n.author_id = u.user_id  where author_id = ").append(currentUserId);


            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();

            if(rs.next()){
                int count = rs.getInt(1);
                returnDTO.setTotalCount(count);
            }
            return returnDTO;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeRs(rs);
            JdbcUtil.closePst(pst);
            JdbcUtil.closeConn(conn);
        }
        return null;
    }

    @Override
    public TableDTO loadTableDTOJ(PageRequest pageRequest) {
        StringBuilder sql = new StringBuilder();
        //联表查询
        sql.append(" select n.*,u.user_name from note n left join user u on n.author_id = u.user_id");
        sql.append(" where ( n.note_title = '"+pageRequest.getSearchWord()+"' or u.user_name = '"+pageRequest.getSearchWord()+"' ) and overt = 1 ");
        sql.append(" order by create_time desc");
        sql.append(" limit ").append(pageRequest.getStart()).append(",").append(pageRequest.getPageSize());

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        TableDTO returnDTO = new TableDTO();

        try{
            //获取表格记录
            conn = JdbcUtil.getConn();
            if(conn == null){
                return returnDTO;
            }
            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();

            returnDTO.setData(fillData(rs));

            //查询总条数，清空
            sql.setLength(0);

            sql.append("select count(*) from note n left join user u on n.author_id = u.user_id  ").append(" where n.note_title = '"+pageRequest.getSearchWord()+"' or u.user_name = '"+pageRequest.getSearchWord()+"' ");


            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();

            if(rs.next()){
                int count = rs.getInt(1);
                returnDTO.setTotalCount(count);
            }
            return returnDTO;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeRs(rs);
            JdbcUtil.closePst(pst);
            JdbcUtil.closeConn(conn);
        }
        return null;
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws Exception {
        Vector<Vector<Object>> vectors = new Vector<>();
        while(rs.next()){
            //开始新一行
            Vector<Object> oneRow = new Vector<>();

            int note_id = rs.getInt("note_id");
            String note_title = rs.getString("note_title");
            String user_name = rs.getString("user_name");
            int author_id = rs.getInt("author_id");
            String note_content = rs.getString("note_content");
            String create_time = DateUtil.convert2Str(rs.getTimestamp("create_time"));
            boolean overt = rs.getBoolean("overt");

            // 加入的顺序要和列名的定义顺序一致
            oneRow.add(note_title);
            oneRow.add(user_name);
            oneRow.add(create_time);

            // 加到多行集合中
            vectors.addElement(oneRow);
        }
        return vectors;
    }

    /**
     * 修改笔记
     * @param note
     * @return
     */
    @Override
    public boolean updateNote(Note note) {
        StringBuilder sql1 = new StringBuilder();
        sql1.append(" select note_id where note_title =? ");

        StringBuilder sql = new StringBuilder();
        sql.append(" update note set note_title=?,note_content=?,create_time=?,overt=? where note_id =? ");
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = JdbcUtil.getConn();
            if (conn == null) {
                return false;
            }
            pst = conn.prepareStatement(sql.toString());

            pst.setString(1,note.getNoteTitle());
            pst.setString(2,note.getNoteContent());
            pst.setTimestamp(3,new Timestamp(note.getCreateTime().getTime()));
            pst.setBoolean(4,note.isOvert());
            pst.setInt(5,note.getNoteId());


            return pst.executeUpdate() == 1;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closePst(pst);
            JdbcUtil.closeConn(conn);
        }
        return false;
    }

    /**
     * 修改笔记
     * @param noteTitles
     * @return
     */

    @Override
    public boolean deleteNote(String[] noteTitles) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from note where note_title in ( ");

        int length = noteTitles.length;
        //插入对应笔记数量的通配符?
        for (int i =0;i<length;i++){
            if (i == (length -1 )){
                sql.append("?");
            }else {
                sql.append("?,");
            }
        }
        sql.append(")");

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = JdbcUtil.getConn();
            if (conn == null) {
                return false;
            }
            pst = conn.prepareStatement(sql.toString());
            for (int i =0;i<length;i++){
                //JDBC从1开始
                pst.setString(i+1,noteTitles[i]);
            }
            return pst.executeUpdate() == length;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closePst(pst);
            JdbcUtil.closeConn(conn);
        }
        return false;
    }

    /**
     * 通过笔记标题查询
     * @param selectedNoteTitle
     * @return
     */

    @Override
    public Note selectByNoteTitle(String selectedNoteTitle) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from note where note_title = ? ");

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        Note note = new Note();

        try{
            conn = JdbcUtil.getConn();
            if(conn == null){
                return note;
            }
            pst = conn.prepareStatement(sql.toString());
            pst.setString(1,selectedNoteTitle);

            rs = pst.executeQuery();

            if(rs.next()){
                int note_id = rs.getInt("note_id");
                String note_title = rs.getString("note_title");
                int author_id = rs.getInt("author_id");
                String note_content = rs.getString("note_content");
                String create_time = DateUtil.convert2Str(rs.getTimestamp("create_time"));
                boolean overt = rs.getBoolean("overt");

                note.setNoteId(note_id);
                note.setNoteTitle(note_title);
                note.setNoteContent(note_content);
                note.setOvert(overt);
            }
            return note;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeRs(rs);
            JdbcUtil.closePst(pst);
            JdbcUtil.closeConn(conn);
        }
        return null;
    }

}
