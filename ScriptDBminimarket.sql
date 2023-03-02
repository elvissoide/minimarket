DROP DATABASE minimarketearf;
CREATE DATABASE minimarketearf;
USE minimarketearf;
DROP TABLE productos;
DROP TABLE proveedores;

CREATE TABLE productos(
	CodProd VARCHAR(5) PRIMARY KEY,
	NomProd VARCHAR(45) NOT NULL,
	DesProd VARCHAR(80) NOT NULL,
	StoProd INT NOT NULL,
    ValVentProd DECIMAL(5,2) NOT NULL,
    IvaProd TINYINT NOT NULL,
    RucProvProd VARCHAR(15) NOT NULL
);
INSERT INTO proveedores VALUES
('1a','Detergente deja 5000g','Detergente en polvo, fuerza limon, 5000g',200,9.43,1,'123456'),
('2a','Enjuague bucal 500ml','Enjuague bucal, supermaxi, 500ml',300,3.95,1,'123456'),
('3a','Papel rollo x4','Papel higienico acolchamax, 4 rollos de 23 metros',150,1.79,0,'123456');
SELECT * FROM productos;

-- PROVEEDORES NO VA
CREATE TABLE proveedores(
	RucProv VARCHAR(15) PRIMARY KEY,
	NomProv VARCHAR(45) NOT NULL,
	DirProv VARCHAR(80) ,
	TelProv VARCHAR(12) NOT NULL,
    CorProv VARCHAR(20)
);
INSERT INTO proveedores VALUES
('123456','La favorita','Av. General Enríquez vía Cotogchoa','328674','sugerencias@favorita.com');
SELECT * FROM proveedores;

CREATE TABLE detallesFactura(
	CodDet VARCHAR(5) PRIMARY KEY,
	NumFactDet VARCHAR(5) NOT NULL,
	CodProdDet VARCHAR(5) NOT NULL,
	CanDet INT NOT NULL,
    ValVenDet DECIMAL(6,2) NOT NULL,
    TotDet DECIMAL(6,2) 
);
INSERT INTO detallesFactura VALUES
('');
SELECT * FROM detallesFactura;

CREATE TABLE cabecerasFactura(
	NumFact VARCHAR(5) PRIMARY KEY,
    IdCajFact VARCHAR(10) NOT NULL,
	IdCliFact VARCHAR(10) NOT NULL,
	FecEmiFact DATE NOT NULL,
	SubFact INT NOT NULL,
    IvaFact DECIMAL(6,2),
    TotFact DECIMAL(6,2) 
);
INSERT INTO cabecerasFactura VALUES
('');
SELECT * FROM cabecerasFactura;

DROP TABLE cajeros;
CREATE TABLE cajeros(
	IdCaj INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NomCaj VARCHAR(20) NOT NULL,
	ApeCaj VARCHAR(20) NOT NULL,
    DirCaj VARCHAR(80) ,
    CorCaj VARCHAR(30) NOT NULL,
    TelCaj VARCHAR(12) NOT NULL,
	UsuCaj VARCHAR(15) NOT NULL,
	ConCaj VARCHAR(20) NOT NULL
) AUTO_INCREMENT=1;
INSERT INTO cajeros (NomCaj, ApeCaj, DirCaj, CorCaj, TelCaj, UsuCaj, ConCaj)
VALUES
('Pablo','Arroyo','Quito, Miravalle 1, Carlos Dousdebes','pablo.arroyo@market.com','0998151451','pablo1451','pa15mrkt'),
('Maria','Fernandez','Quito, Centro Historico','maria.fernandez@market.com','0987127781','maria7781','mar18mrkt'),
('Michael','Ramirez','Quito, La Tola','michael.ramirez@market.com','0983451562','michael1562','mic26mrkt');
SELECT * FROM cajeros;

CREATE TABLE administradores(
	IdAdm VARCHAR(10) PRIMARY KEY,
    NomAdm VARCHAR(20) NOT NULL,
	ApeAdm VARCHAR(20) NOT NULL,
    DirAdm VARCHAR(80) ,
    CorAdm VARCHAR(30) NOT NULL,
    TelAdm VARCHAR(12) NOT NULL,
	UsuAdm VARCHAR(15) NOT NULL,
	ConAdm VARCHAR(20) NOT NULL
);
INSERT INTO administradores VALUES
('');
SELECT * FROM administradores;

-- NO ES NECESARIO CLIENTES
CREATE TABLE clientes(
	IdCli VARCHAR(10) PRIMARY KEY,
    NomCli VARCHAR(15) NOT NULL,
	ApeCli VARCHAR(10) NOT NULL,
    DirCli VARCHAR(10) NOT NULL,
    CorCli VARCHAR(10) ,
	TelCli VARCHAR(15) NOT NULL
);
INSERT INTO clientes VALUES
('');
SELECT * FROM clientes;