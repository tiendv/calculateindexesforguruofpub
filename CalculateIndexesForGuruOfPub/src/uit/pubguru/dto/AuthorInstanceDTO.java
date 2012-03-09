/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.pubguru.dto;

/**
 *
 * @author Administrator
 * -------------------
 * @author Nghiep H. Tran
 * @since 19/08/2011 : add Constructor
 */
public class AuthorInstanceDTO {
    int autoID;
    String instanceName;
    int idAuthor;

    public AuthorInstanceDTO() {
    }

    public AuthorInstanceDTO(int autoID, String instanceName, int idAuthor) {
        this.autoID = autoID;
        this.instanceName = instanceName;
        this.idAuthor = idAuthor;
    }

    public int getAutoID() {
        return autoID;
    }

    public void setAutoID(int autoID) {
        this.autoID = autoID;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
        
}
