package articlesystem;

import articlesystem.model.*;
import articlesystem.service.*;
import articlesystem.service.impl.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final AuthorService authorService = new AuthorServiceImpl();
    private static final ArticleService articleService = new ArticleServiceImpl();
    private static final ModeratorService moderatorService = new ModeratorServiceImpl();
    private static final CategoryService categoryService = new CategoryServiceImpl();
    private static final TagService tagService = new TagServiceImpl();
    private static final UserService userService = new UserServiceImpl();


    public static void main(String[] args) {
        startMenu();
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
        System.out.println("Author Menu:");
        System.out.println("(1) Register");
        System.out.println("(2) Login");
        System.out.println("(3) Exit");

        int choice = Integer.parseInt(scanner.next());
        switch (choice) {
            case 1:
                registerAuthor(scanner);
                break;
            case 2:
                loginAuthor(scanner);
                break;
            case 3:
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
        LocalDate birthDate = null;
        do {
            System.out.print("Enter birthday (YYYY-MM-DD): ");
            String birthday = scanner.next();
            try {
                birthDate = LocalDate.parse(birthday); // convert String to LocalDate
            } catch (Exception e) {
                System.out.println("Invalid birthday");
            }
        } while (birthDate == null);

        List<Author> allAuthors = authorService.findAllAuthors();

        // ثبت کد ملی به عنوان رمز عبور
        Author newAuthor = new Author(allAuthors.size() + 1, username, nationalCode, birthDate, nationalCode);
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

    public static void filterMeno(Author author, Scanner scanner) {
        System.out.println("Would you like to filter by:");
        System.out.println("(1) View All Published/Rejected/Pending Articles");
        System.out.println("(2) Publication Date");
        System.out.println("(3) Category");
        System.out.print("Your choice: ");

        int filterChoice = Integer.parseInt(scanner.next());
        if (filterChoice == 1) {
            viewPublishedArticles(author, scanner);
        } else if (filterChoice == 2) {
            filterArticleDashboard(author, scanner);
        } else if (filterChoice == 3) {
            filterCategoryDashboard(scanner);
        } else {
            System.out.println("Invalid choice");
        }
    }

    public static void filterCategoryDashboard(Scanner scanner) {
        List<Category> allCategories = categoryService.findAllCategory();
        System.out.println("Choose a category Id to filter by:");

        for (Category category : allCategories) {
            System.out.println("Id: " + category.getId() + ", Title: " + category.getTitle());
        }
        int categoryId = Integer.parseInt(scanner.next());

        for (Category category : allCategories) {
            if (categoryId == category.getId()) {
                authorService.viewArticlesPublishedByCategory(category);
            }
        }
    }

    public static void filterArticleDashboard(Author author, Scanner scanner) {
        System.out.println("Filter Article:");
        System.out.println("(1) View All Published Articles");
        System.out.println("(2) View Published Articles after 24 hours ago.");
        System.out.println("(3) View Published Articles after last week.");
        System.out.println("(4) View Published Articles after last month.");
        System.out.println("(5) View Published Articles after six months.");
        System.out.println("(6) View Published Articles after last year.");
        System.out.println("(7) Back to authorDashboard.");

        List<Article> allArticles = articleService.findAllArticlesByAuthorId(author.getId());
        int choice = Integer.parseInt(scanner.next());
        switch (choice) {
            case 1:
                authorService.viewArticles(allArticles);
                break;
            case 2:
                authorService.viewArticlesPublishedAfter24HoursAgo(allArticles);
                break;
            case 3:
                authorService.viewArticlesPublishedAfterOneWeekAgo(allArticles);
                break;
            case 4:
                authorService.viewArticlesPublishedAfterOneMonthAgo(allArticles);
                break;
            case 5:
                authorService.viewArticlesPublishedAfterSixMonthsAgo(allArticles);
                break;
            case 6:
                authorService.viewArticlesPublishedAfterLastOneYearAgo(allArticles);
                break;
            case 7:
                authorDashboard(author, scanner);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public static void authorDashboard(Author author, Scanner scanner) {
        System.out.println("\nAuthor Dashboard:");
        System.out.println("(1) View My Articles");
        System.out.println("(2) Edit Article");
        System.out.println("(3) Add New Article");
        System.out.println("(4) Change Password");
        System.out.println("(5) Logout");

        int choice = Integer.parseInt(scanner.next());
        switch (choice) {
            case 1:
                filterMeno(author, scanner);
                break;
            case 2:
                editArticle(scanner, author);
                break;
            case 3:
                addNewArticle(scanner, author.getId());
                break;
            case 4:
                System.out.print("Enter new password: ");
                String newPassword = scanner.next();
                userService.updatePassword(author.getId(), newPassword);
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

    public static void editArticle(Scanner scanner, Author author) {
        System.out.println("Enter Article ID to Edit:");

        List<Article> allArticles = articleService.findAllArticlesByAuthorId(author.getId());
        for (Article article : allArticles) {
            if (!article.isPublished()) {
                System.out.println("Id: " + article.getId() + ", Title: " + article.getTitle());
            }
        }
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

            articleService.updateArticle(article.getId(), newTitle, newBrief, newContent);
        } else {
            System.out.println("Article not found.");
        }
    }

    public static void addNewArticle(Scanner scanner, int authorId) {
        System.out.println("Adding a New Article:");
        System.out.print("Enter title: ");
        String title = scanner.next();
        System.out.print("Enter brief: ");
        String brief = scanner.next();
        System.out.print("Enter content: ");
        String content = scanner.next();

        List<Category> allCategory = categoryService.findAllCategory();

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

        List<Article> allArticles = articleService.findAllArticlesByAuthorId(authorId);
        Article newArticle = new Article(allArticles.size() + 1, title, brief, content, selectedCategory);

        System.out.println("Enter tags (comma separated). If the tag doesn't exist, it will be added:");
        scanner.nextLine();
        String tagInput = scanner.nextLine();
        String[] tagTitles = tagInput.split(",");
        for (String tagTitle : tagTitles) {
            Tag tag = tagService.findOrCreateTag(tagTitle.trim());
            newArticle.addTag(tag);
        }

        authorService.submitArticle(newArticle);
        articleService.addArticle(newArticle, authorId, selectedCategory.getId());

        System.out.println("Article added successfully!");
    }

    public static Category addNewCategory(Scanner scanner) {
        System.out.print("Enter new category title: ");
        String newCategoryTitle = scanner.next();
        System.out.print("Enter new category description: ");
        String newCategoryDescription = scanner.next();

        Category newCategory = new Category();
        newCategory.setTitle(newCategoryTitle);
        newCategory.setDescription(newCategoryDescription);
        Category category = categoryService.addCategory(newCategory);
        return category;
    }

    public static void viewPublishedArticles(Author author, Scanner scanner) {
        List<Article> publishedArticles = articleService.getAllArticlesByUserId(author.getId());
        if (publishedArticles.isEmpty()) {
            System.out.println("No articles found.");
            authorDashboard(author, scanner);
        } else {
            System.out.println("All Published/Rejected/Pending Articles: ");
            for (int i = 0; i < publishedArticles.size(); i++) {
                Article article = publishedArticles.get(i);
                System.out.println((i + 1) + ". Title: " + article.getTitle() + ", Brief: " + article.getBrief() + ", Status: " + article.getStatus());
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
            moderatorDashboard(scanner);
        } else {
            System.out.println("Invalid credentials");
            moderatorMenu(scanner);
        }
    }

    public static void moderatorDashboard(Scanner scanner) {
        System.out.println("\nModerator Dashboard:");
        System.out.println("(1) Approve/Reject Articles");
        System.out.println("(2) Logout");

        int choice = Integer.parseInt(scanner.next());
        switch (choice) {
            case 1:
                List<Article> allArticles = articleService.findAllPendingArticles();
                for (Article article : allArticles) {
                        System.out.println("Article ID: " + article.getId() + ", Title: " + article.getTitle());
                        System.out.println("(1) Approve  (2) Reject");
                        int decision = Integer.parseInt(scanner.next());
                        if (decision == 1) {
                            moderatorService.approveArticle(article);
                        } else if (decision == 2) {
                            moderatorService.rejectArticle(article);
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
        moderatorDashboard(scanner);
    }
}
