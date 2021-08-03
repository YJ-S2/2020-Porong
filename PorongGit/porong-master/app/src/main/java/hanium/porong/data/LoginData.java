package hanium.porong.data;

import com.google.gson.annotations.SerializedName;
//로그인 요청시 보낼 데이터
public class LoginData {
    @SerializedName("email")
    String email;

    @SerializedName("pwd")
    String pwd;

    public LoginData(String login_email, String login_password) {
        this.email = login_email;
        this.pwd = login_password;
    }
}