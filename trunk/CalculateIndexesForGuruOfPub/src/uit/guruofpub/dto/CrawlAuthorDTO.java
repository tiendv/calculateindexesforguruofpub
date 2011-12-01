/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uit.guruofpub.dto;

/**
 *
 * @author Tin Huynh
 * -------------------
 * @author Nghiep H. Tran
 * @since 19/08/2011 : add Constructor
 */
public class CrawlAuthorDTO {
    private int authorID;
    private String authorName;
    private int crawlDepthLevel;
    private String url;

    public CrawlAuthorDTO() {
    }

    public CrawlAuthorDTO(int authorID, String authorName, int crawlDepthLevel, String url) {
        this.authorID = authorID;
        this.authorName = authorName;
        this.crawlDepthLevel = crawlDepthLevel;
        this.url = url;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getCrawlDepthLevel() {
        return crawlDepthLevel;
    }

    public void setCrawlDepthLevel(int crawlDepthLevel) {
        this.crawlDepthLevel = crawlDepthLevel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
