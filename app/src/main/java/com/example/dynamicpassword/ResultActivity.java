package com.example.dynamicpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dynamicpassword.PasswordGenerator.PasswordGenerator;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    String password;
    String factor;
    PasswordGenerator generator;
    TextView encodedPassword;
    TextView factoredPassword;
    int precision;
    Button reGenerating;
    TextView process;
    String[] dateChoice = {"年", "月", "日", "小时", "分", "秒"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        intent = getIntent();
        password = intent.getStringExtra("password");
        factor = intent.getStringExtra("factor");
        precision = intent.getIntExtra("precision", 0) + 1;
        generator = new PasswordGenerator();
        generator.init(password, factor, precision);
//        encodedPassword = findViewById(R.id.password);
        factoredPassword = findViewById(R.id.factoredPassword);
        reGenerating = findViewById(R.id.reGenerating);
        process = findViewById(R.id.process);
        reGenerating.setOnClickListener(this);
        showPassword();
    }

    private void showPassword() {
        String strEncodedPassword = generator.getPassword();
        String strFactoredPassword = generator.getFactoredPassword();
//        encodedPassword.setText(strEncodedPassword);
        factoredPassword.setText(strFactoredPassword);
        int[] date = generator.date;
        String strProcess = "当前初始密码为:" + password + "\n"
                + "当前密码系数为:" + factor + "\n"
                + "当前时间为:" + date[0] + "年" + date[1] + "月" + date[2] + "日" + date[3] + "时" + date[4] + "分" + date[5] + "秒\n"
                + "当前选择时间精度:" + dateChoice[precision - 1] + "\n\n"
                + "动态密码计算方法:\n"
                + "  第一轮:使用初始密码与时间相加获得对应位密码\n"
                + "  例：计算第一位密码\n"
                + "    将初始密码第一位与日期第一位（年）相加:" +password.split("")[1] + "+" + date[0] + "=" + (Integer.parseInt(password.split("")[1]) + date[0]) + "\n"
                + "    将" + (Integer.parseInt(password.split("")[1]) + date[0]) + "各位继续相加直到结果为一位数\n"
                + "    最终得到第一位密码为:" + strEncodedPassword.split("")[1] +"\n"
                + "    根据上述方法继续对每一位密码进行计算（密码第二位与日期第二位，密码第三位与日期第三位，日期变量循环使用，依此类推）\n"
                + "    可得第一轮密码为:" + strEncodedPassword + "\n\n"
                + "  第二轮:使用第一轮密码与密码系数相乘获得对应位密码\n"
                + "  例：计算第一位密码\n"
                + "    将第一轮密码第一位与密码系数相乘:" + strEncodedPassword.split("")[1] + "*" + factor + "=" + (Integer.parseInt(strEncodedPassword.split("")[1]) * Float.parseFloat(factor)) + "\n"
                + "    将结果四舍五入并各位继续相加直到结果为一位数得到:" + strFactoredPassword.split("")[1] + "\n"
                + "    密码其余各位计算依此类推\n"
                + "根据上述方法最终获得动态密码为";
        process.setText(strProcess);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public static void start(Context context, String password, String factor, int precision){
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra("password", password);
        intent.putExtra("factor", factor);
        intent.putExtra("precision", precision);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        showPassword();
    }
}
