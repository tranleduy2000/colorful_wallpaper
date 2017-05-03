package com.duy.colorfulwallpaper;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.duy.colorfulwallpaper.fragments.VertexShaderFragment;
import com.duy.colorfulwallpaper.services.ColorWallpaperService;


public class PreviewActivity extends AppCompatActivity implements View.OnClickListener {

    VertexShaderFragment fragment;
    private Preferences preferences;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.preview_activity);
        this.preferences = new Preferences(this);
        initContent();
    }

    public void reloadView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (fragment != null) {
            fragmentTransaction.remove(fragment);
        }
        fragment = new VertexShaderFragment();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    private void initContent() {

        findViewById(R.id.btn_set).setOnClickListener(this);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                preferences.setDrawMode(position);
                reloadView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void startSetWallpaper() {
        Intent intent = new Intent();

        if (Build.VERSION.SDK_INT >= 16) {
            /*
             * Open live wallpaper preview (API Level 16 or greater).
			 */
            intent.setAction(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
            intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(this,
                    ColorWallpaperService.class));
        } else {
            /*
             * Open live wallpaper picker (API Level 15 or lower).
			 *
			 * Display a quick little message (toast) with instructions.
			 */
            intent.setAction(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
            Resources res = getResources();
            String hint = res.getString(R.string.picker_toast_prefix)
                    + res.getString(R.string.app_name)
                    + res.getString(R.string.picker_toast_suffix);
            Toast toast = Toast.makeText(this, hint, Toast.LENGTH_LONG);
            toast.show();
        }

        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set:
                startSetWallpaper();
                break;
        }
    }
}
