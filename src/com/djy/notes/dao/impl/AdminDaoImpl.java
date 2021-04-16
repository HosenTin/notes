package com.djy.notes.dao.impl;

import com.djy.notes.dao.inter.AdminDao;
import com.djy.notes.entity.Admin;
import com.djy.notes.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDaoImpl implements AdminDao {
    @Override
    public int login(Admin admin) {
        StringBuilder sql = new StringBuilder();
        sql.append("select password,admin_id from admin where admin_name = ?");

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConn();
            if (conn == null) {
                return 0;
            }
            pst = conn.prepareStatement(sql.toString());
            pst.setString(1, admin.getAdminName());
            rs = pst.executeQuery();

            while (rs.next()) {
                String JdbcPwd = rs.getString("password");
                if (JdbcPwd.equals(admin.getAdminPwd())) {
                    //密码正确，返回管理员的id
                    return rs.getInt("admin_id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeRs(rs);
            JdbcUtil.closePst(pst);
            JdbcUtil.closeConn(conn);
        }
        return 0;
    }
}
