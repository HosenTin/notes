package com.djy.notes.dao.impl;

import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.dao.inter.AnnounceDao;
import com.djy.notes.entity.Announce;
import com.djy.notes.util.DateUtil;
import com.djy.notes.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Vector;

public class AnnounceDaoImpl implements AnnounceDao {
    @Override
    public boolean addAnnounce(Announce announce) {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into announce (announce_title,announce_content,create_time) values(?,?,?) ");
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = JdbcUtil.getConn();
            if (conn == null) {
                return false;
            }

            pst = conn.prepareStatement(sql.toString());
            pst.setString(1,announce.getAnnounceTitle());
            pst.setString(2,announce.getAnnounceContent());
            pst.setTimestamp(3,new Timestamp(announce.getCreateTime().getTime()));

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

        sql.append(" select * from announce order by create_time desc limit ").append(pageRequest.getStart()).append(",").append(pageRequest.getPageSize());

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

            //查询总条数
            sql.setLength(0);

            sql.append("select count(*) from announce ");


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
    public Announce selectByAnnounceTitle(String selectedAnnounceTitle) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from announce where announce_title = ? ");

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        Announce announce = new Announce();

        try{
            conn = JdbcUtil.getConn();
            if(conn == null){
                return announce;
            }
            pst = conn.prepareStatement(sql.toString());
            pst.setString(1,selectedAnnounceTitle);
            rs = pst.executeQuery();

            if(rs.next()){
                int announce_id = rs.getInt("announce_id");
                String announce_title = rs.getString("announce_title");
                String announce_content = rs.getString("announce_content");
                announce.setAnnounceId(announce_id);
                announce.setAnnounceTitle(announce_title);
                announce.setAnnounceContent(announce_content);

            }
            return announce;
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
        while (rs.next()) {
            //开始新一行
            Vector<Object> oneRow = new Vector<>();

            String announce_title = rs.getString("announce_title");
            String create_time = DateUtil.convert2Str(rs.getTimestamp("create_time"));


            // 加入的顺序要和列定义顺序一致
            oneRow.add(announce_title);
            oneRow.add(create_time);

            // 加到多行集合中
            vectors.addElement(oneRow);
        }
        return vectors;
    }
}
