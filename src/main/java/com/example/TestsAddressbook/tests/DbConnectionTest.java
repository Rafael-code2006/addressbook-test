package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import com.example.TestsAddressbook.model.MySet;
import org.testng.annotations.Test;

import java.sql.*;

public class DbConnectionTest {
    private static final String path = "jdbc:mysql://localhost:3306/addressbook?user=root&password=";

    @Test
    public void testDbConnection() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(path);
        Statement st = conn.createStatement();
        ResultSet resultSet = st.executeQuery("select * from group_list");
        MySet<GroupData> groups = new MySet<>();

        while(resultSet.next()){
            groups.add(  new GroupData()
                    .withId(resultSet.getInt("group_id"))
                    .withName(resultSet.getString("group_name"))
                    .withHeader(resultSet.getString("group_header"))
                    .withFooter(resultSet.getString("group_footer")));
        }

        resultSet.close();
        st.close();
        conn.close();
        for(GroupData x : groups){
            System.out.println(x);
        }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
