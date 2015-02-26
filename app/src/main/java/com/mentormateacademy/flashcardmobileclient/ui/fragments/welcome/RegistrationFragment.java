package com.mentormateacademy.flashcardmobileclient.ui.fragments.welcome;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.data.singletons.DataHolder;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.database.helper.ValidationHelper;
import com.mentormateacademy.flashcardmobileclient.models.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class RegistrationFragment extends Fragment implements View.OnClickListener {

    // Fragment - Properties
    // ===============================================

    private EditText userEmailEditText;
    private EditText userPasswordEditText;

    private Bundle userRegistration;

    // Fragment - Constructor
    // ===============================================

    public RegistrationFragment(){}

    public static RegistrationFragment newInstance(){
        RegistrationFragment fragment = new RegistrationFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mActivity = (fragmentActionCallback) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userRegistration = new Bundle();
    }

    // Fragment - Life Cycle
    // ===============================================
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_registration, container, false);

        userEmailEditText      = (EditText) fragmentView.findViewById(R.id.userEmailEditText);
        userPasswordEditText   = (EditText) fragmentView.findViewById(R.id.userPasswordEditText);

        LinearLayout registrationButton = (LinearLayout) fragmentView.findViewById(R.id.registrationButton);
        registrationButton.setOnClickListener(this);

        return fragmentView;
    }

    // Fragment - Methods
    // ===============================================
    public void registration(){

        //
        boolean isEmailValid = false;
        boolean isPassValid  = false;

        // get field credentials
        String userEmail = userEmailEditText.getText().toString();
        String userPass  = userPasswordEditText.getText().toString();

        // check if E-mail is valid
        if(!userEmail.isEmpty() && ValidationHelper.isEmailValid(userEmail)) {
            userRegistration.putString("email", userEmail);
            isEmailValid = true;

            // check if the E-mail already exists
            Bundle checkUserProfile = new Bundle();
            checkUserProfile.putString("email", userEmail);

            ArrayList<User> users = DatabaseRepository.getRepository(getActivity())
                    .getUserRepository().readBy(checkUserProfile);

            if(users.size() != 0) {
                userEmailEditText.setError("User profile already exists");
                isEmailValid = false;
            }

        }
        else {
            userEmailEditText.setError("Invalid E-mail address");
            isEmailValid = false;
        }

        // check if password is valid
        if(!userPass.isEmpty()) {
            userRegistration.putString("pass", userPass);
            isPassValid = true;
        }
        else {
            userPasswordEditText.setError("Invalid password field");
            isPassValid = false;


        }

        if(isEmailValid && isPassValid) {

            // create local profile
            User userObject = new User();
            userObject.setEmail(userEmail);
            userObject.setPassword(userPass);

            // save to local database
            DatabaseRepository.getRepository(getActivity()).getUserRepository().create(userObject);
            long userId = DatabaseRepository.getRepository(getActivity()).getUserRepository().getLastInsertedId();

            // set property to data holder
            DataHolder.getData().setUserId(userId);

            // check activity profile to shared preference
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("IS_USER_LOGGED_IN", true);
            editor.putLong("LOGGED_USER_ID", userId);
            editor.commit();

            //
            mActivity.onRegistrationSuccess();
        }
    }

    @Override
    public void onClick(View v) {
        registration();
    }



    private fragmentActionCallback mActivity;

    public interface fragmentActionCallback {
        public void onRegistrationSuccess();
    }


    public HttpResponse postJSON(String url, JSONObject object) throws IOException {

        // setup HTTP client
        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);
        HttpPost post = new HttpPost(url);

        // prepare JSON object for sending
        StringEntity stringEntity = new StringEntity( object.toString());
        stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(stringEntity);

        return client.execute(post);
    }

    public JSONObject createJSONObject(Bundle arguments) throws JSONException {

        // create new object
        JSONObject jsonObject = new JSONObject();

        // get set of keys
        Set<String> jsonKeys  = arguments.keySet();
        Iterator<String> iterator = jsonKeys.iterator();

        while(iterator.hasNext()) {
            String element = iterator.next();
            jsonObject.put(element, arguments.getString(element));
        }

        return jsonObject;
    }


    public String returnMessage(HttpResponse response) throws IOException {

        InputStream stream = response.getEntity().getContent();
        InputStreamReader inputStreamReader = new InputStreamReader(stream);

        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder builder = new StringBuilder();
        String chink = null;

        while ((chink = reader.readLine()) != null) {
            builder.append(chink);
        }

        return builder.toString();
    }

    class TestPostTask extends AsyncTask<Bundle, Void, String> {


        @Override
        protected String  doInBackground(Bundle... params) {

            // prepare object
            Bundle jsonBundle = params[0];
            String message = null;

            // create Json Object
            try {
                JSONObject jsonObject = createJSONObject(jsonBundle);
                HttpResponse response = postJSON("http://192.168.56.1:8080/signup", jsonObject);
                message = returnMessage(response);

            } catch (JSONException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return message;
        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(getActivity(), "Final message is " + aVoid, Toast.LENGTH_LONG).show();
        }
    }
}
