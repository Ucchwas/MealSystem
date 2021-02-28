package com.emergingit.mealsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.emergingit.mealsystem.Models.Users;
import com.emergingit.mealsystem.Services.UserList;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    UserList userList;
//    TextView uname;

    Button btnItem;

    Button registerButton;
    TextInputLayout u_name,u_email,u_password,u_confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        userList = Common.getUserList();
//        //uname = (TextView)findViewById(R.id.uname);
//
//        userList.getUser().enqueue(new Callback<List<Users>>() {
//            @Override
//            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
//                Log.w("response",response.body().toString());
//                List<Users> userListData=response.body();
//                //uname.setText(userListData.get(0).getUname());
//            }
//
//            @Override
//            public void onFailure(Call<List<Users>> call, Throwable t) {
//                Log.e("error",t.toString());
//            }
//        });

        u_name = (TextInputLayout) findViewById(R.id.name);
        u_email = (TextInputLayout) findViewById(R.id.email);
        u_password = (TextInputLayout) findViewById(R.id.password);
        u_confirm_password = (TextInputLayout) findViewById(R.id.confirm_password);

        registerButton = (Button) findViewById(R.id.btnConfirm);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_name = u_name.getEditText().getText().toString();
                String str_email = u_email.getEditText().getText().toString();
                String str_password = u_password.getEditText().getText().toString();
                Users users = new Users(str_name,str_email,str_password);

                Log.w("Email : ", str_name);

                Common.getUserList().postUser(users).enqueue(new Callback<List<Users>>() {
                    @Override
                    public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                        if(response.isSuccessful()){
                            Log.w("New User : ", response.toString());
                        }
                        else {
                            Log.w("Error", response.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Users>> call, Throwable t) {

                    }
                });

                if(!isEmpty(u_name.getEditText()) && rightEmail(u_email.getEditText()) && passMatch(u_password.getEditText(),u_confirm_password.getEditText())){
                    Toast.makeText(RegisterActivity.this,"Registretion Complete" ,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this , MainActivity.class);
                    startActivity(intent);
                }
                else {
                    if(isEmpty(u_name.getEditText())){
                        Toast.makeText(RegisterActivity.this,"Username Required",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this , RegisterActivity.class);
                        startActivity(intent);
                    }

                    else if(!rightEmail(u_email.getEditText())){
                        Toast.makeText(RegisterActivity.this,"Valid Email Required",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this , RegisterActivity.class);
                        startActivity(intent);
                    }
                    else if(!passMatch(u_password.getEditText(),u_confirm_password.getEditText())){
                        Toast.makeText(RegisterActivity.this,"Password didn't match",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this , RegisterActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean rightEmail(EditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean passMatch(EditText pass1,EditText pass2){
        CharSequence str1 = pass1.getText().toString();
        CharSequence str2 = pass2.getText().toString();
        return str1.equals(str2);
    }
}