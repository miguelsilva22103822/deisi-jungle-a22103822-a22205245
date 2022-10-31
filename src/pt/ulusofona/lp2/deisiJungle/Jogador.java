package pt.ulusofona.lp2.deisiJungle;

class Jogador {
    int id;
    String nome;
    String idEspecie;
    int energia;

    public Jogador(int id, String nome, String idEspecie) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;
    }

    public void setEnergia(int energiaInicial) {
        energia = energiaInicial;
    }

    public void setEspecie(String idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getID() {
        return id;
    }
}
