package com.lz.gles.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.lz.gles.ESTools.OpenGLRendererV2;
import com.lz.gles.R;
import com.lz.gles.shape.TShape.SimplePlane;

/**平面图形渲染*/
public class FifthEsActivity extends AppCompatActivity {
    public static Intent getIntent(Activity act){
        return new Intent(act,FifthEsActivity.class);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // (NEW)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)

        GLSurfaceView view = new GLSurfaceView(this);
        OpenGLRendererV2 renderer = new OpenGLRendererV2();
        view.setRenderer(renderer);
        setContentView(view);

        // Create a new plane.
        SimplePlane plane = new SimplePlane(1, 1);

        // Move and rotate the plane.
        plane.z = 1.7f;
        plane.rx = -65;

        // Load the texture.
        plane.loadBitmap(BitmapFactory.decodeResource(getResources(),
                R.mipmap.jay));

        // Add the plane to the renderer.
        renderer.addMesh(plane);

        /*Cube cube=new Cube(1,1,1);
        cube.rx=45;
        cube.ry=45;
        renderer.addMesh(cube);*/

    }
}
