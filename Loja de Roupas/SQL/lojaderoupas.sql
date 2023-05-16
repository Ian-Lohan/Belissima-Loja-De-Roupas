-- Criando Banco de Dados
create database lojaderoupas;

-- Selecionando o Banco de Dados
use lojaderoupas;

-- Criando Tabela de Usuários
create table usuarios(
id_usuario int primary key not null auto_increment,
nome_usuario varchar(50) not null,
login_usuario varchar(50) not null unique,
senha_usuario varchar(15) not null,
cpf_usuario int(11) not null,
endereco_usuario varchar(60) not null,
numero_usuario int(11) not null,
acesso int(1) not null
);

-- Inserindo Dados na Tabela (CREATE)
insert into usuarios(id_usuario, nome_usuario, login_usuario, senha_usuario, cpf_usuario, endereco_usuario, numero_usuario, acesso)
values(1, 'administrador', 'admin', 'belissima', 1, 'Jupi-PE', 0, 2);

-- Criando Tabela de Clientes
create table clientes(
id_cliente int primary key not null auto_increment,
nome_cliente varchar(50) not null,
cpf_cliente int(11) not null,
numero_cliente int(11),
endereco_cliente varchar(100)
);

-- Criando Tabela Produtos
create table produtos(
id_produto int primary key not null auto_increment,
nome_produto varchar(50) not null,
tipo_produto varchar(50) not null,
cor_produto varchar(50) not null,
preco_produto decimal(10,2) not null,
estoque_produto int not null,
detalhe_produto varchar(100)
);

-- Criando Tabela de Vendas
create table vendas(
id_venda int primary key not null auto_increment,
data_venda timestamp default current_timestamp not null,
pagamento_venda varchar(45) not null,
total_venda int not null,
id_cliente int not null,
id_funcionario int not null,
foreign key (id_cliente) references clientes(id_cliente),
foreign key (id_funcionario) references usuarios(id_usuario)
);

-- Criando Tabela de Detalhes de Vendas
create table detalhesvendas(
id_venda int not null,
id_produto int not null,
quantidade int not null,
precounitario dec(10,2) not null,
subtotal decimal(10,2) generated always as (quantidade * precounitario),
foreign key (id_venda) references vendas(id_venda),
foreign key (id_produto) references produtos(id_produto)
);