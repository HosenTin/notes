package com.djy.notes.service.inter;

import com.djy.notes.bean.Msg;
import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;

public interface CommentService {
    TableDTO loadTableDTO(PageRequest pageRequest);

    Msg addComment(String newComment,String selectedNoterTitler);
}
