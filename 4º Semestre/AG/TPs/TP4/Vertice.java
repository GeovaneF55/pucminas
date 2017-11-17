/**
 * Vértice referente as tarefas a serem executadas
 * pelos funcionários de certa empresa.
 * @author Luigi D. C. Soares <luigi.soares@sga.pucminas.br>
 * @author Gabriel L. Gomes <glgomes@sga.pucminas.br>
 * @author Geovane F. S. Santos <geovane.fonseca@sga.pucminas.br>
 * @version 1
 * @since 15/11/2017
 */

import java.util.ArrayList;
import java.util.List;

class Vertice {
    private int cor;
    private List<Vertice> adjacentes;

    public Vertice() {
        this.cor = -1;
        this.adjacentes = new ArrayList<>();
    }

    public int getCor() {
        return this.cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public List<Vertice> getAdjacentes() {
        return this.adjacentes;
    }

    public void addAdjacente(Vertice vertice) {
        this.adjacentes.add(vertice);
    }

    public boolean verificarCor(int cor) {
        for (Vertice v : this.adjacentes) {
            if (v.getCor() == cor) {
                return true;
            }
        }

        return false;
    }
}