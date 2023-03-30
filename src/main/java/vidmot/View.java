package vidmot;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public enum View {
    UPPHAFSSENA("upphaf-view.fxml"),
    ERFIDLEIKASENA("erfidleika-view.fxml"),
    TIMAMORK("timi-view.fxml"),
    SKAKSENA("skak-view.fxml");

    private final String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
