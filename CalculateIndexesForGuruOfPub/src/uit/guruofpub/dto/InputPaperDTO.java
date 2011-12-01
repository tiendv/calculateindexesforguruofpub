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
public class InputPaperDTO {
    private String title;
    private int depthLevelForCoAuthorNet;
    private ArrayList authorNameList;
    private String conferenceName;
    private String sigName;

    public InputPaperDTO() {
    }

    public InputPaperDTO(String title, int depthLevelForCoAuthorNet, ArrayList authorNameList, String conferenceName, String sigName) {
        this.title = title;
        this.depthLevelForCoAuthorNet = depthLevelForCoAuthorNet;
        this.authorNameList = authorNameList;
        this.conferenceName = conferenceName;
        this.sigName = sigName;
    }

    public ArrayList getAuthorNameList() {
        return authorNameList;
    }

    public void setAuthorNameList(ArrayList authorNameList) {
        this.authorNameList = authorNameList;
    }

    public int getDepthLevelForCoAuthorNet() {
        return depthLevelForCoAuthorNet;
    }

    public void setDepthLevelForCoAuthorNet(int depthLevelForCoAuthorNet) {
        this.depthLevelForCoAuthorNet = depthLevelForCoAuthorNet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getSigName() {
        return sigName;
    }

    public void setSigName(String sigName) {
        this.sigName = sigName;
    }
}
