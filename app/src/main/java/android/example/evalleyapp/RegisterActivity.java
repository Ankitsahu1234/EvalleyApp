package android.example.evalleyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;

public class RegisterActivity extends AppCompatActivity {

    String App_id="evalleyapp-gxkjc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText name=findViewById(R.id.name_register);
        EditText email=findViewById(R.id.username_login);
        EditText password=findViewById(R.id.password_login);
        EditText phone_number=findViewById(R.id.phonenumber_register);
        Button register=findViewById(R.id.register_button);

        Realm.init(this);
        App app=new App(new AppConfiguration.Builder(App_id).build());

        TextView textView=(TextView)findViewById(R.id.account_already);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String main_email=email.getText().toString();
                String main_password=password.getText().toString();
                app.getEmailPassword().registerUserAsync(main_email,main_password,task->{
                    if(task.isSuccess())
                    {
                        Toast.makeText(RegisterActivity.this,"Registeration successfull !",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this,"Please fill all the required fields !",Toast.LENGTH_SHORT).show();
                        Log.v("user","registration is not successfull"+task.getError());
                    }
                });
            }
        });

    }
}