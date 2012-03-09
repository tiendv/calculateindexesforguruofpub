/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.pubguru.dto;

/**
 * @author Loc Do
 * @since 11/08/2011
 * @version 1.0
 * -------------------
 * @author Nghiep H. Tran
 * @since 19/08/2011 : add Constructor
 */
public class DomainDTO {
    private int idDomain;
    private String domainName;

    public DomainDTO() {
    }

    public DomainDTO(int idDomain, String domainName) {
        this.idDomain = idDomain;
        this.domainName = domainName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public int getIdDomain() {
        return idDomain;
    }

    public void setIdDomain(int idDomain) {
        this.idDomain = idDomain;
    }
}
