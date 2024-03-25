public class test {
    public static void main(String[] args) {
        String filepath = "C:\\Users\\Yeswanth Upputuri\\CIA\\workspace\\CIA-finalproject\\src\\main\\java\\EmailSender.java";
         AzureConnector a = new AzureConnector("peer1");
         a.uploadFile(filepath);
    }
}
