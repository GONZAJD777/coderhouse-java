# coderhouse-java

## Comandos SQL 
#### create database if not exists coderhouse_proyecto_final;
#### use coderhouse_proyecto_final;
#### -------------------------------------------------------------
#### drop database coderhouse_proyecto_final;
#### select * from clients;
#### select * from products;
#### select * from carts;
#### -------------------------------------------------------------

# DOCUMENTACION

## 1 - El procedimiento del camino feliz sera crear los productos, crear 1 o mas clientes. 
## 2 - Agregar productos al carrito (usar ID de carrito, no cliente)
## 3 - Facturar la venta generando el ticket al cliente (usar el id del cliente)

## PRODUCTOS

INSERT INTO products (product_name, product_description, product_category, product_code, product_stock, product_price, product_tax_percent, product_creation_date)
VALUES ('Fernet Branca', 'Fernet Branca 1L', 'Alimento', '000A1', 100, 12000, 21, sysdate());

INSERT INTO products (product_name, product_description, product_category, product_code, product_stock, product_price, product_tax_percent, product_creation_date)
VALUES ('Coca-Cola', 'Coca-Cola 2L', 'Alimento', '000A2', 200, 3000, 21, sysdate());

INSERT INTO products (product_name, product_description, product_category, product_code, product_stock, product_price, product_tax_percent, product_creation_date)
VALUES ('Leche LaSerenisima', 'Leche La Serenísima 1L TetraPack', 'Alimento', '000A3', 50, 1500, 21, sysdate());

INSERT INTO products (product_name, product_description, product_category, product_code, product_stock, product_price, product_tax_percent, product_creation_date)
VALUES ('Nesquik', 'Cacao en polvo 400g', 'Alimento', '000A4', 80, 6500, 21, sysdate());

# JSON para probar el endpoint de products
## POST/ http://localhost:8080/api/products
### body

{ "mProductName": "Fernet Branca" ,"mProductDescription": "Fernet Branca 1L" ,"mProductCategory": "Alimento" ,"mProductCode": "000A1" ,"mProductStock": 100 ,"mProductPrice": 12000 ,"mProductTaxPercent": 21 } 

{ "mProductName": "Coca-Cola" ,"mProductDescription": "Coca-Cola 2L" ,"mProductCategory": "Alimento" ,"mProductCode": "000A2" ,"mProductStock": 200 ,"mProductPrice": 3000 ,"mProductTaxPercent": 21 } 

{ "mProductName": "Leche LaSerenisima" ,"mProductDescription": "Leche La Serenisima 1l TetraPack" ,"mProductCategory": "Alimento" ,"mProductCode": "000A3" ,"mProductStock": 50 ,"mProductPrice": 1500 ,"mProductTaxPercent": 21 } 

{ "mProductName": "Nesqik" ,"mProductDescription": "Cacao en polvo 400g" ,"mProductCategory": "Alimento" ,"mProductCode": "000A4" ,"mProductStock": 80 ,"mProductPrice": 6500 ,"mProductTaxPercent": 21 }

## GET/ http://localhost:8080/api/products/{productId}
Listara el producto {productId}

## GET/ http://localhost:8080/api/products
Listara TODOS los productos

-----------------------------------------------------------

## CLIENTES 
#### -> Se recomienda cargarlos a travez del servicio
#### -> Si se cargan a travez de SQL primero realizar el insert del carrito. 

INSERT INTO carts (cart_id, cart_creation_date)
VALUES (1, sysdate());
INSERT INTO clients (client_name, client_address, client_doc_id, client_cart_id, client_creation_date)
VALUES ('Ricardo Freitas', 'Illia 446, Córdoba Capital, Córdoba', 1111111, 1, sysdate());

INSERT INTO carts (cart_id, cart_creation_date)
VALUES (2, sysdate());
INSERT INTO clients (client_name, client_address, client_doc_id, client_cart_id, client_creation_date)
VALUES ('Luciana Torres', 'Av. Colón 1234, Córdoba Capital, Córdoba', 2222222, 2, sysdate());

INSERT INTO carts (cart_id, cart_creation_date)
VALUES (3, sysdate());
INSERT INTO clients (client_name, client_address, client_doc_id, client_cart_id, client_creation_date)
VALUES ('Martín Gómez', 'Calle Buenos Aires 567, Córdoba Capital, Córdoba', 3333333, 3, sysdate());

INSERT INTO carts (cart_id, cart_creation_date)
VALUES (4, sysdate());
INSERT INTO clients (client_name, client_address, client_doc_id, client_cart_id, client_creation_date)
VALUES ('Paula Rodríguez', 'Bv. San Juan 890, Córdoba Capital, Córdoba', 4444444, 4, sysdate());

# JSON para probar el endpoint de clients
## POST/ http://localhost:8080/api/clients
### body
{
"mClientName": "Ricardo Freitas",
"mClientAddress": "Illia 446, Córdoba Capital, Córdoba",
"mClientDocId": 1111111
}

{
"mClientName": "Luciana Torres",
"mClientAddress": "Av. Colón 1234, Córdoba Capital, Córdoba",
"mClientDocId": 2222222
}

{
"mClientName": "Martín Gómez",
"mClientAddress": "Calle Buenos Aires 567, Córdoba Capital, Córdoba",
"mClientDocId": 3333333
}

{
"mClientName": "Paula Rodríguez",
"mClientAddress": "Bv. San Juan 890, Córdoba Capital, Córdoba",
"mClientDocId": 4444444
}
## GET/ http://localhost:8080/api/clients/{clientId}
Listara el cliente {clientId} con su carrito y todos los items en el

## GET/ http://localhost:8080/api/clients
Listara TODOS los clientes con sus carritos y todos los items en el

------------------------------
# CARRITOS

## PUT http://localhost:8080/api/carts/{cartId}
### body
{
"mCartDetailProduct": 1,
"mCartDetailItemQuantity": 10
}

Agregara al carrito {cartId} el CartDetail con los parametros enviados en el body.

Si agregas una cantidad negativa intentara remover items del carrito en la misma cantidad.

Si agregas una cantidad negativa puede hacerlo y la cantidad es menor o igual a 0, removera el item del carrito.

Si puede hacerlo y el resutlado es major a 0, solo disminuira la cantidad del item sin eliminarlo.
Si el producto no existe devolvera error.

Si el carrito no existe, devolvera error.

Falta agregar la validacion para el caso en que no hay suficiente stock, de todas formas esto se valida al momento de generar el ticket no permitiendo concretar la venta del item e informando la falta de stock.

## GET/ http://localhost:8080/api/carts/{cartId}
Listara el carrito {cartId} con todos sus items e informacion del cliente al que le pertenece

## GET/ http://localhost:8080/api/carts
Listara TODOS los carritos con todos sus items e informacion del cliente al que le pertenece

---------------------------------------------
# TICKETS (VENTAS)

## POST http://localhost:8080/api/tickets/client/{clientId}
Creara el ticket por la venta de los items dentro del carrito del cliente {clientId}

Valida que el cliente exista, devolviendo error si este no existe.

Valida que el carrito no este vacio, devolviendo error si lo estuviera.

Valida que exista suficiente stock del producto para completar la compra, informando si no lo hubiera al crear el ticket. Dentro de este escenario si el unico producto del carrito no tiene suficiente stock para completar la compra, el ticket no se genera informando que no hay suficiente stock del producto.

## GET/ http://localhost:8080/api/tickets/client/{clientId}
Listara todos los tickets del cliente {clientId} 

## GET/ http://localhost:8080/api/tickets
Listara TODOS los tickets

----------------------------------------
