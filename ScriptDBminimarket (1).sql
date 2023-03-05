DROP DATABASE minimarketearf;
-- DROP TABLE productos;
-- DROP TABLE proveedores;
CREATE DATABASE minimarketearf;
USE minimarketearf;

-- Creacion de la tabla productos e ingreso de registros
CREATE TABLE productos(
	CodProd INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NomProd VARCHAR(45) NOT NULL,
	DesProd VARCHAR(80) NOT NULL,
	StoProd INT NOT NULL,
    ValVentProd DECIMAL(10,2) NOT NULL,
    IvaProd TINYINT NOT NULL,
    RucProvProd VARCHAR(30) NOT NULL
) AUTO_INCREMENT = 1;
INSERT INTO productos (NomProd, DesProd, StoProd, ValVentProd, IvaProd, RucProvProd)
VALUES
('Detergente deja 5000g','Detergente en polvo, fuerza limon, 5000g',200,9.43,1,'La Favorita'),
('Detergente deja 10000g','Detergente en polvo, fuerza limon, 10000g',300,15.43,1,'La Favorita'),
('Detergente deja 100g','Detergente en polvo, fuerza limon, 100g',300,1.47,1,'La Favorita'),
('Enjuague bucal 500ml','Enjuague bucal, supermaxi, 500ml',300,3.95,1,'Supermaxi'),
('Enjuague bucal 250ml','Enjuague bucal, supermaxi, 250ml',400,1.95,1,'Supermaxi'),
('Enjuague bucal 1000ml','Enjuague bucal, supermaxi, 1000ml',500,7.95,1,'Supermaxi'),
('Papel rollo x4','Papel higienico acolchamax, 4 rollos de 23 metros',150,4.79,0,'Santa Maria'),
('Papel rollo x12','Papel higienico acolchamax, 12 rollos de 23 metros',350,6.79,0,'Santa Maria'),
('Papel rollo x20','Papel higienico acolchamax, 20 rollos de 23 metros',250,15.79,0,'Santa Maria');
-- SELECT * FROM productos;
-- SELECT * FROM productos WHERE NomProd LIKE '%detergente%';
-- SELECT CodProd, NomProd, DesProd, StoProd, ValVentProd FROM productos;
-- SELECT CodProd, DesProd, ValVentProd FROM productos WHERE CodProd = 1;
-- SELECT MAX(CodProd) FROM productos;

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
('Domenica','Lamar','Quito, Cumbaya, parque central','domenica.lamar@market.com','0992344242','dome4242','dom24mrkt'),
('Michael','Ramirez','Quito, La Tola','michael.ramirez@market.com','0983451562','michael1562','mic26mrkt');
-- SELECT * FROM cajeros;

-- Creacion de tabla cabeceras de facturas
CREATE TABLE cabecerasFacturas(
	NumFact INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    IdCajFact VARCHAR(10) NOT NULL REFERENCES cajeros(IdCaj),
	NomCliFact VARCHAR(60) NOT NULL,
	FecEmiFact DATE NOT NULL,
	SubFact DECIMAL(10,2),
    IvaFact DECIMAL(10,2),
    TotFact DECIMAL(10,2) 
);
INSERT INTO cabecerasFacturas (IdCajFact, NomCliFact, FecEmiFact, SubFact, IvaFact, TotFact)
VALUES
(1,'Juan Perez','2020-02-15',NULL,NULL,NULL),
(1,'Steven Sanchez','2020-03-17',NULL,NULL,NULL),
(1,'Ronald Guaman','2020-02-10',NULL,NULL,NULL),
(2,'Cristian Cevallos','2020-03-15',NULL,NULL,NULL);
-- SELECT * FROM cabecerasFacturas;
-- SELECT MAX(NumFact) FROM cabecerasFacturas;

-- Creacion de detalles de factura
CREATE TABLE detallesFacturas(
	CodDet INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NumFactDet INT NOT NULL REFERENCES cabecerasFacturas(NumFact),
	CodProdDet INT NOT NULL REFERENCES productos(CodProd),
	CanDet INT NOT NULL,
    ValVenDet DECIMAL(10,2) NOT NULL REFERENCES productos(ValVentProd),
    TotDet DECIMAL(10,2) 
);
INSERT INTO detallesFacturas (NumFactDet, CodProdDet, CanDet, ValVenDet, TotDet)
VALUES
(1,1,1,9.43,9.43),
(1,2,1,15.43,15.43),
(1,3,1,1.47,1.47),
(2,4,3,3.95,11.85);
-- SELECT * FROM detallesFacturas;

CREATE TABLE administradores(
	IdAdm INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NomAdm VARCHAR(20) NOT NULL,
	ApeAdm VARCHAR(20) NOT NULL,
    DirAdm VARCHAR(80) ,
    CorAdm VARCHAR(30) NOT NULL,
    TelAdm VARCHAR(12) NOT NULL,
	UsuAdm VARCHAR(15) NOT NULL,
	ConAdm VARCHAR(20) NOT NULL
);
INSERT INTO administradores (NomAdm, ApeAdm, DirAdm, CorAdm, TelAdm, UsuAdm, ConAdm)
VALUES
('Jesus','Colcha','Quito, Tumbaco','jesus.colcha@market.com','0992387466','jesus7466','je66mrkt'),
('Cristhian','Gomez','Quito, Pifo','cristhian.gomez@market.com','0985353412','cris3411','cr21mrkt');
-- SELECT * FROM administradores;
