package com.duy.colorfulwallpaper.fragments;

import android.view.View;
import android.view.View.OnClickListener;

import com.duy.colorfulwallpaper.shader.AbstractRenderer;
import com.duy.colorfulwallpaper.shader.VertexShaderRenderer;

public class VertexShaderFragment extends AbstractFragment implements
        OnClickListener {

    @Override
    public AbstractRenderer createRenderer() {
        return new VertexShaderRenderer(getActivity(), this);
    }

    @Override
    public void onClick(View v) {
        ((VertexShaderRenderer) mRenderer).toggleWireframe();
    }

}
