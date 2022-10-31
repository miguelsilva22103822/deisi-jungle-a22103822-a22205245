package pt.ulusofona.lp2.deisiJungle;

class Casa {
    Jogador[] jogadores = new Jogador[4];

    public Casa() {

    }

    public void addJogador(int id, String nome, int idEspecie, int energiaInicial) {

        for (Jogador jogador : jogadores) {
            if (jogador == null) {
                jogador = new Jogador(id, nome);
                jogador.setEnergia(energiaInicial);
            }
        }
    }

}