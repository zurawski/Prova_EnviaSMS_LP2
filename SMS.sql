create table CONTATOS (
ID serial primary key,
Numero varchar(10) not null,
Nome varchar(50) not null)


create table GRUPOS (
ID serial not null primary key,
Nome varchar(50))

create table GRUPOS_CONTATOS (
ID_Grupo integer not null,
ID_Contato integer not null,
foreign key (ID_Grupo) references GRUPOS(ID),
foreign key (ID_Contato) references CONTATOS(ID))

create table MODELO_MENSAGEM(
ID serial not null primary key,
Nome_Modelo varchar(40) not null,
Msg varchar(150))

create table MENSAGENS_ENVIADAS(
ID serial not null primary key,
Numero_Enviado varchar(10),
Pessoa_Enviou  varchar(40),
Status_Retorno_Msg integer,
Data_Envio date,
Hora_Envio time)



create table PARAMETROS_SISTEMA(
patch_icons varchar(200) not null)

create table USUARIOS(
id serial not null primary key,
cpf varchar(20),
nome varchar(50),
senha varchar(50),
acesso varchar(20),
email varchar(100))