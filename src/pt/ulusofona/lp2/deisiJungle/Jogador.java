package pt.ulusofona.lp2.deisiJungle;

class Jogador {
    private final int id;
    private final String nome;
    private String idEspecie;
    private int energia;

    public Jogador(int id, String nome, String idEspecie) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;
    }

    public void setEspecie(String idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getID() {
        return id;
    }

    public String[] getInfo() {
        String[] info = new String[4];

        info[0] = String.valueOf(id);
        info[1] = nome;
        info[2] = idEspecie;
        info[3] = String.valueOf(energia);

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
