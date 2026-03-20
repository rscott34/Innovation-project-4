--
-- PostgreSQL database dump
--


-- Dumped from database version 17.6
-- Dumped by pg_dump version 17.6

-- Started on 2026-03-20 16:18:18

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
-- TOC entry 217 (class 1259 OID 24762)
-- Name: app_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.app_users (
    user_id character varying(255) NOT NULL,
    password character varying(255),
    user_name character varying(255),
    user_type character varying(255),
    CONSTRAINT app_users_user_type_check CHECK (((user_type)::text = ANY (ARRAY[('Consumer'::character varying)::text, ('Verifier'::character varying)::text])))
);



--
-- TOC entry 218 (class 1259 OID 24768)
-- Name: changeLog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."changeLog" (
    log_id character varying(255) NOT NULL,
    entity_type character varying(255),
    entity_id character varying(255),
    changed_by character varying(255),
    "timestamp" character varying(255),
    change_summary character varying(255)
);



--
-- TOC entry 219 (class 1259 OID 24773)
-- Name: claims; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.claims (
    claim_id character varying(255) NOT NULL,
    product_id character varying(255) NOT NULL,
    evidence_id character varying(255) NOT NULL,
    claim_type character varying(255),
    claim_text character varying(255),
    confidence_label character varying(255),
    rationale character varying(255)
);



--
-- TOC entry 220 (class 1259 OID 24778)
-- Name: evidence; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.evidence (
    evidence_id character varying(255) NOT NULL,
    type character varying(50),
    issuer character varying(255),
    date character varying(255),
    summary character varying(255),
    file_reference character varying(255)
);



--
-- TOC entry 221 (class 1259 OID 24783)
-- Name: inputShares; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."inputShares" (
    input_id character varying(50) NOT NULL,
    product_id character varying(50),
    input_name character varying(50),
    country character varying(50),
    percentage integer
);



--
-- TOC entry 222 (class 1259 OID 24786)
-- Name: issueReport; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."issueReport" (
    issue_id character varying(50) NOT NULL,
    product_id character varying(50),
    reported_by character varying(50),
    type character varying(50),
    description text,
    status character varying(50),
    resolution_note text
);



--
-- TOC entry 223 (class 1259 OID 24791)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    product_id character varying(255) NOT NULL,
    name character varying(255),
    category character varying(255),
    brand character varying(255),
    description character varying(255)
);



--
-- TOC entry 224 (class 1259 OID 24796)
-- Name: questMission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."questMission" (
    mission_id character varying(255) NOT NULL,
    product_id character varying(255),
    tier character varying(255),
    question text,
    answer text,
    grading_type character varying(255),
    options character varying(255),
    explanation text,
    anchor character varying(255)
);



--
-- TOC entry 225 (class 1259 OID 24801)
-- Name: stages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.stages (
    stage_id character varying(255) NOT NULL,
    product_id character varying(255),
    stage_type character varying(50),
    location character varying(255),
    start_date character varying(255),
    end_date character varying(255) DEFAULT NULL::character varying,
    description character varying(255),
    stage_name character varying(255),
    CONSTRAINT stages_stage_name_check CHECK (((stage_name)::text = ANY (ARRAY[('RawMaterials'::character varying)::text, ('Processing'::character varying)::text, ('Assembly'::character varying)::text, ('Transport'::character varying)::text, ('Retail'::character varying)::text])))
);



--
-- TOC entry 226 (class 1259 OID 24808)
-- Name: verifiers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.verifiers (
    "Verifier_ID" character varying(255) NOT NULL,
    "Username" character varying(255),
    "Password" character varying(255) NOT NULL
);



--
-- TOC entry 4947 (class 0 OID 24762)
-- Dependencies: 217
-- Data for Name: app_users; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4948 (class 0 OID 24768)
-- Dependencies: 218
-- Data for Name: changeLog; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."changeLog" (log_id, entity_type, entity_id, changed_by, "timestamp", change_summary) VALUES ('L_001', 'Claim', 'C001', 'V1', '2025-02-18', 'Raw materials for P001 updated to Valencia, Spain');


--
-- TOC entry 4949 (class 0 OID 24773)
-- Dependencies: 219
-- Data for Name: claims; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C001', 'P000', 'E1', 'Sustainability', 'Organic Certified', 'Verified', 'EU organic certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C002', 'P000', 'E3', 'Environmental', 'Carbon Neutral Certified', 'Verified', 'Carbon Neutral Certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C003', 'P001', 'E1', 'Sustainability', 'Organic Certified', 'Verified', 'EU organic certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C004', 'P001', 'E2', 'Social Responsbility', 'Fair Labour Certified', 'Verified', 'FLA certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C005', 'P002', 'E1', 'Sustainability', 'Organic Certified', 'Verified', 'EU organic certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C006', 'P003', 'E1', 'Sustainability', 'Organic Certified', 'Verified', 'EU organic certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C007', 'P004', 'E2', 'Social Responsbility', 'Fair Labour Certified', 'Verified', 'FLA certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C008', 'P004', 'E2', 'Social Responsbility', 'Fair Labour Certified', 'Verified', 'FLA certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C009', 'P005', 'E2', 'Social Responsbility', 'Fair Labour Certified', 'Verified', 'FLA certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C010', 'P006', 'E3', 'Environmental', 'Carbon Neutral Certified', 'Verified', 'Carbon Neutral Certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C011', 'P006', 'E2', 'Social Responsbility', 'Fair Labour Certified', 'Verified', 'FLA certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C012', 'P007', 'E2', 'Social Responsbility', 'Fair Labour Certified', 'Verified', 'FLA certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C013', 'P008', 'E1', 'Sustainability', 'Organic Certified', 'Verified', 'EU organic certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C014', 'P009', 'E2', 'Social Responsbility', 'Fair Labour Certified', 'Verified', 'FLA certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C015', 'P009', 'E2', 'Social Responsbility', 'Fair Labour Certified', 'Verified', 'FLA certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C016', 'P010', 'E3', 'Environmental', 'Carbon Neutral Certified', 'Verified', 'Carbon Neutral Certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C017', 'P010', 'E2', 'Social Responsbility', 'Fair Labour Certified', 'Verified', 'FLA certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C018', 'P011', 'E2', 'Social Responsbility', 'Fair Labour Certified', 'Verified', 'FLA certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C019', 'P012', 'E3', 'Environmental', 'Carbon Neutral Certified', 'Verified', 'Carbon Neutral Certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C020', 'P012', 'E2', 'Social Responsbility', 'Fair Labour Certified', 'Verified', 'FLA certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C021', 'P013', 'E3', 'Environmental', 'Carbon Neutral Certified', 'Verified', 'Carbon Neutral Certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C022', 'P013', 'E2', 'Social Responsbility', 'Fair Labour Certified', 'Verified', 'FLA certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C023', 'P014', 'E1', 'Sustainability', 'Organic Certified', 'Verified', 'EU organic certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C024', 'P015', 'E1', 'Sustainability', 'Organic Certified', 'Verified', 'EU organic certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C025', 'P016', 'E2', 'Social Responsbility', 'Fair Labour Certified', 'Verified', 'FLA certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C026', 'P016', 'E2', 'Social Responsbility', 'Fair Labour Certified', 'Verified', 'FLA certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C027', 'P017', 'E3', 'Environmental', 'Carbon Neutral Certified', 'Verified', 'Carbon Neutral Certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C028', 'P017', 'E2', 'Social Responsbility', 'Fair Labour Certified', 'Verified', 'FLA certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C029', 'P018', 'E3', 'Environmental', 'Carbon Neutral Certified', 'Verified', 'Carbon Neutral Certificate attached');
INSERT INTO public.claims (claim_id, product_id, evidence_id, claim_type, claim_text, confidence_label, rationale) VALUES ('C030', 'P019', 'E1', 'Sustainability', 'Organic Certified', 'Verified', 'EU organic certificate attached');


--
-- TOC entry 4950 (class 0 OID 24778)
-- Dependencies: 220
-- Data for Name: evidence; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.evidence (evidence_id, type, issuer, date, summary, file_reference) VALUES ('E1', 'Certificate', 'V1', '2025-01-15', 'Certificate for EU organic label', 'assets/eu-organic-logo-600x400_0.png');
INSERT INTO public.evidence (evidence_id, type, issuer, date, summary, file_reference) VALUES ('E2', 'Certificate', 'V1', '2025-01-28', 'Fair Labor Association (FLA) verification', 'assets/FairLaborAccreditationBadge_ONLYFORAPPROVEDUSE-e1726245330900.png');
INSERT INTO public.evidence (evidence_id, type, issuer, date, summary, file_reference) VALUES ('E3', 'Certificate', 'V1', '2025-02-17', 'Certified Carbob Neutral Company', 'assets/Carbon-Neutral-Logo.jpg');
INSERT INTO public.evidence (evidence_id, type, issuer, date, summary, file_reference) VALUES ('E4', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A');


--
-- TOC entry 4951 (class 0 OID 24783)
-- Dependencies: 221
-- Data for Name: inputShares; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-001', 'P000', 'Spanish Origin Materials', 'Spain', '70');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-002', 'P000', 'French Origin Materials', 'France', '30');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-003', 'P001', 'Spanish Origin Materials', 'Spain', '60');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-004', 'P001', 'French Origin Materials', 'France', '40');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-005', 'P002', 'British Origin Materials', 'United Kingdom', '90');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-006', 'P002', 'Chinese Origin Materials', 'China', '10');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-007', 'P003', 'British Origin Materials', 'United Kindom', '90');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-008', 'P003', 'Chinese Origin Materials', 'China', '10');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-009', 'P004', 'British Origin Materials', 'United Kingdom', '80');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-010', 'P004', 'Chinese Origin Materials', 'China', '20');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-011', 'P005', 'Italian Origin Materials', 'Italy', '90');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-012', 'P005', 'French Origin Materials', 'France', '10');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-013', 'P006', 'Swiss Origin Materials', 'Switzerland', '40');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-014', 'P006', 'Botswanan Origin Materials', 'Botswana', '60');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-015', 'P007', 'French Origin Materials', 'France', '50');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-016', 'P007', 'South African Origin Materials', 'South Africa', '50');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-017', 'P008', 'Chinese Origin Materials', 'China', '70');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-018', 'P008', 'American Origin Materials', 'USA', '30');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-019', 'P009', 'French Origin Materials', 'France', '60');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-020', 'P009', 'Spanish Origin Materials', 'Spain', '40');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-021', 'P010', 'Banglaseshi Origin Materials', 'Bangladesh', '70');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-022', 'P010', 'Vietnamese Origin Materials', 'Vietnam', '30');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-023', 'P011', 'Vietnamese Origin Materials', 'Vietnam', '80');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-024', 'P011', 'Bangladeshi Origin Materials', 'Bangladesh', '20');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-025', 'P012', 'Indian Origin Materials', 'India', '70');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-026', 'P012', 'Vietnamese Origin Materials', 'Vietnam', '30');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-027', 'P013', 'Indonesian Origin Materials', 'Indonesia', '30');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-028', 'P013', 'Indian Origin Materials', 'India', '70');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-029', 'P014', 'American Origin Materials', 'USA', '50');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-030', 'P014', 'Vietnamese Origin Materials', 'Vietnam', '50');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-031', 'P015', 'British Origin Materials', 'United Kingdom', '50');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-032', 'P015', 'French Origin Materials', 'France', '50');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-033', 'P016', 'Swedish Origin Materials', 'Sweden', '40');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-034', 'P016', 'Finnish Origin Materials', 'Finland', '60');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-035', 'P017', 'Hungarian Origin Materials', 'Hungary', '60');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-036', 'P017', 'Botswanan Origin Materials', 'Botswana', '40');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-037', 'P018', 'Vietnamese Origin Materials', 'Vietnam', '50');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-038', 'P018', 'Bangladeshi Origin Materials', 'Bangladesh', '50');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-039', 'P019', 'British Origin Materials', 'United Kingdom', '80');
INSERT INTO public."inputShares" (input_id, product_id, input_name, country, percentage) VALUES ('IN-040', 'P019', 'Vietnamese Origin Materials', 'Vietnam', '20');


--
-- TOC entry 4952 (class 0 OID 24786)
-- Dependencies: 222
-- Data for Name: issueReport; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."issueReport" (issue_id, product_id, reported_by, type, description, status, resolution_note) VALUES ('ISS_01', 'P001', 'Anonymous', 'False Claim', 'Stages display raw materials are from Millares, Spain, but they are from Valencia, Spain.', 'Unresolved', 'Noted by admin.');


--
-- TOC entry 4953 (class 0 OID 24791)
-- Dependencies: 223
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P000', 'Olive Oil', 'FOOD', 'Tesco', 'Olive oil from Spain');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P001', 'Orange', 'FOOD', 'Tesco', 'Orange from Spain');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P002', 'Potato', 'FOOD', 'Tesco', 'Sack of potatoes from United Kingdom');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P003', 'Cheddar Cheese', 'FOOD', 'Tesco', 'Block of cheddar cheese from United Kingdom');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P004', 'Bread', 'FOOD', 'Tesco', 'Loaf of bread from United Kingdom');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P005', 'Gucci Handbag', 'LUXURY', 'Gucci', 'Gucci handbag from Italy');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P006', 'Rolex Watch', 'LUXURY', 'Rolex', 'Watch from Switzerland');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P007', 'Cartier Necklace', 'LUXURY', 'Cartier', 'Jewelery from France');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P008', 'iPhone 17', 'LUXURY', 'Apple', 'Smartphone from China');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P009', 'Moët & Chandon Champagne', 'LUXURY', 'Moët & Chandon', 'Champagne from France');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P010', 'T-shirt', 'CLOTHING', 'Next', 'T-shirt from Bangladesh');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P011', 'Hoodie', 'CLOTHING', 'Nicce', 'Hoodie from Vietnam');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P012', 'Jeans', 'CLOTHING', 'Levi', 'Jeans from India');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P013', 'Swim Shorts', 'CLOTHING', 'Quicksilver', 'Swim shorts from Indonesia');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P014', 'Cap', 'CLOTHING', 'Adidas', 'Cap from United States of America');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P015', 'Sofa', 'HOME', 'John Lewis', 'Sofa from the United Kingdom');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P016', 'Desk', 'HOME', 'Ikea', 'Desk from Sweden');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P017', 'Coffee Machine', 'HOME', 'Nescafe', 'Coffee Machine from Hungary');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P018', 'Pillows', 'HOME', 'Dunelm', 'Pillows from Vietnam');
INSERT INTO public.products (product_id, name, category, brand, description) VALUES ('P019', 'Rug', 'HOME', 'Fishpools', 'Rug from the United Kingdom');


--
-- TOC entry 4954 (class 0 OID 24796)
-- Dependencies: 224
-- Data for Name: questMission; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M001', 'P001', 'basic', 'Which country does the Gucci handbag come from?', 'Italy', 'multiple_choice', 'Italy,France,Spain,Germany', 'P005 product information shows that the handbag comes from Italy', '#information');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M002', 'P000', 'basic', 'What type of product is P000?', 'Olive Oil', 'multiple_choice', 'Olive Oil,Orange,Strawberry,Mango', 'P000 is Olive Oil from Spain. View the product details page', '#information');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M003', 'P002', 'basic', 'Which claim is verified for product P002?', 'Organic Certified', 'multiple_choice', 'Fair Labour,Organic,Carbon Neutral,Recycled', 'Claim C005 for P002 is verified with EU Organic certificate E1', '#claims');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M004', 'P060', 'basic', 'Which luxury brand makes product P006?', 'Rolex', 'multiple_choice', 'Rolex,Gucci,Louis Vuitton,Chanel', 'P006 is an Rolex watch from Switzerland', '#information');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M005', 'P001', 'basic', 'Is the ''Organic Certified'' claim for P001 verified?', 'Yes', 'multiple_choice', 'Yes,No', 'Claim C003 for P001 is verified with EU organic certificate E1', '#claims');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M006', 'P003', 'basic', 'Where are the potatoes for P002 harvested?', 'Norfolk UK', 'multiple_choice', 'Devon UK,Suffolk UK,Norfolk UK,Cheshire UK', 'S2-001 shows raw materials from Norfolk, United Kingdom', '#stages');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M007', 'P001', 'intermediate', 'What percentage of materials for P005 come from Italy?', '90', 'numeric', NULL, 'IN-011 shows Italian Origin Materials at 90%', '#origin');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M008', 'P001', 'intermediate', 'What percentage of materials for P013 come from India?', '70', 'numeric', NULL, 'IN-028 shows Indian Origin Materials at 70%', '#origin');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M009', 'P002', 'intermediate', 'Which country contributes less to P002?', 'China', 'multiple_choice', 'Spain,Portugal,China,Bangladesh', 'IN-005 shows UK at 90%, IN-006 shows China at 10%', '#origin');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M010', 'P005', 'intermediate', 'What percentage of materials for P007 come from South Africa?', '50', 'numeric', NULL, 'IN-016 shows South African Origin Materials at 50%', '#origin');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M011', 'P005', 'intermediate', 'What percentage of materials for P018 come from Vietnam?', '50', 'numeric', NULL, 'IN-037 shows Vietnamese Origin Materials at 50%', '#origin');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M012', 'P007', 'intermediate', 'Which country contributes 60% of materials for P009?', 'France', 'multiple_choice', 'Bangladesh,Thailand,India,France', 'IN-019 shows French Origin Materials at 60%', '#origin');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M013', 'P001', 'advanced', 'What is the combined percentage of Spanish and French materials for P001?', '100', 'numeric', NULL, 'Spain (60%) + France (40%) = 100% of materials', '#origin');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M014', 'P001', 'advanced', 'Which claim for P016 is verified?', 'Fair Labour Certified', 'multiple_choice', 'Organic Certified,Carbon Neutral Shipping,Fair Labour,Recycled', 'Claim C025 for P016 is marked as ''Verified''', '#claims');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M015', 'P010', 'advanced', 'Which two countries contribute equally to P015?', 'United Kingdom and France', 'multiple_choice', 'Italy and Belgium,Spain and Portugal,United Kingdom and France,USA and Italy', 'IN-031 and IN-032 show UK (50%) and France (50%) for P015', '#origin');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M016', 'P000', 'advanced', 'Based on stages data, how many days between harvesting and retail for P000?', '10', 'numeric', NULL, 'S0-001 (15/3) to S0-005 (25/3) = 10 days. Check the timeline', '#stages');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M017', 'P002', 'advanced', 'What evidence supports the Fair Labour claim for P001?', 'FLA certificate', 'multiple_choice', 'EU certificate,FLA certificate,Self declaration,None', 'Evidence E2 is a Fair Labor Association (FLA) verification certificate', '#evidence');
INSERT INTO public."questMission" (mission_id, product_id, tier, question, answer, grading_type, options, explanation, anchor) VALUES ('M018', 'P009', 'advanced', 'Which country provides 60% of materials for P009?', 'France', 'multiple_choice', 'Egypt,France,Italy,Spain', 'IN-019 shows French Origin Materials at 60% for P009', '#origin');


--
-- TOC entry 4955 (class 0 OID 24801)
-- Dependencies: 225
-- Data for Name: stages; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S0-001', 'P000', 'Raw Materials', 'Catalonia, Spain', '15/3/2025', NULL, 'Olives harvested from olive tree', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S0-002', 'P000', 'Processing', 'Tarragona, Spain', '17/3/2025', NULL, 'Olives pressed and oil captured', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S0-003', 'P000', 'Assembly', 'Tarragona, Spain', '19/3/2025', NULL, 'Oil packaged into bottles and into crates', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S0-004', 'P000', 'Transport', 'London Gateway Port, UK', '22/3/2025', NULL, 'Flown to United Kingdom', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S0-005', 'P000', 'Retail', 'Tesco Supermarket, London, UK', '25/3/2025', NULL, 'Displayed in supermarket', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S1-001', 'P001', 'Raw Materials', 'Millares, Spain', '2/6/2025', NULL, 'Orange harvested from orange tree', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S1-002', 'P001', 'Processing', 'Valencia, Spain', '4/6/2025', NULL, 'Orange washed and sprayed', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S1-003', 'P001', 'Assembly', 'Valencia, Spain', '6/6/2025', NULL, 'Orange packed netting and into crates', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S1-004', 'P001', 'Transport', 'Dover, UK', '9/6/2025', NULL, 'Orange shipped to United Kingdom', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S1-005', 'P001', 'Retail', 'Tesco Supermarket, London, UK', '12/6/2025', NULL, 'Displayed in supermarket', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S2-001', 'P002', 'Raw Materials', 'Norfolk, UK', '21/1/2025', NULL, 'Potato harvested from potato field', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S2-002', 'P002', 'Processing', 'Norfolk, UK', '23/1/2025', NULL, 'Potato washed and sprayed', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S2-003', 'P002', 'Assembly', 'London, UK', '25/1/2025', NULL, 'Potato packed into plastic bags and into crates', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S2-004', 'P002', 'Transport', 'National Road Network, UK', '28/1/2025', NULL, 'Potatoes transported via UK road networks', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S2-005', 'P002', 'Retail', 'Tesco Supermarket, London, UK', '31/1/2025', NULL, 'Displayed in supermarket', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S3-001', 'P003', 'Raw Materials', 'Bath, UK', '10/9/2025', NULL, 'Milk collected from dairy farm', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S3-002', 'P003', 'Processing', 'Swindon, UK', '12/9/2025', NULL, 'Milk processed into cheddar cheese', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S3-003', 'P003', 'Assembly', 'London, UK', '14/9/2025', NULL, 'Cheese packaged into blocks', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S3-004', 'P003', 'Transport', 'National Road Network, UK', '18/9/2025', NULL, 'Cheese transported via UK road networks', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S3-005', 'P003', 'Retail', 'Tesco Supermarket, London, UK', '21/9/2025', NULL, 'Displayed in supermarket', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S4-001', 'P004', 'Raw Materials', 'Coxheath, UK', '5/4/2025', NULL, 'Wheat harvested from wheat field', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S4-002', 'P004', 'Processing', 'Maidstone, UK', '7/4/2025', NULL, 'Wheat milled into flour', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S4-003', 'P004', 'Assembly', 'Maidstone, UK', '9/4/2025', NULL, 'Bread baked and packaged', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S4-004', 'P004', 'Transport', 'National Road Network, UK', '12/4/2025', NULL, 'Bread transported via UK road networks', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S4-005', 'P004', 'Retail', 'Tesco Supermarket, London, UK', '15/4/2025', NULL, 'Displayed in bakery section', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S5-001', 'P005', 'Raw Materials', 'Tuscany, Italy', '29/7/2025', NULL, 'Leather sourced from cattle', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S5-002', 'P005', 'Processing', 'Florence, Italy', '31/7/2025', NULL, 'Leather cut, treated, and processed', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S5-003', 'P005', 'Assembly', 'Florence, Italy', '2/8/2025', NULL, 'Handbag assembled', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S5-004', 'P005', 'Transport', 'London Heathrow Airport, UK', '5/8/2025', NULL, 'Handbags shipped to United Kingdom', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S5-005', 'P005', 'Retail', 'Gucci Retail Store, London, UK', '8/8/2025', NULL, 'Displayed in luxury store', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S6-001', 'P006', 'Raw Materials', 'Ghanzi, Botswana', '20/2/2025', NULL, 'Precious metals and gemstones mined', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S6-002', 'P006', 'Processing', 'Geneva, Switzerland', '22/2/2025', NULL, 'Gold refined and processed', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S6-003', 'P006', 'Assembly', 'Geneva, Switzerland', '24/2/2025', NULL, 'Watch assembled', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S6-004', 'P006', 'Transport', 'Dover, UK', '26/2/2025', NULL, 'Watches shipped to United Kingdom', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S6-005', 'P006', 'Retail', 'Rolex Retail Store, London, UK', '28/2/2025', NULL, 'Displayed in watch store', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S7-001', 'P007', 'Raw Materials', 'Kimberley, South Africa', '12/5/2025', NULL, 'Precious metals and gemstones mined', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S7-002', 'P007', 'Processing', 'Paris, France', '14/5/2025', NULL, 'Metals refined and gemstones cut', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S7-003', 'P007', 'Assembly', 'Paris France', '16/5/2025', NULL, 'Necklace assembled', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S7-004', 'P007', 'Transport', 'London Gateway Port, UK', '19/5/2025', NULL, 'Jewellery shipped to United Kingdom', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S7-005', 'P007', 'Retail', 'Cartier Retail Store, UK', '22/5/2025', NULL, 'Displayed in jewellery store', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S8-001', 'P008', 'Raw Materials', 'Datong, China', '8/8/2025', NULL, 'Electronic minerals mined', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S8-002', 'P008', 'Processing', 'Beijing, China', '10/8/2025', NULL, 'Electronic components produced', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S8-003', 'P008', 'Assembly', 'Beijing, China', '12/8/2025', NULL, 'Phone assembled', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S8-004', 'P008', 'Transport', 'London Gatwick Airport, UK', '15/8/2025', NULL, 'Phones shipped to United Kingdom', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S8-005', 'P008', 'Retail', 'Apple Store, London, UK', '18/8/2025', NULL, 'Displayed in electronics store', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S9-001', 'P009', 'Raw Materials', 'Champagne, France', '30/9/2025', NULL, 'Grapes harvested from vineyard', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S9-002', 'P009', 'Processing', 'Reims, France', '2/10/2025', NULL, 'Grapes fermented into champagne', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S9-003', 'P009', 'Assembly', 'Reims, France', '4/10/2025', NULL, 'Champagne bottled', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S9-004', 'P009', 'Transport', 'Dover, UK', '7/10/2025', NULL, 'Champagne shipped to United Kingdom', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S9-005', 'P009', 'Retail', 'Harrods, London, UK', '10/10/2025', NULL, 'Displayed in alcohol section', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S10-001', 'P010', 'Raw Materials', 'Dhaka, Bangladesh', '15/3/2025', NULL, 'Cotton harvested from field', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S10-002', 'P010', 'Processing', 'Dhaka, Bangladesh', '17/3/2025', NULL, 'Cotton spun into fabric', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S10-003', 'P010', 'Assembly', 'Dhaka, Bangladesh', '19/3/2025', NULL, 'T-shirt stitched', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S10-004', 'P010', 'Transport', 'London Heathrow Airport, UK', '22/3/2025', NULL, 'Clothing shipped to United Kingdom', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S10-005', 'P010', 'Retail', 'Next Retail Store, London, UK', '25/3/2025', NULL, 'Displayed in clothing store', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S11-001', 'P011', 'Raw Materials', 'Binh Long, Vietnam', '2/6/2025', NULL, 'Cotton harvested from field', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S11-002', 'P011', 'Processing', 'Ho Chi Minh City, Vietnam', '4/6/2025', NULL, 'Fabric cut and processed', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S11-003', 'P011', 'Assembly', 'Ho Chi Minh City, Vietnam', '6/6/2025', NULL, 'Hoodie stitched', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S11-004', 'P011', 'Transport', 'London Heathrow Airport, UK', '9/6/2025', NULL, 'Clothing shipped to United Kingdom', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S11-005', 'P011', 'Retail', 'Nicce Clothing Store, London, UK', '12/6/2025', NULL, 'Displayed in clothing store', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S12-001', 'P012', 'Raw Materials', 'Thane, India', '21/1/2025', NULL, 'Cotton harvested from field', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S12-002', 'P012', 'Processing', 'Mumbai, India', '23/1/2025', NULL, 'Fabric cut and processed', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S12-003', 'P012', 'Assembly', 'Mumbai, India', '25/1/2025', NULL, 'Jeans stitched', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S12-004', 'P012', 'Transport', 'London Gatwick Airport, UK', '28/1/2025', NULL, 'Clothing shipped to United Kingdom', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S12-005', 'P012', 'Retail', 'Levi''s Jeans Store, London, UK', '31/1/2025', NULL, 'Displayed in clothing store', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S13-001', 'P013', 'Raw Materials', 'Jakarta, Indonesia', '10/9/2025', NULL, 'Synthetic fibres produced', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S13-002', 'P013', 'Processing', 'Jakarta, Indonesia', '12/9/2025', NULL, 'Fabric cut and processed', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S13-003', 'P013', 'Assembly', 'Paris, France', '14/9/2025', NULL, 'Swim shorts stitched', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S13-004', 'P013', 'Transport', 'London Gatwick Airport, UK', '18/9/2025', NULL, 'Clothing shipped to United Kingdom', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S13-005', 'P013', 'Retail', 'Quicksilver Retail Store, London, UK', '21/9/2025', NULL, 'Displayed in clothing store', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S14-001', 'P014', 'Raw Materials', 'Dhaka, Bangladesh', '5/4/2025', NULL, 'Cotton harvested from field', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S14-002', 'P014', 'Processing', 'Dhaka, Bangladesh', '7/4/2025', NULL, 'Fabric cut and processed', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S14-003', 'P014', 'Assembly', 'Detroit, Michigan, US', '9/4/2025', NULL, 'Cap stitched', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S14-004', 'P014', 'Transport', 'London Heathrow Airport, UK', '12/4/2025', NULL, 'Clothing shipped to United Kingdom', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S14-005', 'P014', 'Retail', 'Adidas Retail Store, London, UK', '15/4/2025', NULL, 'Displayed in clothing store', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S15-001', 'P015', 'Raw Materials', 'Dhaka, Bangladesh', '29/7/2025', NULL, 'Wood harvested from tree farm', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S15-002', 'P015', 'Processing', 'Manchester, UK', '31/7/2025', NULL, 'Wood cut and processed', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S15-003', 'P015', 'Assembly', 'Manchester, UK', '2/8/2025', NULL, 'Sofa assembled', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S15-004', 'P015', 'Transport', 'National Road Network, UK', '5/8/2025', NULL, 'Furniture transported via UK road networks', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S15-005', 'P015', 'Retail', 'John Lewis Store, London, UK', '8/8/2025', NULL, 'Displayed in furniture store', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S16-001', 'P016', 'Raw Materials', 'Uppsala, Sweden', '20/2/2025', NULL, 'Wood harvested from tree farm', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S16-002', 'P016', 'Processing', 'Stockholm, Sweden', '22/2/2025', NULL, 'Wood cut and processed', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S16-003', 'P016', 'Assembly', 'Stockholm, Sweden', '24/2/2025', NULL, 'Desk assembled', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S16-004', 'P016', 'Transport', 'London Heathrow Airport, UK', '26/2/2025', NULL, 'Furniture shipped to United Kingdom', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S16-005', 'P016', 'Retail', 'Ikea Warehouse Store, London, UK', '28/2/2025', NULL, 'Displayed in furniture store', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S17-001', 'P017', 'Raw Materials', 'Harare, Botswana', '12/5/2025', NULL, 'Metal and plastic materials sourced', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S17-002', 'P017', 'Processing', 'Geneva, Switzerland', '14/5/2025', NULL, 'Machine components produced', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S17-003', 'P017', 'Assembly', 'Geneva, Switzerland', '16/5/2025', NULL, 'Coffee machine assembled', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S17-004', 'P017', 'Transport', 'Dover, UK', '19/5/2025', NULL, 'Coffee machines shipped to United Kingdom', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S17-005', 'P017', 'Retail', 'Nescafe Retail Store, London, UK', '22/5/2025', NULL, 'Displayed in appliance store', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S18-001', 'P018', 'Raw Materials', 'Binh Long, Vietnam', '8/8/2025', NULL, 'Fabric materials sourced', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S18-002', 'P018', 'Processing', 'Ho Chi Minh City', '10/8/2025', NULL, 'Fabric cut and processed', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S18-003', 'P018', 'Assembly', 'Ho Chi Minh City', '12/8/2025', NULL, 'Pillows filled and stitched', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S18-004', 'P018', 'Transport', 'London Heathrow Airport, UK', '15/8/2025', NULL, 'Pillows shipped to United Kingdom', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S18-005', 'P018', 'Retail', 'Dunelm Retail Store, London, UK', '18/8/2025', NULL, 'Displayed in home goods store', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S19-001', 'P019', 'Raw Materials', 'Dhaka, Bangladesh', '30/9/2025', NULL, 'Wool sourced from sheep', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S19-002', 'P019', 'Processing', 'Dhaka, Bangladesh', '2/10/2025', NULL, 'Wool cut and processed', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S19-003', 'P019', 'Assembly', 'Chelmsford, UK', '4/10/2025', NULL, 'Rug woven', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S19-004', 'P019', 'Transport', 'National Road Network, UK', '7/10/2025', NULL, 'Rugs transported via UK road networks', NULL);
INSERT INTO public.stages (stage_id, product_id, stage_type, location, start_date, end_date, description, stage_name) VALUES ('S19-005', 'P019', 'Retail', 'Fishpools Retail Store, London, UK', '10/10/2025', NULL, 'Displayed in furniture store', NULL);


--
-- TOC entry 4956 (class 0 OID 24808)
-- Dependencies: 226
-- Data for Name: verifiers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.verifiers ("Verifier_ID", "Username", "Password") VALUES ('Verifier_1', 'demo', 'demo1');


--
-- TOC entry 4783 (class 2606 OID 24814)
-- Name: changeLog ChangeLog_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."changeLog"
    ADD CONSTRAINT "ChangeLog_pkey" PRIMARY KEY (log_id);


--
-- TOC entry 4785 (class 2606 OID 24816)
-- Name: claims Claims_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.claims
    ADD CONSTRAINT "Claims_pkey" PRIMARY KEY (claim_id);


--
-- TOC entry 4789 (class 2606 OID 24818)
-- Name: inputShares Input_shares_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."inputShares"
    ADD CONSTRAINT "Input_shares_pkey" PRIMARY KEY (input_id);


--
-- TOC entry 4791 (class 2606 OID 24820)
-- Name: issueReport IssueReport_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."issueReport"
    ADD CONSTRAINT "IssueReport_pkey" PRIMARY KEY (issue_id);


--
-- TOC entry 4793 (class 2606 OID 24822)
-- Name: products Product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT "Product_pkey" PRIMARY KEY (product_id);


--
-- TOC entry 4797 (class 2606 OID 24824)
-- Name: stages Stages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stages
    ADD CONSTRAINT "Stages_pkey" PRIMARY KEY (stage_id);


--
-- TOC entry 4799 (class 2606 OID 24826)
-- Name: verifiers Verfiers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.verifiers
    ADD CONSTRAINT "Verfiers_pkey" PRIMARY KEY ("Verifier_ID", "Password");


--
-- TOC entry 4781 (class 2606 OID 24828)
-- Name: app_users app_users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_users
    ADD CONSTRAINT app_users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 4787 (class 2606 OID 24830)
-- Name: evidence evidence.csv_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evidence
    ADD CONSTRAINT "evidence.csv_pkey" PRIMARY KEY (evidence_id);


--
-- TOC entry 4795 (class 2606 OID 24832)
-- Name: questMission questMission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."questMission"
    ADD CONSTRAINT "questMission_pkey" PRIMARY KEY (mission_id);


--
-- TOC entry 4801 (class 2606 OID 24833)
-- Name: stages fkap0f7vq236cwembfeybwg3kg2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stages
    ADD CONSTRAINT fkap0f7vq236cwembfeybwg3kg2 FOREIGN KEY (product_id) REFERENCES public.products(product_id);


--
-- TOC entry 4800 (class 2606 OID 24838)
-- Name: claims fkk2vsdijvrtxf887xp7k0x8nxo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.claims
    ADD CONSTRAINT fkk2vsdijvrtxf887xp7k0x8nxo FOREIGN KEY (product_id) REFERENCES public.products(product_id);


-- Completed on 2026-03-20 16:18:18

--
-- PostgreSQL database dump complete
--

\unrestrict bTeRgYyI29DJez9eN1E63kntawNMVHuZgaEBaNbcs2hxIRjCW3bgatMTITpXnee

