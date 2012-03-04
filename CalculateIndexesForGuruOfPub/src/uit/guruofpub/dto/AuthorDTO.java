package uit.guruofpub.dto;

import uit.guruofpub.dbaccess.AuthorMapper;

/**
 * @author Tin Huynh
 * @author Loc Do
 * @author Nghiep H. Tran
 */
public class AuthorDTO {
    private int idAuthor;
    private String authorName = null;    
    private String image = null;
    private String emailAddress = null;
    private String website = null;
    private int idOrg;
    private int h_index;
    private int g_index;
    private String url;
    private boolean unchangedH_index = true;
    private boolean unchangedG_index = true;
    
    public AuthorDTO() {
    }

    public AuthorDTO(int idAuthor, int idOrg, int h_index, int g_index, String url) {
        this.idAuthor = idAuthor;
        this.idOrg = idOrg;
        this.h_index = h_index;
        this.g_index = g_index;
        this.url = url;
    }
    
    public String getAuthorName() {
        return authorName;
    }
    
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public int getG_index() throws Exception {
        if (unchangedG_index) {
            AuthorMapper authorMapper = new AuthorMapper();
            setG_index(authorMapper.getG_index(idAuthor));
            unchangedG_index = false;
            authorMapper.closeConnection();
        }
        return g_index;
    }

    public void setG_index(int g_index) {
        this.g_index = g_index;
    }

    public int getH_index() throws Exception {
        if (unchangedH_index) {
            AuthorMapper authorMapper = new AuthorMapper();
            setH_index(authorMapper.getH_index(idAuthor));
            unchangedH_index = false;
            authorMapper.closeConnection();
        }
        return h_index;
    }

    public void setH_index(int h_index) {
        this.h_index = h_index;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }
    
    public int getIdOrg() {
        return idOrg;
    }
    
    public void setIdOrg(int idOrg) {
        this.idOrg = idOrg;
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
        
}

