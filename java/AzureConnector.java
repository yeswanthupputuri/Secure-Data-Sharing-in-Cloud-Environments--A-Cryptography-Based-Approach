import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class AzureConnector {
    private BlobServiceClient blobServiceClient;
    private BlobContainerClient blobContainerClient;
    String conn = "DefaultEndpointsProtocol=https;AccountName=ciaproject;AccountKey=lLscjZ4rOwvRsUeZ/JkXFDSNk0wo37Yq3cACGhBGC4Sii98t8ses8P2IQSzL9m5Zwn5gFvQ1OpUX+AStFGse5w==;EndpointSuffix=core.windows.net";

    public AzureConnector() {
        this.blobServiceClient = new BlobServiceClientBuilder().connectionString(conn).buildClient();
    }
    public AzureConnector(String containerName) {
        this.blobServiceClient = new BlobServiceClientBuilder().connectionString(conn).buildClient();
        this.blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
        if (!this.blobContainerClient.exists()) {
            this.blobContainerClient = blobServiceClient.createBlobContainer(containerName);
        }
    }

    /**
     * Uploads a file to the connected Azure Blob container.
     *
     * @param filePath The local file path of the file to upload.
     */
    public void uploadFile(String filePath) {
        File file = new File(filePath);
        String blobName = file.getName();
        BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
        System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());
        // Upload the file
        blobClient.uploadFromFile(filePath);
    }

    public String getFileContent(String fileName) {
        String[] containerNames = {"peer1", "peer2"};
        for (String containerName : containerNames) {
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
            if (containerClient.exists()) {
                var blobItems = containerClient.listBlobs().stream().collect(Collectors.toList());
                for (BlobItem blobItem : blobItems) {
                    if (blobItem.getName().equals(fileName)) {
                        BlobClient blobClient = containerClient.getBlobClient(blobItem.getName());
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        blobClient.download(outputStream);
                        saveFileToLocalDownloadsFolder(fileName, outputStream.toByteArray());
                        return outputStream.toString(StandardCharsets.UTF_8);
                    }
                }
            }
        }
        return null; // File not found in any container

    }
    private void saveFileToLocalDownloadsFolder(String fileName, byte[] fileContent) {
        try {
            String homeDirectory = System.getProperty("user.home");
            File downloadsFolder = new File(homeDirectory, "Downloads");
            File outputFile = new File(downloadsFolder, fileName);

            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                fos.write(fileContent);
                System.out.println("File downloaded to: " + outputFile.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to save file to local Downloads folder.");
        }
    }
}