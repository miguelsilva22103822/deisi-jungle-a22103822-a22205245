package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.Arrays;

class Jogador {
    private final int id;
    private final String nome;
    private final String idEspecie;
    private Especie especie;
    private int energia;
    private int quantidadeBananasComeu;
    private int quantidadeComeu;
    private int distanciaPercorrida;

    public Jogador(int id, String nome, String idEspecie) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;

        switch (this.idEspecie) {
            case "E" -> especie = new Elefante();
            case "L" -> especie = new Leao();
            case "T" -> especie = new Tartaruga();
            case "P" -> especie = new Passaro();
            case "Z" -> especie = new Tarzan();
        }

        this.energia = especie.getEnergiaInicial();
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
        info[4] = especie.getVelocidade();

        return info;
    }

    public String[] getInfoEnergy(int nrPositions) {
        String[] info = new String[2];

        info[0] = String.valueOf(Math.abs(nrPositions) * especie.getConsumoEnergia());
        info[1] = String.valueOf(especie.getEnergiaDescanso());

        return info;
    }

    public int getEnergia() {
        return energia;
    }

    public boolean movementIsValid(int nrPositions) {
        if (nrPositions == 0) {
            return true;
        }

        nrPositions = Math.abs(nrPositions);

        if (nrPositions < Character.getNumericValue(especie.getVelocidade().charAt(0))
                || nrPositions > Character.getNumericValue(especie.getVelocidade().charAt(3))) {
            return false;
        }

        return true;
    }

    public boolean hasEnergy(int nrPositions) {
        if (nrPositions == 0) {
            return true;
        }

        if (energia >= nrPositions * especie.getConsumoEnergia()) {
            return true;
        }

        return false;
    }

    public void updateEnergyMovement(int nrPositions) { // pode ser negativo(nrPositions)
        if (nrPositions == 0) {
            energia += especie.getEnergiaDescanso();
        }
        else {
            energia -= Math.abs(nrPositions) * especie.getConsumoEnergia();
        }

        distanciaPercorrida += Math.abs(nrPositions);
    }

    public String getNome() {
        return nome;
    }

    public String getIdEspecie() {
        return idEspecie;
    }

    public String getDieta () {
        return especie.getDieta();
    }

    public void comer (Alimento alimento, int numJogada) {

        this.energia = alimento.calcularEnergia(energia, getDieta(), quantidadeBananasComeu, numJogada);
        quantidadeComeu++;

        if (alimento.getId().equals("b") && alimento.getQuantidadeBananas() != 0) {
            quantidadeBananasComeu++;
        }

    }

    public int getQuantidadeComeu () {
        return quantidadeComeu;
    }

    public int getDistanciaPercorrida() {
        return distanciaPercorrida;
    }
}
