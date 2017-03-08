package br.edu.ifpb.sinemaps.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import br.edu.ifpb.sinemaps.R;

public class SplashActivity extends Activity{

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.splash);

    Thread thread = new Thread(){

      @Override
      public void run() {
        try {
          sleep(3000);
          Intent intent = new Intent(SplashActivity.this, MainActivity.class);
          startActivity(intent);
          finish();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    thread.start();
  }
}