/*
	Fecha de creación: 17/08/2025
*/

drop database if exists db_becago;

create database db_becago;

use db_becago;

create table Administrador(
	idAdmin int not null auto_increment,
    nombreAdmin varchar(100) not null,
    apellidoAdmin varchar(100) not null,
    telefono varchar(20) not null,
    correoAdmin varchar(150) unique not null,
    passwordAdmin varchar(150) not null,
    nombreDepartamento varchar(150) not null,
    primary key PK_idAdmin(idAdmin)
);

create table Estudiante(
	idEstudiante int not null auto_increment,
    nombreEstudiante varchar(150) not null,
    apellidoEstudiante varchar(150) not null,
    telefono varchar(20) not null,
    correoEstudiante varchar(100) unique not null,     
    passwordEstudiante varchar(150) not null,
    carrera varchar(150) not null,
    horasAsignadas int not null,
    horasCumplidas int default 0,
    primary key PK_idEstudiante(idEstudiante)
);

create table Actividad(
	idActividad int not null auto_increment,
    nombreActividad varchar(100) not null,
    descripcion text not null,
    fechaActividad datetime not null,
    ubicacion varchar(100) not null,
    horasDadas decimal(5,1) not null,
    cuposDisponibles int not null,
    idAdmin int not null,
    primary key PK_idActividad(idActividad),
    constraint FK_Actividad_Admin foreign key(idAdmin)
		references Administrador(idAdmin) on delete cascade
);

create table Inscripcion(
	idInscripcion int not null auto_increment,
    idActividad int not null,
    idEstudiante int not null,
    fechaInscripcion datetime not null,
    estado varchar(100) not null,
    primary key PK_idInscripcion(idInscripcion),
    constraint FK_Inscripcion_Actividad foreign key(idActividad)
		references Actividad(idActividad) on delete cascade,
	constraint FK_Inscripcion_Estudiante foreign key(idEstudiante)
		references Estudiante(idEstudiante)
);

create table Notificacion(
	idNotificacion int not null auto_increment,
    idEstudiante int not null,
    idActividad int not null,
    mensaje text not null,
    fechaEnvio datetime not null,
    primary key PK_idNotificacion(idNotificacion),
    constraint FK_Notificacion_Estudiante foreign key(idEstudiante)
		references Estudiante(idEstudiante),
	constraint FK_Notificacion_Actividad foreign key(idActividad)
		references Actividad(idActividad)
);

select * from Administrador;
delete from Administrador where idAdmin=1;

select * from Estudiante;
delete from Estudiante where idEstudiante=1;


















