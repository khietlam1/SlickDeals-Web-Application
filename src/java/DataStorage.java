//Group file week3

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public interface DataStorage {

  
    String getType(String accid);
    
    String createReply(String parentid, String title, String content, String author);
    
     ArrayList<Thread> getMyThreads(String accid);
    
    
    String createThread(String parentid, String title, String Content, String author, LocalDateTime timestamp, int rating, String status);

    ArrayList<String> getAllThreadIds();
    
    ArrayList<Thread> getqualifiedthreadids();
    
    String getchangeStatus(String threadid);

    
    ArrayList<Thread> getthreads(String searchParam); 

    ArrayList<Thread> getbestthreads();
    
 
  String Signup( String accid, String pw);

    String login(String accid, String pw);

    void updatestatus(String status);

    void publishThread(String threadid);

    ArrayList<Thread> getreplies(String threadid);

    void updateThread(String threadid, String status);
    
    int rateThread(String threadid,int rating);
    
    Thread getthreaddetail(String threadid);
    
   
    
 }
