create database bdproy1

GO

use bdproy1

GO

create login dbaproy1
	with password = '123',
	default_database = bdproy1
GO

create user dbaproy1 
	for login dbaproy1
	with default_schema = proy1

GO

create login usproy1
	with password = '123',
	default_database = bdproy1
 
GO

create user usproy1 
	for login usproy1
	with default_schema = proy1

GO

grant create table, select, insert, update, delete to dbaproy1

GO

grant select to usproy1

GO

create schema proy1 authorization dbaproy1

GO