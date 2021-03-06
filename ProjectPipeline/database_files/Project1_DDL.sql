DROP TABLE IF EXISTS empl_mgr;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS empl_role;
DROP TABLE IF EXISTS reimb_request;
DROP TABLE IF EXISTS reimb_req_status;

CREATE TABLE empl_role(
	roleId integer PRIMARY KEY,
	role varchar(32) NOT NULL,
	UNIQUE(role)
);

CREATE TABLE employee(
	employeeId SERIAL PRIMARY KEY,
	username varchar(128) NOT NULL UNIQUE,
	password varchar(128) NOT NULL,
	firstname varchar(64) NOT NULL,
	lastname varchar(64) NOT NULL,
	email	varchar(128) NOT NULL UNIQUE,
	workyears integer NOT NULL,     
	roleId integer REFERENCES empl_role(roleId)
);

CREATE TABLE empl_mgr(
	managerId integer REFERENCES employee(employeeId),
	employeeId integer REFERENCES employee(employeeId),
	PRIMARY KEY(managerId,employeeId) 
);

CREATE TABLE reimb_req_status(
	statusId integer PRIMARY KEY,
	status varchar(32) NOT NULL UNIQUE
);

CREATE TABLE reimb_request(
	reimbReqId serial PRIMARY KEY,
	employeeId integer REFERENCES employee(employeeId),
	location varchar(128) NOT NULL,
	requestDate date,
	approvalDate date,
	cost double precision NOT NULL,
	description text,
	receipt bytea,
	statusId integer REFERENCES reimb_req_status(statusId)
);

