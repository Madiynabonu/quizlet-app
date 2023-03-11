package com.nooglers.utils;

import com.nooglers.domains.Document;
import jakarta.servlet.http.Part;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;


public class ImageUtils {
    private static final Path rootPath = Path.of("/home/baxtigul/apps/library/upload");

    public static InputStream resizeImage(InputStream uploadedInputStream , String extension , int width , int height) {
        try {
            BufferedImage image = ImageIO.read(uploadedInputStream);
            Image originalImage = image.getScaledInstance(width , height , Image.SCALE_SMOOTH);

            int type = ( ( image.getType() == 0 ) ? BufferedImage.TYPE_INT_ARGB : image.getType() );
            BufferedImage resizedImage = new BufferedImage(width , height , type);

            Graphics2D g2d = resizedImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION , RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING , RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(originalImage , 0 , 0 , width , height , null);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            ImageIO.write(resizedImage , extension , byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch ( IOException e ) {
            return uploadedInputStream;
        }
    }

    public static Document saveImage(Part part) {
        try {
            String generateUniqueName = StringUtils.generateUniqueName(part);
            String originalName = part.getSubmittedFileName();
            String extension = StringUtils.getFileExtension(originalName);
            String mimeType = part.getContentType();
            String fullPath = rootPath.resolve(generateUniqueName).toString();
            Document document = Document.builder()
                    .generatedFileName(generateUniqueName)
                    .originalFileName(originalName)
                    .fileSize(part.getSize())
                    .mimeType(mimeType)
                    .filePath(fullPath)
                    .extension(extension)
                    .build();
            InputStream inputStream = ImageUtils.resizeImage(part.getInputStream() , extension , 100 , 100);
            OutputStream fileOutputStream = new FileOutputStream(fullPath);
            fileOutputStream.write(inputStream.readAllBytes());
            fileOutputStream.close();
            return document;
        } catch ( IOException e ) {
            throw new RuntimeException(e);
        }
    }
}
