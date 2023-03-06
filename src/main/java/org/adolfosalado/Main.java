package org.adolfosalado;

import models.Alumno;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AlumnoDAO gestorAlumnos = new AlumnoDAOODB();
        //registrarAlumnosBd(gestorAlumnos);


        gestorAlumnos.listarTodo();
        gestorAlumnos.listarSuspensos();
        gestorAlumnos.estadisticas();
    }

    private static void registrarAlumnosBd(AlumnoDAO gestorAlumnos) {
        List<Alumno> alumnosRegistrar = rellenarAlumnos();

        for (Alumno alumno : alumnosRegistrar) {
            gestorAlumnos.insertarAlumno(alumno);
        }
    }

    private static ArrayList<Alumno> rellenarAlumnos() {
        List<Alumno> totalAlumnos = new ArrayList<>();

        Alumno alumnoUno = new Alumno();
        alumnoUno.setNombre("Alumno Uno");
        alumnoUno.setTelefono("952000001");
        alumnoUno.setEmail("alumno.uno@correo.com");
        alumnoUno.setAd(5.0);
        alumnoUno.setDi(10.0);

        Alumno alumnoDos = new Alumno();
        alumnoDos.setNombre("Alumno Dos");
        alumnoDos.setTelefono("952000002");
        alumnoDos.setEmail("alumno.dos@correo.com");
        alumnoDos.setAd(4.0);
        alumnoDos.setDi(5.0);

        Alumno alumnoTres = new Alumno();
        alumnoTres.setNombre("Alumno Tres");
        alumnoTres.setTelefono("952000003");
        alumnoTres.setEmail("alumno.tres@correo.com");
        alumnoTres.setAd(1.0);
        alumnoTres.setDi(2.0);

        Alumno alumnoCuatro = new Alumno();
        alumnoCuatro.setNombre("Alumno Cuatro");
        alumnoCuatro.setTelefono("952000004");
        alumnoCuatro.setEmail("alumno.cuatro@correo.com");
        alumnoCuatro.setAd(8.0);
        alumnoCuatro.setDi(7.0);

        Alumno alumnoCinco = new Alumno();
        alumnoCinco.setNombre("Alumno Cinco");
        alumnoCinco.setTelefono("952000005");
        alumnoCinco.setEmail("alumno.cinco@correo.com");
        alumnoCinco.setAd(1.0);
        alumnoCinco.setDi(8.0);

        totalAlumnos.add(alumnoUno);
        totalAlumnos.add(alumnoDos);
        totalAlumnos.add(alumnoTres);
        totalAlumnos.add(alumnoCuatro);
        totalAlumnos.add(alumnoCinco);

        return (ArrayList<Alumno>) totalAlumnos;
    }
}