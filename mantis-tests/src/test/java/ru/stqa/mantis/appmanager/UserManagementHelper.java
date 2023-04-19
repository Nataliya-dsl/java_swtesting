package ru.stqa.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UserManagementHelper extends  HelperBase {
    public UserManagementHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public void goToUserManagementPage() {
        wd.get(app.getProperty("web.baseUrl") + "manage_user_page.php");
    }

    public void chooseUser(String userLogin) {
        type(By.id("search"), userLogin);
        click(By.cssSelector("input[type='submit']"));
        WebElement userLoginLink = wd.findElement(By.partialLinkText(userLogin));
        userLoginLink.click();
    }

    public void resetPassword() {
        WebElement form = wd.findElement(By.id("manage-user-reset-form"));
        form.findElement(By.cssSelector("input[type='submit']")).click();
    }
    public void goToResetconfirmationLink(String link) {
        wd.get(link);
    }

    public void createNewPassword(String userLogin, String newPassword) {
        //type(By.id("realname"), userLogin);
        type(By.id("password"), newPassword);
        type(By.id("password-confirm"), newPassword);
        wd.findElement(By.cssSelector("button[type='submit']")).click();
    }
}
