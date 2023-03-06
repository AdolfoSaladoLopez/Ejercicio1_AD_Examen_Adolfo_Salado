package org.adolfosalado;

import models.Alumno;

public interface AlumnoDAO {
    void insertarAlumno(Alumno alumno);

    void listarTodo();

    void listarSuspensos();

    void estadisticas();
}
