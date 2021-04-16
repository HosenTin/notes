package com.djy.notes.dao.inter;

import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.entity.User;

public interface UserDao {
    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    int login(User user);

    /**
     * 修改/查看个人信息
     * @param user
     * @return
     */
    boolean changeInfo(User user);

    /**
     * 加载表格数据
     * @param pageRequest
     * @return
     */
    TableDTO loadTableDTO(PageRequest pageRequest);

    /**
     * 根据用户名进行查询
     * @param selectedUserName
     * @return
     */
    User selectByUserName(String selectedUserName);

    boolean blackUser(String[] userNames);
    /**
     * ↑拉黑用户，取消拉黑用户↓
     * @param userNames
     * @return
     */
    boolean notblackUser(String[] userNames);

    /**
     * 查询用户状态
     * @param userName
     * @return
     */
    Boolean selectedBlack(String userName);
}
