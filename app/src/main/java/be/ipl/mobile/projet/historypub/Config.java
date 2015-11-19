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

package be.ipl.mobile.projet.historypub;

/**
 * Interface contenant les différentes constantes utilisée dans le programme.
 */
public interface Config {
    /* Prefs */
    String PREF_ETAPE_COURANTE = "etape_courante"; /* Retiens le NUMERO de l'étape courante */
    String PREF_EPREUVE_COURANTE = "epreuve_courante"; /* Retiens l'URL de l'épreuve courante */

    /* Intent extras */
    String EXTRA_ETAPE_COURANTE = "etape";
    String EXTRA_EPREUVE = "epreuve";

    /* XML */
    String NAMESPACE = null;
    String FICHIER_ALMA = "Soignies.xml";

}
