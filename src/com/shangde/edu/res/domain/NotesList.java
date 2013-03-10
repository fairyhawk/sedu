package com.shangde.edu.res.domain;

import java.util.List;

public class NotesList {

	private int cusId;
    private int pointId;
    private int noteId;
    private List<Notes> noteList;
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public int getPointId() {
		return pointId;
	}
	public void setPointId(int pointId) {
		this.pointId = pointId;
	}
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public List<Notes> getNoteList() {
		return noteList;
	}
	public void setNoteList(List<Notes> noteList) {
		this.noteList = noteList;
	}
}
