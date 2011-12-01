package uit.guruofpub.dto;

import java.util.ArrayList;
import uit.guruofpub.dbaccess.ConferenceMapper;
import uit.guruofpub.dbaccess.JournalMapper;
import uit.guruofpub.dbaccess.PaperMapper;

/**
 * @author Tin Huynh
 * @author Loc Do
 * @author Nghiep H. Tran
 */
public class PaperDTO {
    private int idPaper;
    private String doi;
    private String isbn;
    private String url;
    private String title;
    private String abstractContent;
    private String volume;
    private String pages;
    private int year;
    private String viewPublication;
    private String bibTex;
    private String endNote;    
    private int idJournal;
    private int idConference;
    private int idMagazine;
    private int version;
    private String paperFile;
    private ArrayList authorList;
    private String conferenceName;
    private String journalName;
    private int citationCount = -1;

    public PaperDTO() {
    }

    public int getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(int idPaper) {
        this.idPaper = idPaper;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public String getBibTex() {
        return bibTex;
    }

    public void setBibTex(String bibTex) {
        this.bibTex = bibTex;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getEndNote() {
        return endNote;
    }

    public void setEndNote(String endNote) {
        this.endNote = endNote;
    }

    public int getIdConference() {
        return idConference;
    }

    public void setIdConference(int idConference) {
        this.idConference = idConference;
    }

    public int getIdJournal() {
        return idJournal;
    }

    public void setIdJournal(int idJournal) {
        this.idJournal = idJournal;
    }

    public int getIdMagazine() {
        return idMagazine;
    }

    public void setIdMagazine(int idMagazine) {
        this.idMagazine = idMagazine;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getViewPublication() {
        return viewPublication;
    }

    public void setViewPublication(String viewPublication) {
        this.viewPublication = viewPublication;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPaperFile() {
        return paperFile;
    }

    public void setPaperFile(String paperFile) {
        this.paperFile = paperFile;
    }

    /**
     * @return the authorList
     */
    public ArrayList getAuthorList() throws Exception{
        if (authorList == null) {
            PaperMapper paperMapper = new PaperMapper();
            setAuthorList(paperMapper.getAuthorDTOList(idPaper));
        }
            
        return authorList;
    }

    /**
     * @param authorList the authorList to set
     */
    public void setAuthorList(ArrayList authorList) {
        this.authorList = authorList;
    }

    /**
     * @return the conferenceName
     */
    public String getConferenceName() throws Exception {
        if (conferenceName == null) {
            ConferenceMapper conferenceMapper = new ConferenceMapper();
            setConferenceName(conferenceMapper.getConferenceName(idConference));
        }
        
        return conferenceName;
    }

    /**
     * @param conferenceName the conferenceName to set
     */
    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    /**
     * @return the journalName
     */
    public String getJournalName() throws Exception {
        if (journalName == null) {
            JournalMapper journalMapper = new JournalMapper();
            setJournalName(journalMapper.getJournalName(idJournal));
        }
        
        return journalName;
    }

    /**
     * @param journalName the journalName to set
     */
    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    /**
     * @return the citationCount
     */
    public int getCitationCount() throws Exception {
        if (citationCount == -1) {
            PaperMapper paperMapper = new PaperMapper();
            setCitationCount(paperMapper.getCitationCount(idPaper));
        }
        return citationCount;
    }

    /**
     * @param citationCount the citationCount to set
     */
    public void setCitationCount(int citationCount) {
        this.citationCount = citationCount;
    }
}
