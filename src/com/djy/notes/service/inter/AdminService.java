package com.djy.notes.service.inter;

import com.djy.notes.bean.Msg;

public interface AdminService {
    Msg login(String adminName, String password);
}
