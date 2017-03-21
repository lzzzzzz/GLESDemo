package com.lz.gles.activity;


import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.lz.gles.ESTools.OpenGLRenderer;
import com.lz.gles.shape.Square;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**画平面矩形*/
public class FirstEsActivity extends AppCompatActivity {

    public static Intent getIntent(Activity act){
        return new Intent(act,FirstEsActivity.class);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // (NEW)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)

        /***GLSurfaceView 的渲染模式有两种，一种是连续不断的更新屏幕，另一种为on-demand ，
         * 只有在调用requestRender() 在更新屏幕。
         * 缺省为RENDERMODE_CONTINUOUSLY 持续刷新屏幕。*/
        GLSurfaceView view = new GLSurfaceView(this);
        view.setRenderer(new FirstESRender());
        setContentView(view);
    }

    class FirstESRender extends OpenGLRenderer{
        private Square square;
        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
            super.onSurfaceCreated(gl10, eglConfig);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            super.onDrawFrame(gl);
            // Replace the current matrix with the identity matrix
            gl.glLoadIdentity();
            // Translates 4 units into the screen.
            gl.glTranslatef(0, 0, -4);
            square=new Square(2,1);
            square.draw(gl);
            square=new Square(1,2);
            square.draw(gl);
           /* Square square2=new Square(2,4);
            square2.draw(gl);*/
        }
    }
}
