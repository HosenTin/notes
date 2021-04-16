package com.djy.notes.service.inter;

import com.djy.notes.bean.Msg;
import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.entity.Note;

public interface NoteService {
    /**
     * 新增笔记
     * @param noteTitle
     * @param content
     * @param overt
     * @return
     */
    Msg addNote(String noteTitle, String content, boolean overt);

    /**
     * 修改笔记
     * @param noteTitle
     * @param noteContent
     * @param overt
     * @param noteIdToUpdate
     * @return
     */
    Msg updateNote(String noteTitle, String noteContent, boolean overt,int noteIdToUpdate);

    /**
     * 加载表格数据
     * @param pageRequest
     * @return
     */
    TableDTO loadTableDTO(PageRequest pageRequest);

    /**
     * 搜索笔记
     * @param selectedNoteTitle
     * @return
     */
    Note selectByNoteTitle(String selectedNoteTitle);

    /**
     * 删除笔记
     * @param noteTitles
     * @return
     */
    Msg deleteNote(String[] noteTitles);

    TableDTO loadTableDTOD(PageRequest pageRequest);

    TableDTO loadTableDTOJ(PageRequest pageRequest);
}
