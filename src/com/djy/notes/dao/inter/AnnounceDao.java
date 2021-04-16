package com.djy.notes.dao.inter;

import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.entity.Announce;


public interface AnnounceDao {
    boolean addAnnounce(Announce announce);

    TableDTO loadTableDTO(PageRequest pageRequest);

    Announce selectByAnnounceTitle(String selectedAnnounceTitle);
}
