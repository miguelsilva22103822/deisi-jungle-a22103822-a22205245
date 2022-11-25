package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.Arrays;

class Jogador {
    private final int id;
    private final String nome;
    private String idEspecie;
    private Especie especie;
    private int energia;

    public Jogador(int id, String nome, String idEspecie) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;
    }

    public void updateEspecie(Especie especie) {
        this.especie = especie;
    }

    public void setEspecie(String idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getID() {
        return id;
    }

    public String[] getInfo() {
        String[] info = new String[5];

        info[0] = String.valueOf(id);
        info[1] = nome;
        info[2] = idEspecie;
        info[3] = String.valueOf(energia);
        info[4] = Arrays.toString(especie.getVelocidade());

        return info;
    }

    public String[] getInfoEnergy(int nrPositions) {
        String[] info = new String[2];

        info[0] = String.valueOf(nrPositions * especie.getConsumoEnergia());
        info[1] = String.valueOf(especie.getEnergiaDescanso());

        return info;
    }

    public int getEnergia() {
        return energia;
    }

    public void spendEnergy() {
        energia -= 2;
        if (energia < 0) {
            energia = 0;
        }
    }

    public String getNome() {
        return nome;
    }

    public String getIdEspecie() {
        return idEspecie;
    }

}
