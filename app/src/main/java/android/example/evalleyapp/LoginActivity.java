package android.example.evalleyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class LoginActivity extends AppCompatActivity {

    String App_id="evalleyapp-gxkjc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email=findViewById(R.id.username_login);
        EditText password=findViewById(R.id.password_login);
        Button login=findViewById(R.id.login_button);

        Realm.init(this);
        App app=new App(new AppConfiguration.Builder(App_id).build());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String main_email=email.getText().toString();
                String main_password=password.getText().toString();
                Credentials credentials=Credentials.emailPassword(main_email,main_password);
                app.loginAsync(credentials, new App.Callback<User>() {
                    @Override
                    public void onResult(App.Result<User> result) {
                        if(result.isSuccess())
                        {
                            Toast.makeText(LoginActivity.this,"Login successfull !",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Wrong email or password: \nPlease try again !", Toast.LENGTH_SHORT).show();
                            Log.v("kuch bhi", "Login Unsuccessfull");
                        }
                    }
                });
            }
        });

    }
}