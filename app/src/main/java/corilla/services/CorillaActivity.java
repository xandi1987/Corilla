package corilla.services;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by alzi on 11.10.2015.
 */
public class CorillaActivity implements Serializable{

    // private members

    private Date b;

    private Date e;

    private String location;

    private int subscriptionsCount;

    private String timeZone;

    private String title;

    private String url;

    //setter getter
    public void setUrl(String value){
        url = value;
    }

    public String getUrl(){
        return url;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String value){
        title = value;
    }

    public String getTimeZone(){
        return timeZone;
    }

    public void setTimeZone(String value){
        timeZone = value;
    }

    public Date getB(){
        return b;
    }

    public void setB(Date value){
        b = value;
    }

    public Date getE(){
        return e;
    }

    public void setE(Date value){
        e = value;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String value){
        location = value;
    }

    public int getSubscriptionsCount(){
        return subscriptionsCount;
    }

    public void setSubscriptionsCount(int value){
        subscriptionsCount = value;
    }

}
