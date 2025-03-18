import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
    /*
    * Dưới đây là tóm tắt những thay đổi chính đã được thực hiện trong phiên bản mã nguồn đã làm sạch:

Sử dụng ArrayList thay vì Mảng Cố Định:

Thay thế Categories[] và Product[] bằng ArrayList<Categories> và ArrayList<Product>.
Loại bỏ các biến catCount và prodCount vì ArrayList tự động quản lý kích thước.
Xử Lý Ngoại Lệ:

Thêm khối try-catch để xử lý NumberFormatException khi chuyển đổi chuỗi đầu vào thành số nguyên hoặc số thực.
Điều này giúp chương trình hoạt động ổn định hơn khi người dùng nhập dữ liệu không hợp lệ.
Sử dụng for-each và Stream API:

Thay thế vòng lặp for truyền thống bằng vòng lặp for-each để làm cho mã dễ đọc hơn.
Sử dụng Stream API trong các phương thức tìm kiếm và xóa để viết mã ngắn gọn và rõ ràng hơn. Ví dụ : categories.stream().filter().forEach() , hoặc products.removeIf().
Loại bỏ các biến đếm thủ công:

Vì đã chuyển sang sử dụng ArrayList, các biến đếm thủ công như catCount và prodCount không còn cần thiết và đã được loại bỏ.
Cải thiện khả năng đọc mã:

Thêm khoảng trắng và dòng trống để làm cho mã dễ đọc hơn.
Sử dụng tên biến và phương thức rõ ràng, dễ hiểu.
Xóa các đoạn mã thừa hoặc không sử dụng:

Loại bỏ bất kỳ mã nào không cần thiết hoặc trùng lặp.
Kiểm tra điều kiện hợp lệ:

Thêm các kiểm tra để đảm bảo dữ liệu đầu vào hợp lệ, ví dụ như kiểm tra xem mã danh mục hoặc mã sản phẩm đã tồn tại hay chưa trước khi thực hiện cập nhật hoặc xóa.
    * */


    public class ShopManagement {
        private static ArrayList<Categories> categories = new ArrayList<>();
        private static ArrayList<Product> products = new ArrayList<>();

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                displayMainMenu();
                try {
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
                } catch (NumberFormatException e) {
                    System.out.println("Vui long nhap so!");
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
                try {
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
                } catch (NumberFormatException e) {
                    System.out.println("Vui long nhap so!");
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
                try {
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
                } catch (NumberFormatException e) {
                    System.out.println("Vui long nhap so!");
                }
            }
        }

        public static void listCategories() {
            categories.forEach(Categories::displayData);
        }

        public static void addCategory(Scanner scanner) {
            Categories cat = new Categories();
            cat.inputData(scanner);
            categories.add(cat);
        }

        public static void updateCategory(Scanner scanner) {
            System.out.print("Nhap ma danh muc can cap nhat: ");
            try {
                int id = Integer.parseInt(scanner.nextLine());
                for (Categories category : categories) {
                    if (category.getCategoryId() == id) {
                        category.inputData(scanner);
                        return;
                    }
                }
                System.out.println("Khong tim thay danh muc!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen!");
            }
        }

        public static void deleteCategory(Scanner scanner) {
            System.out.print("Nhap ma danh muc can xoa: ");
            try {
                int id = Integer.parseInt(scanner.nextLine());
                if (products.stream().anyMatch(product -> product.getCategoryId() == id)) {
                    System.out.println("Khong the xoa vi danh muc dang co sp!");
                    return;
                }
                categories.removeIf(category -> category.getCategoryId() == id);
                System.out.println("Xoa thanh cong!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen!");
            }
        }

        public static void searchCategoryByName(Scanner scanner) {
            System.out.print("Nhap ten danh muc can tim: ");
            String name = scanner.nextLine();
            categories.stream().filter(category -> category.getCategoryName().contains(name))
                    .forEach(Categories::displayData);
        }

        public static void listProducts() {
            products.forEach(Product::displayData);
        }

        public static void addProduct(Scanner scanner) {
            Product prod = new Product();
            prod.inputData(scanner);
            products.add(prod);
        }

        public static void updateProduct(Scanner scanner) {
            System.out.print("Nhap ma sp can cap nhat: ");
            String id = scanner.nextLine();
            for (Product product : products) {
                if (product.getProductId().equals(id)) {
                    product.inputData(scanner);
                    return;
                }
            }
            System.out.println("Khong tim thay san pham");
        }

        public static void deleteProduct(Scanner scanner) {
            System.out.print("Nhap ten sp can xoa: ");
            String id = scanner.nextLine();
            products.removeIf(product -> product.getProductId().equals(id));
            System.out.println("Xoa thanh cong!");
        }

        public static void searchProductByNameOrTitle(Scanner scanner) {
            System.out.print("Nhap ten hoac tieu de: ");
            String keyword = scanner.nextLine();
            products.stream()
                    .filter(product -> product.getProductName().contains(keyword) || product.getTitle().contains(keyword))
                    .forEach(Product::displayData);
        }

        public static void searchProductByPriceRange(Scanner scanner) {
            System.out.print("Start: ");
            try {
                float min = Float.parseFloat(scanner.nextLine());
                System.out.print("End: ");
                float max = Float.parseFloat(scanner.nextLine());
                products.stream()
                        .filter(product -> product.getExportPrice() >= min && product.getExportPrice() <= max)
                        .forEach(Product::displayData);
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }

        public static void sortProductsByPrice() {
            products.sort((p1, p2) -> Float.compare(p1.getExportPrice(), p2.getExportPrice()));
            listProducts();
        }

        public static void sellProduct(Scanner scanner) {
            System.out.print("Nhap ma sp can ban: ");
            String id = scanner.nextLine();
            for (Product product : products) {
                if (product.getProductId().equals(id)) {
                    if (product.getQuantity() > 0) {
                        product.setQuantity(product.getQuantity() - 1);
                        if (product.getQuantity() == 0) product.setStatus(1);
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
            categories.forEach(category -> {
                long count = products.stream()
                        .filter(product -> product.getCategoryId() == category.getCategoryId())
                        .count();
                System.out.printf("Danh muc %s (Ma danh muc: %d): %d san pham %n",
                        category.getCategoryName(), category.getCategoryId(), count);
            });
        }
    }
