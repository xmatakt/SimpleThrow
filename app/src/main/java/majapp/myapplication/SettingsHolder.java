package majapp.myapplication;

public class SettingsHolder {
    private static SettingsHolder dataObject = null;

    private SettingsHolder() {
        // left blank intentionally
    }

    public static SettingsHolder getInstance() {
        if (dataObject == null)
            dataObject = new SettingsHolder();
        return dataObject;
    }
    private Settings settings;

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
