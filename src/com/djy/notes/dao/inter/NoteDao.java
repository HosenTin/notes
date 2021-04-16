package com.djy.notes.dao.inter;

import com.djy.notes.bean.PageRequest;
import com.djy.notes.bean.TableDTO;
import com.djy.notes.entity.Note;

public interface NoteDao {
    /**
     * 新增笔记
     * @param note
     * @return
     */
    boolean addNote(Note note);

    /**
     * 加载表格
     * @param pageRequest
     * @return
     */
    TableDTO loadTableDTO(PageRequest pageRequest);

    /**
     * 通过笔记标题查询
     * @param selectedNoteTitle
     * @return
     */
    Note selectByNoteTitle(String selectedNoteTitle);

    /**
     * 修改笔记
     * @param note
     * @return
     */
    boolean updateNote(Note note);

    /**
     * 删除笔记
     * @param noteTitles
     * @return
     */
    boolean deleteNote(String[] noteTitles);

    TableDTO loadTableDTOD(PageRequest pageRequest);

    TableDTO loadTableDTOJ(PageRequest pageRequest);
}
