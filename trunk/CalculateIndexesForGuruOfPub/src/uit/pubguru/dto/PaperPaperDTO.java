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
public class PaperPaperDTO {
    int idPaper;
    int idPaperRef;
    String citationContext;

    public PaperPaperDTO() {
    }

    public PaperPaperDTO(int idPaper, int idPaperRef, String citationContext) {
        this.idPaper = idPaper;
        this.idPaperRef = idPaperRef;
        this.citationContext = citationContext;
    }

    public String getCitationContext() {
        return citationContext;
    }

    public void setCitationContext(String citationContext) {
        this.citationContext = citationContext;
    }

    public int getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(int idPaper) {
        this.idPaper = idPaper;
    }

    public int getIdPaperRef() {
        return idPaperRef;
    }

    public void setIdPaperRef(int idPaperRef) {
        this.idPaperRef = idPaperRef;
    }
        
}
