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
    ValVentProd DECIMAL(5,2) NOT NULL,
    IvaProd TINYINT NOT NULL,
    RucProvProd VARCHAR(15) NOT NULL
) AUTO_INCREMENT = 1;
INSERT INTO productos (NomProd, DesProd, StoProd, ValVentProd, IvaProd, RucProvProd)
VALUES
('Detergente deja 5000g','Detergente en polvo, fuerza limon, 5000g',200,9.43,1,'La Favorita'),
('Enjuague bucal 500ml','Enjuague bucal, supermaxi, 500ml',300,3.95,1,'Supermaxi'),
('Papel rollo x4','Papel higienico acolchamax, 4 rollos de 23 metros',150,1.79,0,'Santa Maria');
-- SELECT * FROM productos;

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
	NomCliFact VARCHAR(10) NOT NULL,
	FecEmiFact DATE NOT NULL,
	SubFact DECIMAL(6,2),
    IvaFact DECIMAL(6,2),
    TotFact DECIMAL(6,2) 
);
-- SELECT * FROM cabecerasFacturas;

-- Creacion de detalles de factura
CREATE TABLE detallesFacturas(
	CodDet INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NumFactDet VARCHAR(5) NOT NULL REFERENCES cabecerasFacturas(NumFact),
	CodProdDet VARCHAR(5) NOT NULL REFERENCES productos(CodProd),
	CanDet INT NOT NULL,
    ValVenDet DECIMAL(5,2) NOT NULL REFERENCES productos(ValVentProd),
    TotDet DECIMAL(6,2) 
);
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
