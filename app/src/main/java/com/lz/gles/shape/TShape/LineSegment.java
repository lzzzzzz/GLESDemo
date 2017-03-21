package com.lz.gles.shape.TShape;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by LZ on 2017/3/15.
 * 基本图形 线段
 */

public class LineSegment {

    /**定点缓冲器*/
    private FloatBuffer lineVertexBuffer;

    /**三种模式GL_LINES，GL_LINE_STRIP，GL_LINE_LOOP 来绘制直线*/
    private int lineModel=GL10.GL_LINE_LOOP;

    /**线的颜色*/
    private float[] rgba = new float[] { 1.0f, 1.0f, 1.0f, 1.0f };

    /**线段宽度*/
    private float limeWidth=4f;

    /**是否需要清空画面*/
    private boolean isClear=true;

    /**设置绘制线段坐标*/
    public LineSegment setLineVertexs(float[] vertexs){
        ByteBuffer vbb=ByteBuffer.allocateDirect(vertexs.length*4);
        vbb.order(ByteOrder.nativeOrder());
        lineVertexBuffer=vbb.asFloatBuffer();
        lineVertexBuffer.put(vertexs);
        lineVertexBuffer.position(0);
        return this;
    }

    /**设置绘制线段颜色*/
    public LineSegment setLineColor(float red, float green, float blue, float alpha){
        rgba[0]=red;
        rgba[1]=green;
        rgba[2]=blue;
        rgba[3]=alpha;
        return this;
    }

    /**设置线段绘制模式*/
    public LineSegment setLineModel(int line_Model){
        if(line_Model>0&&line_Model<=3){
            this.lineModel=line_Model;
        }
        return this;
    }
    /**设置线段绘制宽度*/
    public LineSegment setLineWidth(float limeWidth){
        this.limeWidth=limeWidth;
        return this;
    }

    /**设置是否清空画面*/
    public LineSegment setIsClear(boolean isClear){
        this.isClear=isClear;
        return this;
    }

    /**开始绘制*/
    public void draw(GL10 gl10){
        if(lineVertexBuffer==null){
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

        gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, lineVertexBuffer);

        gl10.glColor4f(rgba[0], rgba[1], rgba[2], rgba[3]);
        gl10.glLineWidth(limeWidth);
        gl10.glDrawArrays(lineModel, 0, 4);

        gl10.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
