package com.djy.notes.service.impl;

import com.djy.notes.bean.Msg;
import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.dao.impl.AnnounceDaoImpl;
import com.djy.notes.dao.inter.AnnounceDao;
import com.djy.notes.entity.Announce;
import com.djy.notes.service.inter.AnnounceService;


import java.util.Date;

public class AnnounceServiceImpl implements AnnounceService {
    private AnnounceDao announceDao = new AnnounceDaoImpl();

    @Override
    public Msg addAnnounce(String announceTitle, String announceContent) {
        //封装成announce对象
        Announce announce = new Announce();
        announce.setAnnounceTitle(announceTitle);
        announce.setAnnounceContent(announceContent);
        announce.setCreateTime(new Date());

        boolean bool = announceDao.addAnnounce(announce);
        if (bool) {
            return Msg.buildSuccess("发布公告成功");
        }else {
            return Msg.buildError("发布公告失败");
        }
    }

    @Override
    public TableDTO loadTableDTO(PageRequest pageRequest) {
        return announceDao.loadTableDTO(pageRequest);
    }

    @Override
    public Announce selectByAnnounceTitle(String selectedAnnounceTitle) {
        return announceDao.selectByAnnounceTitle(selectedAnnounceTitle);
    }
}
