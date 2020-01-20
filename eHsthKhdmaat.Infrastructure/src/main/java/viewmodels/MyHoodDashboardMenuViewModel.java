package viewmodels;

/**
 * Created by aahmed on 12/12/2018.
 * ITF
 * http://www.itf.com
 */
public class MyHoodDashboardMenuViewModel {
    private String title;
    private String description;
    private int image;

    public MyHoodDashboardMenuViewModel(String title, String description, int image) {
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
