package hanium.porong.network;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.List;

public class SessionControl {
    static public DefaultHttpClient httpclient = null;
    static public List cookies;

    public static HttpClient getHttpclient() {
        if (httpclient == null) {
            SessionControl.setHttpclient(new DefaultHttpClient());
        }
        return httpclient;
    }

    public static void setHttpclient(DefaultHttpClient httpclient) {
        SessionControl.httpclient = httpclient;
    }
}
