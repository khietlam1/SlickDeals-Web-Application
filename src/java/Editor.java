
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "editor")
@RequestScoped
public class Editor extends Account {
    
    public Editor(String accid, String pw) {
        super(accid,pw);
    }

   

    @Override
    public String updateThread(String threadid, String status) {
        
        this.getData().updateThread(threadid, status);
        return "Filename";
    }

    

}
