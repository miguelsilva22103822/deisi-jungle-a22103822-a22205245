package pt.ulusofona.lp2.deisiJungle;

class Jogador {
    private final int id;
    private final String nome;
    private final String idEspecie;
    private Especie especie;
    private int energia;
    private int quantidadeBananasComeu;
    private int alimentosIngeridos;
    private int distanciaPercorrida;
    private int casaAtual = 1;

    public Jogador(int id, String nome, String idEspecie) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;
        this.alimentosIngeridos = 0;

        switch (this.idEspecie) {
            case "E" -> especie = new Elefante();
            case "L" -> especie = new Leao();
            case "T" -> especie = new Tartaruga();
            case "P" -> especie = new Passaro();
            case "Z" -> especie = new Tarzan();
            case "Q" -> especie = new Esquilo();
            case "U" -> especie = new Unicornio();
        }

        this.energia = especie.getEnergiaInicial();
    }

    public Jogador(int id, String nome, String idEspecie, int energia, int quantidadeBananasComeu,
                   int alimentosIngeridos, int distanciaPercorrida, int casaAtual) {

        this(id, nome, idEspecie);

        this.alimentosIngeridos = alimentosIngeridos;
        this.energia = energia;
        this.quantidadeBananasComeu = quantidadeBananasComeu;
        this.distanciaPercorrida = distanciaPercorrida;
        this.casaAtual = casaAtual;
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

        if (nrPositions < especie.getVelocidadeMin() || nrPositions > especie.getVelocidadeMax()) {
            return false;
        }

        return true;
    }

    public boolean hasEnergy(int nrPositions) { //ver se tem energia pra x casas
        if (nrPositions == 0) {
            return true;
        }

        nrPositions = Math.abs(nrPositions);

        if (energia >= nrPositions * especie.getConsumoEnergia()) {
            return true;
        }

        return false;
    }

    public void updateEnergyMovement(int nrPositions) { // pode ser negativo(nrPositions)
        if (nrPositions == 0) {
            energia += especie.getEnergiaDescanso();

            if(energia > 200) {
                energia = 200;
            }

            if (getDieta().equals("nuv")) {
                energia += 2;
            }
        }
        else {
            energia -= Math.abs(nrPositions) * especie.getConsumoEnergia();

            if (getDieta().equals("nuv")) {
                energia += 2;
            }

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
        alimentosIngeridos++;

        if (alimento.eBanana() && alimento.getQuantidadeBananas() != 0) {
            quantidadeBananasComeu++;
        }

        if(energia > 200) {
            energia = 200;
        }

        if (energia < 0) {
            energia = 0;
        }

    }

    public boolean podeComer(Alimento alimento) {
        return !(eHerbivoro() && alimento.eCarne())
                && alimento.getQuantidadeBananas() != 0;
    }

    public int getQuantidadeComeu () {
        return alimentosIngeridos;
    }

    public int getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public void setCasaAtual(int nrCasa) {
        casaAtual = nrCasa;
    }

    public int getCasaAtual() {
        return casaAtual;
    }

    public String getSaveInfo() {
        return "["
                + id + ","
                + nome + ","
                + idEspecie + ","
                + energia + ","
                + quantidadeBananasComeu + ","
                + alimentosIngeridos + ","
                + distanciaPercorrida + ","
                + casaAtual
                + "]";
    }

    public String getEspecieName() {
        return especie.getNome();
    }

    public String getEspecieId() {
        return especie.getId();
    }

    public boolean eHerbivoro() {
        return especie.getDieta().equals("h");
    }

    public void energiaUnicornio() {
        energia += 2;
    }

    public void energiaUnicornioCasaComAlimento() {
        energia -= 2;
    }

}
