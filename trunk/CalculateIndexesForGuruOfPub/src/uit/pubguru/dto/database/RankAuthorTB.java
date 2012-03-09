/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.pubguru.dto.database;

/**
 *
 * @author Tran Hung Nghiep
 */
public class RankAuthorTB {
    
    //The TableName of table _rank_author
    public static final String TABLE_NAME = "_rank_author";

    //The Columns of table _rank_author
    public static final String COLUMN_AUTHORID = "idAuthor";
    public static final String COLUMN_PUBLICAITONCOUNT = "publicationCount";
    public static final String COLUMN_CITATIONCOUNT = "citationCount";
    public static final String COLUMN_RANK = "rank";
    public static final String COLUMN_COAUTHORCOUNT = "coAuthorCount";
    public static final String COLUMN_H_INDEX = "h_index";
    public static final String COLUMN_G_INDEX = "g_index";
}
