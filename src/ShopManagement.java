import java.util.Scanner;

public class ShopManagement {
    private static Categories[] categories = new Categories[100]; // Giả định tối đa 100 danh mục
    private static int catCount = 0; // Đếm số lượng danh mục hiện có
    private static Product[] products = new Product[1000]; // Giả định tối đa 1000 sản phẩm
    private static int prodCount = 0; // Đếm số lượng sản phẩm hiện có

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    manageCategories(scanner);
                    break;
                case 2:
                    manageProducts(scanner);
                    break;
                case 3:
                    System.out.println("Thoat chuong trinh");
                    return;
                default:
                    System.out.println("Lua chon khong hop le, hay nhap lai");
            }
        }
    }

    public static void displayMainMenu() {
        System.out.println("*********************SHOP MENU*********************");
        System.out.println("1. Quan ly danh muc");
        System.out.println("2. Quan ly sp");
        System.out.println("3. Thoat");
        System.out.print("Nhap lua chon cua ban: ");
    }

    public static void manageCategories(Scanner scanner) {
        while (true) {
            System.out.println("********************CATEGORIE MANAGEMENT*********************");
            System.out.println("1. Danh sach danh muc");
            System.out.println("2. Them moi danh muc");
            System.out.println("3. Cap nhat danh muc");
            System.out.println("4. Xoa danh muc");
            System.out.println("5. Tim kiem danh muc theo ten");
            System.out.println("6. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    listCategories();
                    break;
                case 2:
                    addCategory(scanner);
                    break;
                case 3:
                    updateCategory(scanner);
                    break;
                case 4:
                    deleteCategory(scanner);
                    break;
                case 5:
                    searchCategoryByName(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Lua chon khong hop le");
            }
        }
    }

    public static void manageProducts(Scanner scanner) {
        while (true) {
            System.out.println("************************PRODUCT MANAGEMENT*******************");
            System.out.println("1. Danh sach san pham");
            System.out.println("2. Them moi san pham");
            System.out.println("3. Cap nhat san pham");
            System.out.println("4. Xoa san pham");
            System.out.println("5. Tim kiem san pham theo ten hoac tieu de");
            System.out.println("6. Tim kiem sp theo khaong gia ban");
            System.out.println("7. Sap xeo sp theo gia ban tang dan");
            System.out.println("8. Ban san pham");
            System.out.println("9. Thong ke so luong sp theo danh muc");
            System.out.println("10. Thoat");
            System.out.print("Nhap vao lua chonj cua ban: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    listProducts();
                    break;
                case 2:
                    addProduct(scanner);
                    break;
                case 3:
                    updateProduct(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    searchProductByNameOrTitle(scanner);
                    break;
                case 6:
                    searchProductByPriceRange(scanner);
                    break;
                case 7:
                    sortProductsByPrice();
                    break;
                case 8:
                    sellProduct(scanner);
                    break;
                case 9:
                    getProductByCategories();
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Lua chon khong hop le, hay nhap lai!");
            }
        }
    }

    public static void listCategories() {
        for (int i = 0; i < catCount; i++) {
            categories[i].displayData();
        }
    }

    public static void addCategory(Scanner scanner) {
        if (catCount < categories.length) {
            Categories cat = new Categories();
            cat.inputData(scanner);
            categories[catCount] = cat;
            catCount++;
        } else {
            System.out.println("Danh sach danh muc da day!");
        }
    }

    public static void updateCategory(Scanner scanner) {
        System.out.print("Nhap ma danh muc can cap nhat: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < catCount; i++) {
            if (categories[i].getCategoryId() == id) {
                categories[i].inputData(scanner);
                return;
            }
        }
        System.out.println("Khong tim thay danh muc!");
    }

    public static void deleteCategory(Scanner scanner) {
        System.out.print("Nhap ma danh muc can xoa: ");
        int id = Integer.parseInt(scanner.nextLine());
        // Kiểm tra xem danh mục có sản phẩm không
        boolean hasProducts = false;
        for (int i = 0; i < prodCount; i++) {
            if (products[i].getCategoryId() == id) {
                hasProducts = true;
                break;
            }
        }
        
        if (hasProducts) {
            System.out.println("Khong the xoa vi danh muc dang co sp!");
            return;
        }
        
        // Tìm vị trí của danh mục cần xóa
        int deleteIndex = -1;
        for (int i = 0; i < catCount; i++) {
            if (categories[i].getCategoryId() == id) {
                deleteIndex = i;
                break;
            }
        }
        
        // Nếu tìm thấy, dịch chuyển các phần tử để xóa
        if (deleteIndex != -1) {
            for (int i = deleteIndex; i < catCount - 1; i++) {
                categories[i] = categories[i + 1];
            }
            catCount--;
            System.out.println("Xoa thanh cong!");
        } else {
            System.out.println("Khong tim thay danh muc!");
        }
    }

    public static void searchCategoryByName(Scanner scanner) {
        System.out.print("Nhap ten danh muc can tim: ");
        String name = scanner.nextLine();
        for (int i = 0; i < catCount; i++) {
            if (categories[i].getCategoryName().contains(name)) {
                categories[i].displayData();
            }
        }
    }

    public static void listProducts() {
        for (int i = 0; i < prodCount; i++) {
            products[i].displayData();
        }
    }

    public static void addProduct(Scanner scanner) {
        if (prodCount < products.length) {
            Product prod = new Product();
            prod.inputData(scanner);
            products[prodCount] = prod;
            prodCount++;
        } else {
            System.out.println("Danh sach san pham da day!");
        }
    }

    public static void updateProduct(Scanner scanner) {
        System.out.print("Nhap ma sp can cap nhat: ");
        String id = scanner.nextLine();
        for (int i = 0; i < prodCount; i++) {
            if (products[i].getProductId().equals(id)) {
                products[i].inputData(scanner);
                return;
            }
        }
        System.out.println("Khong tim thay san pham");
    }

    public static void deleteProduct(Scanner scanner) {
        System.out.print("Nhap ma sp can xoa: ");
        String id = scanner.nextLine();
        int deleteIndex = -1;
        for (int i = 0; i < prodCount; i++) {
            if (products[i].getProductId().equals(id)) {
                deleteIndex = i;
                break;
            }
        }
        
        if (deleteIndex != -1) {
            for (int i = deleteIndex; i < prodCount - 1; i++) {
                products[i] = products[i + 1];
            }
            prodCount--;
            System.out.println("Xoa thanh cong!");
        } else {
            System.out.println("Khong tim thay san pham!");
        }
    }

    public static void searchProductByNameOrTitle(Scanner scanner) {
        System.out.print("Nhap ten hoac tieu de: ");
        String keyword = scanner.nextLine();
        for (int i = 0; i < prodCount; i++) {
            if (products[i].getProductName().contains(keyword) || products[i].getTitle().contains(keyword)) {
                products[i].displayData();
            }
        }
    }

    public static void searchProductByPriceRange(Scanner scanner) {
        System.out.print("Start: ");
        float min = Float.parseFloat(scanner.nextLine());
        System.out.print("End: ");
        float max = Float.parseFloat(scanner.nextLine());
        for (int i = 0; i < prodCount; i++) {
            if (products[i].getExportPrice() >= min && products[i].getExportPrice() <= max) {
                products[i].displayData();
            }
        }
    }

    public static void sortProductsByPrice() {
        // Sắp xếp sản phẩm theo giá bán tăng dần (bubble sort)
        for (int i = 0; i < prodCount - 1; i++) {
            for (int j = 0; j < prodCount - i - 1; j++) {
                if (products[j].getExportPrice() > products[j + 1].getExportPrice()) {
                    // Hoán đổi vị trí
                    Product temp = products[j];
                    products[j] = products[j + 1];
                    products[j + 1] = temp;
                }
            }
        }
        listProducts();
    }

    public static void sellProduct(Scanner scanner) {
        System.out.print("Nhap ma sp can ban: ");
        String id = scanner.nextLine();
        for (int i = 0; i < prodCount; i++) {
            if (products[i].getProductId().equals(id)) {
                if (products[i].getQuantity() > 0) {
                    products[i].setQuantity(products[i].getQuantity() - 1);
                    if (products[i].getQuantity() == 0) {
                        products[i].setStatus(1);
                    }
                    System.out.println("Ban thanh cong!");
                } else {
                    System.out.println("San pham da het hang!");
                }
                return;
            }
        }
        System.out.println("Khong tim thay san pham");
    }

    public static void getProductByCategories() {
        for (int i = 0; i < catCount; i++) {
            int count = 0;
            for (int j = 0; j < prodCount; j++) {
                if (products[j].getCategoryId() == categories[i].getCategoryId()) {
                    count++;
                }
            }
            System.out.printf("Danh muc %s (Ma danh muc: %d): %d san pham %n",
                    categories[i].getCategoryName(), categories[i].getCategoryId(), count);
        }
    }
}
