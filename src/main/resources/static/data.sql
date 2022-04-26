INSERT INTO roles(id_rol, nombre_rol) VALUES('1','USER');
INSERT INTO roles(id_rol, nombre_rol) VALUES('2','ADMIN');

INSERT INTO usuarios(nombre, email, passwd, fecha_nacimiento)VALUES('david','david@gmail.com','$2a$10$uZWHQAi8GnVPyx4CG0NOeOy3rssrRKmNhOMdDmxGmze515/Kj8aCS','10-02-2022');

INSERT INTO usuario_rol(id_rol, id_usuario) VALUES(2,1);

INSERT INTO cuentaslol(id_usuario, usuario) VALUES(1,'0kame');