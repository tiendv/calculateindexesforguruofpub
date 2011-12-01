/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.guruofpub.dto;

/**
 *
 * @author Loc Do
 * @since 14/08/2011
 * -------------------
 * @author Nghiep H. Tran
 * @since 19/08/2011 : add Constructor
 */
public class SubdomainPaperDTO {
    int idPaper;
    int idSubdomain;

    public SubdomainPaperDTO() {
    }

    public SubdomainPaperDTO(int idPaper, int idSubdomain) {
        this.idPaper = idPaper;
        this.idSubdomain = idSubdomain;
    }

    public int getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(int idPaper) {
        this.idPaper = idPaper;
    }

    public int getIdSubdomain() {
        return idSubdomain;
    }

    public void setIdSubdomain(int idSubdomain) {
        this.idSubdomain = idSubdomain;
    }
        
}
