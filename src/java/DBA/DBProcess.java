package DBA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Ngo Quang Trong
 */
public class DBProcess {
    
    DBContext dbContext = new DBContext();
    private String queryString = null;

    public DBProcess() {
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
    
    
    public ResultSet getList() throws Exception{
        if(queryString!=null){
            Connection conn = dbContext.getConnection();
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(queryString);
            if(!result.next()) return null;
            else {
                return result;
            }
        }
        return null;
    }
    
    public boolean insert(String... params) throws Exception{
        if(queryString!=null){
            Connection conn = dbContext.getConnection();
            PreparedStatement pst = conn.prepareStatement(queryString);
            for(int i=0;i<params.length;i++){
                pst.setString(i+1, params[i]);
            }
            pst.execute();
            return true;
        }
        else return false;
    }
    
    
    public int getLastID() throws Exception{
        if(queryString!=null){
            Connection conn = dbContext.getConnection();
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(queryString);
            if(!result.next()) return -1;
            else {
                return result.getInt(1);
            }
        }
        return -1;
    }
    
}
