package viewmodels;

/**
 * Created by zahmed on 12/12/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */
public class DashboardMenuViewModel {
    private String title;
    private String description;
    private int image;

    public DashboardMenuViewModel(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }
}
