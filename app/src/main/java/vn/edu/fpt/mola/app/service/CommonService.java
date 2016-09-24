package vn.edu.fpt.mola.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by phuctran93 on 9/20/2016.
 */
public class CommonService {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client;

    public CommonService(OkHttpClient client) {
        this.client = client;
    }


    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("ContentType", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("ContentType", "application/json")
                .addHeader("Accept", "application/json")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    @SuppressWarnings("unused")
    public static class ErrorItem
    {
        private String code;
        private String message;

        public String getCode()
        {
            return code;
        }

        public void setCode(String code)
        {
            this.code = code;
        }

        public String getMessage()
        {
            return message;
        }

        public void setMessage(String message)
        {
            this.message = message;
        }
    }

    public static class ErrorResponse
    {
        private List<ErrorItem> errors = new ArrayList<>();

        public List<ErrorItem> getErrors()
        {
            return errors;
        }

        public void setErrors(List<ErrorItem> errors)
        {
            this.errors = errors;
        }

        public void addError(ErrorItem error)
        {
            this.errors.add(error);
        }
    }
}
