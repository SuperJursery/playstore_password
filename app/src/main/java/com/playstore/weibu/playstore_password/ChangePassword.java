package com.playstore.weibu.playstore_password;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by weibu64 on 2016/8/17.
 */
public class ChangePassword extends AppCompatActivity {
    private final String DEFAULT_PWD="20160901";
    private String PassWord;
    private Button btn;
    private TextView view01;
    private TextView view02;
    private TextView view03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_change);
        Context context = this;
        try{
            context = createPackageContext("com.weibu.settings.settings",Context.CONTEXT_IGNORE_SECURITY);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            Log.d("Jursery","Can't find PackageContext");
        }
        SharedPreferences data = context.getSharedPreferences("passwd", context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = data.edit();
        PassWord = data.getString("passwd",DEFAULT_PWD);

        view01 =(TextView)findViewById(R.id.editText01);
        view02= (TextView)findViewById(R.id.editText02);
        view03= (TextView)findViewById(R.id.editText03);

        btn = (Button)findViewById(R.id.check_btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String str01 = view01.getText().toString();
                String str02 = view02.getText().toString();
                String str03 = view03.getText().toString();
                if (str01.isEmpty() || str02.isEmpty() || str03.isEmpty() ){
                    Toast.makeText(ChangePassword.this, "Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!str01.equals(PassWord) || !str02.equals(str03)){
                    Toast.makeText(ChangePassword.this, "try again !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(str01.equals(PassWord) && str02.equals(str03)){
                    PassWord = str02;
                    editor.putString("passwd",PassWord);
                    editor.commit();
                    Toast.makeText(ChangePassword.this, "Success !", Toast.LENGTH_SHORT).show();
                    try{
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    finish();
                }
            }
        });
    }
}
