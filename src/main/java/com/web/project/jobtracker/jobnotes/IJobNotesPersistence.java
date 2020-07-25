package com.web.project.jobtracker.jobnotes;

import java.util.List;
/**
 * @author Anudish Jinturkar
 * Persistence interface containing persistence methods signature that will perform CRUD operation on job notes of DB.
 */

public interface IJobNotesPersistence {
    JobNotes save(JobNotes jobNotes);

    JobNotes update(JobNotes jobNotes);

    void delete(int jobNoteId);

    List<JobNotes> searchAll(String userID);

    JobNotes findById(int jobNoteId);
}
