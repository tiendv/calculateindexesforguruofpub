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
public class PCMemberDTO {
    int idPCMember;
    String pcMemberName;    
    String image;
    String emailAddress;
    String website;
    String organization;

    public PCMemberDTO() {
    }

    public PCMemberDTO(int idPCMember, String pcMemberName, String image, String emailAddress, String website, String organization) {
        this.idPCMember = idPCMember;
        this.pcMemberName = pcMemberName;        
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

    public int getIdPCMember() {
        return idPCMember;
    }

    public void setIdPCMember(int idPCMember) {
        this.idPCMember = idPCMember;
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

    public String getPcMemberName() {
        return pcMemberName;
    }

    public void setPcMemberName(String pcMemberName) {
        this.pcMemberName = pcMemberName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }        
}
