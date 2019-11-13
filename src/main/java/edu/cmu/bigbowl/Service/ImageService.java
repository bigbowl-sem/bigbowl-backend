package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.ImageDao;
import edu.cmu.bigbowl.Entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;

@Service
public class ImageService {

    @Autowired
    private ImageDao imageDao;

    // Create
    public Image postImage(Image image) {
        return imageDao.save(image);
    }

    public void postFakeImage() throws IOException {
        Random r = new Random();
        Integer numOfImage = 50;
        Path fileLocation = Paths.get("/Users/yflou/Workspace/CMU_MSSM/19fall/Software_Engineering_Management_49786/bigbowl-backend/src/main/java/edu/cmu/bigbowl/resource/cmu.jpg");
        byte[] img = Files.readAllBytes(fileLocation);
        for (Integer cnt = 0; cnt < numOfImage; cnt += 1)
        {
            Image image = new Image("Fake" + cnt, img);
            imageDao.save(image);
        }

        return;
    }

    // Read
    public Collection<Image> getAllImages() {
        return imageDao.findAll();
    }

    public Optional<Image> getImageById(String id) {
        return imageDao.findById(id);
    }

    // Update
    public Optional<Image> updateImages(Image image) {
        if (image.getImageId() != null) {
            // TODO: 10/22/19
            // Right now it will save with the latest JSON which it's Id matched. But won't update
            // accordingly.
            return updateImageById(image.getImageId(), image);
        }
        else{
            return null;
        }
    }

    public Optional<Image> updateImageById(String id, Image image) {
        Optional<Image> optImage = imageDao.findById(id);
        // TODO: 10/22/19
        // Right now it will save with the latest JSON which it's Id matched. But won't update
        // accordingly.
        optImage.ifPresent(theImage -> imageDao.save(image));
        return optImage;
    }

    // Delete
    public Optional<Image> deleteImage(Image image) {
        Optional<Image> optImage = imageDao.findById(image.getImageId());
        optImage.ifPresent(theImage -> imageDao.delete(theImage));
        return optImage;
    }

    public Optional<Image> deleteImageById(String id) {
        Optional<Image> optImage = imageDao.findById(id);
        optImage.ifPresent(theImage -> imageDao.delete(theImage));
        return optImage;
    }

    public void deleteImages() {
        imageDao.deleteAll();
    }
}
