package org.rajawali3d.examples.wallpaper;

import org.rajawali3d.examples.Preferences;
import org.rajawali3d.examples.examples.materials.CustomVertexShaderFragment;
import org.rajawali3d.examples.examples.optimizations.UpdateVertexBufferFragment.UpdateVertexBufferRenderer;
import org.rajawali3d.examples.examples.ui.CanvasTextFragment.CanvasTextRenderer;
import org.rajawali3d.renderer.ISurfaceRenderer;
import org.rajawali3d.util.RajLog;
import org.rajawali3d.view.ISurface;
import org.rajawali3d.wallpaper.Wallpaper;

/**
 * @author Jared Woolston (jwoolston@idealcorp.com)
 */
public class RajawaliExampleWallpaper extends Wallpaper {

    private ISurfaceRenderer mRenderer;

    @Override
    public Engine onCreateEngine() {
        RajLog.v("Creating wallpaper engine.");
        int renderer;
        try {
            renderer = Integer.parseInt(Preferences.getInstance(this).getWallpaperRendererPreference());
        } catch (Exception e) {
            renderer = 0;
        }
        RajLog.i("Creating wallpaper engine: " + renderer);
        // TODO: I'm sure there is a better way to do this
        switch (renderer) {
            case 0:
                mRenderer = new UpdateVertexBufferRenderer(this, null);
                break;
            case 1:
                mRenderer = new CustomVertexShaderFragment.VertexShaderRenderer(this, null);
                break;
            case 2:
                mRenderer = new CanvasTextRenderer(this, null);
                break;
            default:
                mRenderer = new WallpaperRenderer(this);
        }
        return new WallpaperEngine(getBaseContext(), mRenderer, ISurface.ANTI_ALIASING_CONFIG.NONE);
    }
}
