/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uit.pubguru.dto;

/**
 *
 * @author Huynh Ngoc Tin
 * -----------------------
 * @author Loc Do
 * @since 10/08/2011
 * @version 1.1
 * @action: add new attributes
 * -------------------
 * @author Nghiep H. Tran
 * @since 19/08/2011 : add Constructor
 */
public class JournalDTO {
    private int idJournal;
    private String journalName;
    private String website;
    private int yearStart;
    private int yearEnd;    
    private String organization;
    private String url;
    //private int year;

    public JournalDTO() {
    }

    public JournalDTO(int idJournal, String journalName, String website, int yearStart, int yearEnd, String organization, String url) {
        this.idJournal = idJournal;
        this.journalName = journalName;
        this.website = website;
        this.yearStart = yearStart;
        this.yearEnd = yearEnd;
        this.organization = organization;
        this.url = url;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
    
    public int getIdJournal() {
        return idJournal;
    }

    public void setIdJournal(int idJournal) {
        this.idJournal = idJournal;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(int yearEnd) {
        this.yearEnd = yearEnd;
    }

    public int getYearStart() {
        return yearStart;
    }

    public void setYearStart(int yearStart) {
        this.yearStart = yearStart;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
