package com.djy.notes.controller;

import com.djy.notes.bean.Msg;
import com.djy.notes.service.impl.AdminServiceImpl;
import com.djy.notes.service.inter.AdminService;

public class AdminController {
    private AdminService adminService = new AdminServiceImpl();

    public Msg login(String adminName, String password) {
        return adminService.login(adminName,password);
    }
}
