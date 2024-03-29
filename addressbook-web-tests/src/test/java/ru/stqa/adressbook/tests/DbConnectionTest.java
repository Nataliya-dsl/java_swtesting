package ru.stqa.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.adressbook.model.GroupData;
import ru.stqa.adressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

    @Test
    public void testDbconnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=toot&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(" select group_id,group_name,group_header,group_footer from group_list");
            Groups groups = new Groups();
            while (rs.next()) {
                groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                    .withHeader("group_header").withFooter("group_footer"));
            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(groups);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
