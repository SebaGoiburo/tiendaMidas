# Tienda Midas
## Gestiona tus productos y muéstraselos a tus clientes para que realicen su compra
#### ApiRest desarrollada con Java 17 en SpringBoot 3 con Spring Security 6 y manejo de datos a través de JPA y MySql.
#### Para la seguridad implementé una configuración Json Web Token.
#### Para documentar utilicé Swagger con la dependencia correspondiente por no ser compatible SpringFox con SpringBoot3

# Endpoints
## Auth (/auth)
###### Controlador de acceso a la api, únicas url públicas sin credenciales de acceso.
### POST/Login
### POST/Register

## Products   (/product)
 Endpoints relacionados al manejo de productos
### /listProducts
 Listar todos los productos cargados a la base de datos
### /createProduct
 Crear y persistir un producto nuevo en la base de datos
### /update/{id}
 Modificar algún product existente en la base de datos mediante su id
### /delete/{id}
 Borrar algún producto existente en la base de datos mediante su id
### {idProduct}/addToCart/{idUser}
 Agregar el producto referenciado por su Id, al carrito de compras de determinado usuario mediante el ID del mismo.

 ## Users Controllers
 Controladores de acceso interno a roles ADMIN
 ### /listUsers
  Listado de usuarios registrados en el sistema
 ### /deleteShoppingCart/{id}
  Vaciado del carrito de compras del usuario referido por Id

 ## Purchases (/purchase)
 Controladores para concretar y gestionar las ventas concretadas
 ### /buy/{id}
 Comprar el listado de productos que el cliente referenciado por ID tiene en su carrito de compras que es una Lista de Productos
 ### /listPurchases
 Listar todas las compras realizadas por todos los clientes del sistema

 ## Image (/image)
 Controladores para renderizar las imagenes persistidas en la base de datos, tanto para el usuario como para los productos mediante sus respectivos ID
 ### /userImage/{id}
 ### /productImage/{id}


