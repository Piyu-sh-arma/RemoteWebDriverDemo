import java.nio.file.Paths;

public class tst {
    public static void main(String[] args) {
        System.out.println(Paths.get("./resources/drivers/chromedriver.exe").normalize().toAbsolutePath());
    }
}
