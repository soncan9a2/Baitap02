package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import utils.DBConnection;

public class CategoryDaoImpl extends DBConnection implements CategoryDao {

    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public void insert(Category category) {
        String sql = "INSERT INTO Category(cate_name, icons, user_id) VALUES (?,?,?)";
        try {
            conn = super.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, category.getCatename());
            ps.setString(2, category.getIcon());
            ps.setInt(3, category.getUserId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Category category) {
        String sql = "UPDATE Category SET cate_name = ?, icons=?, user_id=? WHERE cate_id = ?";
        try {
            conn = super.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, category.getCatename());
            ps.setString(2, category.getIcon());
            ps.setInt(3, category.getUserId());
            ps.setInt(4, category.getCateid());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Category WHERE cate_id = ?";
        try {
            conn = super.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category get(int id) {
        String sql = "SELECT * FROM Category WHERE cate_id = ?";
        try {
            conn = super.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCateid(rs.getInt("cate_id"));
                category.setCatename(rs.getString("cate_name"));
                category.setIcon(rs.getString("icons"));
                category.setUserId(rs.getInt("user_id"));
                category.setCreatedDate(rs.getDate("created_date"));
                return category;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category get(String name) {
        String sql = "SELECT * FROM Category WHERE cate_name = ?";
        try {
            conn = super.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCateid(rs.getInt("cate_id"));
                category.setCatename(rs.getString("cate_name"));
                category.setIcon(rs.getString("icons"));
                category.setUserId(rs.getInt("user_id"));
                category.setCreatedDate(rs.getDate("created_date"));
                return category;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<Category>();
        String sql = "SELECT * FROM Category";
        try {
            conn = super.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCateid(rs.getInt("cate_id"));
                category.setCatename(rs.getString("cate_name"));
                category.setIcon(rs.getString("icons"));
                category.setUserId(rs.getInt("user_id"));
                category.setCreatedDate(rs.getDate("created_date"));
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<Category> search(String keyword) {
        List<Category> categories = new ArrayList<Category>();
        String sql = "SELECT * FROM Category WHERE cate_name LIKE ?";
        try {
            conn = super.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCateid(rs.getInt("cate_id"));
                category.setCatename(rs.getString("cate_name"));
                category.setIcon(rs.getString("icons"));
                category.setUserId(rs.getInt("user_id"));
                category.setCreatedDate(rs.getDate("created_date"));
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<Category> getByUserId(int userId) {
        List<Category> categories = new ArrayList<Category>();
        String sql = "SELECT * FROM Category WHERE user_id = ?";
        try {
            conn = super.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCateid(rs.getInt("cate_id"));
                category.setCatename(rs.getString("cate_name"));
                category.setIcon(rs.getString("icons"));
                category.setUserId(rs.getInt("user_id"));
                category.setCreatedDate(rs.getDate("created_date"));
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
}
