package com.midasvision.javafxjdbc;

import java.util.ArrayList;
import java.util.List;

public class DepartamentoService {

    public List<Departamento> findAll() {
        List<Departamento> lista = new ArrayList<>();
        lista.add(new Departamento(1L, "Livros"));
        lista.add(new Departamento(2L, "Jogos"));
        lista.add(new Departamento(3L, "Eletros"));
        return lista;
    }
}
