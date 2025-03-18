import java.util.Scanner;

public class Categories implements IApp {
    private static int autoIncrementId = 1;
    private static String[] categoryNames = new String[100];
    private static int categoryCount = 0;

    private int categoryId;
    private String categoryName;
    private int priority;
    private String description;
    private boolean status;

    public Categories() {
        this.categoryId = autoIncrementId++;
    }

    public Categories(String categoryName, int priority, String description, boolean status) {
        this.categoryId = autoIncrementId++;
        this.categoryName = categoryName;
        this.priority = priority;
        this.description = description;
        this.status = status;
        addCategoryName(categoryName);
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        if (validateName(categoryName) && !isDuplicate(categoryName)) {
            removeCategoryName(this.categoryName);
            addCategoryName(categoryName);
            this.categoryName = categoryName;
        }
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.length() > 255 ? description.substring(0, 255) : description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private boolean validateName(String name) {
        return name != null && name.length() >= 6 && name.length() <= 50;
    }

    private boolean isDuplicate(String name) {
        for (int i = 0; i < categoryCount; i++) {
            if (categoryNames[i] != null && categoryNames[i].equals(name)) {
                return true;
            }
        }
        return false;
    }

    private void addCategoryName(String name) {
        if (categoryCount < categoryNames.length) {
            categoryNames[categoryCount++] = name;
        }
    }

    private void removeCategoryName(String name) {
        for (int i = 0; i < categoryCount; i++) {
            if (categoryNames[i] != null && categoryNames[i].equals(name)) {
                categoryNames[i] = categoryNames[--categoryCount];
                categoryNames[categoryCount] = null;
                break;
            }
        }
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.print("Nhap ten danh muc: ");
        String name = scanner.nextLine();
        while (!validateName(name) || isDuplicate(name)) {
            System.out.print("Ten khong hop le hoac da ton tai, vui long nhap lai: ");
            name = scanner.nextLine();
        }
        this.categoryName = name;
        addCategoryName(name);

        this.priority = inputInteger(scanner, "Nhap do uu tien: ");

        System.out.print("Nhap mo ta: ");
        this.description = scanner.nextLine();
        if (description.length() > 255) {
            this.description = description.substring(0, 255);
        }

        System.out.print("Nhap trang thai (true/false): ");
        this.status = Boolean.parseBoolean(scanner.nextLine());
    }

    @Override
    public void displayData() {
        System.out.printf("Ma danh muc: %d | Ten: %s | Do uu tien: %d | Mo ta: %s | Trang thai: %s%n",
                categoryId, categoryName, priority, description, status ? "Hoat dong" : "Khong hoat dong");
    }

    private int inputInteger(Scanner scanner, String message) {
        int number;
        while (true) {
            try {
                System.out.print(message);
                number = Integer.parseInt(scanner.nextLine());
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Gia tri khong hop le, vui long nhap lai!");
            }
        }
    }
}
