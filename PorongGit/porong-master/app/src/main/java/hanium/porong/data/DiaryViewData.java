package hanium.porong.data;

import com.google.gson.annotations.SerializedName;

public class DiaryViewData {
    @SerializedName("email")
    private String email;
    @SerializedName("title")
    private String title;

    public DiaryViewData(){};
    public DiaryViewData(String email, String title){
        this.email = email;
        this.title = title;
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
