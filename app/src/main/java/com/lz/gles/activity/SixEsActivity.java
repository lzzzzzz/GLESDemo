package com.lz.gles.activity;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.lz.gles.ESTools.OpenGLRenderer;
import com.lz.gles.shape.TShape.LineSegment;
import com.lz.gles.shape.TShape.Point;
import com.lz.gles.shape.TShape.Triangle;

import java.util.Random;
import javax.microedition.khronos.opengles.GL10;

/**基本图形测试界面*/
public class SixEsActivity extends AppCompatActivity {
    private int TIME = 1000;
    Handler handler = new Handler();
    private Runnable runnable;

    //线设置为全局变量用于变换
    private LineSegment line;
    private Triangle triangle;

    public static Intent getIntent(Activity act){
        return new Intent(act,SixEsActivity.class);
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // (NEW)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)

        GLSurfaceView view = new GLSurfaceView(this);
        line=new LineSegment();
        triangle=new Triangle();
        final SixESRender render=new SixESRender();
        view.setRenderer(render);
        setContentView(view);

        //一下变换线段的绘制模式和颜色
        runnable= new Runnable() {

            @Override
            public void run() {
                // handler自带方法实现定时器
                try {
                    handler.postDelayed(this, TIME);
                    if(null!=render){
                        Random random=new Random();
                        int flag=(random.nextInt(100)%3)+1;
                        render.changeLineColoeMo(flag);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("exception...");
                }
            }
        };
        handler.postDelayed(runnable, TIME);
    }
    class SixESRender extends OpenGLRenderer {
        //默认模式下此方法会不断调用重绘界面
        @Override
        public void onDrawFrame(GL10 gl) {
            super.onDrawFrame(gl);
            drawPoint(gl);
            //drawAxis(gl);//画坐标轴 此方法会导致界面出现错乱线段未知问题
            drawLine(gl);
            drawTriangle(gl);
        }
        /**画点*/
        private void drawPoint(GL10 gl){
            float[] point_vertices={//此处绘制3个点
                    -0.8f , 0.8f * 1.732f , 0.0f ,
                    0.0f , 0.8f * 1.732f , 0.0f ,
                    0.8f , 0.8f * 1.732f , 0.0f ,
            };
            Point point=new Point();
            point.setVertices(point_vertices).setPointSize(10f);
            point.draw(gl);
        }
        /**画横竖坐标轴*/
        private void drawAxis(GL10 gl){
            float[] vertexs1={
                    0.0f,1.0f*1.732f,0.0f,
                    0.0f,-1.0f*1.732f,0.0f,
            };
            LineSegment line2=new LineSegment();
            line2.setLineVertexs(vertexs1).setLineWidth(6f).setIsClear(false);
            line2.draw(gl);
            float[] vertexs2={
                    -1.0f,0.0f*1.732f,0.0f,
                    1.0f,0.0f*1.732f,0.0f,
            };
            line2.setLineVertexs(vertexs2);
            line2.draw(gl);
        }
        /**画线*/
        private void drawLine(GL10 gl){
            //画线段
            float vertexArray[] = {
                    -0.8f, 0.0f * 1.732f, 0.0f,
                    -0.4f, 0.6f * 1.732f, 0.0f,
                    0.0f, 0.0f * 1.732f, 0.0f,
                    0.4f, 0.6f * 1.732f, 0.0f,
            };
            line.setLineVertexs(vertexArray).setIsClear(false);
            line.draw(gl);
        }
        /**画三角形（注意:经测试若线段放于三角形后绘制会出现错乱问题）*/
        private void drawTriangle(GL10 gl){
            float vertexArray[] = {
                    -0.8f, -0.8f * 1.732f, 0.0f,
                    0.0f, -0.8f * 1.732f, 0.0f,
                    -0.4f, 0.0f * 1.732f, 0.0f,

                    0.0f, -0.4f * 1.732f, 0.0f,
                    0.8f, -0.4f * 1.732f, 0.0f,
                    0.4f, 0.0f * 1.732f, 0.0f,
            };
            triangle.setVertexs(vertexArray).setLineWidth(6f).setIsClear(false);
            triangle.draw(gl);
        }
        /**改变线段颜色*/
        public void changeLineColoeMo(int flag){
            //线段变换方法：只需更换绘制时颜色和绘制模式，在Render不停刷新时即可变换
            if(null==line||triangle==null){
                return;
            }
                if(flag==1){
                    line.setLineColor(1.0f, 0.0f, 0.0f, 1.0f);
                    line.setLineModel(GL10.GL_LINE_LOOP);
                    triangle.setTriangleModel(GL10.GL_TRIANGLES);
                }else if(flag==2){
                    line.setLineColor(0.0f, 1.0f, 0.0f, 1.0f);
                    line.setLineModel(GL10.GL_LINE_STRIP);
                    triangle.setTriangleModel(GL10.GL_TRIANGLE_STRIP);
                }else{
                    line.setLineColor(0.0f, 0.0f, 1.0f, 1.0f);
                    line.setLineModel(GL10.GL_LINES);
                    triangle.setTriangleModel(GL10.GL_TRIANGLE_FAN);
                }
        }
    }

}
