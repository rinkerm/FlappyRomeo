package android.support.p003v7.app;

import android.app.Notification;
import android.media.session.MediaSession;
import android.support.p000v4.app.NotificationBuilderWithBuilderAccessor;

/* renamed from: android.support.v7.app.NotificationCompatImpl21 */
class NotificationCompatImpl21 {
    NotificationCompatImpl21() {
    }

    public static void addMediaStyle(NotificationBuilderWithBuilderAccessor b, int[] actionsToShowInCompact, Object token) {
        Notification.MediaStyle style = new Notification.MediaStyle(b.getBuilder());
        if (actionsToShowInCompact != null) {
            style.setShowActionsInCompactView(actionsToShowInCompact);
        }
        if (token != null) {
            style.setMediaSession((MediaSession.Token) token);
        }
    }
}
