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
public class PaperReviewerDTO {
    int idPaper;
    int idReviewer;
    int rating;
    String content;

    public PaperReviewerDTO() {
    }

    public PaperReviewerDTO(int idPaper, int idReviewer, int rating, String content) {
        this.idPaper = idPaper;
        this.idReviewer = idReviewer;
        this.rating = rating;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(int idPaper) {
        this.idPaper = idPaper;
    }

    public int getIdReviewer() {
        return idReviewer;
    }

    public void setIdReviewer(int idReviewer) {
        this.idReviewer = idReviewer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }        
}
