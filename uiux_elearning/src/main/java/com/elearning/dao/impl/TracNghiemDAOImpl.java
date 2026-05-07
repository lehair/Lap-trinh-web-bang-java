package com.elearning.dao.impl;

import com.elearning.dao.TracNghiemDAO;
import com.elearning.model.DapAn;
import com.elearning.model.TracNghiem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TracNghiemDAOImpl implements TracNghiemDAO {
    @Autowired
    private DataSource dataSource;

    private List<DapAn> getAnswersForQuestion(int maTN, Connection conn) throws SQLException {
        List<DapAn> answers = new ArrayList<>();
        String sql = "SELECT * FROM DAPAN WHERE MaTN = ? ORDER BY MaDA ASC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTN);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DapAn da = new DapAn();
                da.setMaDA(rs.getInt("MaDA"));
                da.setNoiDungDapAn(rs.getString("NoiDungDapAn"));
                da.setCorrect(rs.getBoolean("IsCorrect"));
                da.setMaTN(rs.getInt("MaTN"));
                answers.add(da);
            }
        }
        return answers;
    }

    @Override
    public List<TracNghiem> findAll() {
        List<TracNghiem> list = new ArrayList<>();
        String sql = "SELECT * FROM TRACNGHIEM";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TracNghiem tn = new TracNghiem();
                tn.setMaTN(rs.getInt("MaTN"));
                tn.setNoiDungCauHoi(rs.getString("NoiDungCauHoi"));
                tn.setHinhAnh(rs.getString("HinhAnh"));
                tn.setLoiGiaiThich(rs.getString("LoiGiaiThich"));
                tn.setMaBH(rs.getInt("MaBH"));
                tn.setDanhSachDapAn(getAnswersForQuestion(tn.getMaTN(), conn));
                list.add(tn);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<TracNghiem> findByBaiHocId(int maBH) {
        List<TracNghiem> list = new ArrayList<>();
        String sql = "SELECT * FROM TRACNGHIEM WHERE MaBH = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maBH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TracNghiem tn = new TracNghiem();
                tn.setMaTN(rs.getInt("MaTN"));
                tn.setNoiDungCauHoi(rs.getString("NoiDungCauHoi"));
                tn.setHinhAnh(rs.getString("HinhAnh"));
                tn.setLoiGiaiThich(rs.getString("LoiGiaiThich"));
                tn.setMaBH(rs.getInt("MaBH"));
                tn.setDanhSachDapAn(getAnswersForQuestion(tn.getMaTN(), conn));
                list.add(tn);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public TracNghiem findById(int id) {
        String sql = "SELECT * FROM TRACNGHIEM WHERE MaTN = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TracNghiem tn = new TracNghiem();
                tn.setMaTN(rs.getInt("MaTN"));
                tn.setNoiDungCauHoi(rs.getString("NoiDungCauHoi"));
                tn.setHinhAnh(rs.getString("HinhAnh"));
                tn.setLoiGiaiThich(rs.getString("LoiGiaiThich"));
                tn.setMaBH(rs.getInt("MaBH"));
                tn.setDanhSachDapAn(getAnswersForQuestion(tn.getMaTN(), conn));
                return tn;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public boolean insert(TracNghiem tn) {
        String sql = "INSERT INTO TRACNGHIEM (NoiDungCauHoi, HinhAnh, LoiGiaiThich, MaBH) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, tn.getNoiDungCauHoi());
                ps.setString(2, tn.getHinhAnh());
                ps.setString(3, tn.getLoiGiaiThich());
                ps.setInt(4, tn.getMaBH());
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    insertAnswers(generatedId, tn.getDanhSachDapAn(), conn);
                }
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean update(TracNghiem tn) {
        String sql = "UPDATE TRACNGHIEM SET NoiDungCauHoi=?, HinhAnh=?, LoiGiaiThich=?, MaBH=? WHERE MaTN=?";
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, tn.getNoiDungCauHoi());
                ps.setString(2, tn.getHinhAnh());
                ps.setString(3, tn.getLoiGiaiThich());
                ps.setInt(4, tn.getMaBH());
                ps.setInt(5, tn.getMaTN());
                ps.executeUpdate();

                // Xóa đáp án cũ và thêm mới cho đơn giản
                try (PreparedStatement delPs = conn.prepareStatement("DELETE FROM DAPAN WHERE MaTN = ?")) {
                    delPs.setInt(1, tn.getMaTN());
                    delPs.executeUpdate();
                }
                insertAnswers(tn.getMaTN(), tn.getDanhSachDapAn(), conn);

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private void insertAnswers(int maTN, List<DapAn> answers, Connection conn) throws SQLException {
        if (answers == null || answers.isEmpty()) return;
        String sql = "INSERT INTO DAPAN (NoiDungDapAn, IsCorrect, MaTN) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (DapAn da : answers) {
                ps.setString(1, da.getNoiDungDapAn());
                ps.setBoolean(2, da.isCorrect());
                ps.setInt(3, maTN);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM TRACNGHIEM WHERE MaTN = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}
