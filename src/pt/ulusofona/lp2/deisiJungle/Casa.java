package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

class Casa {
    ArrayList<Jogador> jogadores;
    int tamanhoMax = 4;

    public Casa() {
        this.jogadores = new ArrayList<>();
    }

    public boolean addJogador(int id, String nome, String idEspecie, int energiaInicial) {

        if (jogadores.size() >= 4) {
            return false;
        }

        Jogador jogadorAAdicionar = new Jogador(id, nome, idEspecie);
        jogadorAAdicionar.setEnergia(energiaInicial);
        jogadores.add(jogadorAAdicionar);

        return false;
    }

    public int[] getIDsJogadores() {

        if (jogadores.size() >= 4) {
            return new int[0];
        }

        int i = 0;
        int[] IDsJogadores = new int[4];

        for (Jogador jogador : jogadores) {
            IDsJogadores[i] = jogador.getID();
            i++;
        }

        return IDsJogadores;
    }


}