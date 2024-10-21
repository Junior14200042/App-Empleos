package com.devjr.app_empleo.services;

import com.devjr.app_empleo.model.Vacante;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacantesServicesImpl implements IVacantesServices{

    private List<Vacante> vacantes=null;

    public VacantesServicesImpl(){
         vacantes = new ArrayList<>();

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            vacantes.add(Vacante.builder()
                    .id(1)
                    .nombre("Desarrollador Java")
                    .descripcion("Responsable del desarrollo de aplicaciones en Java.")
                    .fecha(sdf.parse("12-10-2024"))
                    .salario(50000.0)
                    .destacado(1)
                    .imagen("supervision.png")
                    .build());

            vacantes.add(Vacante.builder()
                    .id(2)
                    .nombre("Analista de Sistemas")
                    .descripcion("Análisis y diseño de sistemas de información.")
                    .fecha(sdf.parse("12-10-2024"))
                    .salario(45000.0)
                    .destacado(0)
                    .imagen("programador.png")
                    .build());

            vacantes.add(Vacante.builder()
                    .id(3)
                    .nombre("Diseñador UX/UI")
                    .descripcion("Diseño de interfaces y experiencia de usuario.")
                    .fecha(sdf.parse("12-10-2024"))
                    .salario(40000.0)
                    .destacado(0)
                    .imagen("developer.png")
                    .build());

            vacantes.add(Vacante.builder()
                    .id(4)
                    .nombre("Ingeniero de Software")
                    .descripcion("Desarrollo y mantenimiento de software.")
                    .fecha(sdf.parse("12-10-2024"))
                    .salario(60000.0)
                    .destacado(1)
                    .imagen("desarrollador.png")
                    .build());

            vacantes.add(Vacante.builder()
                    .id(5)
                    .nombre("Gerente de Proyectos")
                    .descripcion("Gestión y coordinación de proyectos de TI.")
                    .fecha(sdf.parse("12-10-2024"))
                    .salario(70000.0)
                    .destacado(1)
                    .imagen("jugador.png")
                    .build());

            vacantes.add(Vacante.builder()
                    .id(6)
                    .nombre("Especialista en Marketing Digital")
                    .descripcion("Estrategias de marketing en línea y redes sociales.")
                    .fecha(sdf.parse("12-10-2024"))
                    .salario(48000.0)
                    .destacado(0)
                    .imagen("programa.png")
                    .build());

            vacantes.add(Vacante.builder()
                    .id(7)
                    .nombre("Soporte Técnico")
                    .descripcion("Asistencia técnica y resolución de problemas informáticos.")
                    .fecha(sdf.parse("12-10-2024"))
                    .salario(35000.0)
                    .destacado(0)
                    .build());
        }catch (ParseException p){
            p.getMessage();
        }
    }

    @Override
    public List<Vacante> buscarTodas() {
        return vacantes;
    }

    @Override
    public Vacante buscarPorId(Integer idVacante) {

        for(Vacante v: vacantes){
            if(v.getId()==idVacante){
                return v;
            }
        }

        return null;
    }

    @Override
    public void guardar(Vacante vacante) {
        vacantes.add(vacante);
    }

    @Override
    public List<Vacante> buscarDestacadas() {
        return null;
    }

    @Override
    public void eliminar(Integer idVacante) {

    }
}
