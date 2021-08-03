package hanium.porong.data;

import com.google.gson.annotations.SerializedName;
//로그인 요청에 대한 응답으로 돌아올 데이터
public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
}