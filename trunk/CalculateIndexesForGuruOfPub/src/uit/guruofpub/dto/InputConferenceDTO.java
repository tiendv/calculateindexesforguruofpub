/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uit.guruofpub.dto;

import java.util.ArrayList;

/**
 *
 * @author Tin Huynh
 * -------------------
 * @author Nghiep H. Tran
 * @since 19/08/2011 : add Constructor
 */
public class InputConferenceDTO {
    private String confName;
    private String sigName;
    private ArrayList paperList;
    private ArrayList pcMemberList;

    public InputConferenceDTO() {
    }

    public InputConferenceDTO(String confName, String sigName, ArrayList paperList, ArrayList pcMemberList) {
        this.confName = confName;
        this.sigName = sigName;
        this.paperList = paperList;
        this.pcMemberList = pcMemberList;
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public ArrayList getPaperList() {
        return paperList;
    }

    public void setPaperList(ArrayList paperList) {
        this.paperList = paperList;
    }

    public ArrayList getPcMemberList() {
        return pcMemberList;
    }

    public void setPcMemberList(ArrayList pcMemberList) {
        this.pcMemberList = pcMemberList;
    }

    public String getSigName() {
        return sigName;
    }

    public void setSigName(String sigName) {
        this.sigName = sigName;
    }

}
