package Authentification;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
			System.out.println("Voulez-vous vous inscrire ou vous connecter ?");
			System.out.println("Pour vous inscrire tapez 1 et pour vous connecter tapez 2");

			Scanner scanner = new Scanner(System.in);
			String reponse = scanner.next();
			while(!reponse.equals("1") && !reponse.equals("2") ) {
				System.out.println(" Reponse invalide, pour vous inscrire tapez 1 et pour vous connecter tapez 2");
				reponse = scanner.next();
			};
			switch(reponse) {
				case "1":
					Inscription();
					break;
				case "2":
					Connexion();
					break;
				default:
					System.out.println("mimknch dkhoul hna");
			}
			scanner.close();
	}
	
	
//	@SuppressWarnings("resource")
	public static void Inscription () {
		try {
			Scanner scanner = new Scanner(System.in);
			String line;
			Boolean ok = true;
			
			File fichier = new File("Bd");
			FileWriter fileWriter = new FileWriter(fichier , true);
			FileReader fileReader = new FileReader(fichier);

			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			System.out.println("Donnez un username");
			String username = scanner.next();
			System.out.println("Donnez un password");
			String password = scanner.next();
			String confirmePassword;
			do {
				System.out.println("Confirmez votre password");
				confirmePassword = scanner.next();
			}while(!confirmePassword.equals(password));
			
			
			while((line = bufferedReader.readLine() ) != null ) {
				String[] checkName = line.split("/");
				if(checkName[0].equals(username)) {
					System.err.println("Username déjà utilisé ! Essayez un autre");
					ok=false;
					break;
				}
			}
			if(ok) {
			bufferedWriter.write( username + "/" + password);
			bufferedWriter.newLine();
			System.out.println("inscription réussie !");
			}else {
				Inscription();
			}
			scanner.close();
			bufferedReader.close();
			bufferedWriter.close();

		}catch(Exception e) {
			System.err.println("Erreur lors de l'inscription" + e);
		}
	}
	
	
	public static void Connexion() {
		try {
		Scanner scanner = new Scanner(System.in);
		
		File fichier = new File("Bd");
		FileReader fileReader = new FileReader(fichier);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		System.out.println("Donnez un username");
		String username = scanner.next();
		System.out.println("Donnez un password");
		String password = scanner.next();
		
		String line;
		Boolean ok = false;
		while((line = bufferedReader.readLine() ) != null ) {
			String[] checkName = line.split("/");
			if(checkName[0].equals(username) && checkName[1].equals(password)) {
				ok=true;
				break;
			}
		}
		if(ok) {
		System.out.println("Bienvenue");
		}else {
			System.err.println("Username ou password incorrecte");
		}
		scanner.close();
		
		
		bufferedReader.close();
		}catch(Exception e) {
			System.err.println("erreur lors de la connection" + e);
		}

	}

}
	