package com.bouchardm.util;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

/**
 * Classe permettant d'effectuer des requêtes à une base de données
 * en utilisant une source de données (JNDI).
 * @author Stéphane Lapointe
 */
public class RequeteBD {

	// Nom de la source de données.
	protected String nomDS;
	// Connexion à la base de données.
	protected Connection connexion = null;
	// Objet permettant d'exécuter des requêtes sur la BD.
	protected Statement stat = null;

	/**
	 * Constructeur paramétré.
	 * @param nomDS : Le nom de la source de données.
	 */
	public RequeteBD(String nomDS) {
		this.nomDS = nomDS;
	}

	/**
	 * Initialise la connexion à la base de données ainsi que l'objet "Statement".
	 * @throws javax.naming.NamingException
	 * @throws java.sql.SQLException
	 */
	public void obtenirConnexion()
			throws NamingException, SQLException {

		// Création de la connexion à la base de données
		// en utilisant la source de données.
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup(this.nomDS);
			this.connexion = ds.getConnection();
		} catch (NamingException ne) {
			throw new NamingException("Impossible de trouver la source de données : " + this.nomDS + " (" + ne.getMessage() + ")");
		} catch (SQLException se) {
			throw new SQLException("Échec de la connexion à la base de données avec la source de données : " + this.nomDS + " (" + se.getMessage() + ")");
		}

		// Création de l'objet "Statement" permettant d'exécuter
		// une requête SQL sur la base de données.
		try {
			stat = connexion.createStatement();
		} catch (SQLException se) {
			// Fermeture de la connexion à la base de données.
			fermerConnexion();
			throw new SQLException("Échec lors de la création de l'objet \"java.sql.Statement\"" + " (" + se.getMessage() + ")");
		}
	}

	/**
	 * Permet de fermer les ressources utilisées pour effectuer
	 * une requête, soit les objets "Statement" et "Connection".
	 */
	public void fermerConnexion() {
		// Tentative de fermeture de l'objet "Statement".
		try {
			stat.close();
		} catch (Exception e) {  // Rien à faire s'il y a un échec.
		} finally {
			// Tentative de fermeture de l'objet "Connection".
			try {
				connexion.close();
			} catch (Exception e) {  // Rien à faire s'il y a un échec.
			}
		}
	}

	/**
	 * Permet d'effectuer une requête SELECT à la base de données.
	 * @param requeteSQL : La requête SQL de type SELECT à exécuter.
	 * @return le jeu de résultats correspondant à la requête.
	 * @throws SQLException
	 */
	public ResultSet executerRequeteSelect(String requeteSQL)
			throws SQLException {

		// Il faut avoir obtenu une connexion à la BD et il faut que l'objet "Statement" ait été créé.
		if (this.connexion == null) {
			throw new SQLException("La connexion à la base de données n'est pas créée");
		} else if (this.stat == null) {
			throw new SQLException("L'objet \"Statement\" n'est pas créé");
		} else {
			ResultSet resultat = null;
			try {
				// Exécution de la requête SQL.
				resultat = stat.executeQuery(requeteSQL);
			} catch (SQLException se) {
				// Fermeture de la connexion à la base de données si la requête SQL cause une erreur.
				fermerConnexion();
				throw new SQLException("Échec lors de l'exécution de la requête SQL : " + requeteSQL + " (" + se.getMessage() + ")");
			}
			// Retourne le jeu de résultats.
			return resultat;
		}
	}

	/**
	 * Permet d'effectuer une requête de mise à jour à la base de données
	 * (INSERT, UPDATE et DELETE).
	 * @param requeteSQL
	 * @return le nombre d'enregistrements affectés par la requête.
	 * @throws SQLException
	 */
	public int executerRequeteMAJ(String requeteSQL)
			throws SQLException {

		// Il faut avoir obtenu une connexion à la BD et il faut que l'objet "Statement" ait été créé.
		if (this.connexion == null) {
			throw new SQLException("La connexion à la base de données n'est pas créée");
		} else if (this.stat == null) {
			throw new SQLException("L'objet \"Statement\" n'est pas créé");
		} else {
			int nbEnrModif = -1;
			try {
				// Exécution de la requête SQL.
				nbEnrModif = stat.executeUpdate(requeteSQL);
			} catch (SQLException se) {
				// Fermeture de la connexion à la base de données si la requête SQL cause une erreur.
				fermerConnexion();
				throw new SQLException("Échec lors de l'exécution de la requête SQL : " + requeteSQL + " (" + se.getMessage() + ")");
			}
			// Retourne le jeu de résultats.
			return nbEnrModif;
		}
	}

	/* Getter et Setter
	 * ================ */
	/**
	 * @return nomDS : Le nom de la source de données.
	 */
	public String getNomDS() {
		return this.nomDS;
	}

	/**
	 * @param nomDS : Le nom de la source de données.
	 */
	public void setNomDS(String nomDS) {
		this.nomDS = nomDS;
	}
}
