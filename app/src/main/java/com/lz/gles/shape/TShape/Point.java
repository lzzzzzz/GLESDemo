package com.lz.gles.shape.TShape;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by LZ on 2017/3/15.
 *
 * 基本图形 点
 */

public class Point {

    /**点坐标存缓冲器*/
    private FloatBuffer verticesBuffe;
    // 绘制颜色
    private float[] rgba= new float[] { 1.0f, 1.0f, 1.0f, 1.0f };

    /**点大小*/
    private float pointSize=1f;

    /**设置绘制点大小*/
    public Point setPointSize(float point_size){
        this.pointSize=point_size;
        return this;
    }

    /** 定义点坐标。*/
    public Point setVertices(float[] vertexx_array) {
        // a float is 4 bytes, therefore
        //we multiply the number if
        // vertices with 4.
        ByteBuffer vbb
                = ByteBuffer.allocateDirect(vertexx_array.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        verticesBuffe = vbb.asFloatBuffer();
        verticesBuffe.put(vertexx_array);
        verticesBuffe.position(0);
        return this;
    }

    /**是否需要清空画面*/
    private boolean isClear=true;

    /**设置绘制颜色*/
    public Point setPointColor(float red, float green, float blue, float alpha){
        rgba[0]=red;
        rgba[1]=green;
        rgba[2]=blue;
        rgba[3]=alpha;
        return this;
    }

    /**设置是否清空画面*/
    public Point setIsClear(boolean isClear){
        this.isClear=isClear;
        return this;
    }

    /**开始绘制*/
    public void draw(GL10 gl10){
        if(verticesBuffe==null){
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
        gl10.glPointSize(pointSize);
        gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffe);
        gl10.glDrawArrays(GL10.GL_POINTS, 0, 3);

        gl10.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

}
