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
	appointment_date			    date,
	taken					boolean default false,
	diagnosis				text
);

/*CONSTRUCTORS AND DESTRUCTORS*/
/*CLIENT*/
create or replace function client(
	in _dni                 text,
	in _name                text,
	in _surname             text,
	in _password            text,
	in _birth_date          date,
	in _address    			text,
	in _phone_number		text,
	in _email               text default 'No especifica.'
) returns client as
$$
	insert into client(dni, name, surname, password, birth_date , email , address, phone_number )
	values(_dni, _name, _surname, _password, _birth_date, _email, _address  , _phone_number )
returning *;
$$ language sql volatile;

create or replace function client_destroy(
    in _dni                 text
) returns void as
$$	
    delete from client where _dni = dni;
$$ language sql volatile strict;
/*EMPLOYEE*/
create or replace function employee(
	in _dni                 text,
	in _name                text,
	in _surname             text,
	in _password            text,
	in _birth_date          date,
	in _job                 text,
	in _salary              integer
) returns employee as
$$
	insert into employee(dni, name, surname, password, birth_date , job , salary )
	values(_dni, _name, _surname, _password, _birth_date, _job, _salary )
returning *;
$$ language sql volatile;


create or replace function employee_destroy(
    in _dni                 text
) returns void as
$$
    delete from employee where _dni = dni;
$$ language sql volatile strict;
/*PET*/
create or replace function pet(
    in _name                text,
    in _birth_date          date,
    in _owner               integer
)returns pet as
$$
    insert into pet( name , birth_date, "owner" )
    values( _name, _birth_date, _owner )
   	returning *;
$$language sql volatile;

create or replace function pet_destroy(
    in _id                  integer
)returns void as
$$
    delete from pet where _id = id;
$$language sql volatile strict;
/*appointment*/
create or replace function appointment(
	in _client_id 				integer,
	in _pet_id					integer,
	in _appointment_date				date
)returns appointment as
$$
	insert into appointment( client_id , pet_id , appointment_date )
	values( _client_id , _pet_id , _appointment_date ) 
	returning *;
$$language sql volatile;

create or replace function appointment_destroy(
	in _id                  integer
)returns void as
$$
	delete from appointment where _id = id ;
$$ language sql volatile strict;

/*FUNCTIONS*/
create or replace function get_client_with_password(
	in _dni					text,
	in _password			text
)returns client as 
$$
	select * from client where _dni = dni and _password = password;
$$language sql volatile strict;

create or replace function get_employee_with_password(
	in _dni					text,
	in _password			text
)returns employee as 
$$
	select * from employee e2 where _dni = dni and _password = password;
$$language sql volatile strict;

create or replace function get_client_by_dni(
	in _dni					text
)returns client as 
$$
	select dni, name, surname, password , birth_date, id, phone_number, address, email from client where dni = _dni;
$$language sql volatile strict;

create or replace function get_employee_by_dni(
	in _dni					text
)returns employee as 
$$
	select dni, name, surname, password , birth_date, file, job, salary from employee where dni = _dni;
$$language sql volatile strict;

create or replace function destroy_client_whit_password( 
	in _dni 				text,
	in _pass				text
)returns void as 
$$
	delete from client c2 where _dni = dni and _pass = "password" ;
$$language sql volatile;

create or replace function destroy_employee_whit_password( 
	in _dni 				text,
	in _pass				text
)returns void as 
$$
	delete from employee where _dni = dni and _pass = "password" ;
$$language sql volatile;


create or replace function pet_search_by_owner(
	in _id				integer
)returns setof pet as
$$
	select id, name, birth_date , "owner" from pet where "owner" = _id;
$$ language sql stable strict;

create or replace function appointment_seach_by_client(
	in _client_id				integer
)returns setof appointment as
$$
	select id , client_id , pet_id , appointment_date, taken , diagnosis from appointment where client_id = _client_id ;
$$ language sql stable strict;

create or replace function appointments_for_date(
	in _date					date
)returns bigint as 
$$
	select count( s2.client_id ) from appointment s2 where s2.appointment_date = _date;
$$language sql stable strict;

create or replace function appointment_seach_by_date(
	in _date					date
)returns setof appointment as
$$
	select * from appointment s2 where s2.appointment_date = _date;
$$ language sql stable strict;

create or replace function appointment_seach_by_pet( 
	in _id						integer
)returns setof  appointment as
$$
	select * from appointment s2 where s2.pet_id = _id and s2.taken = true;
$$ language sql stable strict;

create or replace function updateappointment( 
	in _id				integer,
	in _text			text
)returns void as
$$
	update appointment set taken = true, diagnosis = _text where appointment.id = _id;
$$language sql volatile strict;



/*TRIGGERS*/
create or replace function delete_pets()
returns trigger as 
$$
	begin
		delete from pet where pet.owner = ( select id from client where old.dni = dni);
	return old;
	end;
$$language plpgsql;

create trigger delete_pets_without_owner before delete on client for each row execute procedure delete_pets();

create or replace function delete_appointments()
returns trigger as 
$$
	begin 
		delete from appointment where appointment.pet_id = ( select id from pet where old.id = id );
	return old;
	end;
$$language plpgsql;

create trigger delete_appointments_without_pet before delete on pet for each row execute procedure delete_appointments();

/*querys*/
select Employee('123456789', 'admin', 'DB', 'admin', '1-01-1990' , 'ADMIN' , 35000 );

select Employee('39518896', 'Cesar', 'Centurion', 'rococo', '26-03-1996' , 'VET' , 34000 );
select Employee('42516359', 'Mengano', 'Esposito', 'la14bb', '01-03-1986' , 'VET' , 32000 );
select Employee('38514752', 'Vanesa', 'Bustamante', 'brunoMars', '05-02-1995' , 'VET' , 30000 );
/*dni, name, surname, password, birth_date , email , address, phone_number */
select Client('29564789', 'Mario', 'Bros', 'peach', '11-11-1911', 'mario@gmail.com', 'Guido 3939', '1134889474');
select Client('31544791', 'Anahi', 'Alavar', 'abc_4896', '13-11-1990', 'a.danza.moderna@gmail.gov', 'Lobo Marino 56', '1134269414');
/*( name , birth_date, "owner"*/
