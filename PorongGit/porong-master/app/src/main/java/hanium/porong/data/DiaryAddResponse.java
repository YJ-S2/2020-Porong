package hanium.porong.data;

import com.google.gson.annotations.SerializedName;

public class DiaryAddResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
