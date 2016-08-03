package bill.zts.com.bill.http;

import bill.zts.com.bill.ui.domain.VersionAPI;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/8/2.
 */
public interface ApiInterface {

    String HOST = "https://api.heweather.com/x3/";

    @GET("http://api.fir.im/apps/latest/57a06a4d548b7a28d700063e")
    Observable<VersionAPI> mVersionAPI(@Query("api_token") String api_token);
}
