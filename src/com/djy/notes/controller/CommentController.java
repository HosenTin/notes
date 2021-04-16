package com.djy.notes.controller;

import com.djy.notes.bean.Msg;
import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.service.impl.CommentServiceImpl;
import com.djy.notes.service.inter.CommentService;


public class CommentController {
    private CommentService commentService = new CommentServiceImpl();

    public TableDTO loadTableDTO(PageRequest pageRequest) {
        return  commentService.loadTableDTO(pageRequest);
    }

    public Msg addComment(String newComment,String selectedNoterTitler) {
        return commentService.addComment(newComment,selectedNoterTitler);
    }
}
