package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.CategoryController;
import rikkei.academy.model.Category;
import rikkei.academy.validate.Validate;

import java.util.List;

public class CategoryView {
    public CategoryView() {
        System.out.println("\033[38;2;255;217;0m.―――――――――――――\033[0m CATEGORY MANAGE \033[38;2;255;217;0m――――――――――――.\033[0m");
        System.out.println("\033[38;2;255;217;0m│\033[0m   1. Show List Category                                \033[38;2;255;217;0m │\033[0m");
        System.out.println("\033[38;2;255;217;0m│ \033[0m  2. Create Category                                   \033[38;2;255;217;0m │\033[0m");
        System.out.println("\033[38;2;255;217;0m│ \033[0m  3. Update Category                                    \033[38;2;255;217;0m│\033[0m");
        System.out.println("\033[38;2;255;217;0m│ \033[0m  4. Delete Category                                   \033[38;2;255;217;0m │\033[0m");
        System.out.println("\033[38;2;255;217;0m│\033[0m   5. Detail Category                                  \033[38;2;255;217;0m  │\033[0m");
        System.out.println("\033[38;2;255;217;0m│ \033[0m  6. Back Menu                                        \033[38;2;255;217;0m  │\033[0m");
        System.out.println("\033[38;2;255;217;0m・―――――――――――――――――――――――――――――――――――・\033[0m");
        int chooseMenu = Integer.parseInt(Config.scanner().nextLine());
        switch (chooseMenu) {
            case 1:
                showFormCategoryList();
                break;
            case 2:
                formCreateCategory();
                break;
            case 3:
                formUpdateCategory();
                break;
            case 4:
                formDeleteCategory();
                break;
            case 5:
                formDetailCategory();
                break;
            case 6:
                System.out.println("Enter the \u001B[3m\u001B[32mback\u001B[0m to return Menu: ");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    new ProfileView();
                }
                break;
        }
    }

    CategoryController categoryController = new CategoryController();
    List<Category> categoryList = categoryController.getListCategory();

    public void showFormCategoryList() {
        System.out.println(String.format("%24s", "TABLE CATEGORY"));
        System.out.println("\u001B[38;5;208m│–––––––––┬––––––––––––––––––––––––│\u001B[0m");
        System.out.println("\u001B[38;5;208m│\u001B[0m   ID    \u001B[38;5;208m│\u001B[0m          NAME          \u001B[38;5;208m│\u001B[0m");
        System.out.println("\u001B[38;5;208m│–––––––––│––––––––––––––––––––––––│\u001B[0m");
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println(String.format("\u001B[38;5;208m│\u001B[0m%-9d\u001B[38;5;208m│\u001B[0m%-24s\u001B[38;5;208m│\u001B[0m", categoryList.get(i).getId(), categoryList.get(i).getName()));
        }
        System.out.println("\u001B[38;5;208m│–––––––––┴––––––––––––––––––––––––│\u001B[0m");
        System.out.println("Enter the \u001B[3m\u001B[32mback\u001B[0m to return Menu: ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new CategoryView();
        }
    }

    public void formCreateCategory() {
        while (true) {
            int id = 0;
            if (categoryList.size() == 0) {
                id = 1;
            } else {
                id = categoryList.get(categoryList.size() - 1).getId() + 1;
            }
            String name;
            while (true) {
                System.out.println("Enter the name: ");
                name = Config.scanner().nextLine();
                if (!Validate.checkName(name)) {
                    System.out.println("\u001B[38;2;255;51;51mName is not valid\u001B[0m");
                } else {
                    System.out.println("\u001B[3m\u001B[32mName is valid\u001B[0m");
                    break;
                }
            }
            Category category = new Category(id, name);
            categoryController.createCategoryToDB(category);
            new Config<Category>().writeToFile(Config.PATH_CATEGORY, categoryList);
            System.out.println("\u001B[3m\u001B[32mCreate Success!\u001B[0m");
            System.out.println("Enter the any key to continue or Enter \u001B[3m\u001B[32mback\u001B[0m to return Menu: ");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new CategoryView();
                break;
            }
        }
    }

    public void formUpdateCategory() {
        while (true) {
            System.out.println("Enter the id to update: ");
            int id = Config.scanner().nextInt();
            if (categoryController.detailCategory(id) == null) {
                System.out.println("\u001B[38;2;255;51;51mId not found! Please try again!\u001B[0m");
            } else {
                System.out.println("Enter the name: ");
                String name = Config.scanner().nextLine();
                Category category = new Category(id, name);
                categoryController.updateCategory(category);
                new Config<Category>().writeToFile(Config.PATH_CATEGORY, categoryList);
                System.out.println("\u001B[3m\u001B[32mUpdate success!\u001B[0m");
                System.out.println("Enter the any key to continue or Enter \u001B[3m\u001B[32mback\u001B[0m to return Menu: ");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    new CategoryView();
                    break;
                }
            }
        }
    }

    public void formDeleteCategory() {
        while (true) {
            System.out.println("Enter the id to delete");
            int id = Config.scanner().nextInt();
            if (categoryController.detailCategory(id) == null) {
                System.err.println("\u001B[38;2;255;51;51mId not found! Please try again!\u001B[0m");
            } else {
                categoryController.deleteCategory(id);
                System.out.println("\u001B[3m\u001B[32mDelete success!!\u001B[0m");
                System.out.println("Enter the any key to continue or Enter \u001B[3m\u001B[32mback\u001B[0m to return Menu: ");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    new CategoryView();
                    break;
                }
                break;
            }
        }
    }

    public void formDetailCategory() {
        while (true) {
            System.out.println("Enter the id");
            int id = Config.scanner().nextInt();
            if (categoryController.detailCategory(id) == null) {
                System.err.println("\u001B[38;2;255;51;51mId not found! Please try again!\u001B[0m");
            } else {
                System.out.println("\u001B[38;5;208m⌈–––––––––|––––––––––––––––––––––––⌉\u001B[0m");
                System.out.println("\u001B[38;5;208m|\u001B[0m   ID    \u001B[38;5;208m|\u001B[0m          NAME          \u001B[38;5;208m|\u001B[0m");
                System.out.println("\u001B[38;5;208m|–––––––––|––––––––––––––––––––––––|\u001B[0m");
                for (int i = 0; i < categoryList.size(); i++) {
                    if (categoryList.get(i).getId() == id) {
                        System.out.println(String.format("\u001B[38;5;208m|\u001B[0m%-9d\u001B[38;5;208m|\u001B[0m%-24s\u001B[38;5;208m|\u001B[0m", categoryList.get(i).getId(), categoryList.get(i).getName()));
                    }
                }
                System.out.println("\u001B[38;5;208m⌊–––––––––|––––––––––––––––––––––––⌋\u001B[0m");
                System.out.println("Enter the \u001B[3m\u001B[32mback\u001B[0m to return Menu: ");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    new CategoryView();
                }
            }
        }
    }

}
