package hanium.porong.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class JoinData implements Serializable {
    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("pwd")
    private String pwd;

    @SerializedName("birth")
    private String birth;

    @SerializedName("gender")
    private String gender;

    @SerializedName("roommate")
    private String roommate;

    @SerializedName("child")
    private String child;

    @SerializedName("marry")
    private String marry;

    @SerializedName("job")
    private String job;

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setMarry(String marry) {
        this.marry = marry;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setRoommate(String roommate) {
        this.roommate = roommate;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public JoinData() {};
    public JoinData(String join_name, String join_email, String join_password,
                    String join_birth, String join_gender, String join_job,
                    String join_roommate, String join_married, String join_child) {
        this.username = join_name;
        this.email = join_email;
        this.pwd = join_password;
        this.birth = join_birth;
        this.gender = join_gender;
        this.roommate = join_roommate;
        this.child = join_child;
        this.marry = join_married;
        this.job = join_job;
    }


}