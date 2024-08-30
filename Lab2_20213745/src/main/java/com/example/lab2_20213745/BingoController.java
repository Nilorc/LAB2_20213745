package com.example.lab2_20213745;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class BingoController {

    private List<int[][]> tarjetasBingo;

    private List<Integer> numerosIngresados;

    @GetMapping("/")
    public String mostrarFormularioConfiguracion() {
        return "configuracion"; //vista de configuración
    }

    @PostMapping("/generar")
    public String generarTarjetas(@RequestParam("dimensionTarjeta") int dimensionTarjeta,
                                  @RequestParam("numeroTarjeta") int numeroTarjeta,
                                  Model modelo) {

        ConfiguracionJuego configuracion = new ConfiguracionJuego();
        configuracion.setDimensionTarjeta(dimensionTarjeta);
        configuracion.setNumeroTarjeta(numeroTarjeta);

        tarjetasBingo = new ArrayList<>();
        numerosIngresados = new ArrayList<>();

        for (int i = 0; i < numeroTarjeta; i++) {
            tarjetasBingo.add(generarTarjeta(dimensionTarjeta));
        }

        //ver las tarjetas generadas
        modelo.addAttribute("tarjetasBingo", tarjetasBingo);
        modelo.addAttribute("configuracion", configuracion);
        return "juego";
    }

    @PostMapping("/iniciar")
    public String iniciarJuego(@RequestParam("numero") int numero, Model modelo) {
        numerosIngresados.add(numero);
        modelo.addAttribute("tarjetasBingo", tarjetasBingo);
        modelo.addAttribute("numerosIngresados", numerosIngresados);

        return "juego";
    }


//para generar las tarjetas
    private int[][] generarTarjeta(int dimension) {
        int[][] tarjeta = new int[dimension][dimension];
        Random random = new Random();

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                tarjeta[i][j] = random.nextInt(99) + 1;
            }
        }
        return tarjeta;
    }
}

