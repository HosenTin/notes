package com.djy.notes.service.inter;

import com.djy.notes.bean.Msg;
import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.entity.User;

public interface UserService {
    /**
     * 用户注册
     * @param userName
     * @param password
     * @param telephone
     * @param email
     * @param sign
     * @return
     */
    Msg addUser(String userName, String password , String telephone ,String email , String sign);

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    Msg login(String userName, String password);

    /**
     * 查看/修改用户个人信息
     * @param password
     * @param telephone
     * @param email
     * @param sign
     * @return
     */
    Msg changInfo(String password, String telephone, String email, String sign);

    TableDTO loadTableDTO(PageRequest pageRequest);

    User updateNote(String selectedUserName);

    Msg blackUser(String[] userNames);
    /**
     * ↑拉黑用户，取消拉黑用户↓
     * @param userNames
     * @return
     */
    Msg notblackUser(String[] userNames);

    /**
     * 查询用户状态
     * @param userName
     * @return
     */
    Boolean selectedBlack(String userName);

}
