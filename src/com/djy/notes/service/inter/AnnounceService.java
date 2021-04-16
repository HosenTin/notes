package com.djy.notes.service.inter;

import com.djy.notes.bean.Msg;
import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.entity.Announce;

public interface AnnounceService {
    Msg addAnnounce(String announceTitle, String announceContent);

    TableDTO loadTableDTO(PageRequest pageRequest);

    Announce selectByAnnounceTitle(String selectedAnnounceTitle);
}
