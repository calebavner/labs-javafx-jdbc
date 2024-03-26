package com.midasvision.javafxjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Departamento d) {

    }

    @Override
    public void update(Departamento d) {

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
