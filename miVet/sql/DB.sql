create table vet_user(
	dni					    text primary KEY,
	name				    text not null,
	surname				    text not null,
    password			    text NOT null,
	birth_date			    date not null check( extract(year from age(birth_date)) > 18)    
);

create table client(
	id			            serial  primary key,
    phone_number            text not null,
	address         	    text not null,
	email				    text
) inherits(vet_user);

create table employee(
	file				    serial primary key,
	job					    text not null,
	salary				    integer check(salary > 0)
)inherits(vet_user);

create table pet(
	id			            serial primary key,
	name    			    text not null,
	birth_date   		    date,
	"owner" 	    		integer references client(id)
);

create table appointment(
	id			            serial primary key,
	client_id			    serial references client(id),
	pet_id				    serial references pet(id),
	appointment_date			        timestamp ,
	diagnosis				text
);

create view user_login_info as
	 select vu.dni, vu."password", vu.name, e2.job 
	 from vet_user vu left join employee e2 
	 	on vu.dni = e2.dni; 

create or replace function login_user(
	in _dni					text,
	in _password			text
)returns user_login_info as 
$$
	select * from user_login_info where dni = _dni and "password" = _password;
$$ language sql stable strict;
