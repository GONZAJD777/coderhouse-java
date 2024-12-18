package com.coderhouse.ProyectoFinal_PrimeraEntrega;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.Dao.DaoFactory;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.Models.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private final DaoFactory mDaoFactory= new DaoFactory();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Client mClient1 = new Client("Ricardo Freitas","Illia 446,Cordoba Capital,Cordoba",23445194L);
			Client mClient2 = new Client("Maria Fernandez","Colon 700,Jesus Maria,Cordoba",11444164L);
			Client mClient3 = new Client("Mario Rodriguez","Umberto Primo 461,Cordoba Capital,Cordoba",25415461L);
			Client mClient4 = new Client("Gonzalo Zabala","Rondeau 422,Dean Funes,Cordoba",23554778L);

			Product mProduct1 = new Product("Fernet Branca","Fernet Branca 1L","Alimento","000A1","12000","21");
			Product mProduct2 = new Product("Coca-Cola","Coca-Cola 2L","Alimento","000A2","3000","21");
			Product mProduct3 = new Product("Leche LaSerenisima","Leche La Serenisima 1l TetraPack","Alimento","000A3","1500","21");
			Product mProduct4 = new Product("Neskiq","Cacao en polvo 400g","Alimento","000A4","6500","21");

			mDaoFactory.clientPersistence(mClient1);
			mDaoFactory.clientPersistence(mClient2);
			mDaoFactory.clientPersistence(mClient3);
			mDaoFactory.clientPersistence(mClient4);

			mDaoFactory.productPersistence(mProduct1);
			mDaoFactory.productPersistence(mProduct2);
			mDaoFactory.productPersistence(mProduct3);
			mDaoFactory.productPersistence(mProduct4);


		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
