import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
* Những cải tiến:
Thay mảng tĩnh bằng ArrayList: Giúp quản lý danh sách ID và tên sản phẩm linh hoạt hơn.
Tách hàm nhập số nguyên và số thực: Tránh lặp code, giảm lỗi nhập liệu.
Thêm kiểm tra lỗi nhập dữ liệu:
inputInteger() và inputFloat() giúp kiểm tra giới hạn giá trị.
Kiểm tra giá xuất phải lớn hơn giá nhập nhân với INTEREST.
Tối ưu displayData(): Hiển thị thông tin theo cách rõ ràng hơn.
* */

public class Product implements IApp {
    private static final float INTEREST = 1.2f; // Tỉ lệ lợi nhuận tối thiểu
    private static List<String> proIdList = new ArrayList<>();
    private static List<String> proNamesList = new ArrayList<>();

    private String productId;
    private String productName;
    private float importPrice;
    private float exportPrice;
    private String title;
    private String description;
    private int quantity;
    private int categoryId;
    private int status;

    public Product() {}

    public Product(String productId, String productName, float importPrice, float exportPrice,
                   String title, String description, int quantity, int categoryId, int status) {
        this.productId = productId;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.status = status;
        proIdList.add(productId);
        proNamesList.add(productName);
    }

    public static List<String> getProIdList() {
        return proIdList;
    }

    public static void setProIdList(List<String> proIdList) {
        Product.proIdList = proIdList;
    }

    public static List<String> getProNamesList() {
        return proNamesList;
    }

    public static void setProNamesList(List<String> proNamesList) {
        Product.proNamesList = proNamesList;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private boolean validateId(String id) {
        return id != null && id.matches("^[CET][a-zA-Z0-9]{4}$") && !proIdList.contains(id);
    }

    private boolean validateName(String name) {
        return name != null && name.length() >= 10 && name.length() <= 100 && !proNamesList.contains(name);
    }

    private int inputInteger(Scanner scanner, String message, int min, int max) {
        int value;
        while (true) {
            try {
                System.out.print(message);
                value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) break;
                System.out.println("Giá trị không hợp lệ! Hãy nhập lại.");
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
            }
        }
        return value;
    }

    private float inputFloat(Scanner scanner, String message, float min) {
        float value;
        while (true) {
            try {
                System.out.print(message);
                value = Float.parseFloat(scanner.nextLine());
                if (value > min) break;
                System.out.println("Giá trị không hợp lệ! Hãy nhập lại.");
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số thực hợp lệ!");
            }
        }
        return value;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.print("Nhập mã sản phẩm (5 ký tự, bắt đầu bằng C/E/T): ");
        String id = scanner.nextLine();
        while (!validateId(id)) {
            System.out.print("Mã không hợp lệ hoặc đã tồn tại, hãy nhập lại: ");
            id = scanner.nextLine();
        }
        this.productId = id;
        proIdList.add(id);

        System.out.print("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        while (!validateName(name)) {
            System.out.print("Tên không hợp lệ hoặc đã tồn tại, hãy nhập lại: ");
            name = scanner.nextLine();
        }
        this.productName = name;
        proNamesList.add(name);

        this.importPrice = inputFloat(scanner, "Nhập giá nhập: ", 0);
        this.exportPrice = inputFloat(scanner, "Nhập giá xuất (> " + (importPrice * INTEREST) + "): ", importPrice * INTEREST);

        System.out.print("Nhập tiêu đề: ");
        this.title = scanner.nextLine();
        while (this.title.length() > 200) {
            System.out.println("Tiêu đề quá dài, vui lòng nhập lại!");
            this.title = scanner.nextLine();
        }

        System.out.print("Nhập mô tả: ");
        this.description = scanner.nextLine();

        this.quantity = inputInteger(scanner, "Nhập số lượng sản phẩm: ", 1, Integer.MAX_VALUE);
        this.categoryId = inputInteger(scanner, "Nhập mã danh mục: ", 1, Integer.MAX_VALUE);
        this.status = inputInteger(scanner, "Nhập trạng thái (0: Hoạt động, 1: Hết hàng, 2: Không hoạt động): ", 0, 2);
    }

    @Override
    public void displayData() {
        String statusText = (status == 0) ? "Đang hoạt động" : (status == 1) ? "Hết hàng" : "Không hoạt động";
        System.out.println("=====================================");
        System.out.println("Mã sản phẩm   : " + productId);
        System.out.println("Tên sản phẩm  : " + productName);
        System.out.println("Giá nhập      : " + importPrice);
        System.out.println("Giá xuất      : " + exportPrice);
        System.out.println("Tiêu đề       : " + title);
        System.out.println("Số lượng      : " + quantity);
        System.out.println("Mã danh mục   : " + categoryId);
        System.out.println("Trạng thái    : " + statusText);
        System.out.println("=====================================");
    }
}
