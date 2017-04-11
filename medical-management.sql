--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: foster_homes; Type: TABLE; Schema: public; Owner: t1k1
--

CREATE TABLE foster_homes (
    foster_home_id integer NOT NULL,
    user_id integer NOT NULL,
    facility_name character varying NOT NULL,
    primary_first character varying NOT NULL,
    primary_last character varying NOT NULL,
    address character varying NOT NULL,
    city character varying NOT NULL,
    state character varying NOT NULL,
    zip integer NOT NULL,
    telephone character varying NOT NULL
);


ALTER TABLE foster_homes OWNER TO t1k1;

--
-- Name: foster_homes_foster_home_id_seq; Type: SEQUENCE; Schema: public; Owner: t1k1
--

CREATE SEQUENCE foster_homes_foster_home_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE foster_homes_foster_home_id_seq OWNER TO t1k1;

--
-- Name: foster_homes_foster_home_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: t1k1
--

ALTER SEQUENCE foster_homes_foster_home_id_seq OWNED BY foster_homes.foster_home_id;


--
-- Name: guardians; Type: TABLE; Schema: public; Owner: t1k1
--

CREATE TABLE guardians (
    guardian_id integer NOT NULL,
    patient_id integer NOT NULL,
    first_name character varying NOT NULL,
    last_name character varying NOT NULL,
    relationship character varying NOT NULL,
    address character varying NOT NULL,
    city character varying NOT NULL,
    state character varying NOT NULL,
    zip integer NOT NULL,
    telephone character varying NOT NULL
);


ALTER TABLE guardians OWNER TO t1k1;

--
-- Name: guardians_guardian_id_seq; Type: SEQUENCE; Schema: public; Owner: t1k1
--

CREATE SEQUENCE guardians_guardian_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE guardians_guardian_id_seq OWNER TO t1k1;

--
-- Name: guardians_guardian_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: t1k1
--

ALTER SEQUENCE guardians_guardian_id_seq OWNED BY guardians.guardian_id;


--
-- Name: insurance; Type: TABLE; Schema: public; Owner: t1k1
--

CREATE TABLE insurance (
    insurance_id integer NOT NULL,
    patient_id integer NOT NULL,
    insurance_provider character varying NOT NULL,
    insurance_policy character varying NOT NULL,
    va_policy character varying NOT NULL,
    medicare_policy character varying NOT NULL,
    medicaid_policy character varying NOT NULL
);


ALTER TABLE insurance OWNER TO t1k1;

--
-- Name: insurance_insurance_id_seq; Type: SEQUENCE; Schema: public; Owner: t1k1
--

CREATE SEQUENCE insurance_insurance_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE insurance_insurance_id_seq OWNER TO t1k1;

--
-- Name: insurance_insurance_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: t1k1
--

ALTER SEQUENCE insurance_insurance_id_seq OWNED BY insurance.insurance_id;


--
-- Name: med_histories; Type: TABLE; Schema: public; Owner: t1k1
--

CREATE TABLE med_histories (
    med_history_id integer NOT NULL,
    patient_id integer NOT NULL,
    type character varying NOT NULL,
    name character varying NOT NULL,
    date date NOT NULL,
    medications character varying NOT NULL,
    current boolean NOT NULL
);


ALTER TABLE med_histories OWNER TO t1k1;

--
-- Name: med_histories_med_history_id_seq; Type: SEQUENCE; Schema: public; Owner: t1k1
--

CREATE SEQUENCE med_histories_med_history_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE med_histories_med_history_id_seq OWNER TO t1k1;

--
-- Name: med_histories_med_history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: t1k1
--

ALTER SEQUENCE med_histories_med_history_id_seq OWNED BY med_histories.med_history_id;


--
-- Name: medications; Type: TABLE; Schema: public; Owner: t1k1
--

CREATE TABLE medications (
    medication_id integer NOT NULL,
    patient_id integer NOT NULL,
    name character varying NOT NULL,
    dosage character varying NOT NULL,
    frequency character varying NOT NULL
);


ALTER TABLE medications OWNER TO t1k1;

--
-- Name: medications_medication_id_seq; Type: SEQUENCE; Schema: public; Owner: t1k1
--

CREATE SEQUENCE medications_medication_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE medications_medication_id_seq OWNER TO t1k1;

--
-- Name: medications_medication_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: t1k1
--

ALTER SEQUENCE medications_medication_id_seq OWNED BY medications.medication_id;


--
-- Name: patients; Type: TABLE; Schema: public; Owner: t1k1
--

CREATE TABLE patients (
    patient_id integer NOT NULL,
    foster_home_id integer NOT NULL,
    first_name character varying NOT NULL,
    last_name character varying NOT NULL,
    admit_date date NOT NULL,
    telephone character varying NOT NULL,
    ssid character varying NOT NULL,
    sex character varying NOT NULL,
    birth_date date NOT NULL,
    birth_place character varying NOT NULL,
    faith character varying NOT NULL,
    hobbies character varying NOT NULL,
    preferred_hospital character varying NOT NULL,
    primary_care_name character varying NOT NULL,
    primary_phone character varying NOT NULL
);


ALTER TABLE patients OWNER TO t1k1;

--
-- Name: patients_patient_id_seq; Type: SEQUENCE; Schema: public; Owner: t1k1
--

CREATE SEQUENCE patients_patient_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE patients_patient_id_seq OWNER TO t1k1;

--
-- Name: patients_patient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: t1k1
--

ALTER SEQUENCE patients_patient_id_seq OWNED BY patients.patient_id;


--
-- Name: tasks; Type: TABLE; Schema: public; Owner: t1k1
--

CREATE TABLE tasks (
    task_id integer NOT NULL,
    foster_id integer NOT NULL,
    task_name character varying NOT NULL,
    type character varying NOT NULL,
    recurring boolean NOT NULL,
    frequency interval NOT NULL,
    "time" time without time zone NOT NULL,
    date date NOT NULL,
    completed boolean NOT NULL
);


ALTER TABLE tasks OWNER TO t1k1;

--
-- Name: tasks_task_id_seq; Type: SEQUENCE; Schema: public; Owner: t1k1
--

CREATE SEQUENCE tasks_task_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tasks_task_id_seq OWNER TO t1k1;

--
-- Name: tasks_task_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: t1k1
--

ALTER SEQUENCE tasks_task_id_seq OWNED BY tasks.task_id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: t1k1
--

CREATE TABLE users (
    user_id integer NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL
);


ALTER TABLE users OWNER TO t1k1;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: t1k1
--

CREATE SEQUENCE users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_user_id_seq OWNER TO t1k1;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: t1k1
--

ALTER SEQUENCE users_user_id_seq OWNED BY users.user_id;


--
-- Name: foster_homes foster_home_id; Type: DEFAULT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY foster_homes ALTER COLUMN foster_home_id SET DEFAULT nextval('foster_homes_foster_home_id_seq'::regclass);


--
-- Name: guardians guardian_id; Type: DEFAULT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY guardians ALTER COLUMN guardian_id SET DEFAULT nextval('guardians_guardian_id_seq'::regclass);


--
-- Name: insurance insurance_id; Type: DEFAULT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY insurance ALTER COLUMN insurance_id SET DEFAULT nextval('insurance_insurance_id_seq'::regclass);


--
-- Name: med_histories med_history_id; Type: DEFAULT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY med_histories ALTER COLUMN med_history_id SET DEFAULT nextval('med_histories_med_history_id_seq'::regclass);


--
-- Name: medications medication_id; Type: DEFAULT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY medications ALTER COLUMN medication_id SET DEFAULT nextval('medications_medication_id_seq'::regclass);


--
-- Name: patients patient_id; Type: DEFAULT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY patients ALTER COLUMN patient_id SET DEFAULT nextval('patients_patient_id_seq'::regclass);


--
-- Name: tasks task_id; Type: DEFAULT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY tasks ALTER COLUMN task_id SET DEFAULT nextval('tasks_task_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY users ALTER COLUMN user_id SET DEFAULT nextval('users_user_id_seq'::regclass);


--
-- Data for Name: foster_homes; Type: TABLE DATA; Schema: public; Owner: t1k1
--

COPY foster_homes (foster_home_id, user_id, facility_name, primary_first, primary_last, address, city, state, zip, telephone) FROM stdin;
\.


--
-- Name: foster_homes_foster_home_id_seq; Type: SEQUENCE SET; Schema: public; Owner: t1k1
--

SELECT pg_catalog.setval('foster_homes_foster_home_id_seq', 1, false);


--
-- Data for Name: guardians; Type: TABLE DATA; Schema: public; Owner: t1k1
--

COPY guardians (guardian_id, patient_id, first_name, last_name, relationship, address, city, state, zip, telephone) FROM stdin;
\.


--
-- Name: guardians_guardian_id_seq; Type: SEQUENCE SET; Schema: public; Owner: t1k1
--

SELECT pg_catalog.setval('guardians_guardian_id_seq', 1, false);


--
-- Data for Name: insurance; Type: TABLE DATA; Schema: public; Owner: t1k1
--

COPY insurance (insurance_id, patient_id, insurance_provider, insurance_policy, va_policy, medicare_policy, medicaid_policy) FROM stdin;
\.


--
-- Name: insurance_insurance_id_seq; Type: SEQUENCE SET; Schema: public; Owner: t1k1
--

SELECT pg_catalog.setval('insurance_insurance_id_seq', 1, false);


--
-- Data for Name: med_histories; Type: TABLE DATA; Schema: public; Owner: t1k1
--

COPY med_histories (med_history_id, patient_id, type, name, date, medications, current) FROM stdin;
\.


--
-- Name: med_histories_med_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: t1k1
--

SELECT pg_catalog.setval('med_histories_med_history_id_seq', 1, false);


--
-- Data for Name: medications; Type: TABLE DATA; Schema: public; Owner: t1k1
--

COPY medications (medication_id, patient_id, name, dosage, frequency) FROM stdin;
\.


--
-- Name: medications_medication_id_seq; Type: SEQUENCE SET; Schema: public; Owner: t1k1
--

SELECT pg_catalog.setval('medications_medication_id_seq', 1, false);


--
-- Data for Name: patients; Type: TABLE DATA; Schema: public; Owner: t1k1
--

COPY patients (patient_id, foster_home_id, first_name, last_name, admit_date, telephone, ssid, sex, birth_date, birth_place, faith, hobbies, preferred_hospital, primary_care_name, primary_phone) FROM stdin;
\.


--
-- Name: patients_patient_id_seq; Type: SEQUENCE SET; Schema: public; Owner: t1k1
--

SELECT pg_catalog.setval('patients_patient_id_seq', 1, false);


--
-- Data for Name: tasks; Type: TABLE DATA; Schema: public; Owner: t1k1
--

COPY tasks (task_id, foster_id, task_name, type, recurring, frequency, "time", date, completed) FROM stdin;
\.


--
-- Name: tasks_task_id_seq; Type: SEQUENCE SET; Schema: public; Owner: t1k1
--

SELECT pg_catalog.setval('tasks_task_id_seq', 1, false);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: t1k1
--

COPY users (user_id, username, password) FROM stdin;
\.


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: t1k1
--

SELECT pg_catalog.setval('users_user_id_seq', 1, false);


--
-- Name: foster_homes foster_homes_pk; Type: CONSTRAINT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY foster_homes
    ADD CONSTRAINT foster_homes_pk PRIMARY KEY (foster_home_id);


--
-- Name: guardians guardians_pk; Type: CONSTRAINT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY guardians
    ADD CONSTRAINT guardians_pk PRIMARY KEY (guardian_id);


--
-- Name: insurance insurance_pk; Type: CONSTRAINT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY insurance
    ADD CONSTRAINT insurance_pk PRIMARY KEY (insurance_id);


--
-- Name: med_histories med_histories_pk; Type: CONSTRAINT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY med_histories
    ADD CONSTRAINT med_histories_pk PRIMARY KEY (med_history_id);


--
-- Name: medications medications_pk; Type: CONSTRAINT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY medications
    ADD CONSTRAINT medications_pk PRIMARY KEY (medication_id);


--
-- Name: patients patients_pk; Type: CONSTRAINT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY patients
    ADD CONSTRAINT patients_pk PRIMARY KEY (patient_id);


--
-- Name: tasks tasks_pk; Type: CONSTRAINT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY tasks
    ADD CONSTRAINT tasks_pk PRIMARY KEY (task_id);


--
-- Name: users users_pk; Type: CONSTRAINT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pk PRIMARY KEY (user_id);


--
-- PostgreSQL database dump complete
--
