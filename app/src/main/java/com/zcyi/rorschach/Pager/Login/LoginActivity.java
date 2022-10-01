package com.zcyi.rorschach.Pager.Login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.zcyi.rorschach.R;
import com.zcyi.rorschach.Util.UtilMethod;

public class LoginActivity extends AppCompatActivity {

    LoginViewModel loginViewModel;
    private LinearLayout Root;
    private EditText edUsername;
    private EditText edPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(LoginViewModel.class);
        initView();
        btnLogin.setOnClickListener(v -> {
            loginViewModel.username = edUsername.getText().toString().trim();
            loginViewModel.password = edPassword.getText().toString().trim();
            if (loginViewModel.username.isEmpty() || loginViewModel.password.isEmpty()) {
                Toast.makeText(this, "请输入账号或密码~", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        });

    }

    private void initView() {
        UtilMethod.changeStatusBarFrontColor(true, this);

        Root = (LinearLayout) findViewById(R.id.Root);
        edUsername = (EditText) findViewById(R.id.ed_username);
        edPassword = (EditText) findViewById(R.id.ed_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }
}