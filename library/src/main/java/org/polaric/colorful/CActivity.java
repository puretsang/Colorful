package org.polaric.colorful;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

public abstract class CActivity extends AppCompatActivity {
    private String themeString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeString=Colorful.getThemeString();
        setTheme(Colorful.getThemeDelegate().getStyle());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Colorful.getThemeDelegate().isTranslucent()) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!Colorful.getThemeString().equals(themeString)) {
            Log.d(Util.LOG_TAG, "Theme change detected, restarting activity");
            recreate();
        }
    }
}
