/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.pubguru.dto;

/**
 *
 * @author Loc Do
 * @since 14/08/2011
 * -------------------
 * @author Nghiep H. Tran
 * @since 19/08/2011 : add Constructor
 * @author Loc Do
 * @since 20/08/2011 add url
 */
public class MagazineDTO {
    int idMagazine;
    String magazineName;
    String website;
    int yearStart;
    int yearEnd;
    String organization;
    String url;
    
    public MagazineDTO() {
    }

    public MagazineDTO(int idMagazine, String magazineName, String website, int yearStart, int yearEnd, String organization, String url) {
        this.idMagazine = idMagazine;
        this.magazineName = magazineName;
        this.website = website;
        this.yearStart = yearStart;
        this.yearEnd = yearEnd;
        this.organization = organization;
        this.url = url;
    }

    public int getIdMagazine() {
        return idMagazine;
    }

    public void setIdMagazine(int idMagazine) {
        this.idMagazine = idMagazine;
    }

    public String getMagazineName() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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
