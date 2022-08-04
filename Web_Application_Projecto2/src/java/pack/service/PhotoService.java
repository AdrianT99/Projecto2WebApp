
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "PhotoController")
@ApplicationScoped
public class PhotoService {

    private List<Photo> photos;

    @PostConstruct
    public void init() {
        photos = new ArrayList<>();

        photos.add(new Photo("demo/images/slider1.jpg", "demo/images/slider2.jpg",
                "Description for Image 1", "Title 1"));
        photos.add(new Photo("demo/images/slider3.jpg", "demo/images/slider4.jpg",
                "Description for Image 2", "Title 2"));
    }

    public List<Photo> getPhotos() {
        return photos;
    }
}