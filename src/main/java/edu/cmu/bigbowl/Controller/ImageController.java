package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.Image;
import edu.cmu.bigbowl.Entity.Item;
import edu.cmu.bigbowl.Service.ImageService;
import edu.cmu.bigbowl.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ItemService itemService;

    // GET
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Image> getAllImages() {
        return imageService.getAllImages();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Image getImageById(@PathVariable("id") String id) {
        return imageService.getImageById(id).orElse(null);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Image deleteImage(@RequestBody Image image) {
        return imageService.deleteImage(image).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Image deleteImageById(@PathVariable("id") String id) {
        return imageService.deleteImageById(id).orElse(null);
    }

    @RequestMapping(value = "/reset", method = RequestMethod.DELETE)
    public void deleteAccounts() {
        imageService.deleteImages();
    }

    // PATCH
    @RequestMapping(method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Image updateImage(@RequestBody Image image) {
        return imageService.updateImages(image).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Image updateImageById(@PathVariable("id") String id, @RequestBody Image image) {
        return imageService.updateImageById(id, image).orElse(null);
    }

    // POST
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Image insertImage(@RequestBody Image image) {
        Image existingImage = imageService.getImageById(image.getImageId()).orElse(null);
        if(existingImage == null) {
            return imageService.postImage(image);
        }

        return imageService.updateImageById(image.getImageId(), image).get();
    }

}
