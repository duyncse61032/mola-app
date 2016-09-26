package vn.edu.fpt.mola.app.service;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;
import vn.edu.fpt.mola.app.MolaApp;
import vn.edu.fpt.mola.app.entity.UserForm;
import vn.edu.fpt.mola.app.entity.UserPrincipal;

/**
 * Created by phuctran93 on 9/20/2016.
 */
public class AuthenticationService {

    private Context context;

    public AuthenticationService(Context context) {
        this.context = context;
    }

    public boolean login(String username, String password) {
        try {
            JSONObject loginJson = new JSONObject();
            loginJson.put("username", username);
            loginJson.put("password", password);

            ConnectionService connectionService = new ConnectionService(this.context);
            Response response = connectionService.post("login", loginJson.toString());

            if (response.code() != 200) {
                return false;
            }

            Gson gson = new Gson();
            UserPrincipal userPrincipal = gson.fromJson(response.body().string(), UserPrincipal.class);

            ((MolaApp)context.getApplicationContext()).setUser(userPrincipal);

            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean register(UserForm newUser) {

        try {
            String registerForm = new Gson().toJson(newUser);

            ConnectionService connectionService = new ConnectionService(this.context);
            Response response = connectionService.post("register", registerForm);

            return response.code() == 201;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
