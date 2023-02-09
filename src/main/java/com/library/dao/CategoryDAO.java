package com.library.dao;

import com.library.domain.Book;
import com.library.domain.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CategoryDAO extends DAO{
    private static final ThreadLocal<CategoryDAO> categoryDaoThreadLocal =
            ThreadLocal.withInitial(CategoryDAO::new);
    private static final String FIND_ALL_QUERY = """
select name from category;
""";

    public static CategoryDAO getInstance() {
        return categoryDaoThreadLocal.get();
    }

    private static final  String GET_CATEGORY_ID_QUERY = """
    select id from category where name = ?
""";

    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        Connection connection = getConnection();
        return null;
    }

    public int getCategoryIdFromName(String categoryName) {
        try {
            PreparedStatement preparedStatement =
                    getConnection().prepareStatement(GET_CATEGORY_ID_QUERY);
            preparedStatement.setString(1,categoryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }
}
