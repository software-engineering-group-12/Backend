package com.example.backend.firebase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FirebaseStorage {

    public static void uploadFile(MultipartFile file, String fileName) throws IOException {
        try {
            Bucket bucket = StorageClient.getInstance().bucket();
            bucket.create(fileName + "." + FilenameUtils.getExtension(file.getOriginalFilename()), file.getInputStream());

        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public static String getUrl(String id) {
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.get(id + ".jpg");
        return blob.signUrl(365, TimeUnit.DAYS).toString();
    }
}
