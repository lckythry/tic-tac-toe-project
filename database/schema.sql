--
-- PostgreSQL database dump
--

\restrict IxVlXfO2neoBnLxnam7WJ5Bm6b6dwnSezE34JwHz3DnsRRbzfviHXdu6UFL3e9p

-- Dumped from database version 18.3
-- Dumped by pg_dump version 18.3

-- Started on 2026-06-25 08:48:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 16789)
-- Name: players; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.players (
    id integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(100) NOT NULL,
    wins integer DEFAULT 0,
    losses integer DEFAULT 0,
    draws integer DEFAULT 0,
    score integer DEFAULT 0
);


ALTER TABLE public.players OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16788)
-- Name: players_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.players_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.players_id_seq OWNER TO postgres;

--
-- TOC entry 5018 (class 0 OID 0)
-- Dependencies: 219
-- Name: players_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.players_id_seq OWNED BY public.players.id;


--
-- TOC entry 4855 (class 2604 OID 16792)
-- Name: players id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.players ALTER COLUMN id SET DEFAULT nextval('public.players_id_seq'::regclass);


--
-- TOC entry 5012 (class 0 OID 16789)
-- Dependencies: 220
-- Data for Name: players; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.players (id, username, password, wins, losses, draws, score) FROM stdin;
1	student1	12345	0	0	0	0
2	student2	12345	0	0	0	0
3	student3	12345	0	0	0	0
4	student4	12345	0	0	0	0
5	student5	12345	0	0	0	0
\.


--
-- TOC entry 5019 (class 0 OID 0)
-- Dependencies: 219
-- Name: players_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.players_id_seq', 5, true);


--
-- TOC entry 4861 (class 2606 OID 16801)
-- Name: players players_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.players
    ADD CONSTRAINT players_pkey PRIMARY KEY (id);


--
-- TOC entry 4863 (class 2606 OID 16803)
-- Name: players players_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.players
    ADD CONSTRAINT players_username_key UNIQUE (username);


-- Completed on 2026-06-25 08:48:47

--
-- PostgreSQL database dump complete
--

\unrestrict IxVlXfO2neoBnLxnam7WJ5Bm6b6dwnSezE34JwHz3DnsRRbzfviHXdu6UFL3e9p

