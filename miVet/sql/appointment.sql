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
