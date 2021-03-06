/*
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

package be.ipl.mobile.projet.historypub.config;

/**
 * Interface contenant les différentes constantes utilisée dans le programme.
 */
public interface Config {
    /* Fichier de préférences */
    String PREFERENCES = "HistoryPub_prefs";

    /* Prefs */
    String PREF_ETAPE_COURANTE = "etape_courante"; /* Retiens le NUMERO de l'étape courante */
    String PREF_EPREUVE_COURANTE = "epreuve_courante"; /* Retiens l'URL de l'épreuve courante */
    String PREF_SCORE = "score"; /* Retiens les points totaux de l'utilisateur */
    String PREF_TEMPS_DEBUT = "temps_debut"; /*Le moment où l'on a commencé les épreuves */

    /* Intent extras */
    String EXTRA_ETAPE = "etape";
    String EXTRA_EPREUVE = "epreuve";

    /* XML */
    String FICHIER_ALMA = "Soignies.xml";
    String NAMESPACE = null;
}
