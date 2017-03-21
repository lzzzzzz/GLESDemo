package com.lz.gles.shape.TShape;

import com.lz.gles.shape.Square;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by LZ on 2017/3/10.
 */

/**平面矩形颜色处理*/
public class FloatColorSquare extends Square {

    // The colors mapped to the vertices.
    //顶点色(颜色定义的顺序和顶点的顺序是一致的。||当给每个顶点定义一个颜色时，OpenGL自动为不同顶点颜色之间生成中间过渡颜色（渐变色）。)
    float[] colors = {
            1f, 0f, 0f, 1f, // vertex 0 red
            0f, 1f, 0f, 1f, // vertex 1 green
            0f, 0f, 1f, 1f, // vertex 2 blue
            1f, 0f, 1f, 1f, // vertex 3 magenta
    };

     // Our color buffer.
    private FloatBuffer colorBuffer;//提高效率将顶点颜色存入buffer中

    public FloatColorSquare(){
        super();
        setColorBuffer();
    }
    public FloatColorSquare(float width,float height){
        super(width,height);
        setColorBuffer();
    }
    public FloatColorSquare(float[] vertices_nor){
        super(vertices_nor);
        setColorBuffer();
    }
    //设置顶点颜色缓存
    private void setColorBuffer(){
        // float has 4 bytes, colors (RGBA) * 4 bytes
        ByteBuffer cbb
                = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }

    @Override
    public void draw(GL10 gl10){
        // Enable the color array buffer to be
        //used during rendering.
        gl10.glEnableClientState(GL10.GL_COLOR_ARRAY);//开启着色
        // Point out the where the color buffer is.
        gl10.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        super.draw(gl10);
        // Disable the color buffer.
        gl10.glDisableClientState(GL10.GL_COLOR_ARRAY);//关闭着色
    }

}
