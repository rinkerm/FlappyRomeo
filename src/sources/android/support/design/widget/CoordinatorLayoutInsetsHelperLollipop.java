package android.support.design.widget;

import android.support.p000v4.view.OnApplyWindowInsetsListener;
import android.support.p000v4.view.ViewCompat;
import android.view.View;

class CoordinatorLayoutInsetsHelperLollipop implements CoordinatorLayoutInsetsHelper {
    CoordinatorLayoutInsetsHelperLollipop() {
    }

    public void setupForWindowInsets(View view, OnApplyWindowInsetsListener insetsListener) {
        if (ViewCompat.getFitsSystemWindows(view)) {
            ViewCompat.setOnApplyWindowInsetsListener(view, insetsListener);
            view.setSystemUiVisibility(1280);
        }
    }
}
