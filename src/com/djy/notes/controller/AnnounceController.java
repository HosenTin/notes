package com.djy.notes.controller;

import com.djy.notes.bean.Msg;
import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.entity.Announce;
import com.djy.notes.service.impl.AnnounceServiceImpl;
import com.djy.notes.service.inter.AnnounceService;

public class AnnounceController {
    private AnnounceService announceService = new AnnounceServiceImpl();

    public Msg addAnnounce(String announceTitle, String announceContent) {
        return announceService.addAnnounce(announceTitle,announceContent);
    }

    public TableDTO loadTableDTO(PageRequest pageRequest) {
        return announceService.loadTableDTO(pageRequest);
    }
    public Announce selectByAnnounceTitle(String selectedAnnounceTitle) {
        return announceService.selectByAnnounceTitle(selectedAnnounceTitle);
    }
}
