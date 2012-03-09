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
public class ConferencePCMemberDTO {
    int idConference;
    int idPCMember;
    int year;

    public ConferencePCMemberDTO() {
    }

    public ConferencePCMemberDTO(int idConference, int idPCMember, int year) {
        this.idConference = idConference;
        this.idPCMember = idPCMember;
        this.year = year;
    }

    public int getIdConference() {
        return idConference;
    }

    public void setIdConference(int idConference) {
        this.idConference = idConference;
    }

    public int getIdPCMember() {
        return idPCMember;
    }

    public void setIdPCMember(int idPCMember) {
        this.idPCMember = idPCMember;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
       
}
