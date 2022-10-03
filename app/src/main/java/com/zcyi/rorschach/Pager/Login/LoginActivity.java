package com.zcyi.rorschach.Pager.Login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.zcyi.rorschach.Entity.User;
import com.zcyi.rorschach.R;
import com.zcyi.rorschach.Util.Constant;
import com.zcyi.rorschach.Util.Request.ApiResult;
import com.zcyi.rorschach.Util.Request.UserRequest;
import com.zcyi.rorschach.Util.UtilMethod;
import com.zcyi.rorschach.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    LoginViewModel loginViewModel;
    ActivityLoginBinding binding;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    private void initView() {

        //ViewModel 实例化
        loginViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(LoginViewModel.class);

        //DataBinding 实例化
        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        UtilMethod.changeStatusBarFrontColor(true, this);

        //设置点击事件
        binding.root.setOnClickListener(this);
        binding.btnLogin.setOnClickListener(this);
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Constant.url).build();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.root:
                UtilMethod.hideKeyboard(this);
                View currentFocus = getCurrentFocus();
                if (currentFocus != null) {
                    currentFocus.clearFocus();
                }

                break;
            case R.id.btn_login:
                binding.edUsername.setText("zaoyiii@outlook.com");
                binding.edPassword.setText("zaoyi...");
                loginViewModel.username = binding.edUsername.getText().toString().trim();
                loginViewModel.password = binding.edPassword.getText().toString().trim();
                if (loginViewModel.username.isEmpty() || loginViewModel.password.isEmpty()) {
                    Toast.makeText(this, "请输入账号或密码~", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserRequest userRequest = retrofit.create(UserRequest.class);
                Call<ApiResult<User>> login = userRequest.Login(loginViewModel.username, loginViewModel.password);
                login.enqueue(new Callback<ApiResult<User>>() {
                    @Override
                    public void onResponse(Call<ApiResult<User>> call, Response<ApiResult<User>> response) {
                        ApiResult<User> body = response.body();
                        assert body != null;
                        System.out.println(body + "---====");
                        User user = body.getData();
                        System.out.println(user.getUserToken());

                        Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ApiResult<User>> call, Throwable t) {
                        System.out.println("------" + t.toString());
                        Toast.makeText(getApplicationContext(), "登陆失败", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
        }

    }
}