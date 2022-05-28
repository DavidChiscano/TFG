DROP DATABASE IF EXISTS DAVIDTFG;
CREATE DATABASE IF NOT EXISTS DAVIDTFG;
USE DAVIDTFG;
	 
CREATE TABLE IF NOT EXISTS roles(
	 id_rol bigint not null primary key auto_increment,
	 nombre_rol varchar(20) not null
);
 
CREATE TABLE IF NOT EXISTS usuarios(
	 id_usuario bigint not null primary key auto_increment,
	 nombre varchar(40) not null unique,
	 email varchar(60) not null,
	 passwd varchar(100) not null,
	 fecha_nacimiento varchar(80) not null
);
CREATE TABLE IF NOT EXISTS cuentaslol(
	 id_cuenta bigint not null primary key auto_increment,
	 usuario varchar(50) not null,
	 FOREIGN KEY(id_cuenta) REFERENCES usuarios(id_usuario)
);	 
CREATE TABLE IF NOT EXISTS usuario_rol(
	 id_rol bigint not null, 
	 id_usuario bigint not null,
     primary key(id_rol, id_usuario),
	 FOREIGN KEY (id_rol) REFERENCES roles(id_rol),
     FOREIGN KEY(id_usuario) REFERENCES usuarios(id_usuario)
     );
CREATE TABLE IF NOT EXISTS usuario_cuentas(
	 id_cuenta bigint not null, 
	 id_usuario bigint not null,
     primary key(id_cuenta, id_usuario),
	 FOREIGN KEY (id_cuenta) REFERENCES cuentaslol(id_cuenta),
     FOREIGN KEY(id_usuario) REFERENCES usuarios(id_usuario)
     );