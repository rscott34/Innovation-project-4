--
-- PostgreSQL database dump
--

\restrict 9Ph5xaobSg2k0IuuDktS3VrxTZaSg5qmRRvUpuQVr5vokvzMqLOWsDaYT1HG6s7

-- Dumped from database version 18.1
-- Dumped by pg_dump version 18.1

-- Started on 2026-02-04 16:19:17

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
-- TOC entry 219 (class 1259 OID 16389)
-- Name: Claims; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public."Claims" (
    claim_id character varying(50) NOT NULL,
    product_id character varying(50) NOT NULL,
    claim_type character varying(100),
    claim_text character varying(255),
    confidence_label character varying(50),
    rationale text
);


--
-- TOC entry 222 (class 1259 OID 16414)
-- Name: Input_shares; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public."Input_shares" (
    input_id character varying(50) NOT NULL,
    product_id character varying(50),
    input_name character varying(50),
    country character varying(50),
    percentage integer,
    notes text
);


--
-- TOC entry 220 (class 1259 OID 16398)
-- Name: Products; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public."Products" (
    product_id character varying(50) CONSTRAINT "Product_product_id_not_null" NOT NULL,
    name character varying(100),
    category character varying(50),
    brand character varying(50),
    description text
);


--
-- TOC entry 221 (class 1259 OID 16406)
-- Name: Stages; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public."Stages" (
    stage_id character varying(50) NOT NULL,
    product_id character varying(50),
    stage_type character varying(50),
    location character varying,
    start_date date,
    end_date date,
    description text
);


--
-- TOC entry 223 (class 1259 OID 16422)
-- Name: product; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product (
    product_id character varying(255) NOT NULL,
    brand character varying(255),
    category character varying(255),
    description character varying(255),
    name character varying(255),
    claims bytea,
    components bytea,
    stages bytea
);


--
-- TOC entry 5028 (class 0 OID 16389)
-- Dependencies: 219
-- Data for Name: Claims; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public."Claims" (claim_id, product_id, claim_type, claim_text, confidence_label, rationale) FROM stdin;
C001	P001	Sustainability	Organic Certified	Verified	EU Organic Leaf certificate attached.
C002	P001	Environmental	Carbon Neutral Shipping	Unverified	Pending audit report from logistics provider.
C003	P002	Social Responsibility	Fair Labour	Partially Verified	Factory audit complete; raw material supplier audit pending.
\.


--
-- TOC entry 5031 (class 0 OID 16414)
-- Dependencies: 222
-- Data for Name: Input_shares; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public."Input_shares" (input_id, product_id, input_name, country, percentage, notes) FROM stdin;
IN-01	P001	Spanish Origin Materials	Spain	40	\N
IN-02	P001	Italian Origin Materials	Italy	60	\N
IN-03	P002	Brazilian Origin Materials	Brazil	70	\N
IN-04	P002	Portuguese Origin Materials	Portugal	20	\N
IN-05	P002	French Origin Materials	France	10	\N
\.


--
-- TOC entry 5029 (class 0 OID 16398)
-- Dependencies: 220
-- Data for Name: Products; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public."Products" (product_id, name, category, brand, description) FROM stdin;
P001	Olive Oil Blend	Food	Fake food brand	A mixture of extra virgin olive oils from the Mediterranean.
P002	Leather Tote bag	Luxury	Gucci	Hand crafted leather tote bag finished in France.
\.


--
-- TOC entry 5030 (class 0 OID 16406)
-- Dependencies: 221
-- Data for Name: Stages; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public."Stages" (stage_id, product_id, stage_type, location, start_date, end_date, description) FROM stdin;
S1-001	P001	Raw Materials	Catalonia, Spain	2025-10-15	\N	Harvesting of raw olives.
S1-002	P001	Processing	Tuscany, Italy	2025-10-20	\N	Cold pressing and filtration.
S1-003	P001	Assembly	Tuscany, Italy	2025-10-22	\N	Blending and bottling in glass containers.
S1-004	P001	Transport	Dover, UK	2025-11-01	\N	Freight shipping via road and ferry.
S1-005	P001	Retail	London, UK	2025-11-05	\N	Stocked at WholeFoods Market.
S2-001	P002	Raw Materials	Minas Gerais, Brazil	2025-08-10	\N	Sourcing of raw cowhide.
S2-002	P002	Processing	Porto, Portugal	2025-09-01	\N	Vegetable tanning and dyeing.
S2-003	P002	Assembly	Lyon, France	2025-09-15	\N	Cutting, stitching, and hardware attachment.
S2-004	P002	Transport	Paris, France	2025-09-20	\N	Distribution center sorting.
S2-005	P002	Retail	Milan, Italy	2025-10-01	\N	Flagship store inventory.
\.


--
-- TOC entry 5032 (class 0 OID 16422)
-- Dependencies: 223
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.product (product_id, brand, category, description, name, claims, components, stages) FROM stdin;
\.


--
-- TOC entry 4872 (class 2606 OID 16394)
-- Name: Claims Claims_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."Claims"
    ADD CONSTRAINT "Claims_pkey" PRIMARY KEY (claim_id);


--
-- TOC entry 4878 (class 2606 OID 16421)
-- Name: Input_shares Input_shares_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."Input_shares"
    ADD CONSTRAINT "Input_shares_pkey" PRIMARY KEY (input_id);


--
-- TOC entry 4874 (class 2606 OID 16405)
-- Name: Products Product_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."Products"
    ADD CONSTRAINT "Product_pkey" PRIMARY KEY (product_id);


--
-- TOC entry 4876 (class 2606 OID 16413)
-- Name: Stages Stages_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."Stages"
    ADD CONSTRAINT "Stages_pkey" PRIMARY KEY (stage_id);


--
-- TOC entry 4880 (class 2606 OID 16429)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);


-- Completed on 2026-02-04 16:19:17

--
-- PostgreSQL database dump complete
--

\unrestrict 9Ph5xaobSg2k0IuuDktS3VrxTZaSg5qmRRvUpuQVr5vokvzMqLOWsDaYT1HG6s7

