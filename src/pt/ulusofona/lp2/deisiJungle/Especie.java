package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

abstract class Especie {
    private final String id;
    private final String nome;
    private BufferedImage imagem;

    public Especie(String id, String nome, BufferedImage imagem) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
    }

    public Especie(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    abstract public int getEnergiaInicial();

    abstract public int getConsumoEnergia();

    abstract public int getEnergiaDescanso();

    abstract public IntStream getVelocidade(); // não sei se se pode usar intStream

    //as funções não usam nenhumas vars das classes não sei se se pode
}