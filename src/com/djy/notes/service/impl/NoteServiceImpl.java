package com.djy.notes.service.impl;

import com.djy.notes.bean.Msg;
import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.dao.impl.NoteDaoImpl;
import com.djy.notes.dao.inter.NoteDao;
import com.djy.notes.entity.Note;
import com.djy.notes.service.inter.NoteService;
import com.djy.notes.util.FileUtil;

import java.util.Date;

public class NoteServiceImpl implements NoteService {
    private NoteDao noteDao = new NoteDaoImpl();
    /**
     * 新增笔记
     * @param noteTitle
     * @param content
     * @param overt
     * @return
     */
    @Override
    public Msg addNote(String noteTitle, String content, boolean overt) {
        //封装成note对象
        Note note = new Note();
        note.setNoteTitle(noteTitle);

        note.setAuthorId(FileUtil.getUserId());
        note.setNoteContent(content);
        note.setCreateTime(new Date());
        note.setOvert(overt);
        boolean bool = noteDao.addNote(note);
        if (bool) {
            //新建成功
            return Msg.buildSuccess("新增笔记成功");
        }else {
            return Msg.buildError("笔记名不能重复");
        }
    }

    /**
     * 修改笔记
     * @param noteTitle
     * @param noteContent
     * @param overt
     * @param noteIdToUpdate
     * @return
     */
    @Override
    public Msg updateNote(String noteTitle, String noteContent, boolean overt,int noteIdToUpdate) {
        Note note = new Note();

        note.setNoteTitle(noteTitle);
        note.setAuthorId(FileUtil.getUserId());
        note.setNoteContent(noteContent);
        note.setCreateTime(new Date());
        note.setOvert(overt);
        note.setNoteId(noteIdToUpdate);

        boolean bool = noteDao.updateNote(note);
        if (bool) {
            //修改成功
            return Msg.buildSuccess("修改笔记成功");
        }else {
            return Msg.buildError("修改笔记失败");
        }
    }

    /**
     * 根据笔记标题搜索笔记
     * @param selectedNoteTitle
     * @return
     */
    @Override
    public Note selectByNoteTitle(String selectedNoteTitle) {
        return noteDao.selectByNoteTitle(selectedNoteTitle);
    }

    /**
     * 删除笔记
     * @param noteTitles
     * @return
     */
    @Override
    public Msg deleteNote(String[] noteTitles) {
        boolean bool = noteDao.deleteNote(noteTitles);
        if (bool) {
            //删除成功
            return Msg.buildSuccess("删除笔记成功");
        }else {
            return Msg.buildError("删除笔记失败");
        }
    }

    @Override
    public TableDTO loadTableDTOD(PageRequest pageRequest) {
        return noteDao.loadTableDTOD(pageRequest);
    }

    @Override
    public TableDTO loadTableDTOJ(PageRequest pageRequest) {
        return noteDao.loadTableDTOJ(pageRequest);
    }

    /**
     * 加载表格数据
     * @param pageRequest
     * @return
     */
    @Override
    public TableDTO loadTableDTO(PageRequest pageRequest) {
        return noteDao.loadTableDTO(pageRequest);
    }
}
