package com.duy.colorfulwallpaper.shader;

import android.content.Context;
import android.opengl.GLES20;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import com.duy.colorfulwallpaper.fragments.AbstractFragment;
import com.duy.colorfulwallpaper.Preferences;
import com.duy.colorfulwallpaper.materials.CustomVertexShaderMaterialPlugin;

import org.rajawali3d.Object3D;
import org.rajawali3d.animation.Animation;
import org.rajawali3d.animation.RotateOnAxisAnimation;
import org.rajawali3d.materials.Material;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Sphere;

public class VertexShaderRenderer extends AbstractRenderer {

    private static final String TAG = "VertexShaderRenderer";
    private long mFrameCount = 0;
    private Material mMaterial;
    private Object3D mSphere;

    public VertexShaderRenderer(Context context, @Nullable AbstractFragment fragment) {
        super(context, fragment);
    }

    @Override
    protected void initScene() {
        mMaterial = new Material();
        mMaterial.enableTime(true);
        mMaterial.addPlugin(new CustomVertexShaderMaterialPlugin());
        mSphere = new Sphere(2, 60, 60);
        mSphere.setMaterial(mMaterial);

        setDrawMode(new Preferences(getContext()).getDrawType());

        getCurrentScene().addChild(mSphere);

        Vector3 axis = new Vector3(2, 4, 1);
        axis.normalize();

        RotateOnAxisAnimation mAnim = new RotateOnAxisAnimation(axis, 360);
        mAnim.setRepeatMode(Animation.RepeatMode.INFINITE);
        mAnim.setDurationMilliseconds(12000);
        mAnim.setTransformable3D(mSphere);
        getCurrentScene().registerAnimation(mAnim);
        mAnim.play();

        getCurrentCamera().setPosition(0, 0, 10);
    }

    public void setDrawMode(int mode) {
        switch (mode) {
            case 0:
                mSphere.setDrawingMode(GLES20.GL_LINES);
                break;
            case 1:
                mSphere.setDrawingMode(GLES20.GL_LINE_STRIP);
                break;
            case 2:
                mSphere.setDrawingMode(GLES20.GL_LINE_LOOP);
                break;
            case 3:
                mSphere.setDrawingMode(GLES20.GL_TRIANGLES);
                break;
            case 4:
                mSphere.setDrawingMode(GLES20.GL_TRIANGLE_STRIP);
                break;
            case 5:
                mSphere.setDrawingMode(GLES20.GL_POINTS);
                break;
        }
    }

    @Override
    protected void onRender(long ellapsedRealtime, double deltaTime) {
        super.onRender(ellapsedRealtime, deltaTime);
        mMaterial.setTime(mFrameCount++);
        if (mFrameCount == 1200) mFrameCount = 0;
    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
    }

    public void toggleWireframe() {
        mSphere.setDrawingMode(mSphere.getDrawingMode() == GLES20.GL_TRIANGLES ? GLES20.GL_LINES
                : GLES20.GL_TRIANGLES);
    }
}
