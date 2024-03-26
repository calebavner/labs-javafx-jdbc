package com.midasvision.javafxjdbc;

import java.util.List;

public interface DepartmentDao {

    void insert(Departamento d);

    void update(Departamento d);

    void deleteById(Integer id);

    Departamento findById(Integer id);

    List<Departamento> findAll();
}
