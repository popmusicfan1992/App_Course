package com.myteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.myteam.database.UserDao;
import com.myteam.database.UserDatabase;
import com.myteam.model.Student;


public class login extends AppCompatActivity {
    EditText editUsername, editPassword;
    Button buttonLogin;
    TextView textViewRegister;
    UserDao userDao;
    UserDatabase userDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        userDatabase = Room.databaseBuilder(this,UserDatabase.class,"student")
                .allowMainThreadQueries()
                .build();

        userDao = userDatabase.getUserDao();

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.myteam.login.this, com.myteam.register.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                Student student = userDao.getStudent(username,password);

                if (student != null){
                    Intent intent = new Intent(com.myteam.login.this, com.myteam.home.class);
                    intent.putExtra("student",student);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(com.myteam.login.this, "Đăng ký với username và pasword không đúng",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void init(){
        editUsername = findViewById(R.id.edUserName2);
        editPassword = findViewById(R.id.edPassWord2);
        buttonLogin = findViewById(R.id.btn_DangNhap);
        textViewRegister = findViewById(R.id.textViewRegister);
    }
}