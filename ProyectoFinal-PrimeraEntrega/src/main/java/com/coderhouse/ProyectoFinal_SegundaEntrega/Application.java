package com.coderhouse.ProyectoFinal_SegundaEntrega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

//	@Autowired
//	private final DaoFactory mDaoFactory= new DaoFactory();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
////		try {
////			Client mClient1 = new Client("Ricardo Freitas","Illia 446,Cordoba Capital,Cordoba",23445194L);
////			Client mClient2 = new Client("Maria Fernandez","Colon 700,Jesus Maria,Cordoba",11444164L);
////			Client mClient3 = new Client("Mario Rodriguez","Umberto Primo 461,Cordoba Capital,Cordoba",25415461L);
////			Client mClient4 = new Client("Gonzalo Zabala","Rondeau 422,Dean Funes,Cordoba",23554778L);
////
////			Product mProduct1 = new Product("Fernet Branca","Fernet Branca 1L","Alimento","000A1","12000","21",100);
////			Product mProduct2 = new Product("Coca-Cola","Coca-Cola 2L","Alimento","000A2","3000","21",200);
////			Product mProduct3 = new Product("Leche LaSerenisima","Leche La Serenisima 1l TetraPack","Alimento","000A3","1500","21",50);
////			Product mProduct4 = new Product("Neskiq","Cacao en polvo 400g","Alimento","000A4","6500","21",80);
////
////			mDaoFactory.productPersistence(mProduct1);
////			mDaoFactory.productPersistence(mProduct2);
////			mDaoFactory.productPersistence(mProduct3);
////			mDaoFactory.productPersistence(mProduct4);
////
////
////			CartDetail mCartDetail1 = new CartDetail(mProduct1,1);
////			CartDetail mCartDetail2 = new CartDetail(mProduct2,2);
////			CartDetail mCartDetail3 = new CartDetail(mProduct3,1);
////			CartDetail mCartDetail4 = new CartDetail(mProduct4,1);
////
////			ArrayList<CartDetail> mCartDetailList = new ArrayList<>();
////			mCartDetailList.add(mCartDetail1);
////			mCartDetailList.add(mCartDetail2);
////			mCartDetailList.add(mCartDetail3);
////			mCartDetailList.add(mCartDetail4);
////
////			Cart mCart1 = new Cart(mClient1,mCartDetailList);
////			mClient1.setClientCart(mCart1);
////
////			mDaoFactory.clientPersistence(mClient1);
////			mDaoFactory.clientPersistence(mClient2);
////			mDaoFactory.clientPersistence(mClient3);
////			mDaoFactory.clientPersistence(mClient4);
////
////
////
////		} catch (Exception e) {
////			e.printStackTrace(System.err);
////		}
//	}

}
