//Group file week 3

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SQL_Database implements DataStorage {

    final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/shahd9440?useSSL=false";
    /*final String db_id = "kondurup8985";
    final String db_psw = "1926223";*/
    final String db_id = "shahd9440";
    final String db_psw = "1771687";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String accid;
   
   
    public void getAccid(String accid) {
        this.accid = accid;
    }

    @Override
    public String getType(String accid) {
        try {

            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_psw);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select acctype from account where accid = '" + accid + "'");
            if (resultSet.next()) {
                return  resultSet.getString("acctype");
               
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            //close the database
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
 @Override
  public ArrayList<Thread> getMyThreads(String accid) {
        try {
           
            connection = DriverManager.getConnection(DATABASE_URL,
                    db_id, db_psw);
            
            statement = connection.createStatement();

            ArrayList<Thread>Threads = new ArrayList<Thread>();

            resultSet = statement.executeQuery(
                    "select * from thread "
                    + "where author = '"+ accid + "'"
            );

            while (resultSet.next()) {
                try{
                Threads.add(
                            new Thread(resultSet.getString("threadid"), 
                                    resultSet.getString("parentid"), resultSet.getString("title"), 
                                    resultSet.getString("content"),resultSet.getString("author"),
                                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("timestamp")),
                                    resultSet.getInt("rating"),resultSet.getString("status")
                            )
                    ) ;
            } catch (ParseException ex) {
                    Logger.getLogger(SQL_Database.class.getName()).log(Level.SEVERE, null, ex);
                }
           
            }
 return Threads;
            

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            //close the database
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        
    }
    @Override
    public String Signup(String accid, String pw) {

        try {

            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                    db_id, db_psw);
            //crate the statement
            statement = connection.createStatement();
            statement.executeUpdate("insert into account values"
                    + "('" + accid + "', '" + pw + "', 'user')");
            return ("Account created successfully");

        } catch (SQLException e) {
            return "internalError";

        } finally {
            //close the database
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String login(String accid, String pw) {

        try {

            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                    db_id, db_psw);
            //create statement
            statement = connection.createStatement();
            //search the accountID in the onlineAccount table
            resultSet = statement.executeQuery("Select * from account "
                    + "where accid = '" + accid + "' and pw = '" + pw + "'");

            if (resultSet.next()) {

                return "welcome";
                //go to the welcome page 
            }

            return "loginNotOK";

        } catch (SQLException e) {
            e.printStackTrace();
            return "loginNotOK";

        } finally {
            //close the database
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

    @Override
    public String createThread(String parentid, String title, String content, String author, LocalDateTime timestamp, int rating, String status) {
        try {

             String threadid="MAIN" + (10000 + new Random().nextInt(90000));
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_psw);
            rating = 0;
            parentid ="null1";
            status = "active";
            timestamp = java.time.LocalDateTime.now();
            connection.setAutoCommit(false);
            
            statement = connection.createStatement();
          
           statement.executeUpdate("insert into thread values " + "('" + threadid + " ','" + parentid + "','" + title + "', '" + content + "', '" + author + "','" + timestamp + "', '" + rating + "', '" + status + "')");

            connection.commit();
            connection.setAutoCommit(true);

            return ("user_forum.xhtml");

        } catch (SQLException e) {
            //handle the exceptions
            e.printStackTrace();
            return ("internalError");

        } finally {
            //close the database
            try {
                //resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
      public String createReply(String parentid, String title, String content, String author) {
        try {

            
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_psw);
            String threadid="RE" + (10000 + new Random().nextInt(90000));
            int rating = 0;
            String status = "active";
            LocalDateTime timestamp = java.time.LocalDateTime.now();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
          
            statement.executeUpdate("insert into thread values " + "('" + threadid + " ','" + parentid + "','" + title + "', '" + content + "', '" + author+ "','" + timestamp + "', '" + rating + "', '" + status + "')");

            connection.commit();
            connection.setAutoCommit(true);

            return ("viewthreaddetail");

        } catch (SQLException e) {
         
            e.printStackTrace();
            return ("Thread creation failed");

        } finally {
          
            try {
               
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<String> getAllThreadIds() {
        ArrayList<String> threads = new ArrayList<String>();

        try {
            try {
                //connect to the databse
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SQL_Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection(DATABASE_URL,
                    db_id, db_psw);
            //create statement
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            // create a statement
            resultSet = statement.executeQuery("select threadid from thread where parentid='null1'");
            while (resultSet.next()) {
                String threadids = resultSet.getString("threadid");
                threads.add(threadids);
            }
        } catch (SQLException e) {
            //handle the exceptions
            e.printStackTrace();

        } finally {
            //close the database
            try {
                //resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return threads;
    }
    @Override
    public int rateThread(String threadid, int n) {
        try {
            try {
                //connect to the databse
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SQL_Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection(DATABASE_URL,
                    db_id, db_psw);
            connection.setAutoCommit(false);

            //create statement
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            int r1;
            if (n == 1) {
                r1 = statement.executeUpdate("UPDATE thread SET rating=rating+1 where threadid='" + threadid + "'");
            } else {
                r1 = statement.executeUpdate("UPDATE thread SET rating=rating-1 where threadid='" + threadid + "'");

            }
            connection.commit();
            connection.setAutoCommit(true);
            return r1;
            
        } catch (SQLException e) {
            // to print out the exception message
            e.printStackTrace();
            return 0;
        } finally {
            // close the db connections
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
   public Thread getthreaddetail(String threadid) {
        try {

            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_psw);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from thread where threadid = '" + threadid + "'");
            if (resultSet.next()) {
                try {
                    return new Thread(resultSet.getString("threadid"),
                            resultSet.getString("parentid"), resultSet.getString("title"),
                            resultSet.getString("content"), resultSet.getString("author"),
                            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("timestamp")),
                            resultSet.getInt("rating"), resultSet.getString("status")
                    );
                } catch (ParseException ex) {
                    Logger.getLogger(SQL_Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            //close the database
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updatestatus(String status) {
        try {
            try {
                //connect to the databse
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SQL_Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection(DATABASE_URL,
                    db_id, db_psw);
            //create statement
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            statement.executeUpdate("UPDATE thread SET status='" + status + "'");

        } catch (SQLException e) {
            // to print out the exception message
            e.printStackTrace();
        } finally {
            // close the db connections
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void publishThread(String threadid) {
        try {

            // connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_psw);

            // timestamp=java.time.LocalDateTime.now();
            connection.setAutoCommit(false);
            // create a statement
            statement = connection.createStatement();
            // insert a record into bankAccount Table
            resultSet = statement.executeQuery("Select * from thread "
                    + "where rating> = ' 2'");
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            //handle the exceptions
            e.printStackTrace();

        } finally {
            //close the database
            try {
                //resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Thread> getreplies(String threadid) {
        try {

           
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_psw);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select * from thread "
                    + "where parentid = '" + threadid + "'");
            ArrayList<Thread> Threads=new ArrayList<Thread>();
              while (resultSet.next()) {
                try {
                    Threads.add(
                            new Thread(resultSet.getString("threadid"), 
                                    resultSet.getString("parentid"), resultSet.getString("title"), 
                                    resultSet.getString("content"),resultSet.getString("author"),
                                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("timestamp")),
                                    resultSet.getInt("rating"),resultSet.getString("status")
                            )
                    ) ;
                } catch (ParseException ex) {
                    Logger.getLogger(SQL_Database.class.getName()).log(Level.SEVERE, null, ex);
                }
        
            }
              return Threads;
       

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            //close the database
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       
    }

    @Override
    public ArrayList<Thread> getqualifiedthreadids() {
        try {

            // connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_psw);

            // timestamp=java.time.LocalDateTime.now();
            connection.setAutoCommit(false);
            // create a statement
            statement = connection.createStatement();
            ArrayList<Thread> Threads = new ArrayList<Thread>();

            // insert a record into bankAccount Table
            resultSet = statement.executeQuery("select * from thread "
                    + "where rating >= '2' and parentid='null1' and status='active'");
            connection.commit();
            connection.setAutoCommit(true);

              while (resultSet.next()) {
                try {
                    Threads.add(
                            new Thread(resultSet.getString("threadid"), 
                                    resultSet.getString("parentid"), resultSet.getString("title"), 
                                    resultSet.getString("content"),resultSet.getString("author"),
                                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("timestamp")),
                                    resultSet.getInt("rating"),resultSet.getString("status")
                            )
                    ) ;
                } catch (ParseException ex) {
                    Logger.getLogger(SQL_Database.class.getName()).log(Level.SEVERE, null, ex);
                }
        
            }

            return  Threads;
        } catch (SQLException e) {
            //handle the exceptions
            e.printStackTrace();

        } finally {
            //close the database
            try {
                //resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    
    @Override
    public ArrayList<Thread> getthreads(String searchParam) {
        try {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                    db_id, db_psw);
            //create statement
            statement = connection.createStatement();

            ArrayList<Thread> Threads = new ArrayList<Thread>();
           
            resultSet = statement.executeQuery(
                    "select * from thread where (title like '%" + searchParam + "%' or content like '%" + searchParam + "%' ) and  parentid='null1' order by timestamp desc;");

            while (resultSet.next()) {
                try {
                    Threads.add(
                            new Thread(resultSet.getString("threadid"),
                                    resultSet.getString("parentid"), resultSet.getString("title"),
                                    resultSet.getString("content"), resultSet.getString("author"),
                                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("timestamp")),
                                    resultSet.getInt("rating"), resultSet.getString("status")
                            )
                    );
                } catch (ParseException ex) {
                    Logger.getLogger(SQL_Database.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            return Threads;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            //close the database
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

    @Override
  public ArrayList<Thread> getbestthreads() {
        try {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                    db_id, db_psw);
            //create statement
            statement = connection.createStatement();

            ArrayList<Thread>Threads = new ArrayList<Thread>();

            resultSet = statement.executeQuery(
                    "select * from thread "
                    + "where status = 'best' and parentid='null1'"
            );

            while (resultSet.next()) {
                try{
                Threads.add(
                            new Thread(resultSet.getString("threadid"), 
                                    resultSet.getString("parentid"), resultSet.getString("title"), 
                                    resultSet.getString("content"),resultSet.getString("author"),
                                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("timestamp")),
                                    resultSet.getInt("rating"),resultSet.getString("status")
                            )
                    ) ;
            } catch (ParseException ex) {
                    Logger.getLogger(SQL_Database.class.getName()).log(Level.SEVERE, null, ex);
                }
           
            }
 return Threads;
            

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            //close the database
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        
    }

  
    @Override
    public void updateThread(String threadid, String status) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
  @Override
    public String getchangeStatus(String threadid) {
     try{ 
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                    db_id, db_psw);
            //create statement
            statement = connection.createStatement();

            statement.executeUpdate(
                    "update thread "
                    + "set status='best' where threadid = '"+threadid+"'");
     return "editor_notification";
     }  catch (SQLException ex) {
            Logger.getLogger(SQL_Database.class.getName()).log(Level.SEVERE, null, ex);
            
        }
            return null;
            
            }
}
