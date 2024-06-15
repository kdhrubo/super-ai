CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS hstore;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS chase_2023_benefit_store (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    content text,
    metadata json,
    embedding vector(1536)  -- 1536 is the default embedding dimension
    );

CREATE INDEX ON chase_2023_benefit_store USING HNSW (embedding vector_cosine_ops);

CREATE TABLE IF NOT EXISTS t20_wc_2024_store (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    content text,
    metadata json,
    embedding vector(1536)  -- 1536 is the default embedding dimension
    );

CREATE INDEX ON t20_wc_2024_store USING HNSW (embedding vector_cosine_ops);