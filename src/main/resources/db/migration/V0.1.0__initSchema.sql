CREATE TABLE Customer (
	id                 SERIAL       NOT NULL,
	timestamp_creation TIMESTAMP    NOT NULL,
	timestamp_deletion TIMESTAMP,
	version            BIGINT       NOT NULL,
	first_name         VARCHAR(255) NOT NULL,
	last_name          VARCHAR(255) NOT NULL,
	address            VARCHAR(255) NOT NULL,
	city               VARCHAR(255) NOT NULL,
	user_creation_id   UUID         NOT NULL,
	user_deletion_id   UUID,
	PRIMARY KEY (id)
);

CREATE TABLE Customer_AUD (
	id                 INTEGER       NOT NULL,
	REV                INTEGER      NOT NULL,
	REVTYPE            SMALLINT,
	timestamp_creation TIMESTAMP    NOT NULL,
	timestamp_deletion TIMESTAMP,
	version            BIGINT       NOT NULL,
	first_name         VARCHAR(255) NOT NULL,
	last_name          VARCHAR(255) NOT NULL,
	address            VARCHAR(255) NOT NULL,
	city               VARCHAR(255) NOT NULL,
	user_creation_id   UUID         NOT NULL,
	user_deletion_id   UUID,
	PRIMARY KEY (id, REV)
);

CREATE TABLE Shipping (
	id                 SERIAL       NOT NULL,
	timestamp_creation TIMESTAMP    NOT NULL,
	timestamp_deletion TIMESTAMP,
	version            BIGINT       NOT NULL,
	customer_id        INT          NOT NULL,
	state              VARCHAR(50) NOT NULL,
	send_date          DATE         NOT NULL,
	arrive_date        DATE         NOT NULL,
	priority           INT          NOT NULL,
	user_creation_id   UUID         NOT NULL,
	user_deletion_id   UUID,
	PRIMARY KEY (id)
);

CREATE TABLE Shipping_AUD (
	id                 INTEGER       NOT NULL,
	REV                INTEGER      NOT NULL,
	REVTYPE            SMALLINT,
	timestamp_creation TIMESTAMP    NOT NULL,
	timestamp_deletion TIMESTAMP,
	version            BIGINT       NOT NULL,
	customer_id        INT          NOT NULL,
	state              VARCHAR(255) NOT NULL,
	send_date          DATE         NOT NULL,
	arrive_date        DATE         NOT NULL,
	priority           INT          NOT NULL,
	user_creation_id   UUID         NOT NULL,
	user_deletion_id   UUID,
	PRIMARY KEY (id, REV)
);

CREATE TABLE Shipping_Item (
	id                 SERIAL    NOT NULL,
	timestamp_creation TIMESTAMP NOT NULL,
	timestamp_deletion TIMESTAMP,
	version            BIGINT    NOT NULL,
	shipping_id        INT       NOT NULL,
	product_id         INT       NOT NULL,
	product_count      INT       NOT NULL,
	user_creation_id   UUID      NOT NULL,
	user_deletion_id   UUID,
	PRIMARY KEY (id)
);

CREATE TABLE Shipping_Item_AUD (
	id                 INTEGER    NOT NULL,
	REV                INTEGER   NOT NULL,
	REVTYPE            SMALLINT,
	timestamp_creation TIMESTAMP NOT NULL,
	timestamp_deletion TIMESTAMP,
	version            BIGINT    NOT NULL,
	shipping_id        INT       NOT NULL,
	product_id         INT       NOT NULL,
	product_count      INT       NOT NULL,
	user_creation_id   UUID      NOT NULL,
	user_deletion_id   UUID,
	PRIMARY KEY (id, REV)
);

CREATE TABLE Product (
	id                 SERIAL           NOT NULL,
	timestamp_creation TIMESTAMP        NOT NULL,
	timestamp_deletion TIMESTAMP,
	version            BIGINT           NOT NULL,
	description        VARCHAR(255)     NOT NULL,
	weight             DOUBLE PRECISION NOT NULL,
	user_creation_id   UUID             NOT NULL,
	user_deletion_id   UUID,
	PRIMARY KEY (id)
);

CREATE TABLE Product_AUD (
	id                 INTEGER           NOT NULL,
	REV                INTEGER          NOT NULL,
	REVTYPE            SMALLINT,
	timestamp_creation TIMESTAMP        NOT NULL,
	timestamp_deletion TIMESTAMP,
	version            BIGINT           NOT NULL,
	description        VARCHAR(255)     NOT NULL,
	weight             DOUBLE PRECISION NOT NULL,
	user_creation_id   UUID             NOT NULL,
	user_deletion_id   UUID,
	PRIMARY KEY (id, REV)
);

CREATE TABLE REVINFO (
	REV      SERIAL PRIMARY KEY,
	REVTSTMP BIGINT
);

CREATE TABLE Auth_User (
	id       UUID         NOT NULL PRIMARY KEY,
	username VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	enabled  BOOLEAN DEFAULT TRUE,
	role     VARCHAR(36)  NOT NULL
);

ALTER TABLE Shipping
	ADD CONSTRAINT FK_Customer_id FOREIGN KEY (customer_id) REFERENCES Customer(id);

ALTER TABLE Shipping_Item
	ADD CONSTRAINT FK_Shipping_id FOREIGN KEY (shipping_id) REFERENCES Shipping(id);

ALTER TABLE Shipping_Item
	ADD CONSTRAINT FK_Product_id FOREIGN KEY (product_id) REFERENCES Product(id);

ALTER TABLE Customer_AUD
	ADD CONSTRAINT FK_Customer_AUD_REV FOREIGN KEY (REV) REFERENCES REVINFO(REV);

ALTER TABLE Shipping_AUD
	ADD CONSTRAINT FK_Shipping_AUD_REV FOREIGN KEY (REV) REFERENCES REVINFO(REV);

ALTER TABLE Shipping_Item_AUD
	ADD CONSTRAINT FK_ShippingItem_AUD_REV FOREIGN KEY (REV) REFERENCES REVINFO(REV);

ALTER TABLE Product_AUD
	ADD CONSTRAINT FK_Product_AUD_REV FOREIGN KEY (REV) REFERENCES REVINFO(REV);


/*

 -- Undo script for dropping tables and constraints

-- Drop foreign key constraints from audit tables
ALTER TABLE Product_AUD DROP CONSTRAINT FK_Product_AUD_REV;
ALTER TABLE Shipping_Item_AUD DROP CONSTRAINT FK_Shipping_Item_AUD_REV;
ALTER TABLE Shipping_AUD DROP CONSTRAINT FK_Shipping_AUD_REV;
ALTER TABLE Customer_AUD DROP CONSTRAINT FK_Customer_AUD_REV;

-- Drop foreign key constraints from ShippingItem and Shipping tables
ALTER TABLE Shipping_Item DROP CONSTRAINT FK_Product_id;
ALTER TABLE Shipping_Item DROP CONSTRAINT FK_Shipping_id;
ALTER TABLE Shipping DROP CONSTRAINT FK_Customer_id;

-- Drop the created tables
DROP TABLE IF EXISTS Auth_User;
DROP TABLE IF EXISTS REVINFO;
DROP TABLE IF EXISTS Product_AUD;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Shipping_Item_AUD;
DROP TABLE IF EXISTS Shipping_Item;
DROP TABLE IF EXISTS Shipping_AUD;
DROP TABLE IF EXISTS Shipping;
DROP TABLE IF EXISTS Customer_AUD;
DROP TABLE IF EXISTS Customer;

-- Delete Flyway migration
DELETE FROM flyway_schema_history
WHERE version = 'V0.1.0__initSchema.sql';


 */