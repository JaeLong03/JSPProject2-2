package org.example.project22.dao;

import org.example.project22.bean.BookVO;
import org.example.project22.common.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    private final String BOOK_SELECTALL = "select * from BOOK";
    private final String BOOK_SELECTID = "select * from BOOK where id = ?";
    private final String BOOK_SELECTTITLE = "select * from BOOK where title like ?";
    private final String BOOK_INSERT = "insert into BOOK (title, author, publisher, published_date, genre) values (?,?,?,?,?)";
    private final String BOOK_UPDATE = "update BOOK set title = ?, author = ?, publisher = ?, published_date = ? where id = ?";
    private final String BOOK_DELETE = "delete from BOOK where id = ?";

    public List<BookVO> loadBook(String s){
        List<BookVO> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOOK_SELECTALL);
            rs =  stmt.executeQuery();
            if(s.equals("") || s == null){
                stmt = conn.prepareStatement(BOOK_SELECTALL);
                rs = stmt.executeQuery();
            }
            else { //아니면 키워드로 like로 가져오기
                stmt = conn.prepareStatement(BOOK_SELECTTITLE);
                stmt.setString(1, "%" + s + "%");
                rs = stmt.executeQuery();
            }
            while(rs.next()){
                int id = rs.getInt("id");
                String title =  rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                String published_date = rs.getString("published_date");
                String genre = rs.getString("genre");
                String regdate = rs.getString("regDate");
                int cnt = rs.getInt("cnt");
                list.add(new BookVO(id, title, author, publisher, published_date, genre, regdate, cnt));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public BookVO selectBook(int i){
        BookVO vo = new BookVO();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOOK_SELECTID);
            stmt.setInt(1, i);
            rs = stmt.executeQuery();
            if (rs.next()) {
                vo.setId(rs.getInt("id"));
                vo.setTitle(rs.getString("title"));
                vo.setAuthor(rs.getString("author"));
                vo.setPublisher(rs.getString("publisher"));
                vo.setPublished_date(rs.getString("published_date"));
                vo.setGenre(rs.getString("genre"));
                vo.setRegDate(rs.getString("regDate"));
                vo.setCnt(rs.getInt("cnt"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vo;
    }

    public int insertBook(BookVO vo){
        System.out.println("===> JDBC로 insertBook() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOOK_INSERT);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getAuthor());
            stmt.setString(3, vo.getPublisher());
            stmt.setString(4, vo.getPublished_date());
            stmt.setString(5, vo.getGenre());
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteBook(int i){
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOOK_DELETE);
            stmt.setInt(1, i);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateBook(BookVO vo){return 1;}

    public static void main(String[] args){
        BookVO bookvo = new BookVO("hello", "jaehwan", "hihi", "2025-11-20", "Fantasy");
        BookDAO bookdao = new BookDAO();
        int result = bookdao.insertBook(bookvo);
        if(result == 1)
            System.out.println("Success");
    }
}
