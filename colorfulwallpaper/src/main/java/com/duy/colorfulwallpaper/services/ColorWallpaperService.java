package com.duy.colorfulwallpaper.services;

import com.duy.colorfulwallpaper.shader.VertexShaderRenderer;

import org.rajawali3d.renderer.ISurfaceRenderer;
import org.rajawali3d.view.ISurface;
import org.rajawali3d.wallpaper.Wallpaper;

public class ColorWallpaperService extends Wallpaper {

    private ISurfaceRenderer mRenderer;

    @Override
    public Engine onCreateEngine() {
        mRenderer = new VertexShaderRenderer(this, null);
        return new WallpaperEngine(getBaseContext(), mRenderer, ISurface.ANTI_ALIASING_CONFIG.MULTISAMPLING);
    }


}
