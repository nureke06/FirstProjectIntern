package com.example.nurda.tokenbased;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button_login_login;
    private EditText editText_login_username;
    private EditText editText_login_password;
    private String username;
    private String password;
    private String baseUrl;


    SharedPreferenceConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        config = new SharedPreferenceConfig(getApplicationContext());

        editText_login_username = (EditText) findViewById(R.id.editText_login_username);
        editText_login_password = (EditText) findViewById(R.id.editText_login_password);

        button_login_login = (Button) findViewById(R.id.button_login);


        if (config.readLoginStatus()==true){
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
            finish();
        }

        button_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                username = editText_login_username.getText().toString().toLowerCase();
                password = editText_login_password.getText().toString();

                if (username.equals(getResources().getString(R.string.user_login)) && password.equals(getResources().getString(R.string.user_password))){
                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                    config.writeLoginStatus(true);
                    finish();

                }else {
                    Toast.makeText(MainActivity.this,"Login incorrect. Try again", Toast.LENGTH_SHORT).show();
                    editText_login_password.setText("");
                    editText_login_username.setText("");
                }
            }
        });
    }

    /**
     * This subclass handles the network operations in a new thread.
     * It starts the progress bar, makes the API call, and ends the progress bar.
     */
    public class ExecuteNetworkOperation extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Display the progress bar.
            findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        }
    }

    /**
     * Open a new activity window.
     */
    private void goToSecondActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("password", password);
        bundle.putString("baseUrl", baseUrl);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
