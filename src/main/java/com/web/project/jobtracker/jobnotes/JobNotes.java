package com.web.project.jobtracker.jobnotes;

/**
 * @author Anudish Jinturkar
 * Schema of a job notes.
 */

public class JobNotes {
    private int NoteID;
    private String NoteDetails;
    private String UserID;

    public int getNoteID() { return NoteID; }
    public void setNoteID(int NoteID) { this.NoteID = NoteID; }

    public String getNoteDetails() { return NoteDetails; }
    public void setNoteDetails(String NoteDetails) { this.NoteDetails = NoteDetails; }

    public String getUserID() { return UserID; }
    public void setUserID(String UserID) { this.UserID = UserID; }

}
