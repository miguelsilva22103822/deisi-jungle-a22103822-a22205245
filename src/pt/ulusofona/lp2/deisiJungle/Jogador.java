package pt.ulusofona.lp2.deisiJungle;

class Jogador {
    int id;
    String nome;
    int idEspecie;
    int energia;

    public Jogador(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void setEnergia(int energiaInicial) {
        energia = energiaInicial;
    }

    public void setEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }
}
