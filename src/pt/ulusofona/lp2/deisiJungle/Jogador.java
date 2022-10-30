package pt.ulusofona.lp2.deisiJungle;

class Jogador {
    int id;
    String nome;
    Especie especie;
    int energia;


    public Jogador(int id, String nome, Especie especie) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
    }
}
