package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Validacoes {

    public static ArrayList<String> speciesToArrayList(String[][] species) {
        ArrayList<String> speciesArrayList = new ArrayList<>();

        for (int i = 0; i < species.length; i++) {
            speciesArrayList.add(species[i][0]);
        }

        return speciesArrayList;
    }

    public static boolean validarPlayersInfo(String[][] playersInfo, String[][] species) {

        if(playersInfo.length < 2 || playersInfo.length > 4) {
            return false;
        }

        ArrayList<String> speciesAsList = speciesToArrayList(species);

        List<String> ids = new ArrayList<>();

        for (String[] player : playersInfo) {
            if (Integer.parseInt(player[0]) < 0) {
                return false;
            }

            ids.add(player[0]);

            if (player[1] == null || player[1].equals("")) {
                return false;
            }

            if (!speciesAsList.contains(player[2])) {
                return false;
            }

        }

        //Ver se há 2 id's iguais
        for (int i = 0; i < ids.size(); i++) {
            for (int j = 0; j < ids.size(); j++) {
                if (i != j) {
                    if (ids.get(i).equals(ids.get(j))) {
                        return false;
                    }

                }
            }
        }
        return true;
    }
}
