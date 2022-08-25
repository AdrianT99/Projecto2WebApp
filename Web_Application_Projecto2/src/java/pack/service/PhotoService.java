
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

        photos.add(new Photo("demo/images/imagen1.jpg", "demo/images/imagen1.jpg",
                "Description for Image 1", "Title 1"));
        photos.add(new Photo("demo/images/imagen2.jpg", "demo/images/imagen2.jpg",
                "Description for Image 2", "Title 2"));
        photos.add(new Photo("demo/images/imagen3.jpg", "demo/images/imagen3.jpg",
                "Description for Image 1", "Title 1"));
        photos.add(new Photo("demo/images/imagen4.jpg", "demo/images/imagen14.jpg",
                "Description for Image 2", "Title 2"));
    }

    public List<Photo> getPhotos() {
        return photos;
    }
}