/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uit.guruofpub.dto;

/**
 * @author Tin Huynh
 * @author Loc Do
 * @author Nghiep H. Tran
 */
public class AuthorPaperDTO {
    private int idAuthor;
    private int idPaper;

    public AuthorPaperDTO() {
    }

    public AuthorPaperDTO(int idAuthor, int idPaper) {
        this.idAuthor = idAuthor;
        this.idPaper = idPaper;
    }
    
	public int getIdAuthor() {
		return idAuthor;
	}
	public void setIdAuthor(int idAuthor) {
		this.idAuthor = idAuthor;
	}
	public int getIdPaper() {
		return idPaper;
	}
	public void setIdPaper(int idPaper) {
		this.idPaper = idPaper;
	}
    
}
