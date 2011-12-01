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
public class PaperKeywordDTO {
    int idPaper;
    int idKeyword;

    public PaperKeywordDTO() {
    }

    public PaperKeywordDTO(int idPaper, int idKeyword) {
        this.idPaper = idPaper;
        this.idKeyword = idKeyword;
    }

    public int getIdKeyword() {
        return idKeyword;
    }

    public void setIdKeyword(int idKeyword) {
        this.idKeyword = idKeyword;
    }

    public int getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(int idPaper) {
        this.idPaper = idPaper;
    }
    
}
