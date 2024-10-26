package articlesystem;


import articlesystem.model.*;
import articlesystem.model.enums.ArticleStatus;
import articlesystem.service.*;
import articlesystem.service.impl.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class Main {

    private static final AuthorService authorService = new AuthorServiceImpl();
    private static final ArticleService articleService = new ArticleServiceImpl();
    private static final ModeratorService moderatorService = new ModeratorServiceImpl();
    private static final CategoryService categoryService = new CategoryServiceImpl();
    private static final TagService tagService = new TagServiceImpl();


    public static void main(String[] args) {
        // create default data
        setupSampleData();
        startMenu();
    }


    public static void setupSampleData() {
        Category cat1 = new Category(1, "Technology", "Articles about technology.");
        Category cat2 = new Category(2, "Science", "Articles about science.");
        Category cat3 = new Category(3, "Art", "Articles about art.");
        categoryService.addCategory(cat1);
        categoryService.addCategory(cat2);
        categoryService.addCategory(cat3);


        Moderator moderator = new Moderator(1, "admin", "admin123");
        moderatorService.addModerator(moderator);

        tagService.addTag(new Tag(1, "Tech"));
        tagService.addTag(new Tag(2, "Science"));

        List<Article> articles = new ArrayList<>();
        Article article1 = new Article(11,"Art", "Art is Art", "Art is paint", cat3);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minusHours(24);
        Date date = Date.from(yesterday.atZone(ZoneId.systemDefault()).toInstant());// تبدیل LocalDateTime به Date
        article1.setCreateDate(date);
        article1.setLastUpdateDate(date);
        article1.setPublishDate(date);
        article1.setStatus(ArticleStatus.APPROVED);
        article1.setPublished(true);
        articles.add(article1);

        Article article2 = new Article(22,"Math", "Math is Math", "MAth is number", cat2);
        LocalDateTime oneWeekAgo = now.minusWeeks(1);
        Date dateOneWeekAgo = Date.from(oneWeekAgo.atZone(ZoneId.systemDefault()).toInstant());// تبدیل LocalDateTime به Date
        article2.setCreateDate(dateOneWeekAgo);
        article2.setLastUpdateDate(dateOneWeekAgo);
        article2.setPublishDate(dateOneWeekAgo);
        article2.setStatus(ArticleStatus.APPROVED);
        article2.setPublished(true);
        articles.add(article2);

        Article article3 = new Article(33,"Java", "Java is Java", "Java is a kind of programming language", cat1);
        LocalDateTime oneMonthAgo = now.minusMonths(1);
        Date dateOneMonthAgo = Date.from(oneMonthAgo.atZone(ZoneId.systemDefault()).toInstant());// تبدیل LocalDateTime به Date
        article3.setCreateDate(dateOneMonthAgo);
        article3.setLastUpdateDate(dateOneMonthAgo);
        article3.setPublishDate(dateOneMonthAgo);
        article3.setStatus(ArticleStatus.APPROVED);
        article3.setPublished(true);
        articles.add(article3);

        Article article4 = new Article(44,"C++", "C++ is C++", "C++ is a kind of programming language", cat1);
        LocalDateTime sixMonthAgo = now.minusMonths(6);
        Date dateSixMonthAgo = Date.from(sixMonthAgo.atZone(ZoneId.systemDefault()).toInstant());// تبدیل LocalDateTime به Date
        article4.setCreateDate(dateSixMonthAgo);
        article4.setLastUpdateDate(dateSixMonthAgo);
        article4.setPublishDate(dateSixMonthAgo);
        article4.setStatus(ArticleStatus.APPROVED);
        article4.setPublished(true);
        articles.add(article4);

        Article article5 = new Article(55,"PHP", "PHP is PHP", "PHP is a kind of programming language", cat1);
        LocalDateTime oneYearAgo = now.minusYears(1);
        Date dateOneYearAgo = Date.from(oneYearAgo.atZone(ZoneId.systemDefault()).toInstant());// تبدیل LocalDateTime به Date
        article5.setCreateDate(dateOneYearAgo);
        article5.setLastUpdateDate(dateOneYearAgo);
        article5.setPublishDate(dateOneYearAgo);
        article5.setStatus(ArticleStatus.APPROVED);
        article5.setPublished(true);
        articles.add(article5);
        articleService.addArticles(articles);
    }

    private static void startMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Article System. \nAre you an (1) Author or (2) Moderator?");
        int choice = Integer.parseInt(scanner.next());

        if (choice == 1) {
            authorMenu(scanner);
        } else if (choice == 2) {
            moderatorMenu(scanner);
        } else {
            System.out.println("Invalid choice");
        }
    }

    public static void authorMenu(Scanner scanner) {
        System.out.println("\nAuthor Menu:");
        System.out.println("(1) Register");
        System.out.println("(2) Login");
        System.out.println("(3) View All Published Articles");
        System.out.println("(4) Exit");

        int choice = Integer.parseInt(scanner.next());
        switch (choice) {
            case 1:
                registerAuthor(scanner);
                break;
            case 2:
                loginAuthor(scanner);
                break;
            case 3:
                viewPublishedArticles(scanner);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public static void registerAuthor(Scanner scanner) {
        System.out.println("Register as an Author:");
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter national code: ");
        String nationalCode = scanner.next();
        System.out.print("Enter birthday (YYYY-MM-DD): ");
        String birthday = scanner.next();

        List<Author> allAuthors = authorService.findAllAuthors();

        // ثبت کد ملی به عنوان رمز عبور
        Author newAuthor = new Author(allAuthors.size() + 1, username, nationalCode, birthday, nationalCode);
        authorService.addAuthor(newAuthor);

        System.out.println("Registration successful! \nYour password is your national code.");
        authorMenu(scanner);
    }

    public static void loginAuthor(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        List<Author> allAuthors = authorService.findAllAuthors();

        Author loggedInAuthor = null;
        for (Author author : allAuthors) {
            if (author.getUsername().equals(username) && author.getPassword().equals(password)) {
                loggedInAuthor = author;
                break;
            }
        }

        if (loggedInAuthor != null) {
            System.out.println("Login successful!");
            authorDashboard(loggedInAuthor, scanner);
        } else {
            System.out.println("Invalid credentials");
            authorMenu(scanner);
        }
    }

    public static void authorDashboard(Author author, Scanner scanner) {
        System.out.println("\nAuthor Dashboard:");
        System.out.println("(1) View My Articles");
        System.out.println("(2) Edit Article");
        System.out.println("(3) Add New Article");
        System.out.println("(4) Change Password");
        System.out.println("(5) Logout");

        List<Article> allArticles = articleService.findAllArticles();
        int choice = Integer.parseInt(scanner.next());
        switch (choice) {
            case 1:
                author.viewArticles(allArticles);
                break;
            case 2:
                editArticle(author, scanner);
                break;
            case 3:
                addNewArticle(author, scanner);
                break;
            case 4:
                System.out.print("Enter new password: ");
                String newPassword = scanner.next();
                author.changePassword(newPassword);
                break;
            case 5:
                System.out.println("Logged out!");
                startMenu();
                break;
            default:
                System.out.println("Invalid choice");
        }

        authorDashboard(author, scanner);
    }

    public static void editArticle(Author author, Scanner scanner) {
        System.out.println("Enter Article ID to Edit:");
        int articleId = Integer.parseInt(scanner.next());
        Article article = articleService.findArticleById(articleId);

        if (article != null) {
            System.out.println("Editing Article: " + article.getTitle());
            System.out.print("Enter new title: ");
            String newTitle = scanner.next();
            System.out.print("Enter new brief: ");
            String newBrief = scanner.next();
            System.out.print("Enter new content: ");
            String newContent = scanner.next();

            author.editArticle(article, newTitle, newBrief, newContent);
        } else {
            System.out.println("Article not found.");
        }
    }

    public static void addNewArticle(Author author, Scanner scanner) {
        System.out.println("Adding a New Article:");
        System.out.print("Enter title: ");
        String title = scanner.next();
        System.out.print("Enter brief: ");
        String brief = scanner.next();
        System.out.print("Enter content: ");
        String content = scanner.next();

        List<Category> allCategory = categoryService.findAllCategory();
        // نمایش دسته‌بندی‌ها
        System.out.println("Select a category:");
        for (Category category : allCategory) {
            System.out.println(category.getId() + ": " + category.getTitle());
        }
        System.out.println("(0) Add new category");

        int categoryId = Integer.parseInt(scanner.next());
        Category selectedCategory;

        if (categoryId == 0) {
            selectedCategory = addNewCategory(scanner);
        } else {
            selectedCategory = allCategory.get(categoryId - 1);
        }

        List<Article> allArticles = articleService.findAllArticles();
        Article newArticle = new Article(allArticles.size() + 1, title, brief, content, selectedCategory);

        // نمایش لیست تگ‌ها و افزودن تگ جدید
        System.out.println("Enter tags (comma separated). If the tag doesn't exist, it will be added:");
        scanner.nextLine(); // مصرف خط جدید
        String tagInput = scanner.nextLine();
        String[] tagTitles = tagInput.split(",");
        for (String tagTitle : tagTitles) {
            Tag tag = tagService.findOrCreateTag(tagTitle.trim());
            newArticle.addTag(tag);
        }

        author.submitArticle(newArticle);
        articleService.addArticle(newArticle);

        System.out.println("Article added successfully!");
    }

    public static Category addNewCategory(Scanner scanner) {
        List<Category> allCategory = categoryService.findAllCategory();
        System.out.print("Enter new category title: ");
        String newCategoryTitle = scanner.next();
        System.out.print("Enter new category description: ");
        String newCategoryDescription = scanner.next();

        Category newCategory = new Category(allCategory.size() + 1, newCategoryTitle, newCategoryDescription);
        categoryService.addCategory(newCategory);
        return newCategory;
    }

    public static void viewPublishedArticles(Scanner scanner) {
        System.out.println("Published Articles:");
        List<Article> publishedArticles = articleService.getPublishedArticles();
        for (int i = 0; i < publishedArticles.size(); i++) {
            Article article = publishedArticles.get(i);
            System.out.println((i + 1) + ". Title: " + article.getTitle() + ", Brief: " + article.getBrief());
        }

        System.out.println("Enter the number of the article you want to view in detail, or 0 to go back:");
        int choice = Integer.parseInt(scanner.next());
        if (choice > 0 && choice <= publishedArticles.size()) {
            Article selectedArticle = publishedArticles.get(choice - 1);
            System.out.println("Title: " + selectedArticle.getTitle());
            System.out.println("Brief: " + selectedArticle.getBrief());
            System.out.println("Content: " + selectedArticle.getContent());
        }
    }

    public static void moderatorMenu(Scanner scanner) {
        System.out.println("\nModerator Menu:");
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        List<Moderator> allModerators = moderatorService.findAllModerators();
        Moderator loggedInModerator = null;
        for (Moderator mod : allModerators) {
            if (mod.getUsername().equals(username) && mod.getPassword().equals(password)) {
                loggedInModerator = mod;
                break;
            }
        }

        if (loggedInModerator != null) {
            System.out.println("Login successful!");
            moderatorDashboard(loggedInModerator, scanner);
        } else {
            System.out.println("Invalid credentials");
            moderatorMenu(scanner);
        }
    }

    public static void moderatorDashboard(Moderator moderator, Scanner scanner) {
        System.out.println("\nModerator Dashboard:");
        System.out.println("(1) Approve/Reject Articles");
        System.out.println("(2) Logout");

        int choice = Integer.parseInt(scanner.next());
        switch (choice) {
            case 1:
                List<Article> allArticles = articleService.findAllArticles();
                for (Article article : allArticles) {
                    if (article.getStatus().equals(ArticleStatus.PENDING)) {
                        System.out.println("Article ID: " + article.getId() + ", Title: " + article.getTitle());
                        System.out.println("(1) Approve  (2) Reject");
                        int decision = Integer.parseInt(scanner.next());
                        if (decision == 1) {
                            moderator.approveArticle(article);
                        } else if (decision == 2) {
                            moderator.rejectArticle(article);
                        }
                    }
                }
                break;
            case 2:
                System.out.println("Logged out!");
                startMenu();
                break;
            default:
                System.out.println("Invalid choice");
        }

        moderatorDashboard(moderator, scanner);
    }

}
