package org.adolfosalado;

import models.Alumno;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOODB implements AlumnoDAO {
    @Override
    public void insertarAlumno(Alumno alumno) {
        EntityManager em = EntityManagerFactoryUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.persist(alumno);
        em.getTransaction().commit();
        em.close();

        System.out.println("Alumno insertado con éxito");
    }

    @Override
    public void listarTodo() {
        EntityManager em = EntityManagerFactoryUtil.getEmf().createEntityManager();

        TypedQuery<Alumno> query =
                em.createQuery("SELECT a FROM Alumno a", Alumno.class);

        List<Alumno> listadoAlumnos = query.getResultList();
        em.close();

        System.out.println("LISTADO DE ALUMNOS: ");
        listadoAlumnos.forEach(a -> {
            System.out.println("\tNombre: " + a.getNombre() + "\n" +
                    "\tTeléfono: " + a.getTelefono() + "\n" +
                    "\tEmail: " + a.getEmail() + "\n" +
                    "\tNota global AD: " + a.getAd() + "\n" +
                    "\tNota global DI: " + a.getDi() + "\n");
        });
    }

    @Override
    public void listarSuspensos() {
        List<Alumno> alumnosSuspensos = obtenerAlumnosSuspensos();

        System.out.println("ALUMNOS SUSPENSOS: ");
        alumnosSuspensos.forEach(alumno -> {
            System.out.println("\tNombre: " + alumno.getNombre() + "\n" +
                    "\tTeléfono: " + alumno.getTelefono() + "\n" +
                    "\tEmail: " + alumno.getEmail() + "\n" +
                    "\tNota global AD: " + alumno.getAd() + "\n" +
                    "\tNota global DI: " + alumno.getDi() + "\n");
        });
    }


    @Override
    public void estadisticas() {
        System.out.println("ESTADÍSTICAS: ");
        obtenerMediasAlumnos();
        obtenerRatioAprobados();
    }


    private List<Alumno> obtenerAlumnosSuspensos() {
        List<Alumno> alumnosSuspensos = new ArrayList<>();

        List<Alumno> totalAlumnos = obtenerTotalAlumnos();
        totalAlumnos.forEach(alumno -> {
            if (alumno.getAd() < 5.0 || alumno.getDi() < 5.0) {
                alumnosSuspensos.add(alumno);
            }
        });
        return alumnosSuspensos;
    }

    private void obtenerMediasAlumnos() {
        List<Alumno> totalAlumnos = obtenerTotalAlumnos();
        Double sumaAd = (double) 0;
        Double sumaDi = (double) 0;
        Double mediaAd;
        Double mediaDi;

        for (Alumno alumno : totalAlumnos) {
            sumaAd += alumno.getAd();
            sumaDi += alumno.getDi();
        }

        mediaAd = sumaAd / totalAlumnos.size();
        mediaDi = sumaDi / totalAlumnos.size();

        System.out.println("La media de la asignatura de AD es de " + mediaAd);
        System.out.println("La media de la asignatura de DI es de " + mediaDi);
    }

    private ArrayList<Alumno> obtenerTotalAlumnos() {
        List<Alumno> totalAlumnos = new ArrayList<>();

        EntityManager em = EntityManagerFactoryUtil.getEmf().createEntityManager();

        TypedQuery<Alumno> query =
                em.createQuery("SELECT a FROM models.Alumno a", Alumno.class);

        totalAlumnos = query.getResultList();
        em.close();

        return (ArrayList<Alumno>) totalAlumnos;
    }

    private ArrayList<Alumno> obtenerAlumnosSinSuspensos() {
        List<Alumno> alumnosSinsSuspensos = new ArrayList<>();
        List<Alumno> totalAlumnos = obtenerTotalAlumnos();

        for (Alumno alumno : totalAlumnos) {
            if (alumno.getAd() >= 5.0 && alumno.getDi() >= 5.0) {
                alumnosSinsSuspensos.add(alumno);
            }
        }

        return (ArrayList<Alumno>) alumnosSinsSuspensos;
    }

    private void obtenerRatioAprobados() {
        Integer alumnosSinSuspensosSize = obtenerAlumnosSinSuspensos().size();
        Integer totalAlumnosSize = obtenerTotalAlumnos().size();

        float ratioAprobados = alumnosSinSuspensosSize / totalAlumnosSize.floatValue();

        System.out.println("El ratio de aprobados es de " + ratioAprobados);
    }
}
