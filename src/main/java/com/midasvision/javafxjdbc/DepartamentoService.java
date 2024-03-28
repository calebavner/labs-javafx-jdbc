package com.midasvision.javafxjdbc;

import java.util.ArrayList;
import java.util.List;

public class DepartamentoService {

    private DepartmentDao dao = DaoFactory.createDepartmentDao();

    public List<Departamento> findAll() {
        return dao.findAll();
    }

    public void saveOrUpdate(Departamento d) {
        if(d.getId() == null) {
            dao.insert(d);
            System.out.println("Dados inseridos com sucesso");
        } else {
            dao.update(d);
            System.out.println("Dados atualizados com sucesso");
        }
    }
}
