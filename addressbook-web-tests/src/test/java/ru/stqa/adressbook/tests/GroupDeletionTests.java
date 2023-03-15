package ru.stqa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.adressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {


    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before - 1);
    }

}
