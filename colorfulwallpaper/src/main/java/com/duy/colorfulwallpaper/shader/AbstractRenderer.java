package com.duy.colorfulwallpaper.shader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import com.duy.colorfulwallpaper.fragments.AbstractFragment;

import org.rajawali3d.renderer.Renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public abstract class AbstractRenderer extends Renderer {

    final AbstractFragment fragment;

    public AbstractRenderer(Context context, @Nullable AbstractFragment fragment) {
        super(context);
        this.fragment = fragment;
    }

    @Override
    public void onRenderSurfaceCreated(EGLConfig config, GL10 gl, int width, int height) {
        if (fragment != null) fragment.showLoader();
        super.onRenderSurfaceCreated(config, gl, width, height);
        if (fragment != null) fragment.hideLoader();
    }

    @Override
    public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {
    }

    @Override
    public void onTouchEvent(MotionEvent event) {
    }

}
