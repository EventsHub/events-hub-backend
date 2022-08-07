package com.saxakiil.eventshubbackend.manager;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CdnManager {

    private final Cloudinary cloudinary;

    @SneakyThrows
    public String uploadFile(MultipartFile image) {
        final byte[] uploadedFile = convertMultiPartToBytes(image);
        Map<String, Object> mapResponse = cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
        return mapResponse.get("url").toString();
    }

    @SneakyThrows
    public void deleteFile(final String publicId) {
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

    private byte[] convertMultiPartToBytes(MultipartFile file) throws IOException {
        return file.getInputStream().readAllBytes();
    }
}
