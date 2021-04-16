package com.djy.notes.controller;
import com.djy.notes.bean.Msg;
import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.entity.User;
import com.djy.notes.service.impl.UserServiceImpl;
import com.djy.notes.service.inter.UserService;

public class UserController {

    private UserService userService = new UserServiceImpl();

    /**
     * 用户注册
     * @param userName
     * @param password
     * @param telephone
     * @param email
     * @param sign
     * @return
     */
    public Msg addUser(String userName, String password , String telephone ,String email , String sign) {
        return userService.addUser(userName,password,telephone,email,sign);
    }

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    public Msg login(String userName, String password) {
        return userService.login(userName,password);
    }

    /**
     * 查看/修改用户信息
     * @param password
     * @param telephone
     * @param email
     * @param sign
     * @return
     */
    public Msg changeInfo(String password, String telephone, String email, String sign) {
        return userService.changInfo(password,telephone,email,sign);
    }

    /**
     * 加载表格
     * @param pageRequest
     * @return
     */

    public TableDTO loadTableDTO(PageRequest pageRequest) {
        return userService.loadTableDTO(pageRequest);
    }

    /**
     * 通过用户名进行查询
     * @param selectedUserName
     * @return
     */
    public User selectByUserName(String selectedUserName) {
        return userService.updateNote(selectedUserName);
    }
}

