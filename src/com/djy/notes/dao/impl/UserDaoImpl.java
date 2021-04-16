package com.djy.notes.dao.impl;

import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.dao.inter.UserDao;
import com.djy.notes.entity.User;
import com.djy.notes.util.JdbcUtil;
import com.djy.notes.util.Md5Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class UserDaoImpl implements UserDao {
    /**
     * 用户注册 —— 新增用户
     * @param user
     * @return ture     ——  注册成功
     *         false    ——  注册失败
     */
    @Override
    public boolean addUser(User user) {
        //StringBuilder：线程安全，性能好
        StringBuilder sql = new StringBuilder();
        sql.append("insert into user(user_name,password,telephone,email,sign,isblack) values(?,?,?,?,?,?)");

        Connection conn = null;
        PreparedStatement pst = null;

        try{
            conn = JdbcUtil.getConn();          //获取连接
            if(conn == null){
                return false;
            }
            pst = conn.prepareStatement(sql.toString());
            pst.setString(1,user.getUserName());
            //MD5加密
            pst.setString(2, Md5Util.getMD5(user.getPassword()));
            pst.setString(3,user.getTelephone());
            pst.setString(4,user.getEmail());
            pst.setString(5,user.getSign());
            pst.setBoolean(6,user.getIsblack());
            //影响条数
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
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public int login(User user) {

        StringBuilder sql = new StringBuilder();
        sql.append("select password,user_id from user where user_name = ?");

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try{
            conn = JdbcUtil.getConn();
            if(conn == null){
                return 0;
            }
            pst = conn.prepareStatement(sql.toString());
            pst.setString(1,user.getUserName());
            rs = pst.executeQuery();

            while(rs.next()){
                String JdbcPwd = rs.getString("password");
                //登录时将密码加密与数据库里加密的密码进行比对
                if(JdbcPwd.equals(Md5Util.getMD5(user.getPassword()))){
                    //密码正确，返回用户的id
                    return rs.getInt("user_id");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeRs(rs);
            JdbcUtil.closePst(pst);
            JdbcUtil.closeConn(conn);
        }
        return 0;
    }

    /**
     * 查看/修改个人信息
     * @param user
     * @return
     */
    @Override
    public boolean changeInfo(User user) {
        StringBuilder sql = new StringBuilder();
        sql.append("update user set password=?,telephone=?,email=?,sign=? where user_id=?");

        Connection conn = null;
        PreparedStatement pst = null;

        try{
            conn = JdbcUtil.getConn();
            if(conn == null){
                return false;
            }
            pst = conn.prepareStatement(sql.toString());
            pst.setString(1,user.getPassword());
            pst.setString(2,user.getTelephone());
            pst.setString(3,user.getEmail());
            pst.setString(4,user.getSign());
            pst.setInt(5,user.getUserId());

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
     * 加载表格数据
     * @param pageRequest
     * @return
     */
    @Override
    public TableDTO loadTableDTO(PageRequest pageRequest) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from  user ");
        sql.append(" limit  ").append(pageRequest.getStart())
                .append(",").append(pageRequest.getPageSize());
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        TableDTO returnDTO = new TableDTO();
        try {
            // 获取表格记录
            conn = JdbcUtil.getConn();
            if (conn == null) {
                return returnDTO;
            }
            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();
            returnDTO.setData(fillData(rs));

            // 查询总条数
            sql.setLength(0);
            sql.append("select count(*) from user");
            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();
            if (rs.next()) {
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

    /**
     * 根据用户名进行查询
     * @param selectedUserName
     * @return
     */
    @Override
    public User selectByUserName(String selectedUserName) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from user where user_name = ? ");

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        User user = new User();

        try{
            conn = JdbcUtil.getConn();
            if(conn == null){
                return user;
            }
            pst = conn.prepareStatement(sql.toString());
            pst.setString(1,selectedUserName);
            rs = pst.executeQuery();

            if(rs.next()){
                String password = rs.getString("password");
                String telephone = rs.getString( "telephone");
                String email = rs.getString("email");
                String sign = rs.getString("sign");

                user.setPassword(password);
                user.setTelephone(telephone);
                user.setEmail(email);
                user.setSign(sign);

            }
            return user;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeRs(rs);
            JdbcUtil.closePst(pst);
            JdbcUtil.closeConn(conn);
        }
        return null;
    }

    /**
     * 拉黑用户
     * @param userNames
     * @return
     */
    @Override
    public boolean blackUser(String[] userNames) {
        StringBuilder sql = new StringBuilder();
        //在tinyint的使用中，MySQL中没有布尔类型，定义了布尔类型会自动转换成tinyint。
        //保存Boolean值时用1代表TRUE,0代表FALSE，boolean在MySQL里的类型为tinyint(1)
        sql.append("update user set isblack = false where user_name in ( ");

        int length = userNames.length;

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
            // 给? 赋值
            for (int i =0;i<length;i++){
                pst.setString(i+1,userNames[i]);
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
     * 取消拉黑用户
     * @param userNames
     * @return
     */
    @Override
    public boolean notblackUser(String[] userNames) {
        StringBuilder sql = new StringBuilder();
        //在tinyint的使用中，MySQL中没有布尔类型，定义了布尔类型会自动转换成tinyint。
        //保存Boolean值时用1代表TRUE,0代表FALSE，boolean在MySQL里的类型为tinyint(1)
        sql.append("update user set isblack = true where user_name in ( ");

        int length = userNames.length;

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
            // 给? 赋值
            for (int i =0;i<length;i++){
                pst.setString(i+1,userNames[i]);
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
     * 查询用户状态
     * @param userName
     * @return
     */
    @Override
    public Boolean selectedBlack(String userName) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select isblack from user where user_name = ? ");

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try{
            conn = JdbcUtil.getConn();
            if(conn == null){
                return false;
            }
            pst = conn.prepareStatement(sql.toString());
            pst.setString(1,userName);
            rs = pst.executeQuery();

            if(rs.next()){
                Boolean isblack = rs.getBoolean("isblack");
                return isblack;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeRs(rs);
            JdbcUtil.closePst(pst);
            JdbcUtil.closeConn(conn);
        }
        return null;
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> vectors = new Vector<>();
        while (rs.next()) {
            // 新的一行开始
            Vector<Object> oneRow = new Vector<>();

            String user_name = rs.getString("user_name");
            String password = rs.getString("password");
            String telephone = rs.getString("telephone");
            String email = rs.getString("email");
            String sign = rs.getString("sign");
            Boolean isblack = rs.getBoolean("isblack");

            // 加入的顺序要和列定义顺序一致
            oneRow.add(user_name);
            oneRow.add(password);
            oneRow.add(telephone);
            oneRow.add(email);
            oneRow.add(sign);
            oneRow.add(isblack);

            // 加到多行集合中
            vectors.addElement(oneRow);
        }
        return vectors;
    }
}
