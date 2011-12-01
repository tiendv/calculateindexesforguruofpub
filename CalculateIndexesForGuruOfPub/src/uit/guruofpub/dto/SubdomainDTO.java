/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.guruofpub.dto;

/**
 * @author Loc Do
 * @since 11/08/2011
 * @version 1.0
 * -------------------
 * @author Nghiep H. Tran
 * @since 19/08/2011 : add Constructor
 */
public class SubdomainDTO {
    private int idSubdomain;
    private String subdomainName;
    private int idDomain;

    public SubdomainDTO() {
    }

    public SubdomainDTO(int idSubdomain, String subdomainName, int idDomain) {
        this.idSubdomain = idSubdomain;
        this.subdomainName = subdomainName;
        this.idDomain = idDomain;
    }

    public int getIdDomain() {
        return idDomain;
    }

    public void setIdDomain(int idDomain) {
        this.idDomain = idDomain;
    }

    public int getIdSubdomain() {
        return idSubdomain;
    }

    public void setIdSubdomain(int idSubdomain) {
        this.idSubdomain = idSubdomain;
    }

    public String getSubdomainName() {
        return subdomainName;
    }

    public void setSubdomainName(String subdomainName) {
        this.subdomainName = subdomainName;
    }
}
