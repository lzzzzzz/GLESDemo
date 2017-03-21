package com.lz.gles.activity;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.lz.gles.ESTools.OpenGLRenderer;
import com.lz.gles.shape.Square;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**基本东湖练习*/
public class SecondESActivity extends AppCompatActivity {
    public static Intent getIntent(Activity act){
        return new Intent(act,SecondESActivity.class);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // (NEW)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)

        GLSurfaceView view = new GLSurfaceView(this);
        view.setRenderer(new SecondESRender());
        setContentView(view);
    }

    class SecondESRender extends OpenGLRenderer {
        private Square square;
        private float angle=0f;
        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
            super.onSurfaceCreated(gl10, eglConfig);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            super.onDrawFrame(gl);
            // Replace the current matrix with the identity matrix
            gl.glLoadIdentity();
            // Translates 10 units into the screen.
            gl.glTranslatef(0, 0, -10);

            // SQUARE A
            // Save the current matrix.
            gl.glPushMatrix();//存栈
            // Rotate square A counter-clockwise.
            gl.glRotatef(angle,0,0,1);//旋转变换
            square=new Square(0.5f,0.5f);
            square.draw(gl);
            gl.glPopMatrix();//出栈

            // SQUARE B
            gl.glPushMatrix();//存栈
            gl.glRotatef(-angle,0,0,1);//旋转变换
            // Move square B.
            gl.glTranslatef(2, 0, 0);//平移变换
            // Scale it to 50% of square A
            gl.glScalef(.5f, .5f, .5f);//缩放
            square.draw(gl);

            // SQUARE C
            gl.glPushMatrix();//存栈
            gl.glRotatef(-angle,0,0,1);//旋转变换
            // Move square B.
            gl.glTranslatef(2, 0, 0);//平移变换
            // Scale it to 50% of square A
            gl.glScalef(.5f, .5f, .5f);//缩放
            // Rotate around it's own center.
            gl.glRotatef(angle*10, 0, 0, 1);//自身旋转
            square.draw(gl);
            gl.glPopMatrix();//出栈

            // Increse the angle.
            angle++;//增加变换角度（360一次循环）
        }
    }
}
