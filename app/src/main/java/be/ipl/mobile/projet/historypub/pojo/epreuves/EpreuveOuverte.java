/*
    History Pub est une application de jeu de piste proposant de découvrir la ville de Soignies,
    en parcourant cette dernière de bar en bar.

    Copyright (C) 2015
        Matteo Taroli <contact@matteotaroli.be>
        Nathan Raspe <raspe_nathan@live.be>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package be.ipl.mobile.projet.historypub.pojo.epreuves;

import java.util.List;

/**
 * Epreuve spécifique représentant une question ouverte, la réponse étant unique mais
 * pouvant être donnée de façons différentes.
 */
public class EpreuveOuverte extends Epreuve {

    private final List<Reponse> reponses;

    public EpreuveOuverte(int num, String question, String uri, int points, List<Reponse> reponses, String expliquation) {
        super(num, question, uri, Type.OUVERTE, points, expliquation);
        this.reponses = reponses;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public Reponse getReponse() {
        return reponses.get(0);
    }

    /**
     * Vérifie le réponse donnée.
     *
     * @param reponse Réponse donnée
     * @return True si la réponse est la bonne
     */
    public boolean estReponseCorrecte(Reponse reponse) {
        for (Reponse rep : reponses) {
            if (rep.equals(reponse)) {
                return true;
            }
        }
        return false;
    }
}
