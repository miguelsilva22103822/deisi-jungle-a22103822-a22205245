package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;
import java.util.Collections;

class Casa {
    private ArrayList<Integer> iDsJogadores;
    private final int tamanhoMax = 4;
    private boolean isMeta;
    private Alimento alimento;

    public Casa() {
        this.iDsJogadores = new ArrayList<>();
        alimento = null;
    }

    public Casa(ArrayList<Integer> iDsJogadores, Alimento alimento) {
        this.iDsJogadores = iDsJogadores;
        this.alimento = alimento;
    }

    public boolean addJogador(int id) {

        if (iDsJogadores.size() >= tamanhoMax) {
            return false;
        }

        iDsJogadores.add(id);

        return true;
    }

    public boolean addAlimento(String idAlimento) {

        if (alimento != null) {
            return false;
        }

        switch (idAlimento) {
            case "a" -> alimento = new Agua();
            case "b" -> alimento = new CachoBananas();
            case "m" -> alimento = new Cogumelo();
            case "c" -> alimento = new Carne();
            case "e" -> alimento = new Erva();
            default ->  { return false;}
        }

        return true;
    }

    public String getIDAlimento() {
        if (alimento == null) {
            return null;
        }

        return alimento.getId();
    }

    public int[] getIDsJogadores() {

        if (iDsJogadores.size() == 0) {
            return new int[0];
        }

        return arrayListToArray(iDsJogadores);
    }

    public String[] getInfo(int numJogada) {

        int[] iDsJogadores = getIDsJogadores();
        StringBuilder iDsJogadoresString = new StringBuilder();

        //pôr os ids dos jogadores numa string, separados por virgulas
        for(int i = 0; i < iDsJogadores.length; i++) {
            if (i == iDsJogadores.length - 1) {
                iDsJogadoresString.append(iDsJogadores[i]);
            }
            else {
                iDsJogadoresString.append(iDsJogadores[i]).append(",");
            }
        }

        String[] info = new String[3];

        if (isMeta) {
            info[0] = "finish.png";
            info[1] = "Meta";

        } else if ( alimento != null ) {

            info[0] = alimento.getImagem();
            info[1] = alimento.getToolTip(numJogada);

        }
        else {
            info[0] = "blank.png";
            info[1] = "Vazio";
        }

        info[2] = iDsJogadoresString.toString();

        return info;
    }

    public void setAsMeta() {
        this.isMeta = true;
    }

    public boolean containsJogador(int playerID) {
        for (Integer iD : iDsJogadores) {
            if (iD == playerID) {
                return true;
            }
        }
        return false;
    }

    public boolean removeJogador(int playerID) {
        for (int i = 0; i < iDsJogadores.size(); i++) {
            if (iDsJogadores.get(i) == playerID) {
                iDsJogadores.remove(i);
                return true;
            }
        }
        return false;
    }

    public int[] arrayListToArray(ArrayList<Integer> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }

        int[] array = new int[arrayList.size()];

        int i = 0;
        for (Integer integer : arrayList) {
            array[i] = integer;
            i++;
        }

        return array;
    }

    public int nrJogadores() {
        return iDsJogadores.size();
    }

    public int jogadorIDMenor() {
        if (nrJogadores() == 0){
            return  -1;
        }

        int jogadorMenor = iDsJogadores.get(0);

        for (int i = 0; i < iDsJogadores.size(); i++){
            if (iDsJogadores.get(i) < jogadorMenor){
                jogadorMenor = iDsJogadores.get(i);
            }
        }
        return jogadorMenor;
    }

    public void sortIDs() {
        Collections.sort(iDsJogadores);
    }

    public Alimento getAlimento () {
        return alimento;
    }

    public String[] getSaveInfo() {
        int[] iDsJogadores = getIDsJogadores();
        StringBuilder iDsJogadoresString = new StringBuilder();

        //pôr os ids dos jogadores numa string, separados por virgulas
        for(int i = 0; i < iDsJogadores.length; i++) {
            if (i == iDsJogadores.length - 1) {
                iDsJogadoresString.append(iDsJogadores[i]);
            }
            else {
                iDsJogadoresString.append(iDsJogadores[i]).append(",");
            }
        }

        String[] info = new String[2];

        if (isMeta) {
            info[0] = "Meta";
        } else if (alimento != null) {
            info[0] = alimento.getNome();
        } else {
            info[0] = "Vazio";
        }

        info[1] = iDsJogadoresString.toString();

        return info;
    }

    public String[] getAlimentoSaveInfo() { //esta função n é usada , penso q podemos tirar
        if (alimento == null) {
            return null;
        }

        return alimento.getSaveInfo();
    }
}
