package com.djy.notes.service.impl;

import com.djy.notes.bean.Msg;
import com.djy.notes.dao.impl.AdminDaoImpl;
import com.djy.notes.dao.inter.AdminDao;
import com.djy.notes.entity.Admin;
import com.djy.notes.service.inter.AdminService;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public Msg login(String adminName, String password) {
            Admin admin = new Admin();

            admin.setAdminName(adminName);
            admin.setAdminPwd(password);

            //新增笔记需要userId
            int adminId = adminDao.login(admin);
         if(adminId != 0){
            //将int型的userId转换成字符串型
            return Msg.buildSuccess(adminId+"");
        }else{
            return Msg.buildError("管理员名或密码错误");
        }
    }
}
