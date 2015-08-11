package simulator;

public  class QueryLoader {

	

	public static final String SQL_IMPORT_TIME = "SELECT IMPORTEXPORTID, DATALOCATION, DATETIMEENTERED, DATETIMEMODIFIED, RECORDCOUNT FROM IMPORTEXPORTLOG " +
	"WHERE "+ 
    "DATETIMEENTERED > TO_DATE('31/12/2010','DD/MM/YYYY') " +
    "AND " +
    "DATETIMEENTERED < TO_DATE('01/02/2014','DD/MM/YYYY') " +
    "AND  " +
    "IMPORTEXPORTTYPEID='1' " +
    "order by RECORDCOUNT DESC";
	
	public static final String SQL_IMPORT_TIME_COUNT = "SELECT COUNT(*) FROM IMPORTEXPORTLOG " +
	"WHERE "+ 
    "DATETIMEENTERED > TO_DATE('31/12/2010','DD/MM/YYYY') " +
    "AND " +
    "DATETIMEENTERED < TO_DATE('01/02/2014','DD/MM/YYYY') " +
    "AND  " +
    "IMPORTEXPORTTYPEID='1' " +
    "order by RECORDCOUNT DESC";
	
	public static final String SQL_KILLER = "SELECT count(*),partyid,releasebatchid from cashrecords " +
			"WHERE "+ 
		    "ACTUALVALUEDATE > TO_DATE('31/12/2004','DD/MM/YYYY')  " +
		    "AND " +
		    "ACTUALVALUEDATE < TO_DATE('31/12/2015','DD/MM/YYYY')  " +
		    "AND PARTYID ='TFPI' OR PARTYID = '0133' OR PARTYID = 'TDRH' " +
		    "group by partyid,releasebatchid";
			
	
	
}
