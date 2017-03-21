package com.lz.gles.activity;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.lz.gles.ESTools.OpenGLRenderer;
import com.lz.gles.shape.Cube;
import com.lz.gles.shape.Group;
import com.lz.gles.shape.Mesh;

import javax.microedition.khronos.opengles.GL10;

/**基本图元练习*/
public class FourthESActivity extends AppCompatActivity {
    public static Intent getIntent(Activity act){
        return new Intent(act,FourthESActivity.class);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // (NEW)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)

        GLSurfaceView view = new GLSurfaceView(this);
        view.setRenderer(new FourthESRender());
        setContentView(view);
    }

    class FourthESRender extends OpenGLRenderer {
        private Mesh root;

        public FourthESRender() {
            // Initialize our cube.
            Group group = new Group();
            Cube cube = new Cube(1, 1, 1);
            cube.rx = 45;
            cube.ry = 45;
            group.add(cube);
            root = group;
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            super.onDrawFrame(gl);
            // Replace the current matrix with the identity matrix
            gl.glLoadIdentity();
            // Translates 4 units into the screen.
            gl.glTranslatef(0, 0, -4);
            // Draw our scene.
            root.draw(gl);
        }
    }
}
