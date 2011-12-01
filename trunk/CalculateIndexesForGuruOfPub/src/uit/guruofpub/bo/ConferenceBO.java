/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * -------------------
 */

package uit.guruofpub.bo;


import java.util.ArrayList;
import java.util.HashMap;
import uit.guruofpub.dbaccess.*;
import uit.guruofpub.dto.*;
import uit.guruofpub.utility.GuruOfPubLogger;

/**
 * @author Nghiep H. Tran
 * @author Huynh Ngoc Tin
 * @author Loc Do
 * @author Huong Tran
 */

public class ConferenceBO {

     /**
     * check if the specified conference exist in the DB
     * @param confDTO
     * @return
     * @throws Exception
     */
    public boolean isConferenceExisted(ConferenceDTO confDTO) throws Exception {
        Boolean flag = false;
        ConferenceMapper mapper = null;
        try {
            mapper = new ConferenceMapper();
            if (mapper.isExisted(confDTO)!=-1) flag = true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }
        finally {
            mapper.closeConnection();
        }
        return flag;
    }

    /**
     * insert a new conference into the DB
     * @param confDTO
     * @return
     * @throws Exception
     */
    public int insertNewConference(ConferenceDTO confDTO) throws Exception {
        ConferenceMapper mapper = null;
        try {
            mapper = new ConferenceMapper();
            return mapper.insertObj(confDTO);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }
        finally {
            mapper.closeConnection();
        }
    }

    /**
     * get confID from conferenceName and year
     * @param confName
     * @param year
     * @return
     * @throws Exception
     */
    public int getConferenceID(String confName) throws Exception {
        ConferenceMapper mapper = null;
        try {
            mapper = new ConferenceMapper();
            ConferenceDTO conference = new ConferenceDTO();
            conference.setConferenceName(confName);            
            return mapper.isExisted(conference);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }
        finally {
            mapper.closeConnection();
        }
    }

    /**
     * calculateTotalPaperInOneConference
     * @param authorIDList
     * @param confName
     * @return
     * @throws Exception
     */
    public double calculateTotalPaperInOneConference(ArrayList authorIDList, String confName) throws Exception {
        AuthorPaperMapper authorPaperMapper = new AuthorPaperMapper();
        double totalPaperInOneConference = 0;
        try {
            for (int j=0; j<authorIDList.size(); j++) {
                int curAuthorID = ((Integer)authorIDList.get(j)).intValue() ;
            double totalPaperOfAuthorInConf = authorPaperMapper.countTotalPaperInConfOfAnAuthor(curAuthorID, confName);
                totalPaperInOneConference += totalPaperOfAuthorInConf;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }
        finally {
            authorPaperMapper.closeConnection();
        }

        return totalPaperInOneConference;
    }

    /**
     * calculateTotalPaperInEachConference
     * @param authorIDList
     * @param confNameList
     * @return
     * @throws Exception
     */
    public HashMap calculateTotalPaperInEachConference(ArrayList authorIDList, ArrayList confNameList) throws Exception {
        HashMap totalPaperConfHashMap = new HashMap();
        AuthorPaperMapper authorPaperMapper = new AuthorPaperMapper();
        try {
            for (int i=0; i<confNameList.size(); i++) {
                String confName = (String) confNameList.get(i);
                double sum = 0;
                for (int j=0; j<authorIDList.size(); j++) {
                    int curAuthorID = ((Integer) authorIDList.get(j)).intValue();
                    double totalPaperOfAuthorInConf = authorPaperMapper.countTotalPaperInConfOfAnAuthor(curAuthorID, confName);
                    sum += totalPaperOfAuthorInConf;
                }

                totalPaperConfHashMap.put(confName, new Double(sum));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }
        finally {
            authorPaperMapper.closeConnection();
        }

        return totalPaperConfHashMap;
    }

    /**
     * Metrics #2: calculateTotalConferencePercentage
     * @param authorIDList
     * @return
     * @throws Exception
     */
    public HashMap calculateTotalConferencePercentage(ArrayList authorIDList) throws Exception {
        HashMap confPaperPercentHashMap = new HashMap();
        ConferenceMapper confMapper = new ConferenceMapper();
        AuthorPaperMapper authorPaperMapper = new AuthorPaperMapper();
        try {
            ArrayList confNameList = confMapper.getConferenceNameListForAuthorList(authorIDList);
            for (int i=0; i<confNameList.size(); i++) {
                String confName = (String) confNameList.get(i);
                double percentSum = 0;
                for (int j=0; j<authorIDList.size(); j++) {
                    int curAuthorID = Integer.parseInt((String) authorIDList.get(j)) ;
                    double totalPaperOfAuthor = authorPaperMapper.countTotalPaperOfAnAuthor(curAuthorID);
                    double totalPaperOfAuthorInConf = authorPaperMapper.countTotalPaperInConfOfAnAuthor(curAuthorID, confName);
                    double percentPaperOfAuthorInConf = totalPaperOfAuthorInConf/totalPaperOfAuthor;
                    percentSum += percentPaperOfAuthorInConf;
                }
                confPaperPercentHashMap.put(confName, new Double(percentSum));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }
        finally {
            confMapper.closeConnection();
            authorPaperMapper.closeConnection();
        }

        return confPaperPercentHashMap;
    }

    /**
     * calculateTotalConferencePercentage
     * @param authorIDList
     * @param confNameList
     * @return
     * @throws Exception
     */
    public HashMap calculateTotalConferencePercentage(ArrayList authorIDList, ArrayList confNameList) throws Exception {
        HashMap confPaperPercentHashMap = new HashMap();
        AuthorPaperMapper authorPaperMapper = new AuthorPaperMapper();
        try {
            for (int i=0; i<confNameList.size(); i++) {
                String confName = (String) confNameList.get(i);
                double percentSum = 0;
                for (int j=0; j<authorIDList.size(); j++) {
                    int curAuthorID = Integer.parseInt((String) authorIDList.get(j)) ;
                    double totalPaperOfAuthor = authorPaperMapper.countTotalPaperOfAnAuthor(curAuthorID);
                    double totalPaperOfAuthorInConf = authorPaperMapper.countTotalPaperInConfOfAnAuthor(curAuthorID, confName);
                    double percentPaperOfAuthorInConf = totalPaperOfAuthorInConf/totalPaperOfAuthor;
                    percentSum += percentPaperOfAuthorInConf;
                }

                confPaperPercentHashMap.put(confName, new Double(percentSum));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }
        finally {
            authorPaperMapper.closeConnection();
        }

        return confPaperPercentHashMap;
    }

    /**
     * Metrics #3: calculateTotalConfPercentWithCoAuthor
     * @param authorVertexIDList
     * @param initialAuthorIDList
     * @return
     * @throws Exception
     */
    public HashMap calculateTotalConfPercentWithCoAuthor(ArrayList authorVertexIDList, ArrayList initialAuthorIDList) throws Exception {
        ConferenceMapper confMapper = new ConferenceMapper();
        AuthorPaperMapper authorPaperMapper = new AuthorPaperMapper();

        HashMap totalConfPercentWithCoAuthorHashMap = new HashMap();
        try {
            ArrayList confNameList = confMapper.getConferenceNameListForAuthorList(authorVertexIDList);

            // Loop through all of conferences in the CoAuthor-Network
            for (int i=0; i<confNameList.size(); i++) {
                String confName = (String) confNameList.get(i);
                double sumConfPercentWithCoAuthor = 0;
                for (int j=0; j<initialAuthorIDList.size(); j++) {
                    double curTotalConfPercentWithCoAuthor = 0;
                    int curInitialAuthorID = ((Integer) initialAuthorIDList.get(j)).intValue();
                    
                    // get % papers of curInitialAuthorID in the current conference
                    double totalPaperOfCurInitialAuthorInConf = authorPaperMapper.countTotalPaperInConfOfAnAuthor(curInitialAuthorID, confName);
                    double totalPaperOfCurInitialAuthor = authorPaperMapper.countTotalPaperOfAnAuthor(curInitialAuthorID);
                    double percentPaperOfCurInitialAuthorInConf = totalPaperOfCurInitialAuthorInConf/totalPaperOfCurInitialAuthor;

                    // get the Co-AuthorList of curInitialAuthorID
                    ArrayList coAuthorIDList = authorPaperMapper.getCoIDAuthorList(curInitialAuthorID);

                    // Loop through all co-authors of curInitialAuthorID
                    for (int k=0; k<coAuthorIDList.size(); k++) {
                        int coAuthorID = ((Integer) coAuthorIDList.get(k)).intValue();
                        // get % papers of coAuthorID in the current conference
                        double totalPaperOfAnCoAuthorInConf = authorPaperMapper.countTotalPaperInConfOfAnAuthor(coAuthorID, confName);
                        double totalPaperOfAnCoAuhor = authorPaperMapper.countTotalPaperOfAnAuthor(coAuthorID);
                        double percentPaperOfCoAuthorInConf = totalPaperOfAnCoAuthorInConf/totalPaperOfAnCoAuhor;
                        // count the number of papers which coAuthorID & curInitialAuthorID is the co-author
                        double numberOfPapersCoAuthor = authorPaperMapper.countTotalPaperOfTwoAuthorIsCoAuthor(curInitialAuthorID, coAuthorID);

                        curTotalConfPercentWithCoAuthor += (percentPaperOfCurInitialAuthorInConf + percentPaperOfCoAuthorInConf)
                                                                *numberOfPapersCoAuthor;
                    } // end of while (loop all of co-authors)

                    sumConfPercentWithCoAuthor += curTotalConfPercentWithCoAuthor;
                }

                totalConfPercentWithCoAuthorHashMap.put(confName, new Double(sumConfPercentWithCoAuthor));
            } // end of while(...)
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }
        finally {
            confMapper.closeConnection();
            authorPaperMapper.closeConnection();
        }

        return totalConfPercentWithCoAuthorHashMap;
    }

    /**
     * Metric #3: calculateTotalConfPercentWithCoAuthor
     * @param authorVertexIDList
     * @param initialAuthorIDList
     * @param confNameList
     * @return
     * @throws Exception
     */
    public HashMap calculateTotalConfPercentWithCoAuthor(ArrayList authorVertexIDList, ArrayList initialAuthorIDList, ArrayList confNameList) throws Exception {
        ConferenceMapper confMapper = new ConferenceMapper();
        AuthorPaperMapper authorPaperMapper = new AuthorPaperMapper();

        HashMap totalConfPercentWithCoAuthorHashMap = new HashMap();
        try {
            // Loop through all of conferences in the list - 16 conference_classification for testing
            for (int i=0; i<confNameList.size(); i++) {
                String confName = (String) confNameList.get(i);
                double sumConfPercentWithCoAuthor = 0;
                for (int j=0; j<initialAuthorIDList.size(); j++) {
                    double curTotalConfPercentWithCoAuthor = 0;
                    int curInitialAuthorID = ((Integer) initialAuthorIDList.get(j)).intValue();

                    // get % papers of curInitialAuthorID in the current conference
                    double totalPaperOfCurInitialAuthorInConf = authorPaperMapper.countTotalPaperInConfOfAnAuthor(curInitialAuthorID, confName);
                    double totalPaperOfCurInitialAuthor = authorPaperMapper.countTotalPaperOfAnAuthor(curInitialAuthorID);
                    double percentPaperOfCurInitialAuthorInConf = totalPaperOfCurInitialAuthorInConf/totalPaperOfCurInitialAuthor;

                    // get the Co-AuthorList of curInitialAuthorID
                    ArrayList coAuthorIDList = authorPaperMapper.getCoIDAuthorList(curInitialAuthorID);

                    // Loop through all co-authors of curInitialAuthorID
                    for (int k=0; k<coAuthorIDList.size(); k++) {
                        int coAuthorID = ((Integer) coAuthorIDList.get(k)).intValue();
                        // get % papers of coAuthorID in the current conference
                        double totalPaperOfAnCoAuthorInConf = authorPaperMapper.countTotalPaperInConfOfAnAuthor(coAuthorID, confName);
                        double totalPaperOfAnCoAuhor = authorPaperMapper.countTotalPaperOfAnAuthor(coAuthorID);
                        double percentPaperOfCoAuthorInConf = totalPaperOfAnCoAuthorInConf/totalPaperOfAnCoAuhor;
                        // count the number of papers which coAuthorID & curInitialAuthorID is the co-author
                        double numberOfPapersCoAuthor = authorPaperMapper.countTotalPaperOfTwoAuthorIsCoAuthor(curInitialAuthorID, coAuthorID);

                        curTotalConfPercentWithCoAuthor += (percentPaperOfCurInitialAuthorInConf + percentPaperOfCoAuthorInConf)
                                                                *numberOfPapersCoAuthor;
                    } // end of while (loop all of co-authors)

                    sumConfPercentWithCoAuthor += curTotalConfPercentWithCoAuthor;
                }

                totalConfPercentWithCoAuthorHashMap.put(confName, new Double(sumConfPercentWithCoAuthor));
            } // end of while(...)
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }
        finally {
            confMapper.closeConnection();
            authorPaperMapper.closeConnection();
        }

        return totalConfPercentWithCoAuthorHashMap;
    }
}
