create table login
(
    email  varchar primary key,
    senha  varchar,
    perfil varchar
);

create table endereco
(
    id       integer primary key AUTO_INCREMENT,
    cep      numeric,
    rua      varchar,
    numero   numeric,
    bairro   varchar,
    detalhes varchar
);

create table ficha
(
    id            integer primary key AUTO_INCREMENT,
    duracao       numeric,
    pontuacao     numeric,
    observacoes   varchar,
    nivel         numeric,
    sensibilidade varchar
);

create table paciente
(
    id            integer primary key AUTO_INCREMENT,
    nome_completo varchar,
    idade         numeric,
    id_ficha      integer references ficha (id)
);

create table terapeuta
(
    id            integer primary key AUTO_INCREMENT ,
    nome_completo varchar,
    idade         numeric,
    crp           numeric,
    telefone      varchar,
    cpf           varchar,
    especialidade varchar,
    formacao      varchar,
    id_endereco   integer references endereco (id),
    id_paciente   integer references paciente (id),
    id_login    varchar references login (email)
);

create table responsavel
(
    id            integer primary key AUTO_INCREMENT,
    nome_completo varchar,
    idade         numeric,
    telefone      varchar,
    cpf           varchar,
    parentesco    varchar,
    id_endereco   integer references endereco (id),
    id_paciente   integer references paciente (id),
    id_login    varchar references login (email)
);