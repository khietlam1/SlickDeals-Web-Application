//Group File week 3
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;


@Named(value = "thread")
@ManagedBean
@RequestScoped
public class Thread {

   private String threadid;
    private String parentid;
    private String title;
    private String content;
    private String author;
    private Date timestamp;
    private int rating;
    private String status;
    DataStorage data = new SQL_Database();

    public Thread(String threadid, String parentid, String title, String content, String author, Date timestamp, int rating, String status) {
        this.threadid = threadid;
        this.parentid = parentid;
        this.title = title;
        this.content = content;
        this.author = author;
        this.timestamp = timestamp;
        this.rating = rating;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getThreadid() {
        return threadid;
    }

    public int getRating() {
        return rating;
    }

    public String getStatus() {
        return status;
    }

    public String getParentid() {
        return parentid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setThreadid(String threadid) {
        this.threadid = threadid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
public String getParentTitle(String parentid){
    String parent_title=data.getthreaddetail(parentid).getTitle();
    return parent_title;
}


}
