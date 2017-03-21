package com.lz.gles.ESTools;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class TutorialPartI extends AppCompatActivity {
 // Called when the activity is first created. 
 @Override
 public void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
  this.requestWindowFeature(Window.FEATURE_NO_TITLE); // (NEW)
  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
  WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)

  GLSurfaceView view = new GLSurfaceView(this);
  view.setRenderer(new OpenGLRenderer());
  setContentView(view);
 }
}
