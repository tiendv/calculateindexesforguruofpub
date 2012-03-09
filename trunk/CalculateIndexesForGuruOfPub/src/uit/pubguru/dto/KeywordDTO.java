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
 * @author Loc Do
 * @since 20/08/2011: add URL
 */
public class KeywordDTO {
   int idKeyword;
   String keyword;
   String stemmingVariations;
   String url;
   
   public KeywordDTO() {
   }

   public KeywordDTO(int idKeyword, String keyword, String stemmingVariations, String url) {
        this.idKeyword = idKeyword;
        this.keyword = keyword;
        this.stemmingVariations = stemmingVariations;
        this.url = url;
   }

    public int getIdKeyword() {
        return idKeyword;
    }

    public void setIdKeyword(int idKeyword) {
        this.idKeyword = idKeyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStemmingVariations() {
        return stemmingVariations;
    }

    public void setStemmingVariations(String stemmingVariations) {
        this.stemmingVariations = stemmingVariations;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
   
    
}
