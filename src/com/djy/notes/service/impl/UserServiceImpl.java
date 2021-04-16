package com.djy.notes.service.impl;

import com.djy.notes.bean.Msg;
import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.dao.impl.UserDaoImpl;
import com.djy.notes.dao.inter.UserDao;
import com.djy.notes.entity.User;
import com.djy.notes.service.inter.UserService;
import com.djy.notes.util.FileUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 用户注册
     * @param userName
     * @param password
     * @param telephone
     * @param email
     * @param sign
     * @return
     */
    @Override
    public Msg addUser(String userName, String password , String telephone ,String email , String sign) {
        //封装成User对象
        User user = new User();

        user.setUserName(userName);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setEmail(email);
        user.setSign(sign);
        user.setIsblack(true);

        boolean bool = userDao.addUser(user);
        if(bool){
            return Msg.buildSuccess("注册成功");
        }else{
            return Msg.buildError("该用户名已被注册，请重命名");
        }
    }

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @Override
    public Msg login(String userName, String password) {
        User user = new User();

        user.setUserName(userName);
        user.setPassword(password);

        //新增笔记需要userId
        int userId = userDao.login(user);
        if(userId != 0){
            //将int型的userId转换成字符串型
            return Msg.buildSuccess(userId+"");
        }else{
            return Msg.buildError("用户名或密码错误");
        }
    }

    /**
     * 查看/修改个人信息
     * @param password
     * @param telephone
     * @param email
     * @param sign
     * @return
     */
    @Override
    public Msg changInfo(String password, String telephone, String email, String sign) {
        User user = new User();

        user.setPassword(password);
        user.setTelephone(telephone);
        user.setEmail(email);
        user.setSign(sign);
        user.setUserId(FileUtil.getUserId());

        boolean bool = userDao.changeInfo(user);
        if(bool){
            return Msg.buildSuccess("修改个人信息成功");
        }else{
            return Msg.buildError("修改个人信息失败");
        }
    }

    /**
     * 加载表格数据
     * @param pageRequest
     * @return
     */

    @Override
    public TableDTO loadTableDTO(PageRequest pageRequest) {
        return userDao.loadTableDTO(pageRequest);
    }

    @Override
    public User updateNote(String selectedUserName) {
        return userDao.selectByUserName(selectedUserName);
    }

    /**
     * 拉黑用户
     * @param userNames
     * @return
     */
    @Override
    public Msg blackUser(String[] userNames) {
        boolean bool = userDao.blackUser(userNames);
        if (bool) {
            return Msg.buildSuccess("拉黑成功");
        }else {
            return Msg.buildError("拉黑失败");
        }
    }

    /**
     * 取消拉黑用户
     * @param userNames
     * @return
     */
    @Override
    public Msg notblackUser(String[] userNames) {
        boolean bool = userDao.notblackUser(userNames);
        if (bool) {
            return Msg.buildSuccess("取消拉黑成功");
        }else {
            return Msg.buildError("取消拉黑失败");
        }
    }

    /**
     * 查询用户状态
     * @param userName
     * @return
     */
    @Override
    public Boolean selectedBlack(String userName) {
        return userDao.selectedBlack(userName);
    }
}
