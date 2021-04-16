package com.djy.notes.service.impl;

import com.djy.notes.bean.Msg;
import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.dao.impl.CommentDaoImpl;
import com.djy.notes.dao.inter.CommentDao;
import com.djy.notes.entity.Comment;
import com.djy.notes.service.inter.CommentService;
import com.djy.notes.util.FileUtil;


public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao = new CommentDaoImpl();

    @Override
    public TableDTO loadTableDTO(PageRequest pageRequest) {
        return commentDao.loadTableDTO(pageRequest);
    }

    @Override
    public Msg addComment(String newComment, String selectedNoterTitler) {
        //封装成announce对象
        Comment comment = new Comment();

        String currentUserName = FileUtil.getUserName();

        comment.setNoteTitle(selectedNoterTitler);
        comment.setUserName(currentUserName);
        comment.setCommentContent(newComment);

        boolean bool = commentDao.addComment(comment);
        if (bool) {
            return Msg.buildSuccess("发布评论成功");
        }else {
            return Msg.buildError("发布评论失败");
        }
    }


}
