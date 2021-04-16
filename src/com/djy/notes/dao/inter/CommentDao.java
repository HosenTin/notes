package com.djy.notes.dao.inter;

import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.entity.Comment;

public interface CommentDao {
    TableDTO loadTableDTO(PageRequest pageRequest);

    boolean addComment(Comment comment);
}
