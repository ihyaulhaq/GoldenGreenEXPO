package sukarelawan_page;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.layout.StackPane;

//  kelas  untuk mengelola status aplikasi
public class AppStatusDR {
    private static Set<StackPane> stackPanes = new HashSet<>();

    public static Set<StackPane> getStackPane() {
        return stackPanes;
    }

    public static void setStackPane(StackPane newStackPane) {
        stackPanes.add(newStackPane);
    }
}
