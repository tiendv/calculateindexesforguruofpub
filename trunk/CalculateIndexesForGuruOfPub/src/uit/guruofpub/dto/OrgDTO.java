/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.guruofpub.dto;

import uit.guruofpub.dbaccess.OrgMapper;

/**
 * @author Loc Do
 * @since 11/08/2011
 * @version 1.0
 * -------------------
 * @author Nghiep H. Tran
 * @since 19/08/2011 : add Constructor
 */
public class OrgDTO {
    private int idOrg;
    private String orgName;
    private String website;
    private String continent;
    private int idOrgParent;
    private int h_index;
    private int g_index;
    private String url;
    private boolean unchangedH_index = true;
    private boolean unchangedG_index = true;
    
    public OrgDTO() {
    }

    public OrgDTO(int idOrg, String orgName, String website, String continent, int idOrgParent, int h_index, String url) {
        this.idOrg = idOrg;
        this.orgName = orgName;
        this.website = website;
        this.continent = continent;
        this.idOrgParent = idOrgParent;
        this.h_index = h_index;
        this.url = url;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(int idOrg) {
        this.idOrg = idOrg;
    }

    public int getIdOrgParent() {
        return idOrgParent;
    }

    public void setIdOrgParent(int idOrgParent) {
        this.idOrgParent = idOrgParent;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the g_index
     */
    public int getG_index() throws Exception {
        if (unchangedG_index) {
            OrgMapper orgMapper = new OrgMapper();
            setG_index(orgMapper.getG_index(idOrg));
            unchangedG_index = false;
            orgMapper.closeConnection();
        }
        return g_index;
    }

    /**
     * 
     * @param g_index 
     */
    public void setG_index(int g_index) {
        this.g_index = g_index;
    }

    /**
     * 
     * @return
     * @throws Exception 
     */
    public int getH_index() throws Exception {
        if (unchangedH_index) {
            OrgMapper orgMapper = new OrgMapper();
            setH_index(orgMapper.getH_index(idOrg));
            unchangedH_index = false;
            orgMapper.closeConnection();
        }
        return h_index;
    }

    /**
     * 
     * @param h_index 
     */
    public void setH_index(int h_index) {
        this.h_index = h_index;
    }
}
