package com.midasvision.javafxjdbc;

import java.util.List;

public interface SellerDao {

    void insert(Seller s);

    void update(Seller s);

    void deleteById(Integer id);

    Seller findById(Integer id);

    List<Seller> findAll();

    List<Seller> findByDepartment(Departamento department);
}
