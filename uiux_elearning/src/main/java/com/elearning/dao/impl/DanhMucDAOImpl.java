package com.elearning.dao.impl;
import com.elearning.dao.DanhMucDAO;
import com.elearning.model.DanhMuc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DanhMucDAOImpl implements DanhMucDAO {
    @Autowired
    private DataSource dataSource;

    @Override
    public List<DanhMuc> findAll() {
        List<DanhMuc> list = new ArrayList<>();
        String sql = "SELECT * FROM DANHMUC";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new DanhMuc(rs.getInt("MaDM"), rs.getString("TenDanhMuc"), rs.getString("IconURL")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public DanhMuc findById(int id) {
        String sql = "SELECT * FROM DANHMUC WHERE MaDM = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return new DanhMuc(rs.getInt("MaDM"), rs.getString("TenDanhMuc"), rs.getString("IconURL"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public boolean insert(DanhMuc dm) {
        String sql = "INSERT INTO DANHMUC (TenDanhMuc, IconURL) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dm.getTenDanhMuc());
            ps.setString(2, dm.getIconURL());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public boolean update(DanhMuc dm) {
        String sql = "UPDATE DANHMUC SET TenDanhMuc=?, IconURL=? WHERE MaDM=?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dm.getTenDanhMuc());
            ps.setString(2, dm.getIconURL());
            ps.setInt(3, dm.getMaDM());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM DANHMUC WHERE MaDM = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}
