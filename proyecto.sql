create database libreria;
use libreria;

create table Categoria(
codCategoria int not null primary key auto_increment,
NombreCategoria varchar(40)
);

select * from Categoria;
create table Producto(
CodProducto varchar(8) not null primary key,
NombreProduct varchar(50),
PrecioCompra decimal(10,2),
Ganancia decimal(10,2),
PrecioVenta decimal(10,2),
Cantidad int,
Imagen MEDIUMBLOB,
descripcion text,
codCategoria int,
foreign key (codCategoria) references Categoria (codCategoria)
);

create table TipoUsuario(
CodTipoUsua int not null primary key auto_increment,
Tipo varchar(40)
);

insert into TipoUsuario values (1,'Administrador');
insert into TipoUsuario values(2,'Cliente');

Create table Usuario(
CodUsuario varchar(8) primary key not null,
Nombre varchar(50),
Apellido varchar(50),
Telefono varchar(9),
CorreoElectonico varchar(100),
Contraseña varchar(100),
Direccion varchar(200),
Estado bit(1),
CodTipoUsua int,
Token varchar(50),
foreign key (CodTipoUsua) references TipoUsuario(CodTipoUsua)
);
insert into Usuario values ("USU001","Marisol","De Arias","4598-7895","libreriayahveh@gmail.com","admin","San Martin",1,1,"");


select * from Promociones;


create table Promociones(
CodPromocion varchar(8) not null primary key,
CodProducto varchar(8),
fechaInicio date,
fechaFinal date,
PrecioPromocion decimal(10,2),
foreign key (CodProducto) references Producto(CodProducto)
);

create table Venta(
CodVenta varchar(8) not null primary key,
CodUsuario varchar(8),
FechaVenta date,
Monto double,
Estado varchar(50),
foreign key (CodUsuario) references Usuario(CodUsuario)
);

create table DetalleVenta(
iddetalle int not null primary key auto_increment,
CodProducto varchar(8),
CodVenta varchar(8),
Cantidad int,
total double,
foreign key (CodProducto) references Producto(CodProducto),
foreign key (CodVenta) references Venta(CodVenta)
);

create table Compra(
CodCompra varchar(8) not null primary key,
FechaCompra date,
Monto double
);

create table DetalleCompra(
iddetalleCom int not null primary key auto_increment,
CodCompra varchar(8),
CodProducto varchar(8),
Cantidad int,
Total double,
foreign key (CodProducto) references Producto(CodProducto),
foreign key (CodCompra) references Compra(CodCompra)
);



