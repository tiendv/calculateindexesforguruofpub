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
 */
public class ReviewerDTO {
    int idReviewer;
    String reviewerName;    
    String image;
    String emailAddress;
    String website;
    String organization;

    public ReviewerDTO() {
    }

    public ReviewerDTO(int idReviewer, String reviewerName, String image, String emailAddress, String website, String organization) {
        this.idReviewer = idReviewer;
        this.reviewerName = reviewerName;        
        this.image = image;
        this.emailAddress = emailAddress;
        this.website = website;
        this.organization = organization;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getIdReviewer() {
        return idReviewer;
    }

    public void setIdReviewer(int idReviewer) {
        this.idReviewer = idReviewer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    
}
