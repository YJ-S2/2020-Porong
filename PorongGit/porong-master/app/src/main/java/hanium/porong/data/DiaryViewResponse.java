package hanium.porong.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DiaryViewResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("feeling")
    private String feeling;
   // @SerializedName("reporting_date")
    //private Date reporting_date;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getFeeling() {
        return feeling;
    }
/*
    public Date getReporting_date() {
        return reporting_date;
    }*/
}
