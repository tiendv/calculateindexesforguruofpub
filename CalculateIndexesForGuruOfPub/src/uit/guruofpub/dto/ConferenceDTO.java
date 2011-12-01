/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uit.guruofpub.dto;

import java.util.ArrayList;

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
 * -------------------
 * @author Loc Do
 * @since 20/08/2011: add URL
 */
public class ConferenceDTO {
    private int idConference;
    private String conferenceName;
    private String website;
    private String organization;
    private String organizedLocation;
    private String duration;
    private int yearStart;
    private int yearEnd;
    private String url;
    //private ArrayList authorListOfProgCommittee;

    public ConferenceDTO() {
    }

    public ConferenceDTO(int idConference, String conferenceName, String website, String organization, String organizedLocation, String duration, int yearStart, int yearEnd, String url) {
        this.idConference = idConference;
        this.conferenceName = conferenceName;
        this.website = website;
        this.organization = organization;
        this.organizedLocation = organizedLocation;
        this.duration = duration;
        this.yearStart = yearStart;
        this.yearEnd = yearEnd;
        this.url = url;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganizedLocation() {
        return organizedLocation;
    }

    public void setOrganizedLocation(String organizedLocation) {
        this.organizedLocation = organizedLocation;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getIdConference() {
        return idConference;
    }

    public void setIdConference(int idConference) {
        this.idConference = idConference;
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
