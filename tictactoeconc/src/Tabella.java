public class Tabella {
    char[][] tabella;
    // volatile garantisce la visibilità immediata tra thread
    volatile boolean inCorso = true;

    volatile int turno = 0;

    Tabella() {
        tabella = new char[][]{{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
    }

    public void stampaTabella() {
        System.out.println("  0 1 2");
        for (int i = 0; i < tabella.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tabella[i].length; j++) {
                System.out.print(tabella[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkWin(char simbolo) {
        for (int i = 0; i < 3; i++) {
            if (tabella[i][0] == simbolo && tabella[i][1] == simbolo && tabella[i][2] == simbolo ||
                    tabella[0][i] == simbolo && tabella[1][i] == simbolo && tabella[2][i] == simbolo) {
                return true;
            }
        }
        if (tabella[0][0] == simbolo && tabella[1][1] == simbolo && tabella[2][2] == simbolo ||
                tabella[0][2] == simbolo && tabella[1][1] == simbolo && tabella[2][0] == simbolo) {
            return true;
        }
        return false;
    }

    // pareggio
    public boolean isPieno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabella[i][j] == '-') return false;
            }
        }
        return true;
    }
}