package com.lz.gles.shape;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

/**
 * Created by LZ on 2017/3/10.
 */
/**平面矩形*/
public class Square {
    private float vertices[];
    private float width=1.0f,height=1.0f;
    // The order we like to connect them.
    //顶点顺序
    private short[] indices = { 0, 1, 2, 0, 2, 3 };
    // Our vertex buffer.
    private FloatBuffer vertexBuffer;

    // Our index buffer.
    private ShortBuffer indexBuffer;

    public Square(){
        float vertices_nor[] = {
                -width/2f,  height/2f, 0f,  // 0, Top Left
                -width/2f, -height/2f, 0f,  // 1, Bottom Left
                width/2f, -height/2f, 0f,  // 2, Bottom Right
                width/2,  height/2f, 0.0f,  // 3, Top Right
        };
        setVertices(vertices_nor);
        setBuffer();
    }

    public Square(float width,float height){
        this.width=width;
        this.height=height;
        // Our vertices.
        float vertices_nor[] = {
                -width/2f,  height/2f, 0f,  // 0, Top Left
                -width/2f, -height/2f, 0f,  // 1, Bottom Left
                width/2f, -height/2f, 0f,  // 2, Bottom Right
                width/2,  height/2f, 0.0f,  // 3, Top Right
        };
        setVertices(vertices_nor);
        setBuffer();
    }

    public Square(float[] vertices_nor){
        setVertices(vertices_nor);
        setBuffer();
    }

    /**设置顶点坐标*/
    public void setVertices(float[] vertices_nor){
        this.vertices=vertices_nor;
    }

    private void setBuffer(){
        // a float is 4 bytes, therefore we
        // multiply the number if
        // vertices with 4.
        ByteBuffer vbb
                = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        // short is 2 bytes, therefore we multiply
        //the number if
        // vertices with 2.
        ByteBuffer ibb
                = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }
    public void draw(GL10 gl10){
        // Counter-clockwise winding.
        // GL10.GL_CCW 设置逆时针方法为面的“前面”；
        gl10.glFrontFace(GL10.GL_CCW);
        // Enable face culling.
        // GL10.GL_CULL_FACE 打开 忽略“后面”设置；
        gl10.glEnable(GL10.GL_CULL_FACE);
        // What faces to remove with the face culling.
        // GL10.GL_BACK 明确指明“忽略“哪个面
        gl10.glCullFace(GL10.GL_BACK);

        // Enabled the vertices buffer for writing
        //and to be used during
        // rendering.
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Specifies the location and data format of
        //an array of vertex
        // coordinates to use when rendering.
        //void glVertexPointer(GLint size,GLenum type,GLsizei stride,const GLvoid * pointer)
        //size：指定了每个顶点对应的坐标个数，只能是2,3,4中的一个，默认值是4
        //type：指定了数组中每个顶点坐标的数据类型，可取常量:GL_BYTE, GL_SHORT,GL_FIXED,GL_FLOAT;
        //stride:指定了连续顶点间的字节排列方式，如果为0，数组中的顶点就会被认为是按照紧凑方式排列的，默认值为0
        //pointer:制订了数组中第一个顶点的首地址，默认值为0，对于我们的android，大家可以不用去管什么地址的，一般给一个IntBuffer就可以了。
        /*glVertexPointer():  指定一个指向顶点坐标的数组
          glNormalPointer():  指定一个指向法向量的数组
          glColorPointer():   指定一个指向颜色的数组
          glIndexPointer():   指定一个指向索引的数组
          glTexCoordPointer():  指定一个指向纹理的数组
          glEdgeFlagPointer():  指定一个指向边标志的数组*/

        gl10.glVertexPointer(3, GL10.GL_FLOAT, 0,
                vertexBuffer);

        gl10.glDrawElements(GL10.GL_TRIANGLES, indices.length,
                GL11.GL_UNSIGNED_SHORT, indexBuffer);

        // Disable the vertices buffer.
        gl10.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        // Disable face culling.
        gl10.glDisable(GL10.GL_CULL_FACE);
    }
}
