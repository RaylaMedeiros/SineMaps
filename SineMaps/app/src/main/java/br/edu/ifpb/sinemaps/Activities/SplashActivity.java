package br.edu.ifpb.sinemaps.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class SplashActivity extends ActionBarActivity implements Runnable{

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.splash);
    
    Handler handler = new Handler();
    handler.postDelayed(this,2000);
    }
    
    @override
    public void run() {
      startActivity(new Intent(this, MainActivity.java));
      finish();
      }
}
