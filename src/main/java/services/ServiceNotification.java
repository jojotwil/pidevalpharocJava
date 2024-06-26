package Services;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class ServiceNotification {

    public static void showNotif(String text, String text2) {
        Notifications notificationBuilder = Notifications.create()
                .title(text)
                .text(text2)
                .graphic(null)
                .hideAfter(Duration.seconds(50))
                .position(Pos.TOP_CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                    }
                });

        notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();
    }

}