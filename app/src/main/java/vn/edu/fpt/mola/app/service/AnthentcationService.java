package vn.edu.fpt.mola.app.service;

import android.content.Context;
import android.net.Uri;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import vn.edu.fpt.mola.app.BuildConfig;
import vn.edu.fpt.mola.app.MolaApp;
import vn.edu.fpt.mola.app.entity.UserPrincipal;

/**
 * Created by phuctran93 on 9/20/2016.
 */
public class AnthentcationService {

    private Context context;

    public AnthentcationService(Context context) {
        this.context = context;
    }

    public boolean login(String username, String password) {

        String bomUrl = BuildConfig.MOLA_SERVER;
        Uri buildUri = Uri.parse(bomUrl).buildUpon()
                .appendPath("login")
                .build();

        JSONObject loginJson = new JSONObject();

        try {
            loginJson.put("username", username);
            loginJson.put("password", password);

            OkHttpClient client = ((MolaApp)context.getApplicationContext()).getHttpClient();
            CommonService commonService = new CommonService(client);
            String responseJson = commonService.post(buildUri.toString(), loginJson.toString());

            JSONObject jsonObject = new JSONObject(responseJson);

            if (jsonObject.opt("errors") != null) {
                return false;
            }

            Gson gson = new Gson();
            UserPrincipal userPrincipal = gson.fromJson(responseJson, UserPrincipal.class);

            ((MolaApp)context.getApplicationContext()).setUser(userPrincipal);

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

}
