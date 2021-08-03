package hanium.porong.data;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.SerializedName;

@IgnoreExtraProperties
public class DiaryData {
    @SerializedName("feeling")
    private String feeling;
    @SerializedName("email")
    private String email;
    @SerializedName("content")
    private String content;
    @SerializedName("reporting_date")
    private String reporting_date;
    @SerializedName("title")
    private String title;

    public DiaryData(){};
    public DiaryData(String email, String title, String reporting_date, String feeling, String content){
        this.email = email;
        this.title = title;
        this.reporting_date = reporting_date;
        this.feeling = feeling;
        this.content = content;
    };


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return reporting_date;
    }

    public void setReporting_date(String reporting_date) {
        this.reporting_date = reporting_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
