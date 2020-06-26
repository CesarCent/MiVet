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

/*FUNCTIONS*/
create or replace function get_client_with_password(
	in _dni					text,
	in _password			text
)returns client as 
$$
	select * from client where _dni = dni and _password = password;
$$language sql volatile strict;

create or replace function get_client_by_dni(
	in _dni					text
)returns client as 
$$
	select dni, name, surname, password , birth_date, id, phone_number, address, email from client where dni = _dni;
$$language sql volatile strict;

