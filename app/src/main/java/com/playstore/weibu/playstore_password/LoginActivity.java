package com.playstore.weibu.playstore_password;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class LoginActivity extends AppCompatActivity {
    //private TextView mPassword = null;
    private EditText mPassword = null;
    private Button mComfirm;
    private String EnterStr;
    private View Toolbar_view;
    private volatile String pwd1;
    private final String DEFAULT_PWD="20160901";
    private final String TAG="PlayStoreLogin";
    //private ActionBar actionBar;

    private void getLatestPWD(){
        Context context = this;
        try{
            context = createPackageContext("com.weibu.settings.settings",Context.CONTEXT_IGNORE_SECURITY);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            Log.d("Jursery","Can't find PackageContext");
        }
        SharedPreferences StorageData = context.getSharedPreferences("passwd", Context.MODE_MULTI_PROCESS);
        pwd1 = StorageData.getString("passwd",DEFAULT_PWD);
        //Toast.makeText(LoginActivity.this, pwd1, Toast.LENGTH_SHORT).show();
    }

    private void clearEnterDatas(){
        if (null == mPassword){
            Log.d("Jusery","Can't find EditText!");
            return;
        }
        mPassword.getText().clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*
        Context context = this;
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Toolbar_view = inflater.inflate(R.layout.activity_tool_bar, null, false);
        //actionBar = getActionBar();
        //actionBar.hide();
        try{
            context = createPackageContext("com.weibu.settings.settings",Context.CONTEXT_IGNORE_SECURITY);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            Log.d("Jursery","Can't find PackageContext");
        }
        SharedPreferences StorageData = context.getSharedPreferences("passwd", context.MODE_PRIVATE);
        pwd = StorageData.getString("passwd",DEFAULT_PWD);
        */
        getLatestPWD();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Log.d("Jursery", "toolbar=" + toolbar);
        if (toolbar != null) {
            Log.d("Jursery", "This toolbar=" + toolbar);
            //toolbar.setTitle("ToolbarTEST");
            toolbar.setLogo(R.mipmap.ic_launcher_play_store);
            setSupportActionBar(toolbar);
            //toolbar.inflateMenu(R.menu.action_menu);
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Log.d("Jursery", "do onMenuItemClick");
                    int menuItemId = item.getItemId();
                    if (menuItemId == R.id.action_change) {
                        //Toast.makeText(LoginActivity.this, "change password", Toast.LENGTH_SHORT).show();
                        Intent it = new  Intent();
                        it.setClass(LoginActivity.this,ChangePassword.class);
                        startActivity(it);
                    }
                    return true;
                }
            });
        }


        mPassword = (EditText) findViewById(R.id.editText);
        mComfirm = (Button) findViewById(R.id.comfirm);
        mComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterStr = mPassword.getText().toString();
                clearEnterDatas();
                getLatestPWD();
                //deleteShortCut();
                //Toast.makeText(LoginActivity.this, pwd1, Toast.LENGTH_SHORT).show();
                if (pwd1.equals(EnterStr)) {
                    Toast.makeText(LoginActivity.this, "Comfirmed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setClassName("com.android.vending", "com.android.vending.AssetBrowserActivity");
                    //intent.setAction("android.intent.action.VIEW");
                    startActivity(intent);
                    /*try{
                        Runtime mRuntime = Runtime.getRuntime();
                        mRuntime.exec("am start -n com.android.vending/com.android.vending.AssetBrowserActivity");
                    }catch (IOException e) {
                        e.printStackTrace();
                    } */
                } else {
                    Toast.makeText(LoginActivity.this, R.string.wrong, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        Log.d("Jursery", "do onCreateOptionsMenu");
        return super.onCreateOptionsMenu(menu);
    }

    protected  void deleteShortCut(Context context){
        Log.d("Jursery","Do delteShortCut");
        Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        /*
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "MovieClub");
        //ComponentName comp = new ComponentName("com.android.vending","com.android.vending.AssetBrowserActivity");
        ComponentName comp = new ComponentName("com.movieclub.android","com.movieclub.android.ActivitySplash");
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(comp));
        sendBroadcast(shortcut);
        */
    }

    @Override
    protected void onStart(){
        Log.d(TAG,"Do onStart!");
        super.onStart();
    }

    @Override
    protected void onResume(){
        clearEnterDatas();
        getLatestPWD();
        Log.d(TAG,"Do onResume!");
        super.onResume();
    }

    @Override
    protected void onRestart(){
        Log.d(TAG,"Do onRestart!");
        super.onRestart();
    }

    @Override
    protected void onPause(){
        Log.d(TAG,"Do onPause!");
        super.onPause();
    }

    @Override
    protected void onStop(){
        Log.d(TAG,"Do onStop!");
        super.onStop();
    }

    @Override
    protected  void onDestroy(){
        Log.d(TAG,"Do onDestroy!");
        super.onDestroy();
    }
}

