package com.djy.notes.controller;

import com.djy.notes.bean.Msg;
import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.entity.Note;
import com.djy.notes.service.impl.NoteServiceImpl;
import com.djy.notes.service.inter.NoteService;

public class NoteController {
    private NoteService noteService = new NoteServiceImpl();

    /**
     * 新增笔记
     * @param noteTitle
     * @param noteContent
     * @param overt
     * @return
     */
    public Msg addNote(String noteTitle, String noteContent, boolean overt) {
        return noteService.addNote(noteTitle,noteContent,overt);
    }

    /**
     * 修改笔记
     * @param noteTitle
     * @param noteContent
     * @param overt
     * @param noteIdToUpdate
     * @return
     */
    public Msg updateNote(String noteTitle, String noteContent, boolean overt,int noteIdToUpdate) {
        return noteService.updateNote(noteTitle,noteContent,overt,noteIdToUpdate);
    }

    /**
     * 加载表格
     * @param pageRequest
     * @return
     */
    public TableDTO loadTableDTO(PageRequest pageRequest) {
        return  noteService.loadTableDTO(pageRequest);
    }

    /**
     * 通过笔记标题进行查询
     * @param selectedNoteTitle
     * @return
     */
    public Note selectByNoteTitle(String selectedNoteTitle) {
        return noteService.selectByNoteTitle(selectedNoteTitle);
    }

    public TableDTO loadTableDTOD(PageRequest pageRequest) {
        return noteService.loadTableDTOD(pageRequest);
    }

    public TableDTO loadTableDTOJ(PageRequest pageRequest) {
        return noteService.loadTableDTOJ(pageRequest);
    }
}
