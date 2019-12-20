package com.navigationhybrid.playground;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import me.listenzz.navigation.AwesomeFragment;

public class SplashFragment extends AwesomeFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        setStyle(STYLE_NO_FRAME, R.style.SplashTheme);
        setCancelable(false);
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    protected Integer preferredNavigationBarColor() {
        return Color.TRANSPARENT;
    }

}
