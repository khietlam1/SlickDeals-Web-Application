//Group file week 3
import java.util.ArrayList;
import javax.faces.context.FacesContext;

public abstract class Account {

    private String accid;
    private String pw;
    private Thread threaddetail;
    private DataStorage data = new SQL_Database();
    private ArrayList<Thread> bestthreads;
    private ArrayList<Thread> myThreads;
     private String searchParam = "";

    public String getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(String searchParam) {
        this.searchParam = searchParam;
    }
    
    public ArrayList<Thread> getBestthreads() {
       bestthreads = data.getbestthreads();
        return bestthreads;
    }

    public Thread getThreaddetail() {
        return threaddetail;
    }

    public void setThreaddetail(Thread threaddetail) {
        this.threaddetail = threaddetail;
    }
    public DataStorage getData() {
        return data;
    }

    public void setData(DataStorage data) {
        this.data = data;
    }

    public Account(String accid, String pw) {
        this.accid = accid;
        this.pw = pw;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public ArrayList<String> getAllThreadIds(){
        
        return data.getAllThreadIds();
        
    }
    
    public ArrayList<Thread> getAllThreads(){
        
        return data.getthreads(searchParam);
        
    }
    
       public ArrayList<Thread> getbestthreads(){
        
        return data.getbestthreads();
        
    }
        public ArrayList<Thread> getReplies(String parentid){
        
        return data.getreplies(parentid);
        }
        public ArrayList<Thread> getMyThreads(String accid){
            return data.getMyThreads(accid);
        }
     
     public ArrayList<Thread> getqualifiedthreadids(){
         return data.getqualifiedthreadids();
     }
    
     public String getchangestatus(String threadid){
         return data.getchangeStatus(threadid);
     }
     public String rateThread( int n){
    int i = data.rateThread(threaddetail.getThreadid(), n); //User
        if (i == 1) {
            threaddetail.setRating(threaddetail.getRating() + n);
        }
    return "viewthreaddetail.xhtml";
    }//User

    public abstract String updateThread(String threadid, String status); //Editor

    public String signOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";

    }
 public String getthreaddetail(String threadid) {
     
        if (threadid.equals("null1")) return "viewthreaddetail.xhtml";
        else threaddetail = data.getthreaddetail(threadid);
        return "viewthreaddetail.xhtml";
    }
  
}
