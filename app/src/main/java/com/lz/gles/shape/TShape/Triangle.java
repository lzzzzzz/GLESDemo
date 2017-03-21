package com.lz.gles.shape.TShape;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by LZ on 2017/3/15.
 * 基本图形 三角形
 */

public class Triangle {

    /**三角形顶点坐标缓冲器*/
    private FloatBuffer floatBuffer;

    /**绘制颜色*/
    private float[] rgba= new float[] { 1.0f, 1.0f, 1.0f, 1.0f };

    /**线段宽度*/
    private float limeWidth=4f;

    /**三角形绘制模式:GL10.GL_TRIANGLES、GL10.GL_TRIANGLE_STRIP、GL10.GL_TRIANGLE_FAN*/
    private int triangleMode=GL10.GL_TRIANGLES;

    private boolean isClear=false;

    /**设置是否清空画面*/
    public Triangle setIsClear(boolean isClear){
        this.isClear=isClear;
        return this;
    }

    /**设置三角形顶点*/
    public Triangle setVertexs(float[] vertexs){
        ByteBuffer obb=ByteBuffer.allocateDirect(vertexs.length*4);
        obb.order(ByteOrder.nativeOrder());
        floatBuffer=obb.asFloatBuffer();
        floatBuffer.put(vertexs);
        floatBuffer.position(0);
        return this;
    }
    /**设置三角形绘制模式*/
    public Triangle setTriangleModel(int triangle_Model){
        if(triangle_Model>=4&&triangle_Model<=6){
            this.triangleMode=triangle_Model;
        }
        return this;
    }
    /**设置线段绘制宽度*/
    public Triangle setLineWidth(float limeWidth){
        this.limeWidth=limeWidth;
        return this;
    }

    public void draw(GL10 gl10){
        if(floatBuffer==null){
            return;
        }
        if(isClear){
            gl10.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            // Clears the screen and depth buffer.
            gl10.glClear(GL10.GL_COLOR_BUFFER_BIT
                    | GL10.GL_DEPTH_BUFFER_BIT);
        }

        gl10.glLoadIdentity();
        gl10.glTranslatef(0, 0, -4);

        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl10.glColor4f(rgba[0], rgba[1], rgba[2], rgba[3]);
        gl10.glLineWidth(limeWidth);
        gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, floatBuffer);
        gl10.glDrawArrays(triangleMode, 0, 6);

        gl10.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

}
