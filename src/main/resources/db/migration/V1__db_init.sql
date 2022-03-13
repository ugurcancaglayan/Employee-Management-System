/* Create table */
CREATE SEQUENCE SEQ_MANAGER;
CREATE SEQUENCE SEQ_DEPARTMENT;
CREATE SEQUENCE SEQ_EMPLOYEE;
CREATE SEQUENCE SEQ_ADDRESS;


CREATE TABLE public.manager
(
    id bigint NOT NULL default nextval('SEQ_MANAGER'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    name character varying(255),
    PRIMARY KEY (id)
);

CREATE TABLE public.department
(
    id bigint NOT NULL default nextval('SEQ_DEPARTMENT'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    declaration character varying(255),
    name character varying(255),
    manager_id bigint,
    PRIMARY KEY (id)
);

CREATE TABLE public.employee
(
    id bigint NOT NULL default nextval('SEQ_EMPLOYEE'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    mail character varying(255),
    name character varying(255),
    phone_number character varying(255),
    department_id bigint,
    PRIMARY KEY (id)
);

CREATE TABLE public.address
(
    id bigint NOT NULL default nextval('SEQ_ADDRESS'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    location character varying(255),
    title character varying(255),
    employee_id bigint,
    PRIMARY KEY (id)
);

ALTER TABLE public.address
    ADD FOREIGN KEY (employee_id)
        REFERENCES public.employee (id)
    NOT VALID;


ALTER TABLE public.department
    ADD FOREIGN KEY (manager_id)
        REFERENCES public.manager (id)
    NOT VALID;


ALTER TABLE public.employee
    ADD FOREIGN KEY (department_id)
        REFERENCES public.department (id)
    NOT VALID;
