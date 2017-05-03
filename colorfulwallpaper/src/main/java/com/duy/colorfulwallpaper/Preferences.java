package com.duy.colorfulwallpaper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @author Jared Woolston (jwoolston@keywcorp.com)
 */
public class Preferences {
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private Context context;

    public Preferences(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
        editor = sharedPreferences.edit();
    }

    public void setWallStyle(int position) {
        editor.putInt(context.getString(R.string.key_wall), position);
    }

    public int getPositionWall() {
        try {
            return sharedPreferences.getInt(context.getString(R.string.key_wall), 0);
        } catch (Exception e) {
            return Integer.parseInt(sharedPreferences.getString(context.getString(R.string.key_wall), "0"));
        }
    }

    public boolean useAntiAliasing() {
        return false;
    }

    public int getDrawType() {
        return sharedPreferences.getInt(context.getString(R.string.key_draw_mode), 0);
    }

    public void setDrawMode(int mode) {
        editor.putInt(context.getString(R.string.key_draw_mode), mode).commit();
    }
}
