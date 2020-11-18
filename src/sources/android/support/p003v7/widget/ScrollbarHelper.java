package android.support.p003v7.widget;

import android.support.p003v7.widget.RecyclerView;
import android.view.View;

/* renamed from: android.support.v7.widget.ScrollbarHelper */
class ScrollbarHelper {
    ScrollbarHelper() {
    }

    static int computeScrollOffset(RecyclerView.State state, OrientationHelper orientation, View startChild, View endChild, RecyclerView.LayoutManager lm, boolean smoothScrollbarEnabled, boolean reverseLayout) {
        if (lm.getChildCount() == 0 || state.getItemCount() == 0 || startChild == null || endChild == null) {
            return 0;
        }
        int itemsBefore = reverseLayout ? Math.max(0, (state.getItemCount() - Math.max(lm.getPosition(startChild), lm.getPosition(endChild))) - 1) : Math.max(0, Math.min(lm.getPosition(startChild), lm.getPosition(endChild)));
        if (!smoothScrollbarEnabled) {
            return itemsBefore;
        }
        return Math.round((((float) itemsBefore) * (((float) Math.abs(orientation.getDecoratedEnd(endChild) - orientation.getDecoratedStart(startChild))) / ((float) (Math.abs(lm.getPosition(startChild) - lm.getPosition(endChild)) + 1)))) + ((float) (orientation.getStartAfterPadding() - orientation.getDecoratedStart(startChild))));
    }

    static int computeScrollExtent(RecyclerView.State state, OrientationHelper orientation, View startChild, View endChild, RecyclerView.LayoutManager lm, boolean smoothScrollbarEnabled) {
        if (lm.getChildCount() == 0 || state.getItemCount() == 0 || startChild == null || endChild == null) {
            return 0;
        }
        if (!smoothScrollbarEnabled) {
            return Math.abs(lm.getPosition(startChild) - lm.getPosition(endChild)) + 1;
        }
        return Math.min(orientation.getTotalSpace(), orientation.getDecoratedEnd(endChild) - orientation.getDecoratedStart(startChild));
    }

    static int computeScrollRange(RecyclerView.State state, OrientationHelper orientation, View startChild, View endChild, RecyclerView.LayoutManager lm, boolean smoothScrollbarEnabled) {
        if (lm.getChildCount() == 0 || state.getItemCount() == 0 || startChild == null || endChild == null) {
            return 0;
        }
        if (!smoothScrollbarEnabled) {
            return state.getItemCount();
        }
        return (int) ((((float) (orientation.getDecoratedEnd(endChild) - orientation.getDecoratedStart(startChild))) / ((float) (Math.abs(lm.getPosition(startChild) - lm.getPosition(endChild)) + 1))) * ((float) state.getItemCount()));
    }
}