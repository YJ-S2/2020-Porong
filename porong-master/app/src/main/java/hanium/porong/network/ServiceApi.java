package hanium.porong.network;

import hanium.porong.data.DiaryAddResponse;
import hanium.porong.data.DiaryData;
import hanium.porong.data.DiaryViewData;
import hanium.porong.data.DiaryViewResponse;
import hanium.porong.data.JoinData;
import hanium.porong.data.JoinResponse;
import hanium.porong.data.LoginData;
import hanium.porong.data.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/user/diary/insert")
    Call<DiaryAddResponse> insertDiary(@Body DiaryData data);

    @POST("/user/diary/view")
    Call<DiaryViewResponse> viewDiary(@Body DiaryViewData data);
}
