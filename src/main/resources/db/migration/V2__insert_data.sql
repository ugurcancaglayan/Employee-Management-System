/* Insert data to table */

insert into manager (creation_date, last_modified_date, name)
            values (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Can');

insert into department (creation_date, last_modified_date, declaration, name, manager_id)
            values (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Developers - South Department', 'IT', 1);

insert into address (creation_date, last_modified_date, location, title, employee_id)
            values (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Istanbul', 'Work', 1);

insert into employee (creation_date, last_modified_date, mail, name, phone_number, department_id)
            values (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ugurcancaglayan@outlook.com', 'Uğurcan', '0555 555 5555', 1);
