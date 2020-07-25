package com.web.project.jobtracker.jobnotes;

import java.util.List;

public interface IJobNotesPersistence {
    JobNotes save(JobNotes jobNotes);

    JobNotes update(JobNotes jobNotes);

    void delete(int jobNoteId);

    List<JobNotes> searchAll(String userID);

    JobNotes findById(int jobNoteId);
}
