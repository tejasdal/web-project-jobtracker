package com.web.project.jobtracker.jobcontacts;

public class JobContacts {
    private Long ContactID;
    private String ContactName;
    private String ContactEmail;
    private String Company;
    private String JobPosition;

    public Long getContactID(){ return ContactID; }
    public void setContactID(Long ContactID){ this.ContactID = ContactID; }

    public String getContactName(){ return  ContactName; }
    public void setContactName(String ContactName){ this.ContactName = ContactName; }

    public String getContactEmail(){ return ContactEmail; }
    public void setContactEmail(String ContactEmail){ this.ContactEmail = ContactEmail; }

    public String getCompany(){ return Company; }
    public void setCompany(String Company){ this.Company = Company; }

    public String getJobPosition(){ return JobPosition; }
    public void setJobPosition(String JobPosition){ this.JobPosition = JobPosition; }

}
