package com.lz.gles.ESTools;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.lz.gles.shape.Group;
import com.lz.gles.shape.Mesh;

import javax.microedition.khronos.opengles.GL10;

public class OpenGLRendererV2 implements GLSurfaceView.Renderer {

    private final Group root;

    public OpenGLRendererV2() {
        // Initialize our root.
        Group group = new Group();
        root = group;
    }


    @Override
  public void onSurfaceCreated(GL10 gl10, javax.microedition.khronos.egl.EGLConfig eglConfig) {
    // Set the background color to black ( rgba ).
    gl10.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);  // OpenGL docs.
    // Enable Smooth Shading, default not really needed.
    gl10.glShadeModel(GL10.GL_SMOOTH);// OpenGL docs.
    // Depth buffer setup.
    gl10.glClearDepthf(1.0f);// OpenGL docs.
    // Enables depth testing.
    gl10.glEnable(GL10.GL_DEPTH_TEST);// OpenGL docs.
    // The type of depth testing to do.
    gl10.glDepthFunc(GL10.GL_LEQUAL);// OpenGL docs.
    // Really nice perspective calculations.
    gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, // OpenGL docs.
            GL10.GL_NICEST);
  }


  public void onDrawFrame(GL10 gl) {
   // Clears the screen and depth buffer.
      gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
      // Replace the current matrix with the identity matrix
      gl.glLoadIdentity();
      // Translates 4 units into the screen.
      gl.glTranslatef(0, 0, -4);
      // Draw our scene.
      root.draw(gl);
  }


  public void onSurfaceChanged(GL10 gl, int width, int height) {
   // Sets the current view port to the new size.
   gl.glViewport(0, 0, width, height);// OpenGL docs.
   // Select the projection matrix
   gl.glMatrixMode(GL10.GL_PROJECTION);// OpenGL docs.
   // Reset the projection matrix
   gl.glLoadIdentity();// OpenGL docs.
   // Calculate the aspect ratio of the window
   GLU.gluPerspective(gl, 45.0f,(float) width / (float) height,0.1f, 1000.0f);
   // Select the modelview matrix
   gl.glMatrixMode(GL10.GL_MODELVIEW);// OpenGL docs.
   // Reset the modelview matrix
   gl.glLoadIdentity();// OpenGL docs.
  }

    /**
     * Adds a mesh to the root.
     *
     * @param mesh
     *            the mesh to add.
     */
    public void addMesh(Mesh mesh) {
        root.add(mesh);
    }

}