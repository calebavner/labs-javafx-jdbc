package com.midasvision.javafxjdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Departamento d) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                INSERT INTO department
                (name)
                VALUES
                (?)""", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, d.getNome());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    int id = rs.getInt(1);
                    d.setId(Long.valueOf(String.valueOf(id)));
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected Error!");
            }

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Departamento d) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                UPDATE department
                SET Name = ?
                WHERE Id = ?""");

            ps.setString(1, d.getNome());
            ps.setInt(2, d.getId().intValue());

            ps.executeUpdate();

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Departamento findById(Integer id) {
        return null;
    }

    @Override
    public List<Departamento> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("""
                select * from department""");

            rs = ps.executeQuery();

            List<Departamento> list = new ArrayList<>();

            while(rs.next()) {
                Departamento d = instantiateDepartment(rs);
                list.add(d);
            }
            return list;

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    private Departamento instantiateDepartment(ResultSet rs) throws SQLException{
        Departamento d = new Departamento();
        d.setId(rs.getLong("Id"));
        d.setNome(rs.getString("Name"));
        return d;
    }
}
