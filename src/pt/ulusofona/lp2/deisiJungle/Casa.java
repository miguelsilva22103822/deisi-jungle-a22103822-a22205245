package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

class Casa {
    ArrayList<Integer> iDsJogadores;
    int tamanhoMax = 4;
    boolean isMeta;

    public Casa() {
        this.iDsJogadores = new ArrayList<>();
    }

    public boolean addJogador(int id) {

        if (iDsJogadores.size() >= tamanhoMax) {
            return false;
        }

        iDsJogadores.add(id);

        return true;
    }

    public int[] getIDsJogadores() {

        if (iDsJogadores.size() > tamanhoMax) {
            return new int[0];
        }

        if (iDsJogadores.size() == 0) {
            return new int[0];
        }

        return iDsJogadores.stream().mapToInt(i -> i).toArray();
    }

    public String[] getInfo() {

        int[] iDsJogadores = getIDsJogadores();
        StringBuilder iDsJogadoresString = new StringBuilder();

        //pôr os ids dos jogadores numa string, separados por virgulas
        for(int i = 0; i < iDsJogadores.length; i++) {
            if (i == iDsJogadores.length - 1) {
                iDsJogadoresString.append(iDsJogadores[i]);
            }
            else {
                iDsJogadoresString.append(iDsJogadores[i]).append(", ");
            }
        }

        String[] info = new String[3];

        if (isMeta) {
            info[0] = "finish.png";
            info[1] = "Meta";
        } else {
            info[0] = "blank.png";
            info[1] = "Vazio";
        }
        info[2] = iDsJogadoresString.toString();

        return info;
    }

    public void setAsMeta() {
        this.isMeta = true;
    }

    public boolean isMeta() {
        return isMeta;
    }

}