package com.library.dao;

import com.library.domain.Book;
import com.library.dto.BookCreateDTO;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookDAO extends DAO{
    private static final ThreadLocal<BookDAO> bookDaoThreadLocal =
            ThreadLocal.withInitial(BookDAO::new);
    private static final String INSERT_BOOK_QUERY = """
            insert into book (author,description,  title, cost, categoryId, publishedYear) values
            (?, ?, ?, ?, ?, ?) returning id, createdat;
            """;

    public static final String FIND_ALL_QUERY =
            "select * from book order by createdAt desc offset ? limit ?";

    public static final String TOTAL_COUNT = "select count(0) from book";

    public static BookDAO getInstance() {
        return bookDaoThreadLocal.get();
    }

    public Book save(@NonNull BookCreateDTO dto) {
        Book book = Book.builder()
                .author(dto.author())
                .description(dto.description())
                .title(dto.title())
                .cost(dto.cost())
                .publishedYear(dto.publishedYear())
                .categoryId(dto.categoryId())
                .build();
        Connection connection = getConnection();

        try (PreparedStatement pr = connection.prepareStatement(INSERT_BOOK_QUERY)) {
            pr.setString(1, book.getAuthor());
            pr.setString(2, book.getDescription());
            pr.setString(3, book.getTitle());
            pr.setDouble(4, book.getCost());
            pr.setInt(5, book.getCategoryId());
            pr.setInt(6, book.getPublishedYear());
            ResultSet resultSet = pr.executeQuery();
            if (resultSet.next()) {
                book.setId(resultSet.getLong("id"));
                book.setCreatedAt(resultSet.getTimestamp("createdAt"));
            }
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> findAll(short page, short size) {
        List<Book> books = new ArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement pr = connection.prepareStatement(FIND_ALL_QUERY)) {
            pr.setShort(1, (short) (page * size));
            pr.setShort(2, size);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                books.add(Book.builder()
                        .id(rs.getLong("id"))
                        .author(rs.getString("author"))
                        .description(rs.getString("description"))
                        .title(rs.getString("title"))
                        .cost(rs.getDouble("cost"))
                        .categoryId(rs.getInt("categoryId"))
                        .publishedYear(rs.getInt("publishedYear"))
                        .createdAt(rs.getTimestamp("createdAt"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
    public long totalCount() {
        Connection connection = getConnection();
        try (PreparedStatement pr = connection.prepareStatement(TOTAL_COUNT)) {
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
