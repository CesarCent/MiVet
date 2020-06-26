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

/*FUNCTIONS*/

create or replace function get_employee_with_password(
	in _dni					text,
	in _password			text
)returns employee as 
$$
	select * from employee e2 where _dni = dni and _password = password;
$$language sql volatile strict;

select vu.dni, vu.name, e2.job from vet_user vu left join employee e2 on vu.dni = e2.dni where vu.dni = '0' and vu."password" = 'river' ; 
