/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uit.guruofpub.dbaccess;

import java.sql.Connection;

/**
 *
 * @author Huynh Ngoc Tin
 */
public class AuthorKeywordMapper extends MapperDB {
    public AuthorKeywordMapper() throws Exception {
        super();
    }

    public AuthorKeywordMapper(Connection con) {
        super(con);
    }
}
