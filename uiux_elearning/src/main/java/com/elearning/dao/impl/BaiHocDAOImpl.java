package com.elearning.dao.impl;
import com.elearning.dao.BaiHocDAO;
import com.elearning.model.BaiHoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BaiHocDAOImpl implements BaiHocDAO {
    @Autowired
    private DataSource dataSource;

    @Override
    public List<BaiHoc> findAll() {
        List<BaiHoc> list = new ArrayList<>();
        String sql = "SELECT * FROM BAIHOC";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                BaiHoc bh = new BaiHoc();
                bh.setMaBH(rs.getInt("MaBH"));
                bh.setTenBH(rs.getString("TenBH"));
                bh.setNoiDungBH(rs.getString("NoiDungBH"));
                bh.setLinkURL(rs.getString("LinkURL"));
                bh.setHinhAnh(rs.getString("HinhAnh"));
                bh.setNgayDang(rs.getTimestamp("NgayDang"));
                bh.setMaDM(rs.getInt("MaDM"));
                list.add(bh);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public BaiHoc findById(int id) {
        String sql = "SELECT * FROM BAIHOC WHERE MaBH = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    BaiHoc bh = new BaiHoc();
                    bh.setMaBH(rs.getInt("MaBH"));
                    bh.setTenBH(rs.getString("TenBH"));
                    bh.setNoiDungBH(rs.getString("NoiDungBH"));
                    bh.setLinkURL(rs.getString("LinkURL"));
                    bh.setHinhAnh(rs.getString("HinhAnh"));
                    bh.setNgayDang(rs.getTimestamp("NgayDang"));
                    bh.setMaDM(rs.getInt("MaDM"));
                    return bh;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public boolean insert(BaiHoc bh) {
        String sql = "INSERT INTO BAIHOC (TenBH, NoiDungBH, LinkURL, HinhAnh, MaDM) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bh.getTenBH());
            ps.setString(2, bh.getNoiDungBH());
            ps.setString(3, bh.getLinkURL());
            ps.setString(4, bh.getHinhAnh());
            ps.setInt(5, bh.getMaDM());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public boolean update(BaiHoc bh) {
        String sql = "UPDATE BAIHOC SET TenBH=?, NoiDungBH=?, LinkURL=?, HinhAnh=?, MaDM=? WHERE MaBH=?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bh.getTenBH());
            ps.setString(2, bh.getNoiDungBH());
            ps.setString(3, bh.getLinkURL());
            ps.setString(4, bh.getHinhAnh());
            ps.setInt(5, bh.getMaDM());
            ps.setInt(6, bh.getMaBH());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM BAIHOC WHERE MaBH = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}
