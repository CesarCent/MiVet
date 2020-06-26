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