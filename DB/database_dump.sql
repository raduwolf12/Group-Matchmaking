--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10
-- Dumped by pg_dump version 10.10

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
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


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: configuration; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.configuration (
    id bigint NOT NULL,
    group_size integer,
    pair_size integer
);


ALTER TABLE public.configuration OWNER TO postgres;

--
-- Name: configuration_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.configuration_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.configuration_id_seq OWNER TO postgres;

--
-- Name: configuration_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.configuration_id_seq OWNED BY public.configuration.id;


--
-- Name: final_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.final_group (
    id bigint NOT NULL,
    pair_slots integer,
    solo_slots integer,
    project_id bigint
);


ALTER TABLE public.final_group OWNER TO postgres;

--
-- Name: final_group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.final_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.final_group_id_seq OWNER TO postgres;

--
-- Name: final_group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.final_group_id_seq OWNED BY public.final_group.id;


--
-- Name: final_group_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.final_group_user (
    final_group_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.final_group_user OWNER TO postgres;

--
-- Name: form; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.form (
    id bigint NOT NULL,
    duration_in_days integer NOT NULL,
    is_open boolean NOT NULL,
    opening_time timestamp(6) without time zone
);


ALTER TABLE public.form OWNER TO postgres;

--
-- Name: form_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.form_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.form_id_seq OWNER TO postgres;

--
-- Name: form_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.form_id_seq OWNED BY public.form.id;


--
-- Name: pair_preferences; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pair_preferences (
    group_preference_id bigint NOT NULL,
    group_creator_id bigint
);


ALTER TABLE public.pair_preferences OWNER TO postgres;

--
-- Name: pair_preferences_group_preference_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pair_preferences_group_preference_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pair_preferences_group_preference_id_seq OWNER TO postgres;

--
-- Name: pair_preferences_group_preference_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pair_preferences_group_preference_id_seq OWNED BY public.pair_preferences.group_preference_id;


--
-- Name: pair_preferences_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pair_preferences_users (
    user_id bigint NOT NULL,
    pair_preferences_id bigint NOT NULL
);


ALTER TABLE public.pair_preferences_users OWNER TO postgres;

--
-- Name: project_preferences; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.project_preferences (
    project_preference_id bigint NOT NULL,
    rank integer,
    project_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.project_preferences OWNER TO postgres;

--
-- Name: project_preferences_project_preference_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.project_preferences_project_preference_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.project_preferences_project_preference_id_seq OWNER TO postgres;

--
-- Name: project_preferences_project_preference_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.project_preferences_project_preference_id_seq OWNED BY public.project_preferences.project_preference_id;


--
-- Name: projects; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.projects (
    project_id bigint NOT NULL,
    description character varying(255),
    size bigint,
    title character varying(255),
    visibility boolean,
    user_id bigint NOT NULL
);


ALTER TABLE public.projects OWNER TO postgres;

--
-- Name: projects_project_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.projects_project_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.projects_project_id_seq OWNER TO postgres;

--
-- Name: projects_project_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.projects_project_id_seq OWNED BY public.projects.project_id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id bigint NOT NULL,
    canvas_user_id bigint,
    email character varying(255),
    group_id bigint,
    is_user_paired boolean,
    name character varying(255),
    password character varying(255),
    password_temporary character varying(255),
    role character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_user_id_seq OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- Name: configuration id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.configuration ALTER COLUMN id SET DEFAULT nextval('public.configuration_id_seq'::regclass);


--
-- Name: final_group id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.final_group ALTER COLUMN id SET DEFAULT nextval('public.final_group_id_seq'::regclass);


--
-- Name: form id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.form ALTER COLUMN id SET DEFAULT nextval('public.form_id_seq'::regclass);


--
-- Name: pair_preferences group_preference_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pair_preferences ALTER COLUMN group_preference_id SET DEFAULT nextval('public.pair_preferences_group_preference_id_seq'::regclass);


--
-- Name: project_preferences project_preference_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_preferences ALTER COLUMN project_preference_id SET DEFAULT nextval('public.project_preferences_project_preference_id_seq'::regclass);


--
-- Name: projects project_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projects ALTER COLUMN project_id SET DEFAULT nextval('public.projects_project_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- Data for Name: configuration; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.configuration (id, group_size, pair_size) FROM stdin;
1	7	2
\.


--
-- Data for Name: final_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.final_group (id, pair_slots, solo_slots, project_id) FROM stdin;
\.


--
-- Data for Name: final_group_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.final_group_user (final_group_id, user_id) FROM stdin;
\.


--
-- Data for Name: form; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.form (id, duration_in_days, is_open, opening_time) FROM stdin;
1	10	t	2023-06-10 20:02:33.649378
\.


--
-- Data for Name: pair_preferences; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pair_preferences (group_preference_id, group_creator_id) FROM stdin;
1	1
2	2
3	3
4	4
5	5
6	12
7	13
8	14
9	15
10	16
\.


--
-- Data for Name: pair_preferences_users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pair_preferences_users (user_id, pair_preferences_id) FROM stdin;
1	1
6	1
2	2
7	2
3	3
8	3
4	4
9	4
5	5
10	5
12	6
13	7
14	8
15	9
16	10
\.


--
-- Data for Name: project_preferences; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.project_preferences (project_preference_id, rank, project_id, user_id) FROM stdin;
1	1	4	1
2	2	1	1
3	3	2	1
4	4	3	1
5	5	5	1
6	1	5	2
7	2	3	2
8	3	4	2
9	4	2	2
10	5	1	2
11	1	1	3
12	2	3	3
13	3	2	3
14	4	5	3
15	5	4	3
16	1	2	4
17	2	5	4
18	3	1	4
19	4	3	4
20	5	4	4
21	1	4	5
22	2	3	5
23	3	1	5
24	4	2	5
25	5	5	5
26	1	5	12
27	2	4	12
28	3	1	12
29	4	2	12
30	5	3	12
31	1	5	13
32	2	1	13
33	3	3	13
34	4	2	13
35	5	4	13
36	1	1	14
37	2	4	14
38	3	3	14
39	4	2	14
40	5	5	14
41	1	4	15
42	2	5	15
43	3	3	15
44	4	1	15
45	5	2	15
46	1	5	16
47	2	4	16
48	3	2	16
49	4	1	16
50	5	3	16
51	1	6	17
\.


--
-- Data for Name: projects; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projects (project_id, description, size, title, visibility, user_id) FROM stdin;
1	Test project 1 description	8	Project 1	t	1
2	Test project 2 description	8	Project 2	t	2
3	Test project 3 description	8	Project 3	t	3
4	Test project 4 description	8	Project 4	t	4
5	Test project 5 description	8	Project 5	t	5
6	Test proj desc	8	Test project	t	19
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, canvas_user_id, email, group_id, is_user_paired, name, password, password_temporary, role) FROM stdin;
1	\N	user1@example.com	\N	f	User 1	$2a$10$d48TJaGvzsVc2M/s71F5IeufRYAoDM6sL9ihRAUsWqZYMJhIMx6dC	\N	STUDENT
2	\N	user2@example.com	\N	f	User 2	$2a$10$NFi6iyoJf4HiiF6mdkICVuBIIV4U/0R49ugWmSkAEsK0DWeL7Xqhu	\N	STUDENT
3	\N	user3@example.com	\N	f	User 3	$2a$10$jvMkEX//1y2vopncFF3Ld.q.bqtfzGdYZwIOdVo8DKMaOlY7X2dBS	\N	STUDENT
4	\N	user4@example.com	\N	f	User 4	$2a$10$7AR7gWDWCZP0.2aUszic8u.zELKjLUmwe4ID0m9j3xWUh/EHxfeiG	\N	STUDENT
5	\N	user5@example.com	\N	f	User 5	$2a$10$AVREVYD.7z8/sgYGmDthtej8z5s6wURExENOWWM5sDgVD3RPqULRO	\N	STUDENT
6	\N	user6@example.com	\N	f	User 6	$2a$10$.V4bfKPu7kU7xYSmSdsFleQmZaK6pnGwWdVUdMZNEetc1NmNQ12S6	\N	STUDENT
7	\N	user7@example.com	\N	f	User 7	$2a$10$BNncpkazYEVJdU12sDmAzOceTby35qo5pmZzHjzvbGJmNHvu0e/GK	\N	STUDENT
8	\N	user8@example.com	\N	f	User 8	$2a$10$FKN4AUfD5.d7Fs07QuxQ8OP3yDWl5RQtqbo8/T/Njmo3rBqRedGiO	\N	STUDENT
9	\N	user9@example.com	\N	f	User 9	$2a$10$YkEM/M8EluJKMtabb499keWoZi/eIs0Qh7cqYHd7PVWmagCMCcy96	\N	STUDENT
10	\N	user10@example.com	\N	f	User 10	$2a$10$fCJ4w2iZWxqEH67tjYwF7uHv0ZYr06P4CV44o2/Ls622Ij46KMJoe	\N	STUDENT
11	\N	user11@example.com	\N	f	User 11	$2a$10$3eocf7C7nvD/aKbxFfD0V.e/Sa8N8ejSzR2UoyceEcEcm9nYJEfXa	\N	STUDENT
12	\N	user12@example.com	\N	f	User 12	$2a$10$X2lm.eH70MuCKBjdzlsP5uMPaK40ObtY6tXN291I.wmysjk1Z.4ru	\N	STUDENT
13	\N	user13@example.com	\N	f	User 13	$2a$10$VnoILVbCEpNN1kRTVX9tiOIEzRCB/SyvbSONxyiFNDQ8GoFIMaHOS	\N	STUDENT
14	\N	user14@example.com	\N	f	User 14	$2a$10$eCCuVVTjnNYL2MyYu9Mvf.aWpsxBXYGQtQkjqAtv5lTon3Ch7/piq	\N	STUDENT
15	\N	user15@example.com	\N	f	User 15	$2a$10$2y8ZBSJIV5sjTjN1.AHmdOrbCnQ.oHM7wTfKfXSBDoAogHwwDO6E2	\N	STUDENT
16	\N	user16@example.com	\N	f	User 16	$2a$10$lDgHWponzNqGwYxJH4vwSOb6Tnt5b62uP0adDaScAiMI/2VkWxA8W	\N	STUDENT
17	1	whx862@alumni.ku.dk	1	f	whx	$2a$10$X3C2e9i/v6Qz8kUuN72OUupkvOVJaQN3aH3WyCwJ7Q.1UJyfWCe36	TESTUSERPASS	STUDENT
18	2	qwe@alumni.ku.dk	2	f	qwe	$2a$10$8Cg5aG8Fwq6wiTsce72qkuGx.lluv78liqQf1Qe86VeJmfEhtpQ3a	TESTUSERPASS	TEACHER
19	3	abc@alumni.ku.dk	3	f	abc	$2a$10$AzsdmzgbIOnZ7kC9qp00ROiGBBH/b/EfiFdDszu4VFmMx55mqYhBm	TESTUSERPASS	PROFESSOR
\.


--
-- Name: configuration_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.configuration_id_seq', 1, true);


--
-- Name: final_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.final_group_id_seq', 1, false);


--
-- Name: form_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.form_id_seq', 1, true);


--
-- Name: pair_preferences_group_preference_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pair_preferences_group_preference_id_seq', 10, true);


--
-- Name: project_preferences_project_preference_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.project_preferences_project_preference_id_seq', 51, true);


--
-- Name: projects_project_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.projects_project_id_seq', 6, true);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 19, true);


--
-- Name: configuration configuration_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.configuration
    ADD CONSTRAINT configuration_pkey PRIMARY KEY (id);


--
-- Name: final_group final_group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.final_group
    ADD CONSTRAINT final_group_pkey PRIMARY KEY (id);


--
-- Name: final_group_user final_group_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.final_group_user
    ADD CONSTRAINT final_group_user_pkey PRIMARY KEY (final_group_id, user_id);


--
-- Name: form form_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.form
    ADD CONSTRAINT form_pkey PRIMARY KEY (id);


--
-- Name: pair_preferences pair_preferences_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pair_preferences
    ADD CONSTRAINT pair_preferences_pkey PRIMARY KEY (group_preference_id);


--
-- Name: project_preferences project_preferences_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_preferences
    ADD CONSTRAINT project_preferences_pkey PRIMARY KEY (project_preference_id);


--
-- Name: projects projects_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projects
    ADD CONSTRAINT projects_pkey PRIMARY KEY (project_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: pair_preferences_users fk2x8vedjqikjf5x60x8a9xns88; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pair_preferences_users
    ADD CONSTRAINT fk2x8vedjqikjf5x60x8a9xns88 FOREIGN KEY (pair_preferences_id) REFERENCES public.pair_preferences(group_preference_id);


--
-- Name: final_group fk8xqvmqu2tts4o800lnb1ictby; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.final_group
    ADD CONSTRAINT fk8xqvmqu2tts4o800lnb1ictby FOREIGN KEY (project_id) REFERENCES public.projects(project_id);


--
-- Name: pair_preferences fk9bkmae8og1dwmwi8jqv9x9jrg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pair_preferences
    ADD CONSTRAINT fk9bkmae8og1dwmwi8jqv9x9jrg FOREIGN KEY (group_creator_id) REFERENCES public.users(user_id);


--
-- Name: project_preferences fkawid0m22glifa1u2amxm8w3hy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_preferences
    ADD CONSTRAINT fkawid0m22glifa1u2amxm8w3hy FOREIGN KEY (project_id) REFERENCES public.projects(project_id);


--
-- Name: project_preferences fkb8cv8bna2rxu9wxdtiu8ieyit; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_preferences
    ADD CONSTRAINT fkb8cv8bna2rxu9wxdtiu8ieyit FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: final_group_user fkd9wu6jr7qgs5am84h9samxgfv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.final_group_user
    ADD CONSTRAINT fkd9wu6jr7qgs5am84h9samxgfv FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: projects fkhswfwa3ga88vxv1pmboss6jhm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projects
    ADD CONSTRAINT fkhswfwa3ga88vxv1pmboss6jhm FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: final_group_user fkjf06jtfeyykwme9ayjn1hw6gd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.final_group_user
    ADD CONSTRAINT fkjf06jtfeyykwme9ayjn1hw6gd FOREIGN KEY (final_group_id) REFERENCES public.final_group(id);


--
-- Name: pair_preferences_users fkl59n9gciluxaae5wex2h22a3p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pair_preferences_users
    ADD CONSTRAINT fkl59n9gciluxaae5wex2h22a3p FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- PostgreSQL database dump complete
--

